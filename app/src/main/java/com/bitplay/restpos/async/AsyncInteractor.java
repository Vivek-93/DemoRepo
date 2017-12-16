package com.bitplay.restpos.async;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.bitplay.restpos.AppController;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.Sharedpreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Vivek on 5/11/16.
 */
public class AsyncInteractor implements IAsyncInteractor {
    /******************************************************************************************
     * An Interactor helps models cross application boundaries such as networks or serialization
     * This LoginInteractor knows nothing about a UI or the LoginPresenter
     * Because this is an asynchronous call it will call back on the OnRequestListener when complete
     * ******************************************************************************************
     */
    Context context;

    int count = 0;

    String TAG = "ApiCall";

    Sharedpreferences mPrefs = Sharedpreferences.getUserDataObj(AppController.getInstance().getApplicationContext());


    public void validateCredentialsAsync(final OnRequestListener listener, final int pid, final
    String url) {
        Log.v("url-->>", "" + url.toString());
        getJsonObjectResponse(listener, pid, url);
    }

    @Override
    public void validateCredentialsAsync(final OnRequestListener listener, final String url, int pid) {
        OnGetMethodServerCall(listener, 0, url);
    }

    public void validateCredentialsAsync(final OnRequestListener listener, final String url) {
        // creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("11111111111111111", "" + "----");
                Log.i("---->>", ">>>>>>>>>>>>>>>>>>>>>>>");
            }
        }, 0);
    }

    public void validateCredentialsAsync(final OnRequestListener listener, final int pid, final
    String url, final Map<String, String> paramsMap) {
        onPostMethodServerCall(listener, pid, url, paramsMap);
    }


  /*  public void validateCredentialsMultipartAsync(final OnRequestListener listener, final int pid, final
    String url, final Map<String, String> paramsMap, Map<String, String> images) {
        onPostMethodMultipartCall(listener, pid, url, paramsMap, images);
    }*/

    public void registrationMethod(final OnRequestListener listener, final int pid, final String url, final Map<String, String> parasmMap) {
        onPostMethodServerCall(listener, pid, url, parasmMap);
    }

    public void getJsonObjectResponse(final OnRequestListener listener, final int pid, String url) {


    }

    public void onPostMethodServerCall(final OnRequestListener listener, final int pid,
                                       String url, final Map<String, String> stringMap) {

        if (pid == AppConstants.TAG_ID_LOGIN) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d(TAG, "api call" + response.toString());
                            System.out.println(response);
                            try {
                                listener.onRequestCompletion(pid, response);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("hello", ":" + error.toString());
                            VolleyLog.e("Error: ", error.getMessage());

                            String body;
                            //get response body and parse with appropriate encoding
                            if (error.networkResponse.data != null) {
                                try {
                                    body = new String(error.networkResponse.data, "UTF-8");
                                    Log.d("TAG_ID_LOGIN", "error --" + body);
                                    listener.onRequestCompletionError(pid, body);

                                } catch (UnsupportedEncodingException e) {
                                    error.printStackTrace();
                                }
                            }
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    return stringMap;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    //header.put("Content-Type", "application/json; charset=UTF-8");
                    return header;
                }
            };


            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(stringRequest);
        } else if (pid == AppConstants.TAG_ID_REGISTER) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d(TAG, "api call" + response.toString());
                            System.out.println(response);
                            try {
                                listener.onRequestCompletion(pid, response);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("hello", ":" + error.toString());
                            VolleyLog.e("Error: ", error.getMessage());

                            String body;
                            //get response body and parse with appropriate encoding
                            if (error.networkResponse.data != null) {
                                try {
                                    body = new String(error.networkResponse.data, "UTF-8");
                                    Log.d("TAG_ID_REGISTER", "error --" + body);
                                    listener.onRequestCompletionError(pid, body);

                                } catch (UnsupportedEncodingException e) {
                                    error.printStackTrace();
                                }
                            }
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    return stringMap;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    //header.put("Content-Type", "application/json; charset=UTF-8");
                    return header;
                }
            };
            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(stringRequest);
        } else if (pid == AppConstants.TAG_ID_PROFILE_DETAILS) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d(TAG, "api call" + response.toString());
                            System.out.println(response);
                            try {
                                listener.onRequestCompletion(pid, response);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("hello", ":" + error.toString());
                            VolleyLog.e("Error: ", error.getMessage());

                            String body;
                            //get response body and parse with appropriate encoding
                            if (error.networkResponse.data != null) {
                                try {
                                    body = new String(error.networkResponse.data, "UTF-8");
                                    Log.d("TAG_ID_PROFILE_DETAILS", "error --" + body);
                                    listener.onRequestCompletionError(pid, body);

                                } catch (UnsupportedEncodingException e) {
                                    error.printStackTrace();
                                }
                            }
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    return stringMap;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    //header.put("Content-Type", "application/json; charset=UTF-8");
                    return header;
                }
            };
            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(stringRequest);
        } else if (pid == AppConstants.TAG_ID_PROFILE_UPDATE) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d(TAG, "api call" + response.toString());
                            System.out.println(response);
                            try {
                                listener.onRequestCompletion(pid, response);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("hello", ":" + error.toString());
                            VolleyLog.e("Error: ", error.getMessage());

                            String body;
                            //get response body and parse with appropriate encoding
                            if (error.networkResponse.data != null) {
                                try {
                                    body = new String(error.networkResponse.data, "UTF-8");
                                    Log.d("TAG_ID_PROFILE_UPDATE", "error --" + body);
                                    listener.onRequestCompletionError(pid, body);

                                } catch (UnsupportedEncodingException e) {
                                    error.printStackTrace();
                                }
                            }
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    return stringMap;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    //header.put("Content-Type", "application/json; charset=UTF-8");
                    return header;
                }
            };
            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(stringRequest);
        }
    }

 /*   public void onPostMethodMultipartCall(final OnRequestListener listener, final int pid,
                                          final String url, final Map<String, String> stringMap, final Map<String, String> images) {
        if (pid == AppConstants.TAG_ID_CREATE_OWN_QUESTION) {
            Log.e(TAG, "map:" + stringMap.toString());
            AppController.getInstance().getRequestQueue().getCache().invalidate(url, true);
            Log.e(TAG, "map:" + stringMap.toString());
            SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Response", response);

                            try {
                                Log.i(TAG, "TAG_ID_ADD_A_QUESTION" + response.toString());
                                System.out.println(response);
                                listener.onRequestCompletion(pid, response.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        Log.d("hello", ":" + error.toString());
                        VolleyLog.e("Error: ", error.getMessage());

                        String body;
                        //get response body and parse with appropriate encoding
                        if (error.networkResponse.data != null) {
                            try {
                                body = new String(error.networkResponse.data, "UTF-8");
                                Log.d("API newQusPaper", "error --" + body);
                                listener.onRequestCompletionError(pid, body);
                            } catch (UnsupportedEncodingException e) {
                                error.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            ) {
                *//* @Override
                 protected Map<String, String> getParams() {
                     return stringMap;
                 }
               *//*
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("Authorization", mPrefs.getAuthToken());
                    return header;
                }

            };
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
                smr.addStringParam(entry.getKey(), entry.getValue());
            }
            if (images.size() > 0) {
                for (Map.Entry<String, String> entry : images.entrySet()) {
                    System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
                    smr.addFile(entry.getKey(), entry.getValue());
                }
            }
            AppController.getInstance().addToRequestQueue(smr);

        } else if (pid == AppConstants.TAG_ID_STUDENT_ADD_SOLUTION) {

            Log.e(TAG, "map:" + stringMap.toString());
            SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i(TAG, "TAQ_ID_STUDENT_ADD_SOLUTION" + response.toString());
                                listener.onRequestCompletion(pid, response.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        Log.d("hello", ":" + error.toString());
                        VolleyLog.e("Error: ", error.getMessage());
                        String body;
                        //get response body and parse with appropri
                        // ate encoding
                        if (error.networkResponse.data != null) {
                            try {
                                body = new String(error.networkResponse.data, "UTF-8");
                                Log.d(TAG, "TAQ_ID_STUDENT_ADD_SOLUTION error --" + body);
                                listener.onRequestCompletionError(pid, body);
                            } catch (UnsupportedEncodingException e) {
                                error.printStackTrace();

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ) {
                *//* @Override
                 protected Map<String, String> getParams() {
                     return stringMap;
                 }
               *//*
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("Authorization", mPrefs.getAuthToken());
                    return header;
                }

            };
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
                smr.addStringParam(entry.getKey(), entry.getValue());
            }
            if (images.size() > 0) {
                for (Map.Entry<String, String> entry : images.entrySet()) {
                    System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
                    smr.addFile("media", entry.getValue());
                }
            }
            AppController.getInstance().addToRequestQueue(smr);

        } else if (pid == AppConstants.TAG_ID_UPDATE_OWN_QUESTION) {
            Log.e(TAG, "map:" + stringMap.toString());
            SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Response", response);

                            try {
                                Log.i(TAG, "TAG_ID_UPDATE_OWN_QUESTION" + response.toString());
                                System.out.println(response);
                                listener.onRequestCompletion(pid, response.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        Log.d("hello", ":" + error.toString());
                        VolleyLog.e("Error: ", error.getMessage());

                        String body;
                        //get response body and parse with appropriate encoding
                        if (error.networkResponse.data != null) {
                            try {
                                body = new String(error.networkResponse.data, "UTF-8");
                                Log.d("API newQusPaper", "error --" + body);
                                listener.onRequestCompletionError(pid, body);
                            } catch (UnsupportedEncodingException e) {
                                error.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ) {
                *//* @Override
                 protected Map<String, String> getParams() {
                     return stringMap;
                 }
               *//*
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("Authorization", mPrefs.getAuthToken());
                    return header;
                }

            };
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
                smr.addStringParam(entry.getKey(), entry.getValue());
            }
            if (images.size() > 0) {
                for (Map.Entry<String, String> entry : images.entrySet()) {
                    System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
                    smr.addFile(entry.getKey(), entry.getValue());
                }
            }
            AppController.getInstance().addToRequestQueue(smr);
        } else if (pid == AppConstants.TAG_ID_MY_NOTES_ADD) {
            Log.e(TAG, "map:" + stringMap.toString());
            SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i(TAG, "TAG_ID_MY_NOTES_ADD" + response.toString());
                                listener.onRequestCompletion(pid, response.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        Log.d("hello", ":" + error.toString());
                        VolleyLog.e("Error: ", error.getMessage());
                        String body;
                        //get response body and parse with appropri
                        // ate encoding
                        if (error.networkResponse.data != null) {
                            try {
                                body = new String(error.networkResponse.data, "UTF-8");
                                Log.d(TAG, "TAG_ID_MY_NOTES_ADD error --" + body);
                                listener.onRequestCompletionError(pid, body);
                            } catch (UnsupportedEncodingException e) {
                                error.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ) {
                *//* @Override
                 protected Map<String, String> getParams() {
                     return stringMap;
                 }
               *//*
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("Authorization", mPrefs.getAuthToken());
                    return header;
                }

            };
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
                smr.addStringParam(entry.getKey(), entry.getValue());
            }
            if (images.size() > 0) {
                for (Map.Entry<String, String> entry : images.entrySet()) {
                    System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
                    smr.addFile("image", entry.getValue());
                }
            }
            AppController.getInstance().addToRequestQueue(smr);

        }
    }*/

    private void OnGetMethodServerCall(final OnRequestListener listener, final int pid,
                                       String url) {
        Log.i("makeOBJServerCall", "" + url);
        StringRequest req;
        try {
            AppController.getInstance().getRequestQueue().getCache().invalidate(url, true);
            req = new StringRequest(Request.Method.GET, url, new
                    Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //   mPostCommentResponse.requestCompleted();
                            Log.e("makeJsonObjReq-success", "" + response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                listener.onRequestCompletion(pid, jsonObject, null);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("inside OnError", "" + volleyError);
                    String errorMsg = "";
                    if (volleyError.networkResponse != null && volleyError.networkResponse.data
                            != null) {
                        VolleyError error = new VolleyError(new String(volleyError
                                .networkResponse.data));
                        //  volleyError = error;
                        try {
                            JSONObject jsonObject = new JSONObject(new String(volleyError
                                    .networkResponse.data));
                            //Log.i("")
                            Log.e("error data", jsonObject.toString());
                            errorMsg = jsonObject.getString(AppConstants.TAGNAME.MESSAGE.getValue());
                            Log.e("error", "" + errorMsg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (volleyError.networkResponse.statusCode == 401) {

                    }
                    listener.onRequestCompletionError(pid, errorMsg);
                }
            });

            //   queue.add(sr);
            req.setShouldCache(true);
            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}