package com.jjudykim.todoprojectbyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // TODO #####
    // 1. https://aries574.tistory.com/152 참조해서 EditText 테마 만들어 수정하기
    // 2. 로그인 화면 레이아웃 재구성

    EditText et_id;
    EditText et_password;
    TextView btn_signUp;
    TextView btn_findId;
    Button btn_login;
    Button btn_socialLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.id_ET);
        et_password = findViewById(R.id.password_ET);
        btn_signUp = findViewById(R.id.signUp_Btn);
        btn_findId = findViewById(R.id.findID_Btn);
        btn_login = findViewById(R.id.login_Btn);


        // 로그인 버튼 상호작용
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_id.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요", Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(et_password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
                } else {
                    if (et_id.getText().toString().equals("aaa") && et_password.getText().toString().equals("aaa")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "회원 정보가 틀립니다\n아이디 및 비밀번호를 확인하세요", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        // 회원가입 상호작용
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });


        // 아이디 찾기 상호작용
        btn_findId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindIDActivity.class);
                startActivity(intent);
            }
        });
    }
}