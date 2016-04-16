package com.lagopusempire.zspongeutils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.service.permission.Subject;

public interface IPermissions {
    
    public String[] getNodes();
    public boolean defaultAccess();
    
    public default boolean verify(Subject s) {
        boolean usePermissions = Sponge.getServiceManager().isRegistered(PermissionService.class);
        
        if(!usePermissions) {
            return defaultAccess();
        }
        
        String[] nodes = getNodes();
        for(String node : nodes) {
            if(s.hasPermission(node)) {
                return true;
            }
        }
        
        return false;
    }
}
