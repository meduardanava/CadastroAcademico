package com.example.exemploactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btCadastroAluno;
    private Button btCadastroProfessor;
    private Button btCadastroDisciplina;
    private Button btCadastroTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Cadastro Acadêmico");

        setContentView(R.layout.activity_main);

        btCadastroAluno = findViewById(R.id.btCadastroAluno);
        btCadastroProfessor = findViewById(R.id.btCadastroProfessor);
        btCadastroDisciplina = findViewById(R.id.btCadastroDisciplina);
        btCadastroTurma = findViewById(R.id.btCadastroTurma);

        btCadastroAluno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                abrirActivity(CadastroAlunoActivity.class);
            }
        });

        btCadastroProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroProfessor.class);
            }
        });

        btCadastroDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroDisciplina.class);
            }
        });

        btCadastroTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroTurmasActivity.class);
            }
        });

    }

    private void abrirActivity(Class<?> activity){
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }

}