package com.example.shopping4.home;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopping4.R;
import com.example.shopping4.database.MyHelper;
/**
 * created by: xy
 * on: 2023/6/19
 * description:意见反馈
 */
public class FeedbackActivity extends AppCompatActivity {
    private EditText feedbackEditText;
    private EditText contactEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedbackEditText = findViewById(R.id.feedbackEditText);
        contactEditText = findViewById(R.id.contactEditText);
        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feeedback = feedbackEditText.getText().toString();
                String contact = contactEditText.getText().toString();
                MyHelper helper = MyHelper.getmInstance(FeedbackActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();
                if(db.isOpen()){
                    String sql = "insert into feedback(feedback,contact) values(?,?)";
                    db.execSQL(sql,new Object[]{feeedback,contact});
                    Log.e("submit successful!",contact);
                    Toast.makeText(FeedbackActivity.this, "提交意见成功！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(FeedbackActivity.this, "插入失败！", Toast.LENGTH_SHORT).show();
                }
                db.close();
                finish();
            }
        });
    }
}