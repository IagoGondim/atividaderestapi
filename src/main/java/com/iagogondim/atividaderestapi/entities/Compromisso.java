package com.iagogondim.atividaderestapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_compromisso")
public class Compromisso {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private Date data;

  @Column
  private Date hora;

  @Column
  private String descricao;

  @OneToOne
  private Local local;

  @ManyToMany
  @JoinTable(
          name = "compromisso_contato",
          joinColumns = @JoinColumn(name = "compromisso_id"),
          inverseJoinColumns = @JoinColumn(name = "contato_id")
  )
  private List<Contato> contatos;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column
  private Date updatedAt;

  public Compromisso(Long id, Date data, Date hora, String descricao, Local local, List<Contato> contatos, Date createdAt, Date updatedAt) {
    this.id = id;
    this.data = data;
    this.hora = hora;
    this.descricao = descricao;
    this.local = local;
    this.contatos = contatos;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Compromisso() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public Date getHora() {
    return hora;
  }

  public void setHora(Date hora) {
    this.hora = hora;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Local getLocal() {
    return local;
  }

  public void setLocal(Local local) {
    this.local = local;
  }

  public List<Contato> getContatos() {
    return contatos;
  }

  public void setContatos(List<Contato> contatos) {
    this.contatos = contatos;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
