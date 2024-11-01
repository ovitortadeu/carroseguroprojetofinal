package br.com.carroseguro.resource;

import br.com.carroseguro.bo.MecanicoBO;
import br.com.carroseguro.to.MecanicoTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/mecanico")
public class MecanicoResource {
    private MecanicoBO mecanicoBO = new MecanicoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<MecanicoTO> resultado = mecanicoBO.listarTodos();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 (OK)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }


    @GET
    @Path("/{idProblema}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vizualizarPeloCodigo(@PathParam("idMecanico") int idMecanico) {
        MecanicoTO resultado = mecanicoBO.vizualizarPeloCodigo(idMecanico);
        if (resultado != null) {
            return Response.ok(resultado).build(); // 200 OK c
        } else {
            return Response.status(404).entity("Mecânico não encontrado").build();
        }
    }
}
