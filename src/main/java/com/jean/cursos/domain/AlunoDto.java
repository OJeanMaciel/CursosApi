package com.jean.cursos.domain;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    private Long id;
    private String nome;
    private String email;
    private Integer telefone;
    private String cep;
    private String cidade;
    private String bairro;
    private String rua;

    private String categoria;
    private String curso;

    private String curso_id;
}