package br.com.davi.pereira.de.souza.botelho.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.davi.pereira.de.souza.botelho.api.entities.ListaEntity;

public interface ListaRepository extends JpaRepository<ListaEntity, Long> {

	Optional<ListaEntity> findByTitulo(String titulo);

}
