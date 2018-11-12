package com.master_vision.trfihi.chat;

import com.master_vision.trfihi.chat.model.ChatModel;
import com.master_vision.trfihi.chat.model.ChatRequestParamModel;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface _ChatService {

    @GET("api/GetSpecificChat")
    Observable<List<ChatModel>> getSpecificChat(@Body ChatRequestParamModel requestParamModel);

    @POST("api/AddChat")
    Observable<String> addChat(@Body ChatModel chatModel);
}
