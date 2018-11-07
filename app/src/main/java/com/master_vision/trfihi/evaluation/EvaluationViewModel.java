package com.master_vision.trfihi.evaluation;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

public class EvaluationViewModel {

    public ObservableField<String> imgUrl= new ObservableField<String>();
    public ObservableField<String> reviewTitle= new ObservableField<String>();
    public ObservableField<Float> rate= new ObservableField<Float>();
    public ObservableField<String> commentTitle= new ObservableField<String>();
    public ObservableField<String> comment= new ObservableField<String>();



    public void onSubmitClick(View view){

    }

    public void onRateChange(RatingBar ratingBar, float rating, boolean fromUser){
        Log.e("Rate :: ", ""+rating);
    }
}
