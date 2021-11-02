package com.example.apptcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    //declaração das variáveis
    EditText loginEmail;
    EditText loginSenha;
    TextView mudarParaCadastro;
    TextView resetSenha;
    Button entrarBtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //definição das variáveis
        loginEmail = (EditText)findViewById((R.id.editTextEmailLogin));
        loginSenha = (EditText)findViewById((R.id.editTextPassswordLogin));
        mudarParaCadastro = (TextView)findViewById(R.id.mudarParaCadastro);
        resetSenha = (TextView)findViewById(R.id.resetSenha);
        entrarBtn = (Button)findViewById(R.id.entrarBtn);

        mAuth = FirebaseAuth.getInstance();

        entrarBtn.setOnClickListener(view -> {
            loginUser();
        });

        mudarParaCadastro.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Register.class));
        });

        resetSenha.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, ForgotPassword.class));
        });
    }

    private void loginUser() {
        String email = loginEmail.getText().toString();
        String senha = loginSenha.getText().toString();

        //verifica se os campos email e senha foram preenchidos
        if(TextUtils.isEmpty(email)) {
            loginEmail.setError(("O campo email não pode estar vazio"));
            loginEmail.requestFocus();
        } else if (TextUtils.isEmpty(senha)) {
            loginSenha.setError("O campo senha não pode estar vazio");
            loginSenha.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    } else {
                        Toast.makeText(Login.this, "Login inválido", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}