package br.com.carroseguro.resource;

import br.com.carroseguro.bo.CidadeBO;
import br.com.carroseguro.bo.ProblemasBO;
import br.com.carroseguro.to.CidadeTO;
import br.com.carroseguro.to.ProblemasTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/problemas")
public class ProblemasResource {
    private ProblemasBO problemasBO = new ProblemasBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<ProblemasTO> resultado = problemasBO.listarTodos();
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
    public Response inserir(@Valid ProblemasTO problemas) throws SQLException {
        ProblemasTO resultado = problemasBO.inserir(problemas);
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
    @Path("/{idProblemas}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid ProblemasTO problemasTO, @PathParam("idProblemas") int idProblemas) {
        problemasTO.setIdProblema(idProblemas);
        ProblemasTO resultado = problemasBO.alterar(problemasTO);
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
    @Path("/idProblemas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idProblemas") int idProblema) {
        Response.ResponseBuilder response = null;
        if(problemasBO.excluir(idProblema)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }
}