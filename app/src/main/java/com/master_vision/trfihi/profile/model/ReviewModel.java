package com.master_vision.trfihi.profile.model;

public class ReviewModel {

    private float Evaluation;
    private int ReviewId;
    private String UserId, ReviewDate, Review_ar, Review_en, Reviewer, ProfileImageURL;

    public ReviewModel() {
    }

    public ReviewModel(float evaluation, int reviewId, String userId, String reviewDate, String review_ar, String review_en, String reviewer, String profileImageURL) {
        Evaluation = evaluation;
        ReviewId = reviewId;
        UserId = userId;
        ReviewDate = reviewDate;
        Review_ar = review_ar;
        Review_en = review_en;
        Reviewer = reviewer;
        ProfileImageURL = profileImageURL;
    }

    public float getEvaluation() {
        return Evaluation;
    }

    public void setEvaluation(float evaluation) {
        Evaluation = evaluation;
    }

    public int getReviewId() {
        return ReviewId;
    }

    public void setReviewId(int reviewId) {
        ReviewId = reviewId;
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

    public String getProfileImageURL() {
        return ProfileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        ProfileImageURL = profileImageURL;
    }
}
