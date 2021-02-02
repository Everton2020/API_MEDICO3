package com.gbc.cadastromedico.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Entity
@EqualsAndHashCode
@Table (name = "tb_medico")
public class Medico implements Serializable {
@Id
/*@GeneratedValue (strategy = GenerationType.AUTO)*/
private long id;

@NotNull
@Size(min = 2, max = 120)
private String nome;

@NotNull
private String crm;

private String telefone;

private String celular;

@ManyToOne
@JsonIgnoreProperties("medico")
private Cep cep;

@ManyToOne
@JsonIgnoreProperties("medico")
private Especialidade especialidade;

@ManyToOne
@JsonIgnoreProperties("medico")
private Especialidade_2 especialidade_2;

}