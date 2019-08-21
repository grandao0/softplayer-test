package com.cassio.player.models.db;

import com.cassio.player.models.enums.Sexo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

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
