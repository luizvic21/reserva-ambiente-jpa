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
@Table(name = "disciplina")
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;

    public Disciplina editar(Disciplina disciplina) {
        this.preencher(disciplina);
        return this;
    }

    private void preencher(Disciplina disciplina) {
        this.descricao = disciplina.getDescricao();
    }
}
