package org.jjv.instances;

import java.util.Set;

public class OperatorInstance {
    private static Set<String> operators;

    public static void create(Set<String> data){
        operators = data;
    }
    public static Set<String> get(){
        return operators;
    }
}
