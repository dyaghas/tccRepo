package com.example.apptcc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DeleteActivity extends AppCompatActivity {

    private Button btnDelete;
    private EditText confirmationText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        btnDelete = (Button)findViewById(R.id.btnDeleteAccount);

        confirmationText = (EditText)findViewById(R.id.editTextConfirmation);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        btnDelete.setOnClickListener(View -> {
            if(confirmationText.getText().toString().equals("excluir conta")) {
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DeleteActivity.this, "Conta excluída com sucesso", Toast.LENGTH_SHORT).show();
                                    if(user == null) {
                                        startActivity(new Intent(DeleteActivity.this, Login.class));
                                    }
                                }
                            }
                        });
            } else {
                Toast.makeText(DeleteActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void finishActivity(View view) {
        finish();
    }

}