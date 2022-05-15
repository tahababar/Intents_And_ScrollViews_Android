package com.tahababar.csfeature;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.tahababar.csfeature.databinding.ActivityDonateBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonateActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDonateBinding binding;
    public static final String EXTRA_FULL_NAME = "com.tahababar.csfeature.EXTRA_FULL_NAME";
    public static final String EXTRA_PHONE = "com.tahababar.csfeature.EXTRA_PHONE";
    public static final String EXTRA_CREDIT_CARD = "com.tahababar.csfeature.EXTRA_CREDIT_CARD";
    public static final String EXTRA_CVC = "com.tahababar.csfeature.EXTRA_CVC";
    public static final String EXTRA_AMOUNT = "com.tahababar.csfeature.EXTRA_AMOUNT";
    public static final String EXTRA_RECEIVE_RECEIPT = "com.tahababar.csfeature.EXTRA_RECEIVE_RECEIPT ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonDonateMain.setOnClickListener(this);
        Intent resultIntent = getIntent();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_donate_main:{
                if(!TextUtils.isEmpty(binding.editTextFullName.getText().toString()) &&
                        !TextUtils.isEmpty(binding.editTextPhone.getText().toString()) &&
                        !TextUtils.isEmpty(binding.editTextCreditCard.getText().toString()) &&
                        !TextUtils.isEmpty(binding.editTextCvc.getText().toString()) &&
                        !TextUtils.isEmpty(binding.editTextAmount.getText().toString())) {
                    String creditCard = binding.editTextCreditCard.getText().toString();
                    int ct = 0;
                    for (int i = 0; i < creditCard.length(); i++) {
                        if (Character.isDigit(creditCard.charAt(i)))
                            ct++;
                    }

                    if(binding.editTextPhone.length() != 8){
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
                        myBuilder.setTitle(getResources().getString(R.string.dialog_phone_title))
                                .setMessage(getResources().getString(R.string.dialog_phone_message))
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                        break;
                    }

                    if(binding.editTextCvc.length() != 3){
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
                        myBuilder.setTitle(getResources().getString(R.string.dialog_cvc_title))
                                .setMessage(getResources().getString(R.string.dialog_cvc_message))
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                        break;
                    }

                    Pattern pattern = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
                    Matcher matcher = pattern.matcher(creditCard);
                    boolean found = matcher.find();
                    if (ct == 16 && found) {
                        Intent returnIntent = new Intent();
                        String fullName = binding.editTextFullName.getText().toString();
                        String phone = binding.editTextPhone.getText().toString();
                        int cvc = Integer.valueOf(binding.editTextCvc.getText().toString());
                        float amount = Float.valueOf(binding.editTextAmount.getText().toString());
                        boolean receiveReceipt = false;
                        if (binding.switchReceiveReceipt.isChecked()) {
                            receiveReceipt = true;
                        }
                        returnIntent.putExtra(EXTRA_FULL_NAME, fullName);
                        returnIntent.putExtra(EXTRA_PHONE, phone);
                        returnIntent.putExtra(EXTRA_CREDIT_CARD, creditCard);
                        returnIntent.putExtra(EXTRA_CVC, cvc);
                        returnIntent.putExtra(EXTRA_AMOUNT, amount);
                        returnIntent.putExtra(EXTRA_RECEIVE_RECEIPT, receiveReceipt);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    } else {
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
                        myBuilder.setTitle(getResources().getString(R.string.dialog_title))
                                .setMessage(getResources().getString(R.string.dialog_message))
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }
                    break;
                }
                else{
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
                    myBuilder.setTitle(getResources().getString(R.string.dialog_error_title))
                            .setMessage(getResources().getString(R.string.dialog_error_message))
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });

                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();
                }
            }
        }
    }
}