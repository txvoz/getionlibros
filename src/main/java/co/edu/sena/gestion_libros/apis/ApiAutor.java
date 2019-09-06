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
import java.util.List;
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
/**
 * Metodo para acutulizar los registros de una tupla.
 * @param rq
 * @param rs
 * @return Hashtable
 */
    @Override
    public Object update(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            Autor entity = controller.findAutor(id);
            controller.edit(entity);//No estoy seguro
            rs.status(201);
            r.put("status", 201);
            r.put("message", "Modificado con exito!");
            r.put("data", entity);
        } catch (Exception e) {
            rs.status(400);
            r.put("status", 400);
            r.put("message", e.getMessage());
        }
        return r;
    }
/**
 * Metodo para eliminar una tupla de la tabla
 * @param rq
 * @param rs
 * @return Hashtable
 */
    @Override
    public Object delete(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            Autor entity = controller.findAutor(id);
            controller.destroy(id);//No estoy seguro
            rs.status(201);
            r.put("status", 201);
            r.put("message", "Eliminado con exito!");
            r.put("data", entity);
        } catch (Exception e) {
            rs.status(400);
            r.put("status", 400);
            r.put("message", e.getMessage());
        }
        return r;
    }
/**
 * Metodo para conulta generar de los registros de una tabla.
 * @param rq
 * @param rs
 * @return Hashtable
 */
    @Override
    public Object getAll(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        List<Autor> libros = controller.findAutorEntities();
        if (libros.size() > 0) {
            r.put("status", 200);
            r.put("message", "Registros encontrados");
            r.put("data", libros);
        } else {
            rs.status(404);
            r.put("status", 404);
            r.put("message", "Registros no encontrados");
        }
        return r;
    }
/**
 * Metodo para consultar una tupla especifico de la tabla 
 * @param rq
 * @param rs
 * @return 
 */
    @Override
    public Object getById(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            Autor entity = controller.findAutor(id);
            if (entity != null) {
                r.put("status", 200);
                r.put("message", "Registro encontrado!");
                r.put("data", entity);
            } else {
                rs.status(404);
                r.put("status", 404);
                r.put("message", "Registro con id@" + id + " encontrado!");
            }
        } catch (Exception e) {
            rs.status(400);
            r.put("status", 400);
            r.put("message", e.getMessage());
        }
        return r;
    }
}
