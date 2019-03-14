package com.code5.kb5.engine;

import com.code5.kb5.engine.config.RetrofitSetting;
import com.code5.kb5.engine.interfaces.RetrofitCallback;
import com.code5.kb5.engine.interfaces.UrlInterface;
import com.code5.kb5.model.bill.BillResponse;
import com.code5.kb5.model.login.LoginParam;
import com.code5.kb5.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppController {
    private UrlInterface getAPI(){return RetrofitSetting.getAPI();}

    public void login(LoginParam loginParam, final RetrofitCallback retrofitCallback) {

        final Call<LoginResponse> taskModelCall = getAPI().login(loginParam);

        taskModelCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                retrofitCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    public void getBill(String token, final RetrofitCallback retrofitCallback){

        final Call<BillResponse> taskModelCall = getAPI().bills("Bearer " + token);

        taskModelCall.enqueue(new Callback<BillResponse>() {
            @Override
            public void onResponse(Call<BillResponse> call, Response<BillResponse> response) {
                retrofitCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<BillResponse> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }
}