/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.gestion_libros.apis;

import co.edu.sena.gestion_libros.apis.abstract_.BasicApi;
import co.edu.sena.gestion_libros.apis.abstract_.IApi;
import co.edu.sena.gestion_libros.controllers.LibroJpaController;
import co.edu.sena.gestion_libros.utils.JsonTransformer;
import com.google.gson.Gson;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import spark.Request;
import spark.Response;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class ApiLibro extends BasicApi implements IApi {

    private static ApiLibro instance = null;
    private Gson gson = null;
    private String path = "/libro";
    LibroJpaController controller;
    /**
     * Constructor Private de la clase
     */
    private ApiLibro() {
        init();
        gson = JsonTransformer.singleton().getGson();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU_LIBROS");
        controller = new LibroJpaController(emf);
    }
    /**
     * Metodo para generar instancia para comenzar el simpleton
     *
     * @return tipo ApiLIbro
     */
    public static ApiLibro singleton() {
        if (instance == null) {
            instance = new ApiLibro();
        }
        return instance;
    }

    /**
     * Metodo para retornar la ubucacion
     *
     * @return de tipo String
     */
    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object create(Request rq, Response rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object update(Request rq, Response rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object delete(Request rq, Response rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getAll(Request rq, Response rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getById(Request rq, Response rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
