package com.pgw;

/**
 * Created by pooja on 6/13/16.
 */
import java.security.Principal;

public class RolePrincipal implements Principal {

    private String name;

    public RolePrincipal(String name) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}