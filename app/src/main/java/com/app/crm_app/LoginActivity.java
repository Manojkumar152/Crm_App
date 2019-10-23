package com.app.crm_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email, password;
    private Button login;
    private TextView resetPassword;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        email= findViewById(R.id.txtEmail);
        password=findViewById(R.id.txtPassword);
        login=findViewById(R.id.loginBtn);
        resetPassword=findViewById(R.id.reset);
        logo= findViewById(R.id.logo);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                 loginUser();
                 break;
        }
    }

    private void loginUser() {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
