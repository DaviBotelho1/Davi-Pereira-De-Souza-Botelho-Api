package br.com.davi.pereira.de.souza.botelho.api.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davi.pereira.de.souza.botelho.api.entities.ItemEntity;
import br.com.davi.pereira.de.souza.botelho.api.entities.ListaEntity;
import br.com.davi.pereira.de.souza.botelho.api.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Transactional
	public ItemEntity cadastro(ItemEntity item) {
		return itemRepository.save(item);
	}

	@Transactional
	public ItemEntity alterar(ItemEntity item) {
		return itemRepository.save(item);
	}

	public List<ItemEntity> listaTodos() {
		return itemRepository.findAll();
	}

	public ItemEntity buscaPeloId(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi encontrado o item pelo id: " + id));
	}

	@Transactional
	public void remover(ItemEntity item) {
		itemRepository.delete(item);
	}

	@Transactional
	public ItemEntity concluido(ItemEntity itemEncontrado) {
		itemEncontrado.setConcluído(true);
		return itemRepository.save(itemEncontrado);
	}

	@Transactional
	public ItemEntity naoConcluido(ItemEntity itemEncontrado) {
		itemEncontrado.setConcluído(false);
		return itemRepository.save(itemEncontrado);
	}

	public List<ItemEntity> listaTodosPorLista(ListaEntity lista) {
		return itemRepository.findAllByLista(lista);
	}

}
