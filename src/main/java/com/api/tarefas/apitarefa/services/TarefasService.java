package com.api.tarefas.apitarefa.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tarefas.apitarefa.models.TarefasModel;
import com.api.tarefas.apitarefa.repositories.TarefasRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefasService {

    @Autowired
    TarefasRepository tarefasRepository;

    @Transactional
    public TarefasModel save(TarefasModel tarefaModel) {
        return tarefasRepository.save(tarefaModel);
    }

    public List<TarefasModel> findAll() {
        return tarefasRepository.findAll();
    }

    public Optional<TarefasModel> findById(UUID id) {
        return tarefasRepository.findById(id);
    }

    @Transactional
    public void delete(TarefasModel tarefasModel) {
        tarefasRepository.delete(tarefasModel);
    }

}
