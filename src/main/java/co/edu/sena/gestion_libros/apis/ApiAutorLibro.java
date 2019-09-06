/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.gestion_libros.apis;

import co.edu.sena.gestion_libros.apis.abstract_.BasicApi;
import co.edu.sena.gestion_libros.apis.abstract_.IApi;
import co.edu.sena.gestion_libros.controllers.AutorLibroJpaController;
import co.edu.sena.gestion_libros.entities.AutorLibro;
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
 * @author ANDRÉS FELIPE MERA TRÓCHEZ
 */
public class ApiAutorLibro extends BasicApi implements IApi {

    private static ApiAutorLibro instance = null;
    private Gson gson = null;
    private String path = "/autorlibro";
    AutorLibroJpaController controller;

    private ApiAutorLibro() {
        init();
        gson = JsonTransformer.singleton().getGson();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU_LIBROS");
        controller = new AutorLibroJpaController(emf);
    }

    public static ApiAutorLibro singleton() {
        if (instance == null) {
            instance = new ApiAutorLibro();
        }
        return instance;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object create(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        String body = rq.body();
        if (!body.trim().equals("")) {
            try {
                AutorLibro entity = gson.fromJson(body, AutorLibro.class);
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
     * Metodo put para actualizar los datos del registro por id.
     *
     * @param rq
     * @param rs
     * @return
     */
    @Override
    public Object update(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            String body = rq.body();
            AutorLibro newEntity = gson.fromJson(body, AutorLibro.class);
            AutorLibro oldEntity = controller.findAutorLibro(id);
            if (oldEntity != null) {
                oldEntity.setAutId(newEntity.getAutId());
                oldEntity.setLibId(newEntity.getLibId());
                controller.edit(oldEntity);
                rs.status(200);
                r.put("status", 200);
                r.put("message", "Modificado con exito!");
                r.put("data", oldEntity);
            } else {
                rs.status(404);
                r.put("status", 404);
                r.put("message", "Modificado con el id " + id + " no encontrado!");
            }
        } catch (Exception e) {
            rs.status(400);
            r.put("status", 400);
            r.put("message", e.getMessage());
        }
        return r;
    }

    @Override
    public Object delete(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            AutorLibro entity = controller.findAutorLibro(id);
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

    @Override
    public Object getAll(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        List<AutorLibro> autores = controller.findAutorLibroEntities();
        if (autores.size() > 0) {
            r.put("status", 200);
            r.put("message", "Registros encontrados");
            r.put("data", autores);
        } else {
            rs.status(404);
            r.put("status", 404);
            r.put("message", "Registros no encontrados");
        }
        return r;
    }

    @Override
    public Object getById(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            AutorLibro entity = controller.findAutorLibro(id);
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
