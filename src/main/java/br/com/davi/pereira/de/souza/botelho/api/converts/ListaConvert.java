package br.com.davi.pereira.de.souza.botelho.api.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.davi.pereira.de.souza.botelho.api.entities.ListaEntity;
import br.com.davi.pereira.de.souza.botelho.api.inputs.ListaInput;
import br.com.davi.pereira.de.souza.botelho.api.outputs.ListaOutput;

@Component
public class ListaConvert {

	@Autowired
	private ModelMapper modelMapper;

	public ListaOutput entityToOutput(ListaEntity listaEntity) {
		return modelMapper.map(listaEntity, ListaOutput.class);
	}

	public List<ListaOutput> entityToOutput(List<ListaEntity> listaEntity) {
		return listaEntity.stream().map(lista -> this.entityToOutput(lista)).collect(Collectors.toList());
	}

	public ListaEntity inputToEntity(ListaInput listaInput) {
		return modelMapper.map(listaInput, ListaEntity.class);
	}

	public void copyDataInputToEntity(ListaInput listaInput, ListaInput listaEntity) {
		modelMapper.map(listaInput, listaEntity);
	}
}
