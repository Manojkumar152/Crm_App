package com.app.crm_app.ApiCalls;

import com.app.crm_app.Model.AddStaffModel;
import com.app.crm_app.Model.CompanyProfile;
import com.app.crm_app.Model.StaffDetailModel;
import com.app.crm_app.Model.UpdateCompanyProfileModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("companyDetail/{userid}")
    Call<CompanyProfile> getProfile(@Path("userid") String userId, @Query("api_key") String key);

    @FormUrlEncoded
    @POST("updateProfile")
    Call<UpdateCompanyProfileModel> updateCompanyProfile(@Field("userid") String userId, @Field("api_key") String apiKey, @Field("company") String company, @Field("gstin_number") String gstin_number, @Field("phonenumber") String phonenumber, @Field("website") String website,@Field("country") String country, @Field("city") String city, @Field("zip") String zip, @Field("state") String state);

    @GET("staffDetail/{userid}")
    Call<StaffDetailModel> getStaffDeatail(@Path("userid") String userId, @Query("api_key") String key);

    @FormUrlEncoded
    @POST("addStaff")
    Call<AddStaffModel> AddStaff(@Field("staffid") String staffid,@Field("api_key") String api_key,@Field("content") String content);


}
