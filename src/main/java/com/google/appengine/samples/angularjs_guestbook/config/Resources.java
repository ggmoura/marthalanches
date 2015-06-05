package com.google.appengine.samples.angularjs_guestbook.config;

/**
 * Created with IntelliJ IDEA.
 * User: Takashi Matsuo <tmatsuo@google.com>
 * Date: 4/5/13
 * Time: 2:57 AM
 */
import com.google.appengine.samples.angularjs_guestbook.rest.GsonMessageBodyHandler;
import com.google.appengine.samples.angularjs_guestbook.rest.GuestbookResource;
import com.google.appengine.samples.angularjs_guestbook.rest.ProdutoEndpoint;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class Resources extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(GuestbookResource.class);
		s.add(GsonMessageBodyHandler.class);
		s.add(ProdutoEndpoint.class);
		return s;
	}
}
