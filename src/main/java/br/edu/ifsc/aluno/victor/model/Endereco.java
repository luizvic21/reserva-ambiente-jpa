package br.edu.ifsc.aluno.victor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString

@NoArgsConstructor
@AllArgsConstructor
@Getter

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cep;
    private String descricao;
    private Integer numero;
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    private Cidade cidade;

    public Endereco(Cidade cidade, Endereco endereco) {
        this.preencher(cidade, endereco);
    }

    private void preencher(Cidade cidade, Endereco endereco) {
        this.cep = endereco.getCep();
        this.descricao = endereco.getDescricao();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidade = cidade;
    }

    public Endereco editar(Cidade cidade, Endereco endereco) {
        preencher(cidade, endereco);
        return this;
    }
}
