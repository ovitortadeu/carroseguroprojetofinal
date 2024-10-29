package br.com.carroseguro.resource;


import br.com.carroseguro.bo.EstadoBO;
import br.com.carroseguro.to.EstadoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/estado")
public class EstadoResource {
    private EstadoBO estadoBO = new EstadoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<EstadoTO> resultado = estadoBO.listarTodos();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@Valid EstadoTO estado) throws SQLException {
        EstadoTO resultado = estadoBO.inserir(estado);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); //201 CREATED
        } else{
            response = Response.status(400); // BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }


    @PUT
    @Path("/estado")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid EstadoTO estadoTO, @PathParam("idEstado") int idEstado) {
        estadoTO.setIdEstado(idEstado);
        EstadoTO resultado = estadoBO.alterar(estadoTO);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 CREATED
        } else{
            response = Response.status(400); // 400 BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idEstado}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idEstado") int idEstado) {
        Response.ResponseBuilder response = null;
        if(estadoBO.excluir(idEstado)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }

}
