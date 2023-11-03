package domain.server.utils;

import io.javalin.http.Context;

public interface ICrudViewsHandler {
    void index(Context context); // DEVOLVER UNA VISTA QUE MUESTRE EL LISTADO DE TODOS LOS RECURSOS
    void show(Context context); // DEVOLVER UNA VISTA QUE MUESTRE EL DETALLE DE UN RECURSO
    void create(Context context); // DEVOLVER UNA VISTA QUE PERMITA CREAR UN RECURSO
    void save(Context context); //GUARDAR EL RECURSO
    void edit(Context context); // DEVOLVER UNA VISTA QUE PERMITA EDITAR EL RECURSO
    void update(Context context); // ACTUALIZAR EL RECURSO
    void delete(Context context); // ELIMINAR EL RECURSO

}
