package br.com.davi.pereira.de.souza.botelho.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.davi.pereira.de.souza.botelho.api.entities.ItemEntity;
import br.com.davi.pereira.de.souza.botelho.api.entities.ListaEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	List<ItemEntity> findAllByLista(ListaEntity lista);

}
