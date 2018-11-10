package com.master_vision.trfihi.profile.model;

import com.google.gson.annotations.SerializedName;

public class ReviewModel {

    @SerializedName("Evaluation")
    private String Evaluation;

    @SerializedName("UserId")
    private String UserId;

    @SerializedName("ReviewDate")
    private String ReviewDate;

    @SerializedName("Review_ar")
    private String Review_ar;

    @SerializedName("Review_en")
    private String Review_en;

    @SerializedName("Reviewer")
    private String Reviewer;

    @SerializedName("ReviewId")
    private String ReviewId;

    @SerializedName("ProfileImageURL")
    private String ProfileImageURL;

    public ReviewModel() {
    }

    public ReviewModel(String evaluation, String userId, String reviewDate, String review_ar, String review_en, String reviewer, String reviewId, String profileImageURL) {
        Evaluation = evaluation;
        UserId = userId;
        ReviewDate = reviewDate;
        Review_ar = review_ar;
        Review_en = review_en;
        Reviewer = reviewer;
        ReviewId = reviewId;
        ProfileImageURL = profileImageURL;
    }

    public String getEvaluation() {
        return Evaluation;
    }

    public void setEvaluation(String evaluation) {
        Evaluation = evaluation;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(String reviewDate) {
        ReviewDate = reviewDate;
    }

    public String getReview_ar() {
        return Review_ar;
    }

    public void setReview_ar(String review_ar) {
        Review_ar = review_ar;
    }

    public String getReview_en() {
        return Review_en;
    }

    public void setReview_en(String review_en) {
        Review_en = review_en;
    }

    public String getReviewer() {
        return Reviewer;
    }

    public void setReviewer(String reviewer) {
        Reviewer = reviewer;
    }

    public String getReviewId() {
        return ReviewId;
    }

    public void setReviewId(String reviewId) {
        ReviewId = reviewId;
    }

    public String getProfileImageURL() {
        return ProfileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        ProfileImageURL = profileImageURL;
    }
}
