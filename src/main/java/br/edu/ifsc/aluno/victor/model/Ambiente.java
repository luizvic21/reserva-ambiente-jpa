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
@Table(name = "ambiente")
public class Ambiente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String sigla;
    private String foto;

    @Column(name = "chave_acesso")
    private String chaveAcesso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloco_id", referencedColumnName = "id")
    private Bloco bloco;

    public Ambiente (Bloco bloco, Ambiente ambiente) {
        this.preencher(bloco, ambiente);
    }

    private void preencher(Bloco bloco, Ambiente ambiente) {
        this.descricao = ambiente.getDescricao();
        this.sigla = ambiente.getSigla();
        this.foto = ambiente.getFoto();
        this.chaveAcesso = ambiente.getChaveAcesso();
        this.bloco = bloco;
    }

    public Ambiente editar(Bloco bloco, Ambiente ambiente) {
        this.preencher(bloco, ambiente);
        return this;
    }
}
