package com.example.mbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mbanking.entities.User;
import com.example.mbanking.helper.SqliteHelper;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        final EditText editTextName = findViewById(R.id.editTextName);
        final EditText editTextEmail = findViewById(R.id.editTextEmail);
        final EditText editTextPassword = findViewById(R.id.editTextPassword);
        final EditText editTextCPassword = findViewById(R.id.editTextCPassword);
        final EditText editTextPhone = findViewById(R.id.editTextPhoneNumber);
        final Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Integer.parseInt(editTextDepositAmount.getText().toString());
                String name = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();
                String cPassword = editTextCPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                int cek =0;

                if(name.isEmpty() && name.length() < 8){
                    cek++;
                }else if(password.isEmpty() && password.length() < 8 && password != cPassword){
                    cek++;
                }else if(sqliteHelper.isEmailExists(email)){
                    cek++;
                }

                if (!sqliteHelper.isEmailExists(email) && cPassword == password && !name.isEmpty() && !password.isEmpty() && password.length() > 8) {
                    sqliteHelper.addUser(new User(name, password, phone, email));
                }
                Snackbar.make(buttonRegister, "User has been successfully registered", Snackbar.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, Snackbar.LENGTH_LONG);
            }
        });

    }
}
