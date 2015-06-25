package br.com.alinesolutions.marthalanches.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("martha-lanches-persistence-unit");

	private EMF() {
		super();
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}