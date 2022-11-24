package com.wooble.wooble.ui.portfolio;

public class EndPoints {
    public static final String ROOT_URL = "http://172.168.0.182/wooble-api/";
    public static final String UPLOAD_FULL_PROFILE = ROOT_URL + "profile.php?apicall=" + "updatefullprofile";
    public static final String GET_FULL_PROFILE = ROOT_URL + "profile.php?apicall=" + "getfullprofile";
    public static final String UPLOAD_ONLY_PROFILE_PIC = ROOT_URL + "editshowprofilepic.php?apicall=" + "updateprofilepic";
    public static final String GET_ONLY_PROFILE_PIC = ROOT_URL + "editshowprofilepic.php?apicall=" + "getprofilepic";
    public static final String UPLOAD_ONLY_COVER_PIC = ROOT_URL + "editshowcoverpic.php?apicall=" + "updatecoverpic";
    public static final String GET_ONLY_COVER_PIC = ROOT_URL + "editshowcoverpic.php?apicall=" + "getcoverpic";
    public static final String GET_ONLY_PROFILE_DATA = ROOT_URL + "showeditprofile.php?apicall=" + "getprofile";
    public static final String UPDATE_ONLY_PROFILE_DATA = ROOT_URL + "showeditprofile.php?apicall=" + "updateprofile";

    public static final String INSERT_ONLY_GALLERY_PIC = ROOT_URL + "insertgetgallerypic.php?apicall=" + "insertgallerypic";
    public static final String GET_GALLERY_DATA = ROOT_URL + "insertgetgallerypic.php?apicall=" + "getgallerydata";
    public static final String INSERT_GALLERY_DATA = ROOT_URL + "insertgetgallerydata.php?apicall=" + "insertgallerydata";
    public static final String DELETE_GALLERY_DATA = ROOT_URL + "deletegallerydata.php?apicall=" + "deletegallerydata";
    public static final String UPDATE_GALLERY_DATA = ROOT_URL + "updategallerydata.php?apicall=" + "updategallerydata";

    public static final String INSERT_BLOG_DATA = ROOT_URL + "blog.php?apicall=" + "insertblogdata";

    public static final String INSERT_RESUME_DATA = "resume.php?apicall=" + "insertresumedata";

    public static final String GET_BLOG_IMAGES = ROOT_URL+"blog.php?apicall=" + "getblogimages";




}
