package com.example.exemploactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exemploactivity.modelo.Disciplina;
import com.example.exemploactivity.modelo.Professor;

import java.util.ArrayList;

public class CadastroDisciplina extends AppCompatActivity {

    private Button btSalvarD;
    private EditText edDescricaoDisc;
    private EditText edCargaHoraria;
    private TextView tvListaDisciplina;
    private TextView tvErroProfessor;
    private Spinner spProfessor;
    private ArrayList<Professor> professores;
    private int posicaoSelecionada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        setTitle("Cadastro Disciplina");

        edDescricaoDisc = findViewById(R.id.edDescricaoDisc);
        edCargaHoraria = findViewById(R.id.edCargaHoraria);
        tvListaDisciplina = findViewById(R.id.tvListaDisciplina);
        tvErroProfessor = findViewById(R.id.tvErroProfessor);

        btSalvarD = findViewById(R.id.btSalvarD);
        btSalvarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDisciplina();
            }
        });

        spProfessor = findViewById(R.id.spProfessor);
        spProfessor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int posicao, long l) {
                if(posicao > 0){
                    posicaoSelecionada = posicao;
                    tvErroProfessor.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        popularListaProfessores();
        atualizarDisciplina();
    }

    private void popularListaProfessores(){

        professores = Controller.getInstancia().retornarProfessores();
        String[]vetorProfs = new String[professores.size()+1];
        vetorProfs[0] = "Selecione o professor";

        for (int i = 0; i < professores.size(); i++) {
            Professor prof = professores.get(i);
            vetorProfs[i+1] = prof.getMatricula() + " - " + prof.getNome();
        }

        ArrayAdapter adapter= new ArrayAdapter(CadastroDisciplina.this,
                android.R.layout.simple_dropdown_item_1line, vetorProfs);

        spProfessor.setAdapter(adapter);
    }

    private void salvarDisciplina(){

        double cargaHoraria = 0;

        if(edDescricaoDisc.getText().toString().isEmpty()){
            edDescricaoDisc.setError("A DISCIPLINA deve ser informada!");
            edDescricaoDisc.requestFocus();
            return;
        }

        if (edCargaHoraria.getText().toString().isEmpty()){
            edCargaHoraria.setError("A CARGA HORÁRIA deve ser informada!");
            edCargaHoraria.requestFocus();
            return;
        }else{
            cargaHoraria = Double.parseDouble(edCargaHoraria.getText().toString());

            if(cargaHoraria <= 0) {
                edCargaHoraria.setError("Informe uma CARGA HORÁRIA valida" +
                        "(somente números e maior que zero)!");
                edCargaHoraria.requestFocus();
                return;
            }
        }

        if(posicaoSelecionada <= 0){
            tvErroProfessor.setVisibility(View.VISIBLE);
            return;
        }

        Disciplina disciplina = new Disciplina();

        disciplina.setDescricao(edDescricaoDisc.getText().toString());
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setProfessor(professores.get(posicaoSelecionada-1));

        Controller.getInstancia().salvarDisciplina(disciplina);
        Toast.makeText(CadastroDisciplina.this,
                "Disciplina cadastrada com sucesso", Toast.LENGTH_LONG).show();

        finish();
    }

    public void atualizarDisciplina(){

        String texto = " ";

        ArrayList<Disciplina> lista = Controller.getInstancia().retornarDisciplinas();

        for(Disciplina disciplina: lista){
            Professor prof = disciplina.getProfessor();
            texto += disciplina.getDescricao() + " - " + disciplina.getCargaHoraria() +
                    " Horas \n" + prof.getNome() + "\n------------------------------------" +
                    "---------------------------------------------\n";
        }

        tvListaDisciplina.setText(texto);
    }
}