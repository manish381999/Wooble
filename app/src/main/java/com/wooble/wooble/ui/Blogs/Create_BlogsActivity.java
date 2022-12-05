package com.wooble.wooble.ui.Blogs;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.cocosw.bottomsheet.BottomSheet;
import com.gowtham.library.utils.CompressOption;
import com.gowtham.library.utils.LogMessage;
import com.gowtham.library.utils.TrimType;
import com.gowtham.library.utils.TrimVideo;
import com.rakeshsutar.blogeditor.BlogEditor;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityCreateBlogsBinding;
import com.wooble.wooble.ui.Gallery.Gallery_Image_CropperActivity;
import com.wooble.wooble.ui.Gallery.ImageUploaderActivity;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.defaults.colorpicker.ColorPickerPopup;


public class Create_BlogsActivity extends AppCompatActivity {
    ActivityCreateBlogsBinding binding;
//    Bitmap bitmap;
//    private String profileEmail;
//    ArrayList<String> arr = new ArrayList<>();
//    String image = null;


    private Bitmap bitmap;
    final int REQ = 12;
    ActivityResultLauncher<String> pickImage;
    ActivityResultLauncher<Intent> mStartForResult;
    ActivityResultLauncher<Intent> startForResult;
    private BlogEditor blogEditor;
    //private TextView html;
    String html, result, image, video,audio;

    public final String APP_TAG = "MyCustomApp";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";
    File photoFile;
    private int trimType;
    String fileLocation = "https://app.wooble.org/img/blog_assets/";
    //String fileLocation = "http://172.168.0.182/wooble_api/blog_assets/";


