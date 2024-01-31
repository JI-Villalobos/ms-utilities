package org.jjv.instances;

import org.jjv.models.Operator;

import java.util.List;
import java.util.Set;

public class OperatorInstance {
    private static Set<String> operators;
    private static List<Operator> operatorList;

    public static void create(Set<String> data){
        operators = data;
    }
    public static Set<String> get(){
        return operators;
    }
    public static void addOperators(List<Operator> operators){
        operatorList.addAll(operators);
    }
    public static List<Operator> getOperatorList(){
        return operatorList;
    }
}
