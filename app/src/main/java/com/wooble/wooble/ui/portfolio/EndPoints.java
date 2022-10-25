package com.wooble.wooble.ui.portfolio;

public class EndPoints {
    private static final String ROOT_URL = "http://172.168.0.182/api/";
    public static final String UPLOAD_URL = ROOT_URL +"profile.php?apicall="+"uploadpic";
    public static final String RETRIEVE_URL = ROOT_URL +"profile.php?apicall="+"getpics";
    public static final String UPLOAD_ONLY_PROFILE_PIC = ROOT_URL +"editshowprofilepic.php?apicall="+"updateprofilepic";
    public static final String GET_ONLY_PROFILE_PIC = ROOT_URL +"editshowprofilepic.php?apicall="+"getprofilepic";
    public static final String UPLOAD_ONLY_COVER_PIC = ROOT_URL +"editshowcoverpic.php?apicall="+"updatecoverpic";
    public static final String GET_ONLY_COVER_PIC = ROOT_URL +"editshowcoverpic.php?apicall="+"getcoverpic";
    public static final String GET_ONLY_PROFILE_DATA = ROOT_URL +"showeditprofile.php?apicall="+"getprofile";
    public static final String UPDATE_ONLY_PROFILE_DATA = ROOT_URL +"showeditprofile.php?apicall="+"updateprofile";
}
