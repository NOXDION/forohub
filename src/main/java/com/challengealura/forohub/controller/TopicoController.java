package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.model.Topico;
import com.alurachallenge.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid Topico topico) {
        if (topicoRepository.findByTituloAndMensaje(topico.getTitulo(), topico.getMensaje()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Topico novoTopico = topicoRepository.save(topico);
        return ResponseEntity.ok(novoTopico);
    }

    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscarTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(topico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid Topico topicoAtualizado) {
        return topicoRepository.findById(id).map(topico -> {
            topico.setTitulo(topicoAtualizado.getTitulo());
            topico.setMensaje(topicoAtualizado.getMensaje());
            topico.setAutor(topicoAtualizado.getAutor());
            topico.setCurso(topicoAtualizado.getCurso());
            topico.setStatus(topicoAtualizado.getStatus());
            topicoRepository.save(topico);
            return ResponseEntity.ok(topico);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id);
        if (topicoExistente.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
