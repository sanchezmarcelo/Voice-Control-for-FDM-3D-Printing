package com.marcelo.android.voiceactivated3dprinter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public interface BedClient {

    @Headers({"Content-Type: application/json", "X-Api-Key: 3F80F0BxxxxxxxxxxC918212F98A"
} )
    @POST("api/printer/bed")
    Call<Bed> newBedSession(@Body Bed bed);
}
