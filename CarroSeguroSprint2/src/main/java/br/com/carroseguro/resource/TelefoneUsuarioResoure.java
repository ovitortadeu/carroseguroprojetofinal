package br.com.carroseguro.resource;

import br.com.carroseguro.bo.CidadeBO;
import br.com.carroseguro.bo.TelefoneUsuarioBO;
import br.com.carroseguro.to.CidadeTO;
import br.com.carroseguro.to.TelefoneUsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/telefone")
public class TelefoneUsuarioResoure {
    private TelefoneUsuarioBO telBO = new TelefoneUsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<TelefoneUsuarioTO> resultado = telBO.listarTodos();
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
    public Response inserir(@Valid TelefoneUsuarioTO telefone) throws SQLException {
        TelefoneUsuarioTO resultado = telBO.inserir(telefone);
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
    @Path("/{idTel}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid TelefoneUsuarioTO telTO, @PathParam("idTel") int idTel) {
        telTO.setIdTelefone(idTel);
        TelefoneUsuarioTO resultado = telBO.alterar(telTO);
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
    @Path("/idTel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idTel") int idTel) {
        Response.ResponseBuilder response = null;
        if(telBO.excluir(idTel)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }
}