package br.edu.ifsc.aluno.victor.model;

import br.edu.ifsc.aluno.victor.enuns.EnumModalidade;
import br.edu.ifsc.aluno.victor.enuns.EnumPeriodo;
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
@Table(name = "curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String email;

    @Enumerated(EnumType.STRING)
    private EnumModalidade modalidade;

    @Enumerated(EnumType.STRING)
    private EnumPeriodo periodo;

    public Curso editar(Curso curso) {
        this.preencher(curso);
        return this;
    }

    private void preencher(Curso curso) {
        this.descricao = curso.getDescricao();
        this.email = curso.getEmail();
        this.modalidade = curso.getModalidade();
        this.periodo = curso.getPeriodo();
    }
}
