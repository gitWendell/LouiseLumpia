package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by gd185082 on 4/19/2018.
 */

public interface ApiService {

//    User Section

//    Create User
    @FormUrlEncoded
    @POST("user_create.php")
    Call<Result> createUser (@Field("name") String name,
                             @Field("username") String username,
                             @Field("password") String password);

//    Login User
    @FormUrlEncoded
    @POST("user_login.php")
    Call<Result> loginUser (@Field("username") String username,
                            @Field("password") String password);

//    End User Section

//    Order Section

    @FormUrlEncoded
    @POST("order_create.php")
    Call<Result> createOrder (@Field("user_id") int user_id,
                              @Field("name") String name,
                              @Field("size") String size,
                              @Field("qty") int qty);

    @FormUrlEncoded
    @POST("order_incomplete.php")
    Call<ResultOrder> getIncompleteOrder (@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("order_user_confirm.php")
    Call<ResultOrder> getUserCongirmOrder (@Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("order_confirm.php")
    Call<ResultOrder> updateOrder (@Field("id") int id);

    @GET("order_admin_confirm.php")
    Call<ResultOrder> getAllConfirmOrder ();

    @FormUrlEncoded
    @POST("order_delete.php")
    Call<ResultOrder> delete (@Field("id") int id);

//    End Order Section


//    Delivery Section

    @FormUrlEncoded
    @POST("delivery_create.php")
    Call<ResultDelivery> createDelivery (@Field("user_id") int user_id,
                                 @Field("contact_person") String contact_person,
                                 @Field("contact_number") int contact_number,
                                 @Field("location") String location,
                                 @Field("note") String note);

//    End Delivery Section

//    Admin Section

    @GET("admin_order_list.php")
    Call<ResultUsersOrderInformation> getUsersOrderInformation();

//    End Admin Section

}
