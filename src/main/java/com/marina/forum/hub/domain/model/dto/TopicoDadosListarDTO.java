package com.marina.forum.hub.domain.model.dto;

import com.marina.forum.hub.domain.model.Topico;

import java.time.LocalDate;

public record TopicoDadosListarDTO(String titulo, String mensagem, String nomeAutor, String emailAutor, LocalDate dataCriacao, String estadoTopico, String curso) {

    public TopicoDadosListarDTO(Topico topico){
        this(   topico.getTitulo(),
                topico.getMensagem(),
                topico.getNomeAutor(),
                topico.getEmailAutor(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getCurso());
    }
}
