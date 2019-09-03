package co.edu.sena.gestion_libros.apis;

import co.edu.sena.gestion_libros.apis.abstract_.BasicApi;
import co.edu.sena.gestion_libros.apis.abstract_.IApi;
import co.edu.sena.gestion_libros.controllers.CategoriaJpaController;
import co.edu.sena.gestion_libros.entities.Categoria;
import co.edu.sena.gestion_libros.utils.JsonTransformer;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import spark.Request;
import spark.Response;

public class ApiCategoria extends BasicApi implements IApi {

    private static ApiCategoria instance = null;
    private Gson gson = null;
    private String path = "/categoria";
    private CategoriaJpaController controller;

    private ApiCategoria() {
        init();
        gson = JsonTransformer.singleton().getGson();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU_LIBROS");
        controller = new CategoriaJpaController(emf);
    }

    public static ApiCategoria singleton() {
        if (instance == null) {
            instance = new ApiCategoria();
        }
        return instance;
    }

    /**
     * Metodo para obtener la ubicacion
     *
     * @return tipo String
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * Metodo para crear un registro en la tabla
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
                Categoria entity = gson.fromJson(body, Categoria.class);
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
     * Metodo para actualizar un registro por el ID de la tupla
     *
     * @param rq
     * @param rs
     * @return Hashtable
     */
    @Override
    public Object update(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            Categoria entity = controller.findCategoria(id);
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
     * Metodo para eliminar un registro de la base de datos
     *
     * @param rq
     * @param rs
     * @return Hashtable
     */
    @Override
    public Object delete(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            Categoria entity = controller.findCategoria(id);
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
     * Metodo para una consulta general de toda la tabla.
     *
     * @param rq
     * @param rs
     * @return
     */
    @Override
    public Object getAll(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        List<Categoria> categorias = controller.findCategoriaEntities();
        if (categorias.size() > 0) {
            r.put("status", 200);
            r.put("message", "Registros encontrados");
            r.put("data", categorias);
        } else {
            rs.status(404);
            r.put("status", 404);
            r.put("message", "Registros no encontrados");
        }
        return r;
    }

    /**
     * Metodo para la consulta por ID de un registro de la tabla.
     *
     * @param rq
     * @param rs
     * @return Hashtable
     */
    @Override
    public Object getById(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            Categoria entity = controller.findCategoria(id);
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
