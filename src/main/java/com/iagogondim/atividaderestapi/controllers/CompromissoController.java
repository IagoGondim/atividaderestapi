package com.iagogondim.atividaderestapi.controllers;

import com.iagogondim.atividaderestapi.entities.Compromisso;
import com.iagogondim.atividaderestapi.repositories.CompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {

  @Autowired
  private CompromissoRepository compromissoRepository;

  // Operação CREATE
  @PostMapping("/")
  public ResponseEntity<Compromisso> criarCompromisso(@RequestBody Compromisso compromisso) {
    compromissoRepository.save(compromisso);
    return ResponseEntity.status(HttpStatus.CREATED).body(compromisso);
  }

  // Operação READ (listar todos)
  @GetMapping("/")
  public ResponseEntity<List<Compromisso>> listarCompromissos() {
    List<Compromisso> compromissos = compromissoRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(compromissos);
  }

  // Operação READ (buscar por ID)
  @GetMapping("/{id}")
  public ResponseEntity<Object> buscarCompromissoPorId(@PathVariable("id") Long id) {
    Optional<Compromisso> optional = compromissoRepository.findById(id);
    if (optional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compromisso não encontrado");
    }
  }

  // Operação UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<Object> atualizarCompromisso(@PathVariable("id") Long id, @RequestBody Compromisso novoCompromisso) {
    Optional<Compromisso> optional = compromissoRepository.findById(id);
    if (optional.isPresent()) {
      Compromisso compromisso = optional.get();
      compromisso.setData(novoCompromisso.getData());
      compromisso.setHora(novoCompromisso.getHora());
      compromisso.setDescricao(novoCompromisso.getDescricao());
      compromisso.setLocal(novoCompromisso.getLocal());
      compromisso.setContatos(novoCompromisso.getContatos());
      compromissoRepository.save(compromisso);
      return ResponseEntity.status(HttpStatus.OK).body(compromisso);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compromisso não encontrado");
    }
  }

  // Operação DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> excluirCompromisso(@PathVariable("id") Long id) {
    Optional<Compromisso> optional = compromissoRepository.findById(id);
    try {
      Compromisso compromisso = optional.orElseThrow();
      compromissoRepository.delete(compromisso);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compromisso não encontrado");
    }
  }
}