    ActivityResultLauncher<Intent> videoTrimResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK &&
                        result.getData() != null) {

                    String path = TrimVideo.getTrimmedVideoPath(result.getData());
                    Uri uri = Uri.fromFile(new File(path));
                    //Uri uri = Uri.parse("contents:/"+TrimVideo.getTrimmedVideoPath(result.getData()));
                    //Uri uri = Uri.parse(new File("/storage/emulated/0/Android/data/com.wooble.wooble/files/TrimmedVideo/trimmed_video_2023_0_23_12_27_18.mp4").toString());
                    //Uri uri = Uri.parse(new File(uri.getPath()));
                    System.out.println("Hii-" + uri);
                    //System.out.println("Hii---"+getRealPathFromURI(getApplicationContext(),uri));
                    video = fileUriToBase64(uri, getContentResolver());
                    System.out.println("Hii" + video);
                    uploadBlogVideo();

                } else
                    LogMessage.v("videoTrimResultLauncher data is null");
            });


    ActivityResultLauncher<Intent> takeOrSelectVideoResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK &&
                        result.getData() != null) {
                    Intent data = result.getData();
                    //check video duration if needed
           /*        if (TrimmerUtils.getDuration(this,data.getData())<=30){
                    Toast.makeText(this,"Video should be larger than 30 sec",Toast.LENGTH_SHORT).show();
                    return;
                }*/
                    if (data.getData() != null) {
                        LogMessage.v("Video path:: " + data.getData());
                        openTrimActivity(String.valueOf(data.getData()));
                    } else {
                        Toast.makeText(this, "video uri is null", Toast.LENGTH_SHORT).show();
                    }
                } else
                    LogMessage.v("takeVideoResultLauncher data is null");
            });

    ActivityResultLauncher<Intent> pickAudio = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK &&
                        result.getData() != null) {
                    Intent data = result.getData();
                    //check video duration if needed
           /*        if (TrimmerUtils.getDuration(this,data.getData())<=30){
                    Toast.makeText(this,"Video should be larger than 30 sec",Toast.LENGTH_SHORT).show();
                    return;
                }*/
                    if (data.getData() != null) {
                        LogMessage.v("Audio path:: " + data.getData());
                        //openTrimActivity(String.valueOf(data.getData()));
                        audio = fileUriToBase64(data.getData(),getContentResolver());
                        uploadBlogAudio();
                    } else {
                        Toast.makeText(this, "Audio uri is null", Toast.LENGTH_SHORT).show();
                    }
                } else
                    LogMessage.v("takeVideoResultLauncher data is null");
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBlogsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_);

        blogEditor = binding.blogEditor;
        blogEditor.setPlaceholder("Type Something...");

        final Intent[] intent = {getIntent()};
        result = intent[0].getStringExtra("return_id");

        binding.createTitle.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.createTitle, InputMethodManager.SHOW_IMPLICIT);


        binding.btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (html == null) {
                    DeleteBlogRow();
                } else {
                    PublishBlog();
                    System.out.println("HTML " + html);
                }
            }
        });


        binding.blogEditor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.toolBox.setVisibility(View.VISIBLE);
                } else {
                    binding.toolBox.setVisibility(View.GONE);
                }
            }
        });

        blogEditor.setOnTextChangeListener(new BlogEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {

                html = "<p>" + text + "</p>";
            }
        });

        binding.actionNoFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.removeFormat();
            }
        });

        binding.actionUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.undo();
            }
        });

        binding.actionRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.redo();
            }
        });

        binding.actionBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setBold();
            }
        });

        binding.actionItalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setItalic();
            }
        });

        binding.actionSubscript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setSubscript();
            }
        });

        binding.actionSuperscript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setSuperscript();
            }
        });

        binding.actionStrikethrough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setStrikeThrough();
            }
        });

        binding.actionUnderline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setUnderline();
            }
        });

        binding.actionHeading1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setHeading(1);
            }
        });

        binding.actionHeading2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setHeading(2);
            }
        });

        binding.actionHeading3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setHeading(3);
            }
        });

        binding.actionHeading4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setHeading(4);
            }
        });

        binding.actionHeading5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setHeading(5);
            }
        });

        binding.actionHeading6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setHeading(6);
            }
        });


        binding.actionTxtColor.setOnClickListener(new View.OnClickListener() {
            //private boolean isChanged;

            //int color = Integer.valueOf("A43C25", 16);

            @Override
            public void onClick(View v) {
                //blogEditor.setTextColor(isChanged ? Color.BLACK : mDefaultColor[0]/*Color.RED*/);
                new ColorPickerPopup.Builder(Create_BlogsActivity.this).initialColor(Color.BLACK)
                        .enableBrightness(true)
                        .enableAlpha(true)
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void
                            onColorPicked(int color) {
                                //TextColorPick = color;
                                blogEditor.setTextColor(color);
                            }
                        });
                //blogEditor.setTextColor(color);
                //isChanged = !isChanged;
            }
        });

        binding.actionBgColor.setOnClickListener(new View.OnClickListener() {
            //private boolean isChanged;
            @Override
            public void onClick(View v) {
                new ColorPickerPopup.Builder(Create_BlogsActivity.this).initialColor(Color.WHITE)
                        .enableBrightness(true)
                        .enableAlpha(true)
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void
                            onColorPicked(int color) {
                                //colorPick = color;
                                blogEditor.setTextBackgroundColor(color);
                            }
                        });
                //blogEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                //isChanged = !isChanged;
            }
        });

        binding.actionIndent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setIndent();
            }
        });

        binding.actionOutdent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setOutdent();
            }
        });


        binding.actionAlignLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setAlignLeft();
            }
        });

        binding.actionAlignCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setAlignCenter();
            }
        });

        binding.actionAlignRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setAlignRight();
            }
        });

        binding.actionInsertBullets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setBullets();
            }
        });

        binding.actionInsertNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.setNumbers();
            }
        });

        binding.actionInsertAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //blogEditor.insertAudio("https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_5MG.mp3");
            }
        });
        binding.actionInsertAudioByLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AudioInsertByLinkAlertDialog();
                showAudioOptions();

            }
        });


        binding.actionInsertLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkAlertDialog();
            }
        });
        binding.actionInsertCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blogEditor.insertTodo();
            }
        });


        pickImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Intent intent = new Intent(Create_BlogsActivity.this, Gallery_Image_CropperActivity.class);
                intent.putExtra("DATA", result.toString());
                //System.out.println("Hii"+ result.toString());
                startActivityForResult(intent, REQ);
            }
        });

        binding.actionCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(Create_BlogsActivity.this, new String[]{Manifest.permission.CAMERA}, 100);
                    }
                    showImageOptions();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    //Intent intent = result.getData();
                    // Handle the Intent
                    System.out.println("Hii" + result);
                    Bundle extras = result.getData().getExtras();
                    Bitmap photo = (Bitmap) extras.get("data");
                    System.out.println("Hii" + photo);
                    String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), photo, "Title", null);
                    Uri uri = Uri.parse(path);
                    //System.out.println("Hii"+Uri.parse(result.getData().toString()));
                    Intent intent = new Intent(Create_BlogsActivity.this, Gallery_Image_CropperActivity.class);
                    intent.putExtra("DATA", uri.toString());
                    startActivityForResult(intent, REQ);

                }
            }
        });


        binding.actionCameraVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //startForResult.launch(new Intent(MediaStore.ACTION_VIDEO_CAPTURE));
                    onDefaultTrimClicked();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }



    private void onDefaultTrimClicked() {
        trimType = 0;
        if (checkCamStoragePer())
            showVideoOptions();

    }

    private boolean checkCamStoragePer() {
        return checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }

    private boolean checkPermission(String... permissions) {
        boolean allPermitted = false;
        for (String permission : permissions) {
            allPermitted = (ContextCompat.checkSelfPermission(this, permission)
                    == PackageManager.PERMISSION_GRANTED);
            if (!allPermitted)
                break;
        }
        if (allPermitted)
            return true;
        ActivityCompat.requestPermissions(this, permissions,
                220);
        return false;
    }

    public void showVideoOptions() {
        try {
            BottomSheet.Builder builder = getBottomSheet();
            builder.sheet(R.menu.menu_video);
            builder.listener(item -> {
                if (R.id.action_open_camera == item.getItemId())
                    captureVideo();
                else if (R.id.action_open_Gallery == item.getItemId())
                    openVideo();
                else if (R.id.action_by_link == item.getItemId())
                    VideoInsertByLinkAlertDialog();
                else if (R.id.action_from_youtube == item.getItemId())
                    YoutubeAlertDialog();
                return false;
            });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showImageOptions() {
        try {
            BottomSheet.Builder builder = getBottomSheet();
            builder.sheet(R.menu.menu_image);
            builder.listener(item -> {
                if (R.id.action_open_camera == item.getItemId())
                    captureImage();
                else if (R.id.action_open_Gallery == item.getItemId())
                    openImage();
                else if (R.id.action_by_link == item.getItemId())
                    ImageInsertByLinkAlertDialog();
                return false;
            });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showAudioOptions() {
        try {
            BottomSheet.Builder builder = getBottomSheet();
            builder.sheet(R.menu.menu_audio);
            builder.listener(item -> {

                if (R.id.action_open_Gallery == item.getItemId())
                    openAudio();
                else if (R.id.action_by_link == item.getItemId())
                    AudioInsertByLinkAlertDialog();
                return false;
            });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openImage() {

        openGallery();
    }

    private void captureImage() {
        try {
            mStartForResult.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BottomSheet.Builder getBottomSheet() {
        return new BottomSheet.Builder(this)/*.title("Select from below options")*/;
    }

    public void captureVideo() {
        try {
            Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
            //intent.putExtra(android.provider.MediaStore.EXTRA_VIDEO_QUALITY, 5);

            //intent.putExtra("android.intent.extra.durationLimit", 30);
            takeOrSelectVideoResultLauncher.launch(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openVideo() {
        try {
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            takeOrSelectVideoResultLauncher.launch(Intent.createChooser(intent, "Select Video"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openAudio() {
        try {
            Intent intent = new Intent();
            intent.setType("audio/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            pickAudio.launch(Intent.createChooser(intent, "Select Audio"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (isPermissionOk(grantResults))
            showVideoOptions();
    }


    private void openTrimActivity(String data) {
        if (trimType == 0) {
            TrimVideo.activity(data)
                    //.setCompressOption(new CompressOption()) //pass empty constructor for default compress option
                    .start(this, videoTrimResultLauncher);
        }
    }

    private boolean isPermissionOk(int... results) {
        boolean isAllGranted = true;
        for (int result : results) {
            if (PackageManager.PERMISSION_GRANTED != result) {
                isAllGranted = false;
                break;
            }
        }
        return isAllGranted;
    }

    private void openGallery() {
        pickImage.launch("image/*");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ) {
            String result = data.getStringExtra("RESULT");
            Uri resultUri = null;
            if (result != null) {
                resultUri = Uri.parse(result);
            }
            image = fileUriToBase64(resultUri, getContentResolver());

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);

            } catch (IOException e) {
                e.printStackTrace();
            }
            uploadBlogImage();
        } else if (requestCode == 80 && resultCode == RESULT_OK && data != null) {
            Uri video_Uri = data.getData();
            video = fileUriToBase64(video_Uri, getContentResolver());
            uploadBlogVideo();
        } else if (requestCode == 100) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), photo, "Title", null);
            Uri uri = Uri.parse(path);
            Intent intent = new Intent(this, Gallery_Image_CropperActivity.class);
            intent.putExtra("DATA", uri.toString());
            startActivity(intent);

        } else if (requestCode == 200) {
            Uri video_Uri = data.getData();
            video = fileUriToBase64(video_Uri, getContentResolver());
            uploadBlogVideo();
        }
    }


    void uploadBlogImage() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String email_id = sessionManagement.getSessionEmail();

        Call<ResponseModel> call = Controller.getInstance()
                .getApiInterface()
                .uploadBlogImage(result,email_id,image);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                String output = responseModel.getMessage();
                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
                //String imageURL = "http://172.168.0.182/wooble_api/blog_assets/" + output;
                String imageURL = fileLocation + output;
                blogEditor.insertImage(imageURL, "wooble", 300);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void uploadBlogVideo() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String email_id = sessionManagement.getSessionEmail();
        Call<ResponseModel> call = Controller.getInstance()
                .getApiInterface()
                .uploadBlogVideo(result, email_id, video);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                String output = responseModel.getMessage();
                //String videoURL = "http://172.168.0.182/wooble_api/blog_assets/" + output;
                String videoURL = fileLocation + output;
                Toast.makeText(getApplicationContext(), videoURL, Toast.LENGTH_SHORT).show();
                blogEditor.insertVideo(videoURL, 400);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void uploadBlogAudio() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String email_id = sessionManagement.getSessionEmail();

        System.out.println("Audio" + audio);
        Call<ResponseModel> call = Controller.getInstance()
                .getApiInterface()
                .uploadBlogAudio(result, email_id, audio);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                String output = responseModel.getMessage();
                String audioURL = fileLocation + output;
                Toast.makeText(getApplicationContext(), audioURL, Toast.LENGTH_SHORT).show();
                blogEditor.insertAudio(audioURL);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void PublishBlog() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String email_id = sessionManagement.getSessionEmail();
        String title = binding.createTitle.getText().toString();
        Call<ResponseModel> call = Controller.getInstance()
                .getApiInterface()
                .publishBlog(result, email_id, title,html);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                String output = responseModel.getMessage();
                //String audioURL = "http://172.168.0.182/wooble_api/blog_assets/" + output;
                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
                //blogEditor.insertAudio(audioURL);
                //Intent intent = new Intent(Create_BlogsActivity.this, BlogFragment.class);
                 Intent intent = new Intent().setClassName(Create_BlogsActivity.this,"com.wooble.wooble.ui.Blogs.BlogFragment.class");
                Create_BlogsActivity.this.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String fileUriToBase64(Uri uri, ContentResolver resolver) {
        String encodedBase64 = "";
        try {
            byte[] bytes = readBytes(uri, resolver);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                encodedBase64 = Base64.getEncoder().encodeToString(bytes);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return encodedBase64;
    }

    private static byte[] readBytes(Uri uri, ContentResolver resolver)
            throws IOException {
        // this dynamically extends to take the bytes you read
        InputStream inputStream = resolver.openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the
        // byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }


    private void YoutubeAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(getApplicationContext());
        //dialog.setMessage("Please Enter Youtube Link");
        dialog.setTitle("Please Enter Youtube Link");
        dialog.setView(edittext);
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        String youtubeUrl = edittext.getText().toString();
                        blogEditor.insertYoutubeVideo(youtubeUrl);
                    }
                });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "cancel is clicked", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void ImageInsertByLinkAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText UrlEdittext = new EditText(getApplicationContext());
        UrlEdittext.setHint("Enter Image Link");
        layout.addView(UrlEdittext);
        final EditText TitleEdittext = new EditText(getApplicationContext());
        TitleEdittext.setHint("Enter Image Title");
        layout.addView(TitleEdittext);
        final EditText ImageHeightEdittext = new EditText(getApplicationContext());
        ImageHeightEdittext.setHint("Enter Image Height");
        layout.addView(ImageHeightEdittext);
        final EditText ImageWidthEdittext = new EditText(getApplicationContext());
        ImageWidthEdittext.setHint("Enter Image Width");
        layout.addView(ImageWidthEdittext);
        //dialog.setMessage("Please Enter Youtube Link");
        dialog.setTitle("Please Enter Image Details");
        dialog.setView(layout);
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        String LinkUrl = UrlEdittext.getText().toString();
                        String TitleUrl = TitleEdittext.getText().toString();
                        Integer ImageHeight = Integer.parseInt(ImageHeightEdittext.getText().toString());
                        Integer ImageWidth = Integer.parseInt(ImageWidthEdittext.getText().toString());
                        blogEditor.insertImage(LinkUrl, TitleUrl, ImageWidth, ImageHeight);

                    }
                });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You cancelled this link", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


    private void LinkAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText UrlEdittext = new EditText(getApplicationContext());
        UrlEdittext.setHint("Enter Link");
        UrlEdittext.setBackgroundColor(Color.WHITE);
        layout.addView(UrlEdittext);
        final EditText TitleEdittext = new EditText(getApplicationContext());
        TitleEdittext.setHint("Enter Title");
        TitleEdittext.setBackgroundColor(Color.WHITE);
        layout.addView(TitleEdittext);
        //dialog.setMessage("Please Enter Youtube Link");
        dialog.setTitle("Please Enter Link & Title");
        dialog.setView(layout);
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        String LinkUrl = UrlEdittext.getText().toString();
                        String TitleUrl = TitleEdittext.getText().toString();
                        blogEditor.insertLink(LinkUrl, TitleUrl);
                    }
                });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You cancelled this link", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


    private void AudioInsertByLinkAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(getApplicationContext());
        //dialog.setMessage("Please Enter Youtube Link");
        dialog.setTitle("Please Enter Audio Link");
        dialog.setView(edittext);
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        String AudioUrl = edittext.getText().toString();
                        blogEditor.insertAudio(AudioUrl);
                    }
                });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "cancel is clicked", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void VideoInsertByLinkAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText UrlEdittext = new EditText(getApplicationContext());
        UrlEdittext.setHint("Enter Video Link");
        layout.addView(UrlEdittext);
        final EditText VideoHeightEdittext = new EditText(getApplicationContext());
        VideoHeightEdittext.setHint("Enter Video Height");
        layout.addView(VideoHeightEdittext);
        final EditText VideoWidthEdittext = new EditText(getApplicationContext());
        VideoWidthEdittext.setHint("Enter Video Width");
        layout.addView(VideoWidthEdittext);
        //dialog.setMessage("Please Enter Youtube Link");
        dialog.setTitle("Please Enter Video Details");
        dialog.setView(layout);
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        String LinkUrl = UrlEdittext.getText().toString();
                        Integer VideoHeight = Integer.parseInt(VideoHeightEdittext.getText().toString());
                        Integer VideoWidth = Integer.parseInt(VideoWidthEdittext.getText().toString());
                        blogEditor.insertVideo(LinkUrl, VideoWidth, VideoHeight);

                    }
                });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You cancelled this link", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


    void DeleteBlogRow() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String email_id = sessionManagement.getSessionEmail();

        Call<ResponseModel> call = Controller.getInstance()
                .getApiInterface()
                .deleteRow(result, email_id);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                String output = responseModel.getMessage();
                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), BlogFragment.class);
