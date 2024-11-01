package br.com.carroseguro.resource;

import br.com.carroseguro.bo.PecasBO;
import br.com.carroseguro.to.MecanicoTO;
import br.com.carroseguro.to.PecasTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/pecas")
public class PecasResource {
    private PecasBO pecas = new PecasBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<PecasTO> resultado = pecas.listarTodos();
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
    @Path("/{idPeca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vizualizarPeloCodigo(@PathParam("idPeca") int idPeca) {
        PecasTO resultado = pecas.vizualizarPeloCodigo(idPeca);
        if (resultado != null) {
            return Response.ok(resultado).build(); // 200 OK c
        } else {
            return Response.status(404).entity("Mecânico não encontrado").build();
        }
    }
}
