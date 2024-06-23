package com.marina.forum.hub.domain.service;

import com.marina.forum.hub.domain.model.Topico;
import com.marina.forum.hub.domain.model.dto.TopicoDadosAtualizarDTO;
import com.marina.forum.hub.domain.model.dto.TopicoDadosDTO;
import com.marina.forum.hub.domain.model.dto.TopicoDadosDetalharDTO;
import com.marina.forum.hub.domain.model.dto.TopicoDadosListarDTO;
import com.marina.forum.hub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    public Topico saveTopico(TopicoDadosDTO dados) {
        Topico topico = new Topico(dados);
        return repository.save(topico);

    }
    public Page<TopicoDadosListarDTO> findAll(Pageable paginacao) {
        Page<Topico> topicos = repository.findAll(paginacao);
        return topicos.map(TopicoDadosListarDTO::new);
    }

    public Optional<TopicoDadosListarDTO> findById(Long id) {
        Optional<Topico> topico = repository.findById(id);
        return topico.map(TopicoDadosListarDTO::new);
    }

    @Transactional
    public Optional<Topico> atualizar(Long id, TopicoDadosAtualizarDTO dados) {
        Optional<Topico> optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.atualizarInformacoes(dados);
            // Atualize outras propriedades conforme necessário
            return Optional.of(topico);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean deletar(Long id) {
        Optional<Topico> optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
