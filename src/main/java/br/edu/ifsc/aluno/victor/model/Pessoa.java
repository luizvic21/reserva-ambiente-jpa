package br.edu.ifsc.aluno.victor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter

@MappedSuperclass
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    private String fone;
    private String fone2;
    private String email;
    private String cpf;
    private String rg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    protected void preencher(Endereco endereco, Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
        this.fone = pessoa.getFone();
        this.fone2 = pessoa.getFone2();
        this.email = pessoa.getEmail();
        this.cpf = pessoa.getCpf();
        this.rg = pessoa.getRg();
        this.endereco = endereco;
    }
}
