package com.marina.forum.hub.domain.model.dto;


import com.marina.forum.hub.domain.model.Topico;

public record TopicoDadosDetalharDTO(Topico topico, String extraInfo, String titulo, String mensagem, String nomeAutor, String emailAutor, String dataDeCriacao, String estadoDoTopico, String curso) {

    public TopicoDadosDetalharDTO(Topico topico, String extraInfo) {
        this(topico, extraInfo, topico.getTitulo(), topico.getMensagem(), topico.getNomeAutor(), topico.getEmailAutor(), topico.getDataCriacao().toString(), topico.getStatus(), topico.getCurso());
    }
}