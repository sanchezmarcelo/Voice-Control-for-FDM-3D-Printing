package com.marcelo.android.voiceactivated3dprinter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public interface HomeClient {
//"Content-Type: application/json"
    //key: ?3F80F0B915C34471B2BF0C918212F98A

    String key = "3F80F0B915C34471B2BF0C918212F98A";

    public String getKey();

    @Headers({"Content-Type: application/json", "X-Api-Key: " + key})
    @POST("api/printer/printhead")
    Call<Home> newHomeSession(@Body Home home);
}
