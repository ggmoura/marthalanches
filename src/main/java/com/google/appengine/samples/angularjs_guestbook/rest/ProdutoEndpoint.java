package com.google.appengine.samples.angularjs_guestbook.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.samples.angularjs_guestbook.domain.Produto;

/**
 * 
 */
@Stateless
@Path("/produtos")
public class ProdutoEndpoint {
	
	@POST
	@Consumes("application/json")
	public Response create(Produto entity) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		entity.setId(Long.valueOf(entity.getNomeProduto().hashCode()));
		Key produtokKey = KeyFactory.createKey("Produto", entity.getId());
		Entity produto = new Entity("Produto", produtokKey);
		produto.setProperty("nomeProduto", entity.getNomeProduto());
		produto.setProperty("precoCusto", entity.getPrecoCusto());
		produto.setProperty("precoVenda", entity.getPrecoVenda());
		datastoreService.put(produto);
		return Response.created(UriBuilder.fromResource(ProdutoEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		//TODO - Implementar meto para deletar
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Key produtoKey = KeyFactory.createKey("Produto", id);
		Query query = new Query("Produto", produtoKey);
		Entity produtoEntity = datastoreService.prepare(query).asSingleEntity();
		
		if (produtoEntity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(Produto.fromEntity(produtoEntity)).build();
	}

	@GET
	@Produces("application/json")
	public List<Produto> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
		List<Produto> produtos = new ArrayList<Produto>();
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Produto").addSort("nomeProduto", Query.SortDirection.ASCENDING);
		List<Entity> produtoEntities = datastoreService.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for (Entity produto : produtoEntities) {
			produtos.add(Produto.fromEntity(produto));
		}
		return produtos;
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
		//TODO atualizar 

		return Response.noContent().build();
	}
}
