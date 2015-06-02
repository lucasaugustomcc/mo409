package br.unicamp.ic.mo409.server;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AAAUserAuthenticationToken extends AbstractAuthenticationToken {
 
    private static final long serialVersionUID = -1092219614309982278L;
    private final Object principal;
    private Object credentials;
 
    public AAAUserAuthenticationToken(Object principal, Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        
        System.out.println(this.principal);
    	System.out.println(this.credentials);

        super.setAuthenticated(true);
    }
 
    public Object getCredentials() {
        return this.credentials;
    }
 
    public Object getPrincipal() {    	
        return this.principal;
    }
}
