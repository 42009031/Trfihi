package com.master_vision.trfihi.profile.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.profile.model.ReviewModel;


public class ReviewItemViewModel extends BaseObservable {

    private Float evaluation;
    private String reviewer, review;
    private ReviewModel reviewModel;
    private String profileImageURL ;


    public ReviewItemViewModel(ReviewModel reviewModel){
        this.reviewModel = reviewModel;

        this.evaluation = Float.parseFloat(reviewModel.getEvaluation());
        this.reviewer = reviewModel.getReviewer();
        this.review = reviewModel.getReview_ar();
        this.review = reviewModel.getReview_en();
        this.reviewer = reviewModel.getReviewer();
        this.profileImageURL = reviewModel.getProfileImageURL();
    }



    public void onItemClick(View view){
        Toast.makeText(view.getContext(), reviewModel.getReviewer(), Toast.LENGTH_SHORT).show();
        view.startAnimation(Helper.BtnClickAnimation);
    }

    @Bindable
    public Float getEvaluation() {
        return this.evaluation;
    }
    @Bindable
    public String getProfileImageURL() {
        return this.profileImageURL;
    }
    @Bindable
    public String getReviewer() {
        return this.reviewer;
    }
    @Bindable
    public String getReview() {
        return this.review;
    }



}
