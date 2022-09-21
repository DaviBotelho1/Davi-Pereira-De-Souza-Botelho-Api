package br.com.davi.pereira.de.souza.botelho.api.inputs;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInput {

	@Length(message = "O campo titulo deve conter no máximo 100 caracteres", max = 100)
	@NotEmpty(message = "O titulo nome é obrigatório!")
	private String titulo;

	@NotNull(message = "O id da lista é obrigatório")
	private Long listaId;

}
