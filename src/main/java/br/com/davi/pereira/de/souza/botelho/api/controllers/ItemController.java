package br.com.davi.pereira.de.souza.botelho.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.pereira.de.souza.botelho.api.configs.ControllerConfig;
import br.com.davi.pereira.de.souza.botelho.api.converts.ItemConvert;
import br.com.davi.pereira.de.souza.botelho.api.entities.ItemEntity;
import br.com.davi.pereira.de.souza.botelho.api.entities.ListaEntity;
import br.com.davi.pereira.de.souza.botelho.api.inputs.ItemInput;
import br.com.davi.pereira.de.souza.botelho.api.outputs.ItemOutput;
import br.com.davi.pereira.de.souza.botelho.api.services.ItemService;
import br.com.davi.pereira.de.souza.botelho.api.services.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Item")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ControllerConfig.PRE_URL + "/itens")
public class ItemController {

	@Autowired
	private ListaService listaService;

	@Autowired
	private ItemConvert itemConvert;

	@Autowired
	private ItemService itemService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@Operation(summary = "Cadastra um item", description = "Cadastra um novo item")
	public ItemOutput cadastraItem(@RequestBody @Valid ItemInput itemInput) {
		ItemEntity itemEntity = itemConvert.inputToEntity(itemInput);
		convertListas(itemInput, itemEntity);
		ItemEntity itemCriado = itemService.cadastro(itemEntity);
		return itemConvert.entityToOutput(itemCriado);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Alterar item", description = "Alterar dados do item")
	public ItemOutput alteraItem(
			@Parameter(description = "Representação de um item") @RequestBody @Valid ItemInput itemInput,
			@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		ItemEntity itemEncontrado = itemService.buscaPeloId(id);
		itemConvert.copyInputToEntity(itemInput, itemEncontrado);
		convertListas(itemInput, itemEncontrado);
		ItemEntity itemAlterado = itemService.alterar(itemEncontrado);
		return itemConvert.entityToOutput(itemAlterado);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui item", description = "Exclui o item pelo id")
	public void removeItem(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		ItemEntity Item = itemService.buscaPeloId(id);
		itemService.remover(Item);
	}

	@PutMapping("/{id}/concluir")
	@Operation(summary = "Concluí um item", description = "Concluí o item selecionado")
	public ItemOutput concluiItem(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		ItemEntity itemEncontrado = itemService.buscaPeloId(id);
		ItemEntity itemConcluido = itemService.concluido(itemEncontrado);
		return itemConvert.entityToOutput(itemConcluido);
	}

	@PutMapping("/{id}/nao-concluir")
	@Operation(summary = "Não concluí item", description = "Salva um item como não concluído")
	public ItemOutput itemNaoConcluido(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		ItemEntity itemEncontrado = itemService.buscaPeloId(id);
		ItemEntity itemNaoConcluido = itemService.naoConcluido(itemEncontrado);
		return itemConvert.entityToOutput(itemNaoConcluido);
	}

	@GetMapping("/lista/{listaId}")
	@Operation(summary = "Lista todos os itens", description = "Lista todos os itens pelo id da lista")
	public List<ItemOutput> listaItensPorIdLista(
			@Parameter(description = "Id da lista", example = "1") @PathVariable Long listaId) {
		ListaEntity lista = listaService.buscaPeloId(listaId);
		List<ItemEntity> listaItens = itemService.listaTodosPorLista(lista);
		return itemConvert.listEntityToListOutput(listaItens);
	}

	private void convertListas(ItemInput itemInput, ItemEntity itemEntity) {
		itemEntity.setLista(listaService.buscaPeloId(itemInput.getListaId()));
	}
}
