package com.master_vision.trfihi.registration.register;

import com.master_vision.trfihi.registration.register.model.RegistrationRequestModel;
import com.master_vision.trfihi.registration.register.model.RegistrationResponseModel;

import retrofit2.http.Body;
import retrofit2.http.POST;
import io.reactivex.Observable;

public interface _RegistrationService {

    @POST("api/Account/Register")
    Observable<RegistrationResponseModel> postRegistration(@Body RegistrationRequestModel model);
}
