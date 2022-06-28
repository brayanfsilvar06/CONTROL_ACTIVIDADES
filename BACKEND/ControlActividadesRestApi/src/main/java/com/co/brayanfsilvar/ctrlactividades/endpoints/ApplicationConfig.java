/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.endpoints;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author BrayanFSilvaR
 */
@javax.ws.rs.ApplicationPath("APIRest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.co.brayanfsilvar.ctrlactividades.endpoints.WSActividad.class);
        resources.add(com.co.brayanfsilvar.ctrlactividades.endpoints.WSAuthAPI.class);
        resources.add(com.co.brayanfsilvar.ctrlactividades.endpoints.WSPersona.class);
        resources.add(com.co.brayanfsilvar.ctrlactividades.security.CrossOriginFilter.class);
        resources.add(com.co.brayanfsilvar.ctrlactividades.security.RestSecurityFilter.class);
        resources.add(org.glassfish.jersey.client.filter.HttpDigestAuthFilter.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
    }
    
}
