package com.bitplay.restpos.interfaces.bookeddetail;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.models.bookeddetail.BookedDetailModel;
import com.bitplay.restpos.models.getguestdetail.GetGuestDetailModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.NetworkStatus;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.BookedItemsActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 22-12-2017.
 */

public class BookedDetailPresenteImpl implements IBookedDetailPresenter,OnRequestListener{

    private IBookedDetailView mIBookedDetailView;
    private BookedItemsActivity mBookedItemsActivity;
    private AsyncInteractor mAsyncInteractor;
    private BookedDetailModel[] mBookedDetailModel;
    private String mBookedDetailError;

    public BookedDetailPresenteImpl(IBookedDetailView mIBookedDetailView) {
        this.mIBookedDetailView = mIBookedDetailView;
        this.mBookedItemsActivity = (BookedItemsActivity) mIBookedDetailView;
        mAsyncInteractor = new AsyncInteractor();
    }



    @Override
    public void bookedDetailsApi(int tablenumber) {

        if (NetworkStatus.checkNetworkStatus(mBookedItemsActivity)) {
            //   Utils.showProgress(mTableDetailsActivity);
            Map<String, String> params = new HashMap<String, String>();
            params.put("tablenumber", String.valueOf(tablenumber));
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_GET_BOOKED_ORDER_DETAIL,
                    AppConstants.URL.GETBOOKEDORDERDETAIL.getUrl(), params);
        } else {
            Utils.showToast(mBookedItemsActivity, "Please connect to internet");
        }

    }

    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {

        if (pid == AppConstants.TAG_ID_GET_BOOKED_ORDER_DETAIL) {

            try {
                if (responseJson != null) {
                    Gson gson = new Gson();

                    mBookedDetailModel = gson.fromJson(responseJson,BookedDetailModel[].class);
                    mIBookedDetailView.onBookedDetailsApiSuccess(pid, mBookedDetailModel);
                } else {

                    //  mIRegisterView.onLoginError(pid, mRegisterModel.getMeta().getStatus());

                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onRequestCompletionError(int pid, String error) {

        if (pid == AppConstants.TAG_ID_GET_BOOKED_ORDER_DETAIL) {
            Gson gson = new Gson();
            mBookedDetailError= gson.toJson(error);
            mIBookedDetailView.onBookedDetailsApiError(pid, error);
        }

    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }
}
