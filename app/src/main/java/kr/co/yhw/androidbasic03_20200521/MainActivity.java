package kr.co.yhw.androidbasic03_20200521;

import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import kr.co.yhw.androidbasic03_20200521.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;

    final int REQ_FOR_PHONE_NUM =1002;
    final int REQ_FOR_NICK_NAME =1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.EdtPhoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext,EditUserPhoneNumActivity.class);
                startActivityForResult(myIntent, REQ_FOR_PHONE_NUM);

            }
        });
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, EditUserNickNameActivity.class);
                startActivityForResult(myIntent, REQ_FOR_NICK_NAME);
            }
        };

        binding.EdtNickName.setOnClickListener(ocl);
    }

    @Override
    public void setValues() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_FOR_NICK_NAME){
            if (resultCode == RESULT_OK){

                String nick = data.getStringExtra("editedNickName");

                binding.userNickName.setText(nick);


            }
        }
    }
}
