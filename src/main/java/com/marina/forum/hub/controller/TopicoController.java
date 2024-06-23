package com.marina.forum.hub.controller;

import com.marina.forum.hub.domain.model.Topico;
import com.marina.forum.hub.domain.model.dto.TopicoDadosAtualizarDTO;
import com.marina.forum.hub.domain.model.dto.TopicoDadosDTO;
import com.marina.forum.hub.domain.model.dto.TopicoDadosDetalharDTO;
import com.marina.forum.hub.domain.model.dto.TopicoDadosListarDTO;
import com.marina.forum.hub.domain.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
@Validated
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> createTopic(@Valid @RequestBody TopicoDadosDTO dados, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoService.saveTopico(dados);
        //Cria a URI do novo tópico
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        // Retorna a URI do novo tópico
        return ResponseEntity.created(uri).body(new TopicoDadosDetalharDTO(topico, "Topico criado com sucesso!!").topico());
    }
    @GetMapping
    public Page<TopicoDadosListarDTO> listarTodos(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao){
        return topicoService.findAll(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDadosListarDTO> buscarPorId(@PathVariable Long id) {
        Optional<TopicoDadosListarDTO> topico = topicoService.findById(id);
        return topico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoDadosAtualizarDTO dados) {
        Optional<Topico> atualizado = topicoService.atualizar(id, dados);
        return atualizado.map(t -> ResponseEntity.ok("Tópico atualizado com sucesso!"))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        boolean deletado = topicoService.deletar(id);
        if (deletado) {
            return ResponseEntity.ok("Tópico deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
