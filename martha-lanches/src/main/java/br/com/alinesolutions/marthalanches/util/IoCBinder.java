package br.com.alinesolutions.marthalanches.util;

import br.com.alinesolutions.marthalanches.service.cliente.ClienteService;
import br.com.alinesolutions.marthalanches.service.cliente.IClienteService;

import com.google.inject.Binder;
import com.google.inject.Module;

public class IoCBinder implements Module {

	public void configure(Binder binder) {

		// Service Binders
		binder.bind(IClienteService.class).to(ClienteService.class);
		binder.bind(IClienteService.class).to(ClienteService.class);

	}

}
