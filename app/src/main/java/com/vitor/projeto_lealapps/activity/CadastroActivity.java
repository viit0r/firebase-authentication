package com.vitor.projeto_lealapps.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vitor.projeto_lealapps.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText email, senha;
    private Button cadastrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        email = findViewById(R.id.inputEmailCadastro);
        senha = findViewById(R.id.inputSenhaCadastro);
        cadastrar = findViewById(R.id.btnCriarConta);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarUsuario();
            }
        });
    }

    public void criarUsuario(){
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Erro ao criar usuário: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}