package com.example.shopping4.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping4.R;
import com.example.shopping4.WelcomeActivity;
import com.example.shopping4.database.MyHelper;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText etPhone, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();
                MyHelper helper = MyHelper.getmInstance(MainActivity.this);
                SQLiteDatabase db = helper.getReadableDatabase();
                if(db.isOpen()){
                    Cursor cursor = db.rawQuery("select * from user",null);
                    while(cursor.moveToNext()){
                        @SuppressLint("Rang") String phones = ((Cursor)cursor).getString(cursor.getColumnIndex("phone"));
                        @SuppressLint("Rang") String passwords = ((Cursor)cursor).getString(cursor.getColumnIndex("password"));
                        if(Objects.equals(phone,phones)&&Objects.equals(password,passwords)){
                            Log.e("land success...",phone);
                            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                        }
                    }
                    cursor.close();
                    db.close();
                }else {
                    Toast.makeText(MainActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }

}