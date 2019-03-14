package com.code5.kb5.engine.interfaces;

import retrofit2.Response;

public interface RetrofitCallback {
    void onResponse(Response<?> response);

    void onFailure(String result);
}
