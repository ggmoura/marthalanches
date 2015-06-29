package br.com.alinesolutions.marthalanches.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.alinesolutions.marthalanches.model.Produto;
import br.com.alinesolutions.marthalanches.service.produto.ProdutoService;
import br.com.alinesolutions.marthalanches.util.ServiceFactory;

@Stateless
@Path("/produtos")
public class ProdutoEndpoint {
	
	private ProdutoService service;
	
	public ProdutoEndpoint() {
		service = ServiceFactory.getService(ProdutoService.class);
	}

	@POST
	@Consumes("application/json")
	public Response create(Produto entity) {
		try {
			Response build = null;
			try {
				service.persist(entity);
				build = Response.created(UriBuilder.fromResource(ProdutoEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
			} catch (EntityExistsException e) {
				build = Response.status(Status.CONFLICT).build();
			}
			return build;			
		} catch (Exception e) {
			throw e;
		}
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Produto entity = service.find(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		service.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		Produto entity = service.find(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<Produto> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
		final List<Produto> results = service.listAll(startPosition, maxResult);
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, Produto entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (service.find(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		service.merge(entity);
		return Response.noContent().build();
	}
}
