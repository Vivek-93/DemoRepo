package com.bitplay.restpos.utils;

import org.apache.http.NameValuePair;

import java.util.List;

public class AppConstants {



    public static int TAG_ID_LOGIN = 101;
    public static int TAG_ID_REGISTER= 102;
    public static int TAG_ID_PROFILE_DETAILS= 103;
    public static int TAG_ID_PROFILE_UPDATE= 104;
    public static int TAG_ID_TABLE_DETAILS=106;


    public static String baseUrlInsideApp = "http://192.168.1.106:8082/api";

    List<NameValuePair> params;
    public static String serverUrl = "http://192.168.1.106:8082/api";


    public static String termsUrl = "http://192.168.1.106:8082/api";
    public static boolean IS_LIVE_BUILD = false;



    private static String BASE_URL;
    public static String WEBSERVICE_HOST;


    static {
        if (IS_LIVE_BUILD) {
            WEBSERVICE_HOST = "http://192.168.0.106:8082/api";
        } else {
            WEBSERVICE_HOST = "http://192.168.0.106:8082/api";

        }
        BASE_URL = WEBSERVICE_HOST;
    }

    /*
     * SERVER CALLS


    /*
     *  Enum to get URL and TAG names
     */
    public enum URL {



        LOGIN("/Values/Login/"),
        REGISTER("/Values/Add"),
        PROFILEDETAILS("/Values/UserDetail"),
        PROFILEUPDATE("/Values/Update"),
        TABLEDETAILS("/Restaurant/tableDetail");




        private String url;

        public String baseUrl = "http://192.168.0.106:8082/api";
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