//                startActivity(intent);
//                finish();
                Intent intent = new Intent().setClassName(getApplicationContext(),"com.wooble.wooble/com.wooble.wooble.ui.Blogs.BlogFragment.class");
                Create_BlogsActivity.this.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void PublishDraft() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String email_id = sessionManagement.getSessionEmail();
        String title = binding.createTitle.getText().toString();
        Call<ResponseModel> call = Controller.getInstance()
                .getApiInterface()
                .publishDraft(result, email_id, title,html);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                String output = responseModel.getMessage();
                //String audioURL = "http://172.168.0.182/wooble_api/blog_assets/" + output;
                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
                //blogEditor.insertAudio(audioURL);
                Intent intent = new Intent(getApplicationContext(), BlogFragment.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getBase64String(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();

        String base64String = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            base64String = Base64.getEncoder().encodeToString(imageBytes);
        }
        return base64String;

    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==33 && resultCode==RESULT_OK && data!=null){
//            Uri uri=data.getData();
//
//            try {
//                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            ImageView imageView=new ImageView(Create_BlogsActivity.this);
//            imageView.setImageBitmap(bitmap);
//            image = getBase64String(bitmap);
//            arr.add(image);
//            addView(imageView, 400, 400);
//
//        }
//
//        }
//       public void addView(ImageView imageView, int width, int height){
//           LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width, height);
//           layoutParams.setMargins(5,0,5,0);
//
//           imageView.setLayoutParams(layoutParams);
//           binding.linerLayout.addView(imageView);
//       }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        if (html == null || html.equals("<p></p>")) {
            DeleteBlogRow();
        } else {
            PublishDraft();
        }
        return true;
    }
