package br.com.davi.pereira.de.souza.botelho.api.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davi.pereira.de.souza.botelho.api.entities.ItemEntity;
import br.com.davi.pereira.de.souza.botelho.api.entities.ListaEntity;
import br.com.davi.pereira.de.souza.botelho.api.repositories.ItemRepository;
import br.com.davi.pereira.de.souza.botelho.api.repositories.ListaRepository;

@Service
public class ListaService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ListaRepository listaRepository;

	@Transactional
	public ListaEntity cadastro(ListaEntity lista) {
		return listaRepository.save(lista);
	}

	@Transactional
	public ListaEntity alterar(ListaEntity lista) {
		return listaRepository.save(lista);
	}

	public List<ListaEntity> listaTodos() {
		return listaRepository.findAll();
	}

	public ListaEntity buscaPeloId(Long id) {
		return listaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi encontrado a lista pelo id: " + id));
	}

	@Transactional
	public void remove(ListaEntity lista) {
		List<ItemEntity> itensEncontrados = itemRepository.findAllByLista(lista);
		if (!itensEncontrados.isEmpty()) {
			for (ItemEntity itemEntity : itensEncontrados) {
				itemRepository.delete(itemEntity);
			}
		}
		listaRepository.delete(lista);
	}

}
