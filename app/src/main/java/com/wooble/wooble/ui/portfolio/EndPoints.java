package com.wooble.wooble.ui.portfolio;

public class EndPoints {
    private static final String ROOT_URL = "http://172.168.0.182/wooble-api/";
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
    public static final String UPDATE_ONLY_GALLERY_DATA = ROOT_URL + "insertgetgallerydata.php?apicall=" + "insertgallerydata";

    public static final String INSERT_ONLY_PROJECT_DATA = ROOT_URL + "project.php?apicall=" + "insertprojectdata";
    public static final String INSERT_ONLY_PROJECT_IMAGE_1 = ROOT_URL + "project.php?apicall=" + "insertprojectimage_1";
    public static final String INSERT_ONLY_PROJECT_IMAGE_2 = ROOT_URL + "project.php?apicall=" + "insertprojectimage_2";
    public static final String INSERT_ONLY_PROJECT_IMAGE_3 = ROOT_URL + "project.php?apicall=" + "insertprojectimage_3";
    public static final String INSERT_ONLY_PROJECT_IMAGE_4 = ROOT_URL + "project.php?apicall=" + "insertprojectimage_4";
    public static final String INSERT_ONLY_PROJECT_IMAGE_5 = ROOT_URL + "project.php?apicall=" + "insertprojectimage_5";
    public static final String INSERT_ONLY_PROJECT_IMAGE_6 = ROOT_URL + "project.php?apicall=" + "insertprojectimage_6";
    public static final String INSERT_ONLY_PROJECT_VIDEO = ROOT_URL + "project.php?apicall=" + "insertprojectvideo";
    public static final String INSERT_ONLY_PROJECT_PDF = ROOT_URL + "project.php?apicall=" + "insertprojectpdf";


}
