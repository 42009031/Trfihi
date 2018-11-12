package com.master_vision.trfihi.home.messages;

import com.master_vision.trfihi.chat.model.ChatModel;
import com.master_vision.trfihi.home.messages.model.MessageModel;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;


public interface _MessageService {

    @GET("api/GetAllUsersChat")
    Observable<List<MessageModel>> getAllUsersChat(@Body String userId);

}
