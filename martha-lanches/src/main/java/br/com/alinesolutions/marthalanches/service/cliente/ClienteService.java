package br.com.alinesolutions.marthalanches.service.cliente;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.alinesolutions.marthalanches.model.Cliente;
import br.com.alinesolutions.marthalanches.util.EMF;

public class ClienteService implements IClienteService {

	@Override
	public void persist(Cliente entity) {
		EntityManager mgr = getEntityManager();
		try {
			containsCliente(entity);
			System.out.println("cliente já cadastrado");
		} catch (NoResultException nre) {
			mgr.persist(entity);			
		} finally {
			mgr.close();
		}
	}

	private void containsCliente(Cliente entity) {
		EntityManager mgr = getEntityManager();
		try {
			Query query = mgr.createQuery("select from Cliente as cliente where cliente.email = :email");
			query.setParameter("email", entity.getEmail());
			Cliente cliente = (Cliente) query.getSingleResult();
			if (cliente != null) {
				throw new EntityExistsException("Object already exists");
			}
		} finally {
			mgr.close();
		}
	}

	@Override
	public void merge(Cliente entity) {
		EntityManager mgr = getEntityManager();
		try {
			Cliente newEntity = mgr.find(Cliente.class, entity.getId());
			buildNewCliente(newEntity, entity);
			mgr.merge(newEntity);
		} finally {
			mgr.close();
		}
	}

	private void buildNewCliente(Cliente newEntity, Cliente entity) {
		newEntity.setEmail(entity.getEmail());
		newEntity.setEndereco(entity.getEndereco());
		newEntity.setNome(entity.getNome());
		newEntity.setTelefone(entity.getTelefone());
	}

	@Override
	public void remove(Cliente entity) {
		EntityManager mgr = getEntityManager();
		try {
			entity = mgr.find(Cliente.class, entity.getId());
			mgr.remove(entity);
		} finally {
			mgr.close();
		}
	}

	@Override
	public Cliente find(Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Cliente cliente = mgr.find(Cliente.class, id);
			return cliente;
		} finally {
			mgr.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listAll(Integer startPosition, Integer maxResult) {

		EntityManager mgr = getEntityManager();
		try {
			Query query = mgr.createQuery("select from Cliente as cliente");
			if (startPosition != null && maxResult != null && maxResult > 0) {
				query.setFirstResult(0);
				query.setMaxResults(maxResult);
			}
			List<Cliente> resultList = (List<Cliente>) query.getResultList();
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
