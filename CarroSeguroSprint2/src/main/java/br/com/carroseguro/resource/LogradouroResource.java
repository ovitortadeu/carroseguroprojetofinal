package br.com.carroseguro.resource;

import br.com.carroseguro.bo.LogradouroBO;
import br.com.carroseguro.to.LogradouroTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/logradouro")
public class LogradouroResource {
    private LogradouroBO logradouroBO = new LogradouroBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<LogradouroTO> resultado = logradouroBO.listarTodos();
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
    public Response inserir(@Valid LogradouroTO logradouro) throws SQLException {
        LogradouroTO resultado = logradouroBO.inserir(logradouro);
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
    @Path("/logradouro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid LogradouroTO logradouroTO, @PathParam("idLogradouro") int idLogradouro) {
        logradouroTO.setIdLogradouro(idLogradouro);
        LogradouroTO resultado = logradouroBO.alterar(logradouroTO);
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
    @Path("/idLogradouro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idLogradouro") int idLogradouro) {
        Response.ResponseBuilder response = null;
        if(logradouroBO.excluir(idLogradouro)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }
}
