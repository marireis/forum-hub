package com.marina.forum.hub.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDadosAtualizarDTO(

        String emailAutor,

        @NotBlank
        String mensagem,

        String curso)
{

}
