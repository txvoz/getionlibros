package co.edu.sena.gestion_libros.entities;

import co.edu.sena.gestion_libros.entities.AutorLibro;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-30T10:08:19")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-29T20:07:59")
>>>>>>> master
@StaticMetamodel(Autor.class)
public class Autor_ { 

    public static volatile SingularAttribute<Autor, Integer> autId;
    public static volatile SingularAttribute<Autor, String> autGenero;
    public static volatile SingularAttribute<Autor, String> autNombre;
    public static volatile ListAttribute<Autor, AutorLibro> autorLibroList;
    public static volatile SingularAttribute<Autor, Date> autFechaNacimiento;

}