package com.code5.kb5.engine.interfaces;

import com.code5.kb5.engine.config.UrlService;
import com.code5.kb5.model.bill.BillResponse;
import com.code5.kb5.model.login.LoginParam;
import com.code5.kb5.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UrlInterface {
    @Headers("Content-Type:application/json")
    @POST(UrlService.login)
    Call<LoginResponse> login(@Body LoginParam loginParam);

    @Headers("Content-Type:application/json")
    @GET(UrlService.bills)
    Call<BillResponse> bills(@Header("Authorization") String auth);

    /* @Headers("Content-Type:application/json")
    @GET(UrlService.brands)
    Call<BrandResponse> brands(@Query("category_id") int id, @Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @GET(UrlService.products)
    Call<ProductResponse> products(@Query("brand_id") int id, @Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @GET(UrlService.specifications)
    Call<SpecificationResponse> specifications(@Query("product_id") int id, @Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @GET(UrlService.branches)
    Call<BranchResponse> branches(@Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @POST(UrlService.create_leads)
    Call<CreateLeadsResponse> createLeads(@Body Leads leads, @Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @PUT(UrlService.update_leads)
    Call<CreateLeadsResponse> updateLeads(@Body Leads leads, @Path("code") String code, @Header("Authorization") String auth); */
}
