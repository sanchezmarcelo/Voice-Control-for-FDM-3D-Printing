package com.marcelo.android.voiceactivated3dprinter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public interface OctoPrintClient {
//"Content-Type: application/json"
    //key: ?3F80F0B915C34471B2BF0C918212F98A

    @Headers({"Content-Type: application/json", "X-Api-Key: 3F80F0B915C34471B2BF0C918212F98A"
    } )
    @POST("api/printer/printhead")
    Call<VoiceCommand> newHomeSession(@Body VoiceCommand voiceCommand);
  //  Call<Void> newLoginSession(@Body VoiceCommand voiceCommand);
}
