package co.edu.sena.gestion_libros.init;

import co.edu.sena.gestion_libros.apis.ApiAutor;
import co.edu.sena.gestion_libros.apis.ApiAutorLibro;
import co.edu.sena.gestion_libros.apis.ApiCategoria;
import co.edu.sena.gestion_libros.apis.ApiLibro;
import spark.*;

public class Main {
/**
 * 
 * @param args 
 */
    public static void main(String[] args) {
        Spark.staticFiles.location("/public");
        Spark.port(99);       
        //Registro de apis
        ApiCategoria.singleton();
        ApiAutor.singleton();
        ApiLibro.singleton();
        ApiAutorLibro.singleton();
    }
    
}
