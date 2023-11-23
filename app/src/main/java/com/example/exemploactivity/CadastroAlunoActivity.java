package com.example.exemploactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exemploactivity.modelo.Aluno;

import java.util.ArrayList;

public class CadastroAlunoActivity extends AppCompatActivity {

    private Button btSalvar;
    private EditText edRaAluno;
    private EditText edNomeAluno;
    private EditText edDataNascAluno;
    private EditText edCpfAluno;
    private TextView tvListaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        setTitle("Cadastro Aluno");

        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edDataNascAluno = findViewById(R.id.edDataNascAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        tvListaAlunos = findViewById(R.id.tvListaAluno);
        btSalvar = findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SalvarAluno();
            }
        });

        atualizarLista();
    }

    private void SalvarAluno() {

        int ra;

        if (edRaAluno.getText().toString().isEmpty()){
            edRaAluno.setError("O RA do aluno deve ser informado!");
            return;
        }else {
            try {
                ra = Integer.parseInt(edRaAluno.getText().toString());
            }catch (Exception ex){
                edRaAluno.setError("Informe um RA válido(somente números)!");
                return;
            }
        }

        if (edNomeAluno.getText().toString().isEmpty()){
            edNomeAluno.setError("O NOME do aluno deve ser informado!");
            return;
        }

        if (edDataNascAluno.getText().toString().isEmpty()){
            edDataNascAluno.setError("A DATA DE NASCIMENTO do aluno deve ser informada!");
            return;
        }

        if (edCpfAluno.getText().toString().isEmpty()){
            edCpfAluno.setError("O CPF do aluno deve ser informado!");
            return;
        }

        Aluno aluno = new Aluno();

        aluno.setRa(ra);
        aluno.setNome(edNomeAluno.getText().toString());
        aluno.setDataNasc(edDataNascAluno.getText().toString());
        aluno.setCpf(edCpfAluno.getText().toString());

        Controller.getInstancia().salvarAluno(aluno);
        Toast.makeText(CadastroAlunoActivity.this,
                "Aluno cadastrado com sucesso!", Toast.LENGTH_LONG).show();

        finish();
    }
    private void atualizarLista(){

        String texto = " ";

        ArrayList<Aluno> lista = Controller.getInstancia().retornarAlunos();

        for (Aluno aluno : lista) {
            texto += aluno.getRa() + " - " + aluno.getNome() + " \n " +
                    aluno.getDataNasc() + " - " + aluno.getCpf() +
                    "\n----------------------------------------" +
                    "-----------------------------------------\n";
        }

        tvListaAlunos.setText(texto);
    }
}