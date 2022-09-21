package br.com.davi.pereira.de.souza.botelho.api.controllers;

import java.net.URISyntaxException;
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
import br.com.davi.pereira.de.souza.botelho.api.converts.ListaConvert;
import br.com.davi.pereira.de.souza.botelho.api.entities.ListaEntity;
import br.com.davi.pereira.de.souza.botelho.api.inputs.ListaInput;
import br.com.davi.pereira.de.souza.botelho.api.outputs.ListaOutput;
import br.com.davi.pereira.de.souza.botelho.api.services.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Lista")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ControllerConfig.PRE_URL + "/lista")
public class ListaController {

	@Autowired
	private ListaService listaService;

	@Autowired
	private ListaConvert listaConvert;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Cadastrar uma lista", description = "Cadastra uma nova lista")
	public ListaOutput criaLista(@Valid @RequestBody ListaInput lista) throws URISyntaxException {
		ListaEntity listaEntity = listaConvert.inputToEntity(lista);
		ListaEntity listaCadastrada = listaService.cadastro(listaEntity);
		return listaConvert.entityToOutput(listaCadastrada);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Alterar uma lista", description = "Altera uma lista pelo id")
	public ListaOutput alteraLista(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id,
			@Parameter(description = "Representação de uma lista") @Valid @RequestBody ListaInput listaInput) {
		ListaEntity listaEntity = listaConvert.inputToEntity(listaInput);
		listaEntity.setId(id);
		ListaEntity listaAlterada = listaService.alterar(listaEntity);
		return listaConvert.entityToOutput(listaAlterada);
	}

	@GetMapping
	@Operation(summary = "Lista todas as listas", description = "Lista todas as listas cadastradas contendo o ID e titulo")
	public List<ListaOutput> listarListas() {
		List<ListaEntity> listaTodas = listaService.listaTodos();
		return listaConvert.entityToOutput(listaTodas);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca uma lista", description = "Busca uma lista pelo id")
	public ListaOutput buscaListarPorId(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id) {
		ListaEntity ListaEntity = listaService.buscaPeloId(id);
		return listaConvert.entityToOutput(ListaEntity);
	}

	@Operation(summary = "Remove uma lista", description = "Remove uma lista pelo id")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeLista(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id) {
		ListaEntity ListaEntity = listaService.buscaPeloId(id);
		listaService.remove(ListaEntity);
	}

}
