package kr.co.yhw.androidbasic03_20200521;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.co.yhw.androidbasic03_20200521.databinding.ActivityEditUserPhoneNumBinding;

public class EditUserPhoneNumActivity extends BaseActivity {

    ActivityEditUserPhoneNumBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_user_phone_num);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        binding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPhoneNum = binding.EdtPhoneNum.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("phone",inputPhoneNum);

                setResult(RESULT_OK,intent);
                finish();
            }

        });
    }

    @Override
    public void setValues() {

    }
}
