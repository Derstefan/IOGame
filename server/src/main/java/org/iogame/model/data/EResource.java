package org.iogame.model.data;


import java.util.HashMap;

public enum EResource {
    // Std Res
    CLASTER,
    SEVIT,
    NEDRIL,
    // Rare Res
    ONAMA,
    JIVANA,
    APOGHYT;



    public static HashMap<EResource,Double> emptyContainer(){
        HashMap<EResource,Double> container = new HashMap<>();
        for(EResource r: EResource.values()){
            container.put(r,0.0);
        }
        return container;
    }
}