//    private void insertBlogData() {
//
//        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
//        profileEmail = sessionManagement.getSessionEmail();
//        String title = binding.createTitle.getText().toString().trim();
//        String content = binding.createDescription.getText().toString().trim();
//
//        //our custom volley request
//        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.INSERT_BLOG_DATA,
//                new Response.Listener<NetworkResponse>() {
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//                        try {
//                            JSONObject obj = new JSONObject(new String(response.data));
//                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(Create_BlogsActivity.this, BlogFragment.class);
//                            startActivity(intent);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email_id", profileEmail);
//                params.put("title", title);
//                params.put("content", content);
//                return params;
//            }
//
//        };
//
//        Volley.newRequestQueue(this).add(volleyMultipartRequest);
//    }
//    void insertData(){
//        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
//        String email_id = sessionManagement.getSessionEmail();
//        String title = binding.createTitle.getText().toString().trim();
//        String content = binding.createDescription.getText().toString().trim();
//        String name = String.valueOf(System.currentTimeMillis());
//
//        Call<ResponseModel> call = Controller.getInstance()
//                .getApiInterface()
//                .insertBlog(email_id,title,content,arr);
//
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
//                ResponseModel responseModel = response.body();
//                String output = responseModel.getMessage();
//                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(getApplicationContext(), BlogFragment.class);
//                        startActivity(intent);
//                    }
//                }, 2000);
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(Create_BlogsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
