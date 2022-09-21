package br.com.davi.pereira.de.souza.botelho.api.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.davi.pereira.de.souza.botelho.api.entities.ItemEntity;
import br.com.davi.pereira.de.souza.botelho.api.inputs.ItemInput;
import br.com.davi.pereira.de.souza.botelho.api.outputs.ItemOutput;

@Component
public class ItemConvert {

	@Autowired
	private ModelMapper modelMapper;

	public ItemOutput entityToOutput(ItemEntity itemEntity) {
		return modelMapper.map(itemEntity, ItemOutput.class);
	}

	public List<ItemOutput> entityToOutput(List<ItemEntity> itemEntity) {
		return itemEntity.stream().map(item -> this.entityToOutput(item)).collect(Collectors.toList());
	}

	public ItemEntity inputToEntity(ItemInput itemInput) {
		return modelMapper.map(itemInput, ItemEntity.class);
	}

	public void copyInputToEntity(ItemInput itemInput, ItemEntity itemEncontrado) {
		modelMapper.map(itemInput, itemEncontrado);
	}

	public List<ItemOutput> listEntityToListOutput(List<ItemEntity> listaItens) {
		return listaItens.stream().map(item -> this.entityToOutput(item)).collect(Collectors.toList());
	}
}
