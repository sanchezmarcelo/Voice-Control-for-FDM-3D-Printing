package com.marcelo.android.voiceactivated3dprinter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public interface NozzleClient {
    @Headers({"Content-Type: application/json", "X-Api-Key: 3F80F0xxxxxxxxxxxxxxx212F98A"
    } )
    @POST("api/printer/tool")
    Call<Nozzle> newNozzleSession(@Body Nozzle nozzle);
}
