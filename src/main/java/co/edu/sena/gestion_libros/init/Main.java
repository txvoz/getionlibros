package co.edu.sena.gestion_libros.init;

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
        
    }
    
}
