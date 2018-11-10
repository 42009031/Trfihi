package com.master_vision.trfihi.edit_profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.master_vision.trfihi.R;
import com.master_vision.trfihi.common.methods.FetchPath;
import com.master_vision.trfihi.common.methods.Helper;
import com.master_vision.trfihi.databinding.ActivityEditProfileBinding;
import com.squareup.picasso.Picasso;


public class EditProfileActivity extends AppCompatActivity {

    private ActivityEditProfileBinding binding;
    private EditProfileViewModel e_prof_vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    private void bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        e_prof_vm = new EditProfileViewModel(this);
        binding.setProfVM(e_prof_vm);
    }

    public void onBackClick(View view) {
        view.startAnimation(Helper.BtnClickAnimation);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
// camera
        if (requestCode == Helper.MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, Helper.CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
// gallery
        else if (requestCode == Helper.MY_GALLERY_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Gallery permission granted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, Helper.GALLERY_REQUEST);
            } else {
                Toast.makeText(this, "Gallery permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // camera || gallery
        if ((requestCode == Helper.CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
                || (requestCode == Helper.GALLERY_REQUEST && resultCode == Activity.RESULT_OK)) {
            Uri photoUri = data.getData();
            if (photoUri != null) {
                e_prof_vm.captureCameraImgUri = FetchPath.getPath(this, photoUri);
            }
            e_prof_vm.profileImageUrl.set(String.valueOf(data.getData()));
        }
    }

}
