package br.com.carroseguro.resource;

import br.com.carroseguro.bo.UsuarioBO;
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
        if (resultado != null) {
            return Response.ok(resultado).build(); // 200 OK com a lista de usuários
        } else {
            return Response.status(404).entity("Usuários não encontrados").build(); // 404 NOT FOUND
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(@Valid UsuarioTO usuario) throws SQLException {
        UsuarioTO resultado = usuarioBO.inserir(usuario);
        if (resultado != null) {
            return Response.created(null).entity(resultado).build(); // 201 CREATED com o usuário criado
        } else {
            return Response.status(400).entity("Erro ao inserir usuário").build(); // 400 BAD REQUEST
        }
    }

    @PUT
    @Path("/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid UsuarioTO usuarioTO, @PathParam("idUsuario") int idUsuario) {
        usuarioTO.setIdUsuario(idUsuario);
        UsuarioTO resultado = usuarioBO.alterar(usuarioTO);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{idUsuario}")
    public Response excluir(@PathParam("idUsuario") int idUsuario) {
        if (usuarioBO.excluir(idUsuario)) {
            return Response.status(204).build(); // 204 NO CONTENT
        } else {
            return Response.status(404).entity("Usuário não encontrado").build(); // 404 NOT FOUND
        }
    }

    @GET
    @Path("/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vizualizarPeloCodigo(@PathParam("idUsuario") int idUsuario) {
        UsuarioTO resultado = usuarioBO.vizualizarPeloCodigo(idUsuario);
        if (resultado != null) {
            return Response.ok(resultado).build(); // 200 OK
        } else {
            return Response.status(404).entity("Usuário não encontrado").build(); // 404 NOT FOUND
        }
    }
}
