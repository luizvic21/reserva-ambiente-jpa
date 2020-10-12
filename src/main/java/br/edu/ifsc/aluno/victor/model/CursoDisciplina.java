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
@Table(name = "curso_disciplina")
public class CursoDisciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sigla_curso")
    private String siglaCurso;

    @Column(name = "fase_curso")
    private Integer faseCurso;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id", referencedColumnName = "id")
    private Servidor docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplina_id", referencedColumnName = "id")
    private Disciplina disciplina;

    public CursoDisciplina(Servidor docente, Curso curso, Disciplina disciplina, CursoDisciplina cursoDisciplina) {
        this.preencher(docente, curso, disciplina, cursoDisciplina);
    }

    private void preencher(Servidor docente, Curso curso, Disciplina disciplina, CursoDisciplina cursoDisciplina) {
        this.siglaCurso = cursoDisciplina.getSiglaCurso();
        this.faseCurso = cursoDisciplina.getFaseCurso();
        this.cargaHoraria = cursoDisciplina.getCargaHoraria();
        this.docente = docente;
        this.curso = curso;
        this.disciplina = disciplina;
    }

    public CursoDisciplina editar(Servidor docente, Curso curso, Disciplina disciplina, CursoDisciplina cursoDisciplina) {
        this.preencher(docente, curso, disciplina, cursoDisciplina);
        return this;
    }
}
