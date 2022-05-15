package com.tahababar.csfeature;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.tahababar.csfeature.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityHomeBinding binding;
    public static final int FROM_DONATE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textBirthLocation.setOnClickListener(this);
        binding.textCurrentLocation.setOnClickListener(this);
        binding.buttonDonate.setOnClickListener(this);
        binding.buttonBiography.setOnClickListener(this);
        binding.buttonMoreInfo.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.text_birth_location:{
                Intent birthIntent =  new Intent();
                birthIntent.setAction(Intent.ACTION_VIEW);
                birthIntent.setData(Uri.parse(getResources().getString(R.string.uri_birth_location)));
                if (birthIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(birthIntent);
                }
                break;
            }
            case R.id.text_current_location:{
                Intent currentIntent =  new Intent();
                currentIntent.setAction(Intent.ACTION_VIEW);
                currentIntent.setData(Uri.parse(getResources().getString(R.string.uri_current_location)));
                if (currentIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(currentIntent);
                }
                break;
            }
            case R.id.button_more_info:{
                Intent infoIntent =  new Intent();
                infoIntent.setAction(Intent.ACTION_VIEW);
                infoIntent.setData(Uri.parse(getResources().getString(R.string.uri_wikipedia)));
                if (infoIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(infoIntent);
                }
                break;
            }
            case R.id.button_donate:{
                Intent donateIntent = new Intent(this, DonateActivity.class);
                startActivityForResult(donateIntent, FROM_DONATE_ACTIVITY);
                break;
            }
            case R.id.button_biography:{
                Intent biographyIntent = new Intent(this, BiographyActivity.class);
                startActivity(biographyIntent);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, @Nullable Intent data){
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == FROM_DONATE_ACTIVITY && responseCode == Activity.RESULT_OK){
            boolean receiptGenerator = data.getBooleanExtra(DonateActivity.EXTRA_RECEIVE_RECEIPT, false);
            if (receiptGenerator) {
                String fullName = data.getStringExtra(DonateActivity.EXTRA_FULL_NAME);
                String phone = data.getStringExtra(DonateActivity.EXTRA_PHONE);
                String creditCard = data.getStringExtra(DonateActivity.EXTRA_CREDIT_CARD);
                int cvc = data.getIntExtra(DonateActivity.EXTRA_CVC, 0);
                float amount = data.getFloatExtra(DonateActivity.EXTRA_AMOUNT, 0f);
                Intent messageIntent = new Intent();
                messageIntent.setAction(Intent.ACTION_SENDTO);
                messageIntent.setData(Uri.parse("sms:" + phone));
                messageIntent.putExtra("sms_body", "Thank you " + fullName + " for" +
                        " your donation of $ " + String.valueOf(amount) + " using card number" +
                        " ending in " + creditCard.substring(15, 19));
                if (messageIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(messageIntent);
                }
            }
        }
    }
}