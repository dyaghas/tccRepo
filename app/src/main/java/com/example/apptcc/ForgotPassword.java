package com.example.apptcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText editEmail;
    private Button btnResetPassword;
    private Button btnReturn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editEmail = (EditText)findViewById(R.id.editTextEmailLogin);
        btnResetPassword = (Button)findViewById(R.id.enviarBtn);
        btnReturn = (Button)findViewById(R.id.voltarBtn);

        mAuth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(View -> {

                String email = editEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Digite um e-mail válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "Sucesso", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgotPassword.this, "Erro ao enviar e-mail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            });

        btnReturn.setOnClickListener(View -> {
                finish();

        });

    }
}