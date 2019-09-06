package co.edu.sena.gestion_libros.init;

import co.edu.sena.gestion_libros.apis.*;
import spark.*;

public class Main {

    public static void main(String[] args) {
        
        Spark.staticFiles.location("/public");
        //puerto de conexion
        Spark.port(99);       
        //Registro de apis
        ApiCategoria.singleton();
        ApiLibro.singleton();
    }
}
