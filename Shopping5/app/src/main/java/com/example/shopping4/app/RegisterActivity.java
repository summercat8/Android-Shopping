package com.example.shopping4.app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shopping4.R;
import com.example.shopping4.database.MyHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText etPhone,etPassword;
    private Button btnRegister;
    private TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 初始化控件
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // 关闭当前 Activity 并返回到登录页面
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();
                MyHelper helper = MyHelper.getmInstance(RegisterActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();
                if(db.isOpen()){
                    String sql = "insert into user(phone,password) values(?,?)";
                   db.execSQL(sql,new Object[]{phone,password});
                    Log.e("register success!",phone);
                }
                db.close();
                finish();
            }
        });
    }
}