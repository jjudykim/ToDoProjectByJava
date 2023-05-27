package com.jjudykim.todoprojectbyjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    // ColorSet
    int color_warningRed;
    int color_checkedGreen;

    // TextView
    TextView tv_id_warning;
    TextView tv_passwordCheck_warning;

    // EditText
    EditText et_id;
    EditText et_password;
    EditText et_passwordCheck;

    // ImageView
    ImageView iv_passwordCheck;

    // Button
    ImageButton btn_back;
    Button btn_idCheck;

    String userID;
    String userPassword;


    boolean isIdCheckDone = false;
    boolean isPasswordCheckDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        color_warningRed = ContextCompat.getColor(this, R.color.warningRed);
        color_checkedGreen = ContextCompat.getColor(this, R.color.checkedGreen);

        tv_id_warning = findViewById(R.id.signUp_id_warning_TV);
        tv_passwordCheck_warning = findViewById(R.id.signUp_passwordCheck_warning_TV);

        et_id = findViewById(R.id.signUp_id_ET);
        et_password = findViewById(R.id.signUp_password_ET);
        et_passwordCheck = findViewById(R.id.signUp_passwordCheck_ET);

        btn_back = findViewById(R.id.signUp_back_Btn);
        btn_idCheck = findViewById(R.id.signUp_idCheck_Btn);

        iv_passwordCheck = findViewById(R.id.signUp_passwordCheck_IV);

        // 아이디 중복 체크 클릭
        btn_idCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_id.getText().toString())) {
                    et_id.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.warningRed));
                    tv_id_warning.setTextColor(color_warningRed);
                    tv_id_warning.setText("사용할 아이디를 입력해주세요");
                } else if(et_id.getText().toString().length() < 5) {
                    et_id.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.warningRed));
                    tv_id_warning.setTextColor(color_warningRed);
                    tv_id_warning.setText("아이디는 최소 5자 이상이어야 합니다.");
                } else {
                    isIdCheckDone = true;
                    et_id.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.checkedGreen));
                    userID = et_id.getText().toString();
                    et_id.setText(userID);
                    et_id.setEnabled(false);
                    tv_id_warning.setTextColor(color_checkedGreen);
                    tv_id_warning.setText("사용 가능한 아이디입니다.");
                }
            }
        });


        // Password 입력 종료시 임시적으로 값 저장...
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isPasswordCheckDone = false;
                userPassword = et_password.getText().toString();
                if (et_passwordCheck.getText().toString().equals(userPassword)) samePasswordWritten();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // PasswordCheck 입력시마다 Password와 입력한 값과 같은지 체크
        et_passwordCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_passwordCheck.getText().toString().equals(userPassword)) {
                    samePasswordWritten();
                } else {
                    isPasswordCheckDone = false;
                    tv_passwordCheck_warning.setTextColor(color_warningRed);
                    tv_passwordCheck_warning.setText("비밀번호가 틀립니다.");
                    et_passwordCheck.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.warningRed));
                    iv_passwordCheck.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void samePasswordWritten() {
        tv_passwordCheck_warning.setTextColor(color_checkedGreen);
        tv_passwordCheck_warning.setText("비밀번호가 일치합니다.");
        et_passwordCheck.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.checkedGreen));
        iv_passwordCheck.setVisibility(View.VISIBLE);
        isPasswordCheckDone = true;
    }
}