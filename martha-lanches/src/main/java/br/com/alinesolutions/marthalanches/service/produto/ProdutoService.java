package br.com.alinesolutions.marthalanches.service.produto;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.alinesolutions.marthalanches.model.Produto;
import br.com.alinesolutions.marthalanches.util.EMF;

public class ProdutoService implements IProdutoService {

	@Override
	public void persist(Produto entity) {
		EntityManager mgr = getEntityManager();
		try {
			containsEntity(entity);
			System.out.println("cliente já cadastrado");
		} catch (NoResultException nre) {
			mgr.persist(entity);			
		} finally {
			mgr.close();
		}
	}

	private void containsEntity(Produto entity) {
		EntityManager mgr = getEntityManager();
		try {
			Query query = mgr.createQuery("select from Cliente as cliente where cliente.email = :email");
			query.setParameter("email", entity.getNome());
			Produto cliente = (Produto) query.getSingleResult();
			if (cliente != null) {
				throw new EntityExistsException("Object already exists");
			}
		} finally {
			mgr.close();
		}
	}

	@Override
	public void merge(Produto entity) {
		EntityManager mgr = getEntityManager();
		try {
			Produto newEntity = mgr.find(Produto.class, entity.getId());
			buildNewCliente(newEntity, entity);
			mgr.merge(newEntity);
		} finally {
			mgr.close();
		}
	}

	private void buildNewCliente(Produto newEntity, Produto entity) {
		newEntity.setNome(entity.getNome());
		newEntity.setPrecoCusto(entity.getPrecoCusto());
		newEntity.setPrecoVenda(entity.getPrecoVenda());
		newEntity.setQuantidadeEstoque(entity.getQuantidadeEstoque());
	}

	@Override
	public void remove(Produto entity) {
		EntityManager mgr = getEntityManager();
		try {
			entity = mgr.find(Produto.class, entity.getId());
			mgr.remove(entity);
		} finally {
			mgr.close();
		}
	}

	@Override
	public Produto find(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Produto cliente = mgr.find(Produto.class, id);
			return cliente;
		} finally {
			mgr.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listAll(Integer startPosition, Integer maxResult) {

		EntityManager mgr = getEntityManager();
		try {
			Query query = mgr.createQuery("select from Produto as cliente");
			query.setHint("datanucleus.appengine.datastoreReadConsistency", "EVENTUAL");
			if (startPosition != null && maxResult != null && maxResult > 0) {
				query.setFirstResult(0);
				query.setMaxResults(maxResult);
			}
			List<Produto> resultList = (List<Produto>) query.getResultList();
			return resultList;
		} finally {
			if (mgr != null) {
				mgr.close();				
			}
		}
	}
	
	private EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}
	
}
