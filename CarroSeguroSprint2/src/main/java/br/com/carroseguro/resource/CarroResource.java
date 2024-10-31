package br.com.carroseguro.resource;

import br.com.carroseguro.bo.CarroBO;
import br.com.carroseguro.bo.CidadeBO;
import br.com.carroseguro.to.CarroTO;
import br.com.carroseguro.to.CidadeTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/carro")
public class CarroResource {
    private CarroBO carroBO = new CarroBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        ArrayList<CarroTO> resultado = carroBO.listarTodos();
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
    public Response inserir(@Valid CarroTO carro) throws SQLException {
        CarroTO resultado = carroBO.inserir(carro);
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
    @Path("/{idCarro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(@Valid CarroTO carroTO, @PathParam("idCarro") int idCarro) {
        carroTO.setIdCarro(idCarro);
        CarroTO resultado = carroBO.alterar(carroTO);
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
    @Path("/{idCarro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("idCarro") int idCarro) {
        Response.ResponseBuilder response = null;
        if(carroBO.excluir(idCarro)) {
            response = Response.status(204); // 204 NO CONTENT
        } else {
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }
}