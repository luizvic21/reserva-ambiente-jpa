package br.edu.ifsc.aluno.victor.model;

import br.edu.ifsc.aluno.victor.enuns.EnumTipoServidor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-12T16:12:00")
@StaticMetamodel(Servidor.class)
public class Servidor_ { 

    public static volatile SingularAttribute<Servidor, EnumTipoServidor> tipoServidor;
    public static volatile SingularAttribute<Servidor, String> foto;
    public static volatile SingularAttribute<Servidor, String> siape;

}