package com.bitplay.restpos.utils;

import org.apache.http.NameValuePair;

import java.util.List;

public class AppConstants {


    public static int SUCCESS_CODE = 200;
    public static int TAG_ID_LOGIN = 100;
    public static int TAG_ID_CLASS_LIST = 101;
    public static int TAG_ID_REGISTER = 102;
    public static int TAG_ID_FORGOT_PASSWORD_RESEND_OTP = 110;
    public static int TAG_ID_SET_PASSWORD = 111;
    public static int TAG_ID_SUBJECT_LIST = 128;
    public static int TAG_ID_DELETE_A_QUESTION_PAPER = 171;
    public static int TAG_ID_CLASS_LIST_UPDATED = 235;


    public static String baseUrlInsideApp = "http://13.65.211.49:8000";

    List<NameValuePair> params;
    public static String serverUrl = "http://192.168.1.30:8000/app";


    public static String termsUrl = "http://192.168.1.30:8000/app";
    public static boolean IS_LIVE_BUILD = false;


    private static String BASE_URL;
    public static String WEBSERVICE_HOST;


    static {
        if (IS_LIVE_BUILD) {
            WEBSERVICE_HOST = "http://192.168.1.30:8000/app";
        } else {
            WEBSERVICE_HOST = "http://192.168.1.30:8000/app";

        }
        BASE_URL = WEBSERVICE_HOST;
    }

    /*
     * SERVER CALLS


    /*
     *  Enum to get URL and TAG names
     */
    public enum URL {

        CLASS_LIST("/student/class_list/"),
        REGISTRATION("/student/user_register/"),
        OTP_VERIFICATION("/student/user_otp_confirmation/"),
        RESEND_OTP("/student/user_resend_otp_signup/"),
        LOGIN("/student/user_login/"),
        FORGOT_PASSWORD("/student/user_forgot_password/"),
        LOGOUT("/student/user_logout/");



        private String url;

        public String baseUrl = "http://13.84.219.29:8000";
        //public String baseUrl = "http://192.168.1.29:8000/";

        URL(String url) {

            this.url = baseUrl + url;
        }

        public String getUrl() {
            return url;
        }
    }


    public enum TAGNAME {

        EMAIL("email"), PASSWORD("password"), UID("uid"), TAGS("tags"), DESCRIPTION("description"), SRC
                ("src"), MOBILE("mobile"), KEY("key"), DATA
                ("data"), STATUS("status"), USERID("userId"), DEVICEID("deviceId"), MESSAGE("message"), CMD
                ("cmd"), DEVICE_ID_GCM("deviceid"), FILES("files"),
        LOCATION("location"), LATITUDE("latitude"), LONGITUDE("longitude"), GCMTOKEN("gcmtoken"), TOKEN
                ("token"), FOLLOWERPHONENO("follwerPhoneno"),;

        private String value;

        TAGNAME(String tag) {
            this.value = tag;
        }

        public String getValue() {
            return value;
        }
    }
   /* ---------------------*/
}
