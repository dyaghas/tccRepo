package com.example.apptcc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Pop extends MainActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_pop_window);

        //Popup ImageView
        ImageView popImage = (ImageView)findViewById(R.id.imageViewPop);
        TextView popText = (TextView)findViewById(R.id.popUpText);

        //Define o tamanho do popup na tela
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.4));

        Intent intent = getIntent();
        int pressedButton = intent.getIntExtra("pressedButton", 0);

        //define qual será a imagem e texto do popup com base no botão pressionado
        switch(pressedButton) {
            case 1:
                popImage.setImageResource(R.drawable.ic_alert);
                popText.setText(R.string.str_btn1);
                break;
            case 2:
                popImage.setImageResource(R.drawable.ic_danger);
                popText.setText(R.string.str_btn2);
                break;
            case 3:
                popImage.setImageResource(R.drawable.ic_question);
                popText.setText(R.string.str_btn3);
                break;
            case 4:
                popImage.setImageResource(R.drawable.ic_signal);
                popText.setText(R.string.str_btn4);
                break;
            default:
        }
    }

    public void finishActivity(View view) {
        finish();
    }

}
