package com.api.tarefas.apitarefa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tarefas.apitarefa.models.TarefasModel;

public interface TarefasRepository extends JpaRepository<TarefasModel, UUID> {

}
