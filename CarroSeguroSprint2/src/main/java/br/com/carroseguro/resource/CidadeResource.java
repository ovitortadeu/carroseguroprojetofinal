package br.com.carroseguro.resource;

import br.com.carroseguro.bo.CidadeBO;
import br.com.carroseguro.to.CidadeTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/cidade")
public class CidadeResource {
    private CidadeBO cidadeBO = new CidadeBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<CidadeTO> resultado = cidadeBO.listarTodos();
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
    public Response inserir(@Valid CidadeTO cidade) throws SQLException {
        CidadeTO resultado = cidadeBO.inserir(cidade);
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
    @Path("/{idCidade}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid CidadeTO cidadeTO, @PathParam("idCidade") int idCidade) {
        cidadeTO.setIdCidade(idCidade);
        CidadeTO resultado = cidadeBO.alterar(cidadeTO);
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
    @Path("/idCidade")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idCidade") int idCidade) {
        Response.ResponseBuilder response = null;
        if(cidadeBO.excluir(idCidade)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }
}
