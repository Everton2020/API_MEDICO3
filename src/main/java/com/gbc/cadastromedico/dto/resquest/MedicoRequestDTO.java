package com.gbc.cadastromedico.dto.resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequestDTO {

    private String nome;

    private String crm;

    private String telefone;

    private String celular;

    private String cep;

    private String especialidade;

    private String especialidade_2;
}
