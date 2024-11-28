package com.example.ex_three;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");
        builder.setView(R.layout.activity_main2);
        final AlertDialog dialog = builder.create();
        dialog.show();

        Button cancelButton = dialog.findViewById(R.id.cancelButton);
        Button signInButton = dialog.findViewById(R.id.signInButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEditText = dialog.findViewById(R.id.usernameEditText);
                EditText passwordEditText = dialog.findViewById(R.id.passwordEditText);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (username.equals("admin") && password.equals("123456")) {
                    Toast.makeText(MainActivity2.this, "登录成功", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity2.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}