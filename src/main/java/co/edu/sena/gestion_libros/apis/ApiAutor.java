/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.gestion_libros.apis;

import co.edu.sena.gestion_libros.apis.abstract_.BasicApi;
import co.edu.sena.gestion_libros.apis.abstract_.IApi;
import co.edu.sena.gestion_libros.controllers.AutorJpaController;
import co.edu.sena.gestion_libros.entities.Autor;
import co.edu.sena.gestion_libros.utils.JsonTransformer;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Hashtable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import spark.Request;
import spark.Response;

/**
 *
 * @author Andrés Felipe Mera Tróchez
 */
public class ApiAutor extends BasicApi implements IApi {

    private static ApiAutor instance = null;
    private Gson gson = null;
    private String path = "/autor";
    AutorJpaController controller;

    /**
     * Constructor Private de la clase
     */
    private ApiAutor() {
        init();
        gson = JsonTransformer.singleton().getGson();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU_LIBROS");
        controller = new AutorJpaController(emf);
    }

    /**
     * Metodo para generar instancia para comenzar el simpleton
     *
     * @return tipo ApiAutor
     */
    public static ApiAutor singleton() {
        if (instance == null) {
            instance = new ApiAutor();
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

    /**
     * Metodo para registrar un tupla en la table
     *
     * @param rq
     * @param rs
     * @return Hashtable
     */
    @Override
    public Object create(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        String body = rq.body();
        if (!body.trim().equals("")) {
            try {
                Autor entity = gson.fromJson(body, Autor.class);
                controller.create(entity);
                rs.status(201);
                r.put("status", 201);
                r.put("message", "Creado con exito!");
                r.put("data", entity);
            } catch (JsonSyntaxException e) {
                rs.status(400);
                r.put("status", 400);
                r.put("message", e.getMessage());
            }
        } else {
            rs.status(400);
            r.put("status", 400);
            r.put("message", "El body no puede llegar vacio!");
        }
        return r;
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
