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
@Table(name = "bloco")
public class Bloco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String foto;

    public Bloco editar(Bloco bloco) {
        this.preencher(bloco);
        return this;
    }

    private void preencher(Bloco bloco) {
        this.descricao = bloco.getDescricao();
        this.foto = bloco.getFoto();
    }
}
