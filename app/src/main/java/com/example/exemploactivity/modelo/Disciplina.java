package com.example.exemploactivity.modelo;

public class Disciplina {

    private String Descricao;
    private double CargaHoraria;
    private Professor professor;

    public Disciplina() {
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public double getCargaHoraria() {
        return CargaHoraria;
    }

    public void setCargaHoraria(double cargaHoraria) {
        CargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
