package com.code5.kb5.engine.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RetrofitPdfApi {
    @Streaming
    @GET
    Call<ResponseBody> downloadFileByUrl(@Url String fileUrl);

//    @Streaming
//    @GET(UrlService.get_sbg)
//    Call<ResponseBody> getSbg(@Path("loanid") String loanid, @Header("Authorization") String auth);
}
