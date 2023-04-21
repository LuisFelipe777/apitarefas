package com.api.tarefas.apitarefa.dtos;

import jakarta.validation.constraints.NotBlank;

public class TarefasDto {
    private String titulo;
    @NotBlank
    private String tarefa;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}
