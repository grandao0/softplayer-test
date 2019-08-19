package com.cassio.player.models.db;

import com.cassio.player.models.Sexo;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "data_cadastro")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "data_atualizacao")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
