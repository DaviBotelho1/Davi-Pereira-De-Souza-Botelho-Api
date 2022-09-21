package br.com.davi.pereira.de.souza.botelho.api.outputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOutput {
	private Long id;
	private String titulo;
	private Boolean concluido;
	private ListaOutput lista;

}
