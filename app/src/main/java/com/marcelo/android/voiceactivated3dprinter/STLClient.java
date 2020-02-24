package com.marcelo.android.voiceactivated3dprinter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface STLClient {

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST("http://ec2-52-14-63-95.us-east-2.compute.amazonaws.com/connectToDB.php")
    Call<STL> newPrintRequest(@Body STL stl);
}
