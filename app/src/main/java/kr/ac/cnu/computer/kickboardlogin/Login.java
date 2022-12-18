package kr.ac.cnu.computer.kickboardlogin;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Login extends AppCompatActivity {
    TextView sign;
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //회원가입 버튼
        sign = findViewById(R.id.signin);

        //회원가입 버튼 클릭시, 회원가입 페이지로 이동
        sign.setOnClickListener(v -> {
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        });
        Button loginbutton = findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login.this, Camera.class);
                startActivity(intent2);

            }
        });
    }
}