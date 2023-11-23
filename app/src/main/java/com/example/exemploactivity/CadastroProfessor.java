package com.example.exemploactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exemploactivity.modelo.Professor;

import java.util.ArrayList;

public class CadastroProfessor extends AppCompatActivity {

    private Button btSalvarP;
    private EditText edMatriculaProfessor;
    private EditText edNomeProfessor;
    private EditText edDataNascProfessor;
    private EditText edCpfProfessor;
    private EditText edDataAdmissao;
    private TextView tvListaProfessores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        setTitle("Cadastro Professor");

        edMatriculaProfessor = findViewById(R.id.edMatriculaProfessor);
        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edDataNascProfessor = findViewById(R.id.edDataNascProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
        edDataAdmissao = findViewById(R.id.edDataAdmissao);
        tvListaProfessores = findViewById(R.id.tvListaProfessor);
        btSalvarP = findViewById(R.id.btSalvarP);
        btSalvarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salvarProfessor();
            }
        });

        atualizarProfessor();
    }

    private void salvarProfessor() {

        int matricula;

        if (edMatriculaProfessor.getText().toString().isEmpty()){
            edMatriculaProfessor.setError("A MATRÍCULA do professor deve ser informada!");
            return;
        }else {
            try {
                matricula = Integer.parseInt(edMatriculaProfessor.getText().toString());
            }catch (Exception ex){
                edMatriculaProfessor.setError("Informe uma MATRÍCULA válida(somente números!)");
                return;
            }
        }

        if (edNomeProfessor.getText().toString().isEmpty()){
            edNomeProfessor.setError("O NOME do professor deve ser informado!");
            return;
        }

        if (edDataNascProfessor.getText().toString().isEmpty()){
            edDataNascProfessor.setError("A DATA DE NASCIMENTO do professor deve ser informada!");
            return;
        }

        if(edCpfProfessor.getText().toString().isEmpty()){
            edCpfProfessor.setError("O CPF do professor deve ser informado!");
            return;
        }

        if (edDataAdmissao.getText().toString().isEmpty()){
            edDataAdmissao.setError("A DATA DE ADMISSÃO deve ser informada!");
            return;
        }

        Professor professor = new Professor();

        professor.setMatricula(matricula);
        professor.setNome(edNomeProfessor.getText().toString());
        professor.setDataNasc(edDataNascProfessor.getText().toString());
        professor.setCpf(edCpfProfessor.getText().toString());
        professor.setDataAdmissao(edDataAdmissao.getText().toString());

        Controller.getInstancia().salvarProfessor(professor);
        Toast.makeText(CadastroProfessor.this,
                "Professor cadastrado com sucesso!", Toast.LENGTH_LONG).show();

        finish();
    }

    private void atualizarProfessor(){

        String texto = "";

        ArrayList<Professor> lista = Controller.getInstancia().retornarProfessores();

        for (Professor professor: lista){
            texto += professor.getMatricula() + " - " + professor.getNome() + " \n" +
                    professor. getDataNasc() + " - " + professor.getCpf() + " - " +
                    professor.getDataAdmissao() + "\n--------------------------" +
                    "-------------------------------------------------------\n";
        }

        tvListaProfessores.setText(texto);
    }
}