package br.com.davi.pereira.de.souza.botelho.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_Item")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, name = "titulo")
	private String titulo;

	@Column(name = "concluido")
	private Boolean Concluído = false;

	@ManyToOne
	@JoinColumn(name = "lista_id")
	private ListaEntity lista;

}
