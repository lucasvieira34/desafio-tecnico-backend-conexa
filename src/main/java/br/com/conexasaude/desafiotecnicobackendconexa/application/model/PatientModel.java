package br.com.conexasaude.desafiotecnicobackendconexa.application.model;

import lombok.Data;

@Data
public class PatientModel {

    private String nome;
    private String cpf;
    private Integer idade;
    private String telefone;

}