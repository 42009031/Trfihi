package com.master_vision.trfihi.profile.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;
import com.master_vision.trfihi.TrfihiApp;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.network.NetworkClient;
import com.master_vision.trfihi.profile._ProfileService;
import com.master_vision.trfihi.profile.adapter.ReviewAdapter;
import com.master_vision.trfihi.profile.model.ProfileModel;
import com.master_vision.trfihi.profile.model.ReviewModel;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


public class ProfileViewModel extends BaseObservable {

    public ObservableField<String> profileImageUrl = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> dateOfBirth = new ObservableField<>();
    public ObservableField<String> gender = new ObservableField<>();
    public ObservableField<Float> rating = new ObservableField<>();
    public ObservableArrayList<ReviewModel> dataList = new ObservableArrayList<>();
    public ReviewAdapter reviewAdapter;

    private static final String TAG = "ProfileViewModel";
    private Context context;
    private String userId;
    private _ProfileService profileService;

    public ProfileViewModel(Context context, String userId) {
        this.context = context;
        this.userId = userId;
        this.profileService = NetworkClient.getService(_ProfileService.class);
        this.reviewAdapter = new ReviewAdapter();
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners, data population, etc.
        getUserById();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }



    public void getUserById() {
        Helper.displayLoadDialog(context);
        TrfihiApp.getCompositeDisposable().add(profileService.getUserById(userId)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProfileModel>() {
                    @Override
                    public void accept(ProfileModel response) {
                        Helper.dismissLoading();
                        if (response != null) {

                            if (!TextUtils.isEmpty(response.getProfileImageURL())) {
                                profileImageUrl.set(response.getProfileImageURL());
                            }
                            if (!TextUtils.isEmpty(response.getDOB())) {
                                dateOfBirth.set(response.getDOB());
                            }
                            if (!TextUtils.isEmpty(response.getGender())) {
                                gender.set(response.getGender());
                            }
                            if (!TextUtils.isEmpty(response.getDisplayName())) {
                                username.set(response.getDisplayName());
                            }
                            getAllReviews();
                        } else {
                            Toast.makeText(context, "Server response error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Helper.dismissLoading();
                        throwable.printStackTrace();
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    public void getAllReviews() {
        Helper.displayLoadDialog(context);
        TrfihiApp.getCompositeDisposable().add(profileService.GetAllReviews(userId)
                .subscribeOn(TrfihiApp.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ReviewModel>>() {
                    @Override
                    public void accept(List<ReviewModel> response) {
                        Helper.dismissLoading();
                        if (response.size() != 0) {
                            dataList.addAll(response);
                            reviewAdapter.updateData(dataList);

                            float evaluation = 0;
                            for (ReviewModel reviewModel : response) {
                                evaluation += Float.parseFloat(reviewModel.getEvaluation());
                            }
                            rating.set(evaluation/response.size());
                        } else {
                            Toast.makeText(context, "Server response error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Helper.dismissLoading();
                        throwable.printStackTrace();
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

}
