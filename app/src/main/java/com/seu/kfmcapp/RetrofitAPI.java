package com.seu.kfmcapp;

import static com.seu.kfmcapp.RetrofitPost.retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("interns")
    Call<User> getUserInformation(@Field("name") String name,@Field("job") String job);
    Call<Intern> getInternInformation(@Field("First name: ") String firstName, @Field("Last name: ") String lastName,
                                      @Field("Email: ") String Email,@Field("Password: ") String password,
                                      @Field ("Phone: ") String phone,@Field("Gender: ") Boolean Gender );


   @GET("majors")
    Call<Major> getMajor();


    @GET("majors")
    Call<ArrayList<Major>> getAllMajors();




}
