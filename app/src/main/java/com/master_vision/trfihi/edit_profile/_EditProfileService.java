package com.master_vision.trfihi.edit_profile;

import com.master_vision.trfihi.profile.model.ProfileModel;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface _EditProfileService {

    @POST("api/GetUserById")
    Observable<ProfileModel> getUserById(@Body String userId);

    @POST("api/UpdateUser")
    @Multipart
    Observable<String> updateUser(@Path("ID") String ID,
                                  @Part MultipartBody.Part ProfileImageURL,
                                  @Part("DOB") String DOB,
                                  @Part("gender") String gender,
                                  @Part("ShowPicture") String ShowPicture,
                                  @Part("ShowBirthDate") String ShowBirthDate,
                                  @Part("ShowGender") String ShowGender);


}
