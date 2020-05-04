package br.jss.warehouse.controller;

import br.jss.warehouse.dao.ProductDAO;
import br.jss.warehouse.model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Stateless
@Path("products")
public class ProductController {

    @Inject
    private ProductDAO productDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getById(@PathParam("id") Short id) {
        return productDAO.getById(id);
    }

    @GET
    @Path("search={name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getByName(@PathParam("name") String name) {
        return productDAO.getByName(name);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid Product product, @Context UriInfo uriInfo) {
        product = productDAO.save(product);
        URI uri = uriInfo.getAbsolutePathBuilder().build("/" + product.getId());
        return Response.created(uri).entity(product).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Short id) {
        try {
            productDAO.remove(id);
            return Response.ok().header("status", String.format("[%d] deleted", id)).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NO_CONTENT).header("reason", e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product update(@PathParam("id") Short id, @Valid Product product) {
        product.setId(id);
        return productDAO.save(product);
    }
}
