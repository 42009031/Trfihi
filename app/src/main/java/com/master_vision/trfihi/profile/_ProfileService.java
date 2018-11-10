package com.master_vision.trfihi.profile;

import com.master_vision.trfihi.profile.model.ProfileModel;
import com.master_vision.trfihi.profile.model.ReviewModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface _ProfileService {

    @POST("api/GetAllReviews")
    Observable<List<ReviewModel>> GetAllReviews(@Body String userId);

    @POST("api/GetUserById")
    Observable<ProfileModel> getUserById(@Body String userId);

}
