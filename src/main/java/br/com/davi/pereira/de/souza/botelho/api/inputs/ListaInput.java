package br.com.davi.pereira.de.souza.botelho.api.inputs;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaInput {

	@Length(message = "O campo titulo conter no máximo 100 caracteres", max = 100)
	@NotEmpty(message = "O campo titulo é obrigatório!")
	private String titulo;

}
