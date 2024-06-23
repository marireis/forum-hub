package com.marina.forum.hub.domain.model;

import com.marina.forum.hub.domain.model.dto.TopicoDadosAtualizarDTO;
import com.marina.forum.hub.domain.model.dto.TopicoDadosDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String titulo;


    private String mensagem;
    @Column(name = "data_criacao")
    private LocalDate dataCriacao = LocalDate.now();


    private String status;


    private String nomeAutor;
    @Column(name = "email_autor")
    private String emailAutor;


    private String curso;

    public Topico(TopicoDadosDTO dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = (dados.data_criacao() != null) ? dados.data_criacao() : LocalDate.now();
        this.status = dados.estadoTopico();
        this.nomeAutor = dados.nomeAutor();
        this.emailAutor = dados.emailAutor();
        this.curso = dados.curso();
    }

    public void atualizarInformacoes(TopicoDadosAtualizarDTO dados) {

        if (dados.emailAutor() != null) {
            this.emailAutor = dados.emailAutor();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }

    }

}
