package com.marcelo.android.voiceactivated3dprinter;

import android.location.Location;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PrintJobClient {
    @Headers({"Content-Type: multipart/form-data", "X-Api-Key: XXXXXXXXXXXXXXXXX",
           "Content-Type: multipart/form-data", "Content-Disposition: form-data", "name: file", "filename: cloud.gcode",
            "Content-Type: application/octet-stream"
    })
    @POST("api/files/local")
    Call<Print> newPrint(@Body Print print);
}
