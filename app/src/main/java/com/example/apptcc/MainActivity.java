package com.example.apptcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableRow alertButton = (TableRow)findViewById( R.id.alertButton );
        TableRow emergencyButton = (TableRow)findViewById( R.id.emergencyButton );
        TableRow questionButton = (TableRow)findViewById( R.id.questionButton );
        TableRow signalButton = (TableRow)findViewById( R.id.signalButton );

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        //verifica se o usuário está logado e muda a activity de acordo.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    public void buttonPopUp(View view) {

        int pressedButton;

        //cria o intent do popup
        Intent intent = new Intent(MainActivity.this, Pop.class);

        //define o valor de pressedButton com base no botão apertado
        switch (view.getId()) {
            case R.id.alertButton:
                pressedButton = 1;
                break;
            case R.id.emergencyButton:
                pressedButton = 2;
                break;
            case R.id.questionButton:
                pressedButton = 3;
                break;
            case R.id.signalButton:
                pressedButton = 4;
                break;
            default:
                pressedButton = 0;
        }
        intent.putExtra("pressedButton", pressedButton);
        startActivity(intent);
    }

}