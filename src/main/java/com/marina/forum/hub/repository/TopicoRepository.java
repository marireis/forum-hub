package com.marina.forum.hub.repository;

import com.marina.forum.hub.domain.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndNomeAutor(String titulo, String autor);
}
