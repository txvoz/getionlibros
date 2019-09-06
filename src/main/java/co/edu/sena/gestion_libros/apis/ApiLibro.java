package co.edu.sena.gestion_libros.apis;

import co.edu.sena.gestion_libros.apis.abstract_.BasicApi;
import co.edu.sena.gestion_libros.apis.abstract_.IApi;
import co.edu.sena.gestion_libros.controllers.LibroJpaController;
import co.edu.sena.gestion_libros.entities.Libro;
import co.edu.sena.gestion_libros.utils.JsonTransformer;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import spark.Request;
import spark.Response;

public class ApiLibro extends BasicApi implements IApi {

    private static ApiLibro instance = null;
    private Gson gson = null;
    private String path = "/libro";
    private LibroJpaController controller;

    private ApiLibro() {
        init();
        gson = JsonTransformer.singleton().getGson();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU_LIBROS");
        controller = new LibroJpaController(emf);
    }

    public static ApiLibro singleton() {
        if (instance == null) {
            instance = new ApiLibro();
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
                Libro entity = gson.fromJson(body, Libro.class);
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
        Hashtable<String, Object> retorno = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            String body = rq.body();
            Libro newEntity = gson.fromJson(body, Libro.class);
            Libro oldEntity = controller.findLibro(id);
            if (oldEntity != null) {
                oldEntity.setLibNombre(newEntity.getLibNombre());
                oldEntity.setLibNumeroPaginas(newEntity.getLibNumeroPaginas());
                oldEntity.setLibFechaPublicacion(newEntity.getLibFechaPublicacion());
                oldEntity.setCatId(newEntity.getCatId());
                controller.edit(oldEntity);
                retorno.put("status", 200);
                retorno.put("message", "Registro actualizado con exito!");
                retorno.put("data",oldEntity);
            } else {
                rs.status(404);
                retorno.put("status", 404);
                retorno.put("message", "Registros con id@"+id+" no encontrado!");
            }
        } catch (Exception e) {
            rs.status(400);
            retorno.put("status", 400);
            retorno.put("message", e.getMessage());
        }
        return retorno;
    }

    @Override
    public Object delete(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            controller.destroy(id);
            r.put("status", 200);
            r.put("message", "Eliminado con exito!");
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
        List<Libro> categorias = controller.findLibroEntities();
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

    @Override
    public Object getById(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            Libro entity = controller.findLibro(id);
            if (entity != null) {
                r.put("status", 200);
                r.put("message", "Registro encontrado!");
                r.put("data", entity);
            } else {
                rs.status(404);
                r.put("status", 404);
                r.put("message", "Registro con id@" + id + " no encontrado!");
            }
        } catch (Exception e) {
            rs.status(400);
            r.put("status", 400);
            r.put("message", e.getMessage());
        }
        return r;
    }

}
