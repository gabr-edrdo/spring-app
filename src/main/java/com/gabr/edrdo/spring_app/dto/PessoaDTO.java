package com.gabr.edrdo.spring_app.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;

@Data
public class PessoaDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @NotNull(message = "A data de nascimento é obrigatória!")
    @Past(message = "Data de nascimento inválida!")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "O CEP é obrigatório")
    private String cep;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
