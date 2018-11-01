package com.master_vision.trfihi.databinding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.common.ui.PicassoCircleTransformation;
import com.master_vision.trfihi.common.ui.RoundedCornersTransformation;
import com.master_vision.trfihi.common.ui.TypeFaceProvider;
import com.squareup.picasso.Picasso;

public class DataBindingAdapter {

    @BindingAdapter("bind:toast")
    public static void showToast(View view, String toast) {
        if (toast != null && !toast.isEmpty())
            Toast.makeText(view.getContext(), toast, Toast.LENGTH_LONG).show();
    }

    @BindingAdapter({"bind:circle_image_url"})
    public static void loadCircleImage(ImageView imageView, String imageUrl) {
        if(!imageUrl.isEmpty())
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.empty)
                .transform(new PicassoCircleTransformation())
                .into(imageView);
    }

    @BindingAdapter({"bind:image_url"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        if(!imageUrl.isEmpty())
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.empty)
                .into(imageView);
    }

    @BindingAdapter({"bind:image_url_round_corner", "bind:image_url_radius", "bind:image_url_margin"})
    public static void loadImageUrlRoundCorner(ImageView imageView, String imageUrl, int radius, int margin) {
        if(!imageUrl.isEmpty())
            Picasso.with(imageView.getContext())
                    .load(imageUrl)
                    .transform(new RoundedCornersTransformation(radius,margin))
                    .placeholder(R.drawable.empty)
                    .into(imageView);
    }

    @BindingAdapter("bind:src_compat_image")
    public static void bindSrcCompat(ImageView imageView, int drawable) {
        if(drawable != 0)
        imageView.setImageResource(drawable);
    }

    @BindingAdapter("bind:circle_src_compat_image")
    public static void setCircleImageResource(ImageView imageView, int drawable) {
        if(drawable != 0)
        imageView.setImageResource(drawable);
        Picasso.with(imageView.getContext())
                .load("")
                .transform(new PicassoCircleTransformation())
                .placeholder(R.drawable.empty)
                .into(imageView);
    }

    @BindingAdapter("bind:font")
    public static void setTypeFace(View view, String font_name) {
        TextView textView = (TextView) view;
        if (TextUtils.isEmpty(font_name)) {
            textView.setTypeface(TypeFaceProvider.getTypeFace());
        } else {
            if (font_name.equals(Helper.AR))
                textView.setTypeface(TypeFaceProvider.getArTypeface());
            else
                textView.setTypeface(TypeFaceProvider.getEnTypeface());
        }
    }

    @BindingAdapter("bind:font_textInputLayout")
    public static void setTypeFace(TextInputLayout textInputLayout, String font_name) {
        textInputLayout.setTypeface(TypeFaceProvider.getTypeFace());
    }

    @BindingAdapter("bind:rating")
    public static void setRate(RatingBar ratingBar, float val) {
        ratingBar.setRating(val);
    }

    @BindingAdapter("bind:hide_password")
    public static void changeInputType(EditText editText, boolean hide) {
        if (hide)
            editText.setTransformationMethod(new PasswordTransformationMethod());
        else
            editText.setTransformationMethod(null);
    }

    @BindingAdapter("bind:spinner_adapter")
    public static void setSpinnerAdapter(Spinner view, ArrayAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("bind:set_spinner_selection")
    public static void setSpinnerSelection(Spinner view, int pos) {
        view.setSelection(pos, true);
    }



}
