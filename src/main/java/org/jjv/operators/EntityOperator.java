package org.jjv.operators;

import org.jjv.instances.OperatorInstance;
import org.jjv.models.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntityOperator {
    public static List<Operator> verify(List<Operator> target){
        List<Operator> unregisteredOperators = new ArrayList<>();
        Set<String> registeredOperators = OperatorInstance.get();

        target.forEach(t -> {
            if (!registeredOperators.contains(t.rfc()))
                unregisteredOperators.add(t);
        });

        return unregisteredOperators;
    }
}
