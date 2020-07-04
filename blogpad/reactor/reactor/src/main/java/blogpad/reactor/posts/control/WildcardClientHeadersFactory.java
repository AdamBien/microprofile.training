package blogpad.reactor.posts.control;

import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

public class WildcardClientHeadersFactory implements ClientHeadersFactory{

	@Override
	public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {
            System.out.println("--- incoming ---" + incomingHeaders);
            System.out.println("--- clientOutgoingHeaders ---" + clientOutgoingHeaders);
            return incomingHeaders;
	}
    
}