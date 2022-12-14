/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author DELL
 */
@javax.ws.rs.ApplicationPath("recipesApi")
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
        resources.add(services.CORSFilter.class);
        resources.add(services.CategoryService.class);
        resources.add(services.FollowerService.class);
        resources.add(services.NotificationService.class);
        resources.add(services.NotificationTypeService.class);
        resources.add(services.RecipeSaveService.class);
        resources.add(services.RecipesService.class);
        resources.add(services.UsersService.class);
    }
    
}
