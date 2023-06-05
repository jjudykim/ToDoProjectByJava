package com.jjudykim.todoprojectbyjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class SignUpActivity extends AppCompatActivity {

    // DB
    private DatabaseReference mDatabase;

    // ColorSet
    int color_warningRed;
    int color_checkedGreen;

    // TextView
    TextView tv_id_warning;
    TextView tv_passwordCheck_warning;
    TextView tv_nickName_warning;

    // EditText
    EditText et_id;
    EditText et_password;
    EditText et_passwordCheck;
    EditText et_email;
    EditText et_nickName;
    EditText et_team;

    // ImageView
    ImageView iv_passwordCheck;

    // Button
    ImageButton btn_back;
    Button btn_idCheck;
    Button btn_teamCheck;
    Button btn_emailCheck;
    Button btn_signUp;

    // Spinner
    Spinner emailSpinner;
    String[] emailItems;

    // CheckBox
    CheckBox cb_team;

    String userID;
    String userPassword;
    String userNickname;
    String userEmail;
    String userTeam;
    String emailDomain;


    boolean isIdChecked = false;
    boolean isPasswordChecked = false;
    boolean isEmailChecked = false;
    boolean isNicknameChecked = false;
    boolean isTeamChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        color_warningRed = ContextCompat.getColor(this, R.color.warningRed);
        color_checkedGreen = ContextCompat.getColor(this, R.color.checkedGreen);

        tv_id_warning = findViewById(R.id.signUp_id_warning_TV);
        tv_passwordCheck_warning = findViewById(R.id.signUp_passwordCheck_warning_TV);
        tv_nickName_warning = findViewById(R.id.signUp_nickName_warning_TV);

        et_id = findViewById(R.id.signUp_id_ET);
        et_password = findViewById(R.id.signUp_password_ET);
        et_passwordCheck = findViewById(R.id.signUp_passwordCheck_ET);
        et_email = findViewById(R.id.signUp_email_ET);
        et_nickName = findViewById(R.id.signUp_nickName_ET);
        et_team = findViewById(R.id.signUp_team_ET);

        btn_back = findViewById(R.id.signUp_back_Btn);
        btn_idCheck = findViewById(R.id.signUp_idCheck_Btn);
        btn_teamCheck = findViewById(R.id.signUp_team_Btn);
        btn_emailCheck = findViewById(R.id.signUp_emailCheck_Btn);
        btn_signUp = findViewById(R.id.signUp_userSignUp_Btn);

        emailSpinner = findViewById(R.id.signUp_email_Spinner);
        emailItems = getResources().getStringArray(R.array.email_array);

        cb_team = findViewById(R.id.signUp_team_CB);

        iv_passwordCheck = findViewById(R.id.signUp_passwordCheck_IV);

        mDatabase = FirebaseDatabase.getInstance().getReference();


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
                    isIdChecked = true;
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
                isPasswordChecked = false;
                userPassword = et_password.getText().toString();
                if (et_passwordCheck.getText().toString().equals(userPassword)) samePasswordWritten();
                else wrongPasswordWritten();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // PasswordCheck 입력 시마다 Password와 입력한 값과 같은지 체크
        et_passwordCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_passwordCheck.getText().toString().equals(userPassword)) {
                    samePasswordWritten();
                } else {
                    isPasswordChecked = false;
                    wrongPasswordWritten();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // nickname을 입력 시에 글자 수 체크 (2자 이상이면 통과)
        et_nickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_nickName.getText().toString().length() < 2) {
                    tv_nickName_warning.setTextColor(color_warningRed);
                    tv_nickName_warning.setText("닉네임은 최소 2자 이상이어야 합니다.");
                    et_nickName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.warningRed));
                } else {
                    tv_nickName_warning.setTextColor(color_checkedGreen);
                    tv_nickName_warning.setText("사용 가능한 닉네임입니다.");
                    et_nickName.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.checkedGreen));
                    isNicknameChecked = true;
                    userNickname = et_nickName.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // ArrayAdapter 생성 및 스피너 연결
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emailItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emailSpinner.setAdapter(adapter);

        emailSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emailDomain = emailItems[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                emailDomain = "";
            }
        });

        // TODO
        // 1. 스피너 높이 조절 시도해보기 (참조 : https://stackoverflow.com/questions/21426038/how-do-i-set-the-maximum-length-for-a-spinners-drop-down-list?noredirect=1&lq=1)
        // 2. 직접 입력 구현
        // 3. 코드 깔끔하게 정리하기 (함수, 기능별로)


        btn_emailCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = et_email.getText().toString() + "@" + emailDomain;
                isEmailChecked = true;
                System.out.println("이메일 체크 완료 : " + userEmail);
            }
        });

        cb_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb_team.isChecked()) {
                    et_team.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                    et_team.setEnabled(false);
                    btn_teamCheck.setEnabled(false);
                    userTeam = "";
                    isTeamChecked = true;
                } else {
                    et_team.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.boldGray));
                    et_team.setEnabled(true);
                    btn_teamCheck.setEnabled(true);
                    isTeamChecked = false;
                }
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isIdChecked) {

                }else if(!isPasswordChecked) {

                }else if(!isEmailChecked) {

                }else if(!isNicknameChecked) {

                }else if(!isTeamChecked) {

                } else {
                    writeNewUser(userID, userPassword, userNickname, userEmail, userTeam);
                    finish();
                }
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
        isPasswordChecked = true;
    }

    protected void wrongPasswordWritten() {
        tv_passwordCheck_warning.setTextColor(color_warningRed);
        tv_passwordCheck_warning.setText("비밀번호가 틀립니다.");
        et_passwordCheck.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.warningRed));
        iv_passwordCheck.setVisibility(View.INVISIBLE);
    }

    private void writeNewUser(String id, String password, String nickname, String email, String team) {
        User user = new User(id, password, nickname, email, team);
        System.out.println(user);
        mDatabase.child("users").child(id).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(getApplicationContext(), "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NotNull Exception e) {
                        // Write failed
                        Toast.makeText(getApplicationContext(), "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}