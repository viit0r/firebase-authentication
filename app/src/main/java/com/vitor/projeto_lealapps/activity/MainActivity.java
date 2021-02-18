package com.vitor.projeto_lealapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vitor.projeto_lealapps.R;

public class MainActivity extends AppCompatActivity {

    private Button deslogar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deslogar = findViewById(R.id.btnDeslogar);
        deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        validarLogin();
    }

    public void validarLogin(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Toast.makeText(this, "Bem vindo " + user.getEmail() + "!", Toast.LENGTH_SHORT).show();
        } else{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}