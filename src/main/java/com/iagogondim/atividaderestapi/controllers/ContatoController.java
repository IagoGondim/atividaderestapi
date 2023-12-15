package com.iagogondim.atividaderestapi.controllers;

import com.iagogondim.atividaderestapi.entities.Contato;
import com.iagogondim.atividaderestapi.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

  @Autowired
  ContatoRepository contatoRepository;

  @PostMapping("/")
  public ResponseEntity<Contato> criarContato(@RequestBody Contato contato) {
    contatoRepository.save(contato);
    return ResponseEntity.status(HttpStatus.CREATED).body(contato);
  }

  @GetMapping("/")
  public ResponseEntity<List<Contato>> listarContatos() {
    List<Contato> contatos = contatoRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(contatos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> buscarContatoPorId(@PathVariable("id") Long id) {
    Optional<Contato> optional = contatoRepository.findById(id);
    if (optional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> atualizarContato(@PathVariable("id") Long id, @RequestBody Contato novoContato) {
    Optional<Contato> optional = contatoRepository.findById(id);
    if (optional.isPresent()) {
      Contato contato = optional.get();
      contato.setNome(novoContato.getNome());
      contato.setEmail(novoContato.getEmail());
      contato.setFone(novoContato.getFone());
      contatoRepository.save(contato);
      return ResponseEntity.status(HttpStatus.OK).body(contato);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> excluirContato(@PathVariable("id") Long id) {
    Optional<Contato> optional = contatoRepository.findById(id);
    try {
      Contato contato = optional.orElseThrow(); // Alterado para usar orElseThrow para simplificar
      contatoRepository.delete(contato);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado");
    }
  }
}
