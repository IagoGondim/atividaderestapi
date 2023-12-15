package com.iagogondim.atividaderestapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "tb_contato")
public class Contato {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String nome;
  @Column
  private String email;
  @Column
  private String fone;
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column
  private Date updatedAt;

  public Contato(Long id, String nome, String email, String fone) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.fone = fone;
  }

  public Contato() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFone() {
    return fone;
  }

  public void setFone(String fone) {
    this.fone = fone;
  }
}
