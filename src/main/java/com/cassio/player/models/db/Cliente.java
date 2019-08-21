package com.cassio.player.models.db;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.cassio.player.models.enums.Sexo;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sexo")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Column(name = "email")
	private String email;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Column(name = "naturalidade")
	private String naturalidade;

	@Column(name = "nacionalidade")
	private String nacionalidade;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "data_cadastro")
	private OffsetDateTime dataCadastro;

	@Column(name = "data_atualizacao")
	private OffsetDateTime dataAtualizacao;

	@PrePersist
	private void prePersist() {
		this.setDataCadastro(OffsetDateTime.now());
		this.setDataAtualizacao(OffsetDateTime.now());
	}
}
