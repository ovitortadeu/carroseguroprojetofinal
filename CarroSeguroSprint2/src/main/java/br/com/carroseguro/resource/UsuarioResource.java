package br.com.carroseguro.resource;

import br.com.carroseguro.bo.CidadeBO;
import br.com.carroseguro.bo.UsuarioBO;
import br.com.carroseguro.to.CidadeTO;
import br.com.carroseguro.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/usuario")
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<UsuarioTO> resultado = usuarioBO.listarTodos();
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
    public Response inserir(@Valid UsuarioTO usuario) throws SQLException {
        UsuarioTO resultado = usuarioBO.inserir(usuario);
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
    @Path("/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid UsuarioTO usuarioTO, @PathParam("idUsuario") int idUsuario) {
        usuarioTO.setIdUsuario(idUsuario);
        UsuarioTO resultado = usuarioBO.alterar(usuarioTO);
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
    @Path("/idUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idUsuario") int idUsuario) {
        Response.ResponseBuilder response = null;
        if(usuarioBO.excluir(idUsuario)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }

    @GET
    @Path("/idUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response vizualizarPeloCodigo(@PathParam("idUsuario") int idUsuario) {
        UsuarioTO resultado = usuarioBO.vizualizarPeloCodigo(idUsuario);
        Response.ResponseBuilder response = null;
        if(resultado!=null) {
            response = Response.ok(); // 200 OK
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }
}