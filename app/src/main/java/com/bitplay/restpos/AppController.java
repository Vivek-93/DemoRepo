
package com.bitplay.restpos;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bitplay.restpos.interfaces.networkstate.NetworkStateReceiverListener;
import com.bitplay.restpos.utils.Utils;
import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by Vivek on 26-04-2017.
 */

public class AppController extends SugarApp implements NetworkStateReceiverListener {

    public static final String TAG = AppController.class.getSimpleName();

    public static final int socketTimeout = 15000;//30 seconds- change to what you want

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        // TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        // Fabric.with(this, new Twitter(authConfig));
        mInstance = this;
        SugarContext.init(this);
        Utils.checkingInternetWithOurReceiver(mInstance, this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            MultiDex.install(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        try {
            SugarContext.terminate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
            /*
             * if (mImageLoader == null) { mImageLoader = new
		 * ImageLoader(this.mRequestQueue, new LruBitmapCache()); }
		 */
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
//		set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
//        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        req.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }


    public void requestRetryPoilcy() {
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;


    @Override
    public void networkAvailable() {
        try {
            // Toast.makeText(mInstance, " CONNECTED! ", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void networkUnavailable() {
        try {
            Toast.makeText(mInstance, "No Active Internet Connection ,Please try again later", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
    private void showNetworkNotAvailableAlert() {
        Intent i = new Intent(this, AlertDialogActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
*/


}
