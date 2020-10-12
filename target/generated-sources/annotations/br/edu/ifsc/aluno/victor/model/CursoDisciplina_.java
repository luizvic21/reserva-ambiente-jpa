package br.edu.ifsc.aluno.victor.model;

import br.edu.ifsc.aluno.victor.model.Curso;
import br.edu.ifsc.aluno.victor.model.Disciplina;
import br.edu.ifsc.aluno.victor.model.Servidor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-12T15:54:26")
@StaticMetamodel(CursoDisciplina.class)
public class CursoDisciplina_ { 

    public static volatile SingularAttribute<CursoDisciplina, String> siglaCurso;
    public static volatile SingularAttribute<CursoDisciplina, Curso> curso;
    public static volatile SingularAttribute<CursoDisciplina, Disciplina> disciplina;
    public static volatile SingularAttribute<CursoDisciplina, Servidor> docente;
    public static volatile SingularAttribute<CursoDisciplina, Integer> id;
    public static volatile SingularAttribute<CursoDisciplina, Integer> faseCurso;
    public static volatile SingularAttribute<CursoDisciplina, Integer> cargaHoraria;

}