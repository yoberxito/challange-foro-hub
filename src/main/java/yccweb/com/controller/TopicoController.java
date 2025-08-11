package yccweb.com.controller;/*
 * Copyright (c) 2025 yober cieza coronel. Todos los derechos reservados.
 *
 * Este archivo es parte de foro-hub.
 *
 * foro-hub es software propietario: no puedes redistribuirlo y/o modificarlo sin el
 * permiso expreso del propietario. Está sujeto a los términos y condiciones
 * que acompañan el uso del software.
 *
 * Cualquier uso no autorizado puede ser sancionado según la ley vigente.
 */

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import yccweb.com.domain.topico.DataTopico;
import yccweb.com.domain.topico.Topico;
import yccweb.com.domain.topico.TopicoRepository;
import yccweb.com.infra.exception.GeneralExeption.*;
import yccweb.com.infra.exception.ValidacionEcepction;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")

public class TopicoController {
    @Autowired
    private TopicoRepository repository;


    @PostMapping
    public void saveTopico(@Valid @RequestBody DataTopico dataTopico) {
        //Buscamos topicos duplicados
        Optional<Topico> topico = repository.findByTituloAndMensaje(dataTopico.titulo(), dataTopico.mensaje());

        if(topico.isPresent()){
            throw new ValidacionEcepction("Ya existe un registro");
        }else {
            Topico topico1=new Topico(dataTopico);
            repository.save(topico1);
        }

    }
    @GetMapping
    public ResponseEntity<List<DataTopico>> getListTopicos(@PageableDefault (size = 10, sort={"fechaCreacion"}) Pageable pageable){

        List<DataTopico> list=repository.findAllByOrderByFechaCreacionAsc(pageable).stream().map(T -> new DataTopico(T)).collect(Collectors.toList());
        System.out.println(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataTopico> getListTopicos(@PathVariable(name = "id") Long id){

        DataTopico detallTopic=repository.findById(id).map(T->new DataTopico(T)).get();
        System.out.println(detallTopic);
        return ResponseEntity.ok(detallTopic);
    }
    @PutMapping("/{id}")
    @Transactional
    public void actualizarTopico(@PathVariable(name = "id") Long id,@Valid @RequestBody Topico topico1) {
        Topico existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tópico con id: " + id));

        existente.setTitulo(topico1.getTitulo());
        existente.setMensaje(topico1.getMensaje());
        existente.setAutor(topico1.getAutor());
        existente.setCurso(topico1.getCurso());

        repository.save(existente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteTopico(@PathVariable(name = "id") Long id) {
        Topico existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tópico con id: " + id));

        repository.deleteById(id);
    }
}
