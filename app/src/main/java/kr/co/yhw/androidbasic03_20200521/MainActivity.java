package kr.co.yhw.androidbasic03_20200521;

import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import kr.co.yhw.androidbasic03_20200521.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    final int REQ_FOR_EMAIL =1003;
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
        binding.storeLinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                플레이스토어에서 원하는 앱의 링크 주소를 deatails?부터 복사
//                =>웹상에서 찾아보면 검색 가능함
                Uri myUri = Uri.parse("market://details?id=com.kakao.talk");
                Intent myIntent = new Intent(Intent.ACTION_VIEW,myUri);
                startActivity(myIntent);
            }
        });
        binding.webLinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naverLinkUrl ="http://naver.com";

                Uri myUri = Uri.parse(naverLinkUrl);
                Intent myIntent = new Intent(Intent.ACTION_VIEW,myUri);
                startActivity(myIntent);
            }
        });
        binding.smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                문자 전송하기
                String dialPhoneNum = binding.userPhoneNum.getText().toString();

                Uri myUri = Uri.parse(String.format("smsto:%s",dialPhoneNum));
                Intent myIntent = new Intent(Intent.ACTION_SENDTO,myUri);
                myIntent.putExtra("sms_body","테스트 문자내용");
                startActivity(myIntent);
            }
        });
        binding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //       전화 바로걸기=> 권한 문제로 앱이 죽어버림
                String dialPhoneNum = binding.userPhoneNum.getText().toString();

                Uri myUri = Uri.parse(String.format("tel:%s",dialPhoneNum));
                Intent myIntent = new Intent(Intent.ACTION_CALL,myUri);
                startActivity(myIntent);
            }
        });

        binding.dialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                전화걸기  안드로이드 =Uri정보 요구
                String dialPhoneNum = binding.userPhoneNum.getText().toString();

                Uri myUri = Uri.parse(String.format("tel:%s",dialPhoneNum));
                Intent myIntent = new Intent(Intent.ACTION_DIAL,myUri);
                startActivity(myIntent);
            }
        });

        binding.EdtPhoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext,EditUserPhoneNumActivity.class);
                startActivityForResult(myIntent, REQ_FOR_PHONE_NUM);

            }
        });
        binding.EdtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext,EditUserEmailActivity.class);
                startActivityForResult(myIntent, REQ_FOR_EMAIL);
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
                if(data!= null) {
                    String nick = data.getStringExtra("editedNickName");

                    binding.userNickName.setText(nick);
                    Toast.makeText(mContext, "닉네임 변경 완료", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(mContext, "닉네임변경을 취소하였습니다", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == REQ_FOR_PHONE_NUM){
            if (resultCode == RESULT_OK){
                if(data!= null){
                    String phone = data.getStringExtra("phone") ;
                    binding.userPhoneNum.setText(phone);
                    Toast.makeText(mContext, "전화번호 변경 완료", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(mContext, "전화번호 변경을 취소 했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == REQ_FOR_EMAIL) {
            if (resultCode == RESULT_OK){
                if ( data != null){
                    String email = data.getStringExtra("email");
                    binding.userEmail.setText(email);
                    Toast.makeText(mContext, "이메일 변경 완료", Toast.LENGTH_SHORT).show();
                }
            }
            else  {
                Toast.makeText(mContext, "이메일 변경을 취소 했습니다.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
