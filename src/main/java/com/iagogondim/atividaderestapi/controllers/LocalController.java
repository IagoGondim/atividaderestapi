package com.iagogondim.atividaderestapi.controllers;


import com.iagogondim.atividaderestapi.entities.Local;
import com.iagogondim.atividaderestapi.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locais")
public class LocalController {
  @Autowired
  LocalRepository localRepository;

  @PostMapping("/")
  public ResponseEntity<Local> inserir(@RequestBody Local local) {
    localRepository.save(local);
    return ResponseEntity.status(HttpStatus.CREATED).body(local);

  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> atualizar(@PathVariable("id") Long id, @RequestBody Local novoLocal) {
    Optional<Local> optional = localRepository.findById(id);
    if (optional.isPresent()) {
      Local local = optional.get();

      // Atualizar os atributos do local com os valores do novo local
      local.setNome(novoLocal.getNome());
      local.setRua(novoLocal.getRua());
      local.setNumero(novoLocal.getNumero());
      local.setBairro(novoLocal.getBairro());
      local.setCidade(novoLocal.getCidade());
      local.setEstado(novoLocal.getEstado());
      local.setCep(novoLocal.getCep());

      localRepository.save(local);
      return ResponseEntity.status(HttpStatus.OK).body(local);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local não encontrado");
    }
  }

  @GetMapping("/")
  public ResponseEntity<List<Local>> listar() {
    return ResponseEntity.status(HttpStatus.OK).body(localRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> buscarPorId(@PathVariable("id") Long id) {
    Optional<Local> optional = localRepository.findById(id);
    if (optional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local não encontrado");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> excluir(@PathVariable("id") Long id) {

    Optional<Local> optional = localRepository.findById(id);
    try {
      Local local = optional.get();
      localRepository.delete(local);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado");
    }
  }
}
