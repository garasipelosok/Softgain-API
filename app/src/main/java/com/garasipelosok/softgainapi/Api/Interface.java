package com.garasipelosok.softgainapi.Api;

import com.garasipelosok.softgainapi.Model.Auth.Login;
import com.garasipelosok.softgainapi.Model.Barang.Barang;
import com.garasipelosok.softgainapi.Model.Barang.SingleDataBarang;
import com.garasipelosok.softgainapi.Model.GetReponseSuccess;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Interface {
    @FormUrlEncoded
    @POST("login") // api/login
    Call<Login> postLogin(@Field("email") String email,
                          @Field("password") String password);

//    @POST("barang") // api/barang.index
    @GET("barang")
    Call<Barang> postBarang(@Header("Authorization") String Authorization);

    @FormUrlEncoded
//    @PUT("barang/store") // api/barang.store
    @POST("barang")
    Call<GetReponseSuccess> postBarangStore(@Header("Authorization") String Authorization,
                                            @Field("nomor_barang") String nomor_barang,
                                            @Field("nama_barang") String nama_barang,
                                            @Field("jumlah_barang") String jumlah_barang,
                                            @Field("keterangan") String keterangan);

//    @POST("barang/edit/{id}") // api/barang.show | barang.edit
    @GET("barang/{id}")
    Call<SingleDataBarang> postBarangEdit(@Header("Authorization") String Authorization,
                                          @Path(value = "id", encoded = true) String id);

    @FormUrlEncoded
//    @POST("barang/update/{id}") // api/barang.update
    @PUT("barang/{id}")
    Call<GetReponseSuccess> postBarangUpdate(@Header("Authorization") String Authorization,
                                             @Field("nomor_barang") String nomor_barang,
                                             @Field("nama_barang") String nama_barang,
                                             @Field("jumlah_barang") String jumlah_barang,
                                             @Field("keterangan") String keterangan,
                                             @Path(value = "id", encoded = true) String id);

//    @DELETE("barang/delete/{id}") // api/barang.destroy
    @DELETE("barang/{id}")
    Call<GetReponseSuccess> postBarangDelete(@Header("Authorization") String Authorization,
                                             @Path(value = "id", encoded = true) String id);
}
