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
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    //declaração das variáveis
    EditText usuarioEmail;
    EditText usuarioSenha;
    EditText confirmarSenha;
    TextView mudarParaLogin;
    Button registrarBtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //definição das variáveis
        usuarioEmail = (EditText)findViewById((R.id.editTextEmailLogin));
        usuarioSenha = (EditText)findViewById((R.id.editTextPassswordLogin));
        confirmarSenha = (EditText)findViewById((R.id.editTextConfirmPassword));
        mudarParaLogin = (TextView)findViewById(R.id.mudarParaLogin);
        registrarBtn = (Button)findViewById(R.id.registrarBtn);

        mAuth = FirebaseAuth.getInstance();

        //botão de registro
        registrarBtn.setOnClickListener(view -> {
            createUser();
        });

        //muda para a página de login
        mudarParaLogin.setOnClickListener(view -> {
            startActivity(new Intent(Register.this, Login.class));
        });
    }

    private void createUser(){
        String email = usuarioEmail.getText().toString();
        String senha = usuarioSenha.getText().toString();

        //verifica se os campos email e senha foram preenchidos
        if(TextUtils.isEmpty(email)) {
            usuarioEmail.setError(("O campo email não pode estar vazio"));
            usuarioEmail.requestFocus();
        } else if (TextUtils.isEmpty(senha)) {
            usuarioSenha.setError("O campo senha não pode estar vazio");
            usuarioSenha.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Cadastro efetuado com sucesso
                                Toast.makeText(Register.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(Register.this, Login.class));

                            } else {
                                //Erro ao efetuar o cadsatro
                                Toast.makeText(Register.this, "O cadastro falhou: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }
}