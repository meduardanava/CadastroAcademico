package com.example.exemploactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exemploactivity.modelo.Aluno;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CadastroTurmasActivity extends AppCompatActivity {

    private AutoCompleteTextView tvAdicionarAlunos;
    private ArrayList<Aluno> listaAlunos;
    private RadioButton rbAnual;
    private RadioButton rbSemestral;
    private RadioGroup rgSistema;
    private Spinner spPeriodo;
    private  String[]vetAnual = new String[]{"Selecione o ano", "1º Ano", "2º Ano", "3ºAno"};
    private String[]vetSementral = new String[]{"Selecione o semestre", "1º Sementre", "2º Semestre",
            "3º Semestre", "4º Semestre", "5º Semestre", "6º Semestre"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turmas);
        
        setTitle("Cadastro Turmas");

        tvAdicionarAlunos = findViewById(R.id.tvAdicionarAlunos);
        rbAnual= findViewById(R.id.rbAnual);
        rbSemestral= findViewById(R.id.rbSemestral);
        rgSistema= findViewById(R.id.rgSistema);
        spPeriodo= findViewById(R.id.spPeriodo);

        rgSistema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                carregarPeriodo();
            }
        });

        carregarAlunos();
    }

    private void carregarAlunos(){

        listaAlunos = Controller.getInstancia().retornarAlunos();
        String[]vetAlunos = new String[listaAlunos.size()];

        for (int i = 0; i < listaAlunos.size(); i++){
            Aluno aluno = listaAlunos.get(i);
            vetAlunos[i] = aluno.getNome();
        }
        ArrayAdapter adapter = new ArrayAdapter(CadastroTurmasActivity.this,
                android.R.layout.simple_dropdown_item_1line,vetAlunos);

        tvAdicionarAlunos.setAdapter(adapter);
    }

    private void carregarPeriodo(){

        ArrayAdapter adapter = null;

        if (rbAnual.isChecked()){
            adapter = new ArrayAdapter<>(CadastroTurmasActivity.this,
                    android.R.layout.simple_dropdown_item_1line,
                    vetAnual);
        }
        if (rbSemestral.isChecked()) {
            adapter = new ArrayAdapter<>(CadastroTurmasActivity.this,
                    android.R.layout.simple_dropdown_item_1line,
                    vetSementral);
        }
        spPeriodo.setAdapter(adapter);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_padrao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.mnSalvar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}