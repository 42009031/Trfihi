package com.master_vision.trfihi.profile.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.master_vision.trfihi.BR;
import com.master_vision.trfihi.profile.adapter.ReviewAdapter;
import com.master_vision.trfihi.profile.model.ProfileModel;
import com.master_vision.trfihi.profile.model.ReviewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends BaseObservable {

    private static final String TAG = "ProfileViewModel";
    private ArrayList<ReviewModel> data;
    private ReviewAdapter reviewAdapter;
    private ProfileModel profileModel = new ProfileModel("0",
            "Name",
            "phone",
            "email",
            "http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png",
            "5",
            "Female",
            "23/12/1991");


    public ProfileViewModel() {
        data = new ArrayList<>();
        reviewAdapter = new ReviewAdapter();
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners, data population, etc.
        populateData();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public ArrayList<ReviewModel> getData() {
        return this.data;
    }

    @Bindable
    public ReviewAdapter getAdapter() {
        return this.reviewAdapter;
    }

    @Bindable
    public float getRating() {
        return 2.5f;
    }
    @Bindable
    public String getProfileImageUrl() {
        return profileModel.getProfileImageURL();
    }
    @Bindable
    public String getUsername(){
        return profileModel.getDisplayName();
    }
    @Bindable
    public String getDateOfBirth(){
        return profileModel.getDOB();
    }

    @Bindable
    public String getGender(){
        return profileModel.getGender();
    }


    private void populateData() {
        // populate the data from the source, such as the database.
        for (int i = 0; i < 10; i++) {
            ReviewModel dataModel = new ReviewModel();
            dataModel.setReviewer("name " + String.valueOf(i));
            dataModel.setEvaluation(3.5f);
            dataModel.setReview_en("good gamer");
            dataModel.setProfileImageURL("http://riptoned.com/wp-content/uploads/2017/01/no-user-image.png");
            data.add(dataModel);

        }
        notifyPropertyChanged(BR.data);
    }


}
