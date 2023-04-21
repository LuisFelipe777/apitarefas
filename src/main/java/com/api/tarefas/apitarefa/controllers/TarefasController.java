package com.api.tarefas.apitarefa.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.tarefas.apitarefa.dtos.TarefasDto;
import com.api.tarefas.apitarefa.models.TarefasModel;
import com.api.tarefas.apitarefa.services.TarefasService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<Object> saveTarefa(@RequestBody @Valid TarefasDto tarefasDto) {
        var tarefaModel = new TarefasModel();
        BeanUtils.copyProperties(tarefasDto, tarefaModel);
        tarefaModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefasService.save(tarefaModel));
    }

    @GetMapping
    public ResponseEntity<List<TarefasModel>> getAllTarefas() {
        return ResponseEntity.status(HttpStatus.OK).body(tarefasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTarefas(@PathVariable(value = "id") UUID id) {
        Optional<TarefasModel> tarefasModelOptional = tarefasService.findById(id);
        if (!tarefasModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta tarefa não existe");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(tarefasModelOptional.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTarefas(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid TarefasDto tarefasDto) {
        Optional<TarefasModel> tarefasModelOptional = tarefasService.findById(id);
        if (!tarefasModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta tarefa não existe");
        }
        var tarefasModel = tarefasModelOptional.get();
        tarefasModel.setTitulo(tarefasDto.getTitulo());
        tarefasModel.setTarefa(tarefasDto.getTarefa());

        return ResponseEntity.status(HttpStatus.OK).body(tarefasService.save(tarefasModel));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOneTarefas(@PathVariable(value = "id") UUID id) {
        Optional<TarefasModel> tarefasModelOptional = tarefasService.findById(id);
        if (!tarefasModelOptional.isPresent()) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Essa tarefa não existe");
        } else {
            tarefasService.delete(tarefasModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluída");
        }

    }
}
