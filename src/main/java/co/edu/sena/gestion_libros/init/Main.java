package co.edu.sena.gestion_libros.init;

import co.edu.sena.gestion_libros.apis.abstract_.ApiCategoria;
import spark.*;

public class Main {

    public static void main(String[] args) {
        Spark.staticFiles.location("/public");
        Spark.port(99);       
        //Registro de apis
        ApiCategoria.singleton();
        
        /*
        prueba1n
        */
    }
    
}
