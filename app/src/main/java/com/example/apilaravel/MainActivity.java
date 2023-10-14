package com.example.apilaravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apilaravel.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void formulario(View view){
        Intent intent = null;
        intent = new Intent(this,FormularioActivity.class);
        if(intent!=null) {
            startActivity(intent);

        }
    }
}