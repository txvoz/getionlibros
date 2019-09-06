package co.edu.sena.gestion_libros.entities;

import co.edu.sena.gestion_libros.entities.Libro;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-05T19:08:19")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, Integer> catId;
    public static volatile ListAttribute<Categoria, Libro> libroList;
    public static volatile SingularAttribute<Categoria, String> catNombre;

}