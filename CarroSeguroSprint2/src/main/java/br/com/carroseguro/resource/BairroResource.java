package br.com.carroseguro.resource;

import br.com.carroseguro.bo.BairroBO;
import br.com.carroseguro.bo.CidadeBO;
import br.com.carroseguro.to.BairroTO;
import br.com.carroseguro.to.CidadeTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/bairro")
public class BairroResource {
    private BairroBO bairroBO = new BairroBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<BairroTO> resultado = bairroBO.listarTodos();
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
    public Response inserir(@Valid BairroTO bairro) throws SQLException {
        BairroTO resultado = bairroBO.inserir(bairro);
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
    @Path("/{idBairro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid BairroTO bairroTO, @PathParam("idBairro") int idBairro) {
        bairroTO.setIdBairro(idBairro);
        BairroTO resultado = bairroBO.alterar(bairroTO);
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
    @Path("/idBairro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idBairro") int idBairro) {
        Response.ResponseBuilder response = null;
        if(bairroBO.excluir(idBairro)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }
}