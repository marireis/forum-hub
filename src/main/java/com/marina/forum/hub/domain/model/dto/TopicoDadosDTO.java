package com.marina.forum.hub.domain.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record TopicoDadosDTO(@NotBlank
                             String titulo,

                             @NotBlank
                             String mensagem,


                             LocalDate data_criacao,

                             @NotBlank
                             String estadoTopico,

                             @NotBlank
                             String nomeAutor,

                             @NotBlank
                             @Email
                             String emailAutor,

                             @NotBlank
                             String curso ) {}
