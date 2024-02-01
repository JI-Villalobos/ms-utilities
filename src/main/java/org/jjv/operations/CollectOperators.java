package org.jjv.operations;

import org.jjv.instances.ClientInstance;
import org.jjv.instances.EntityInstance;
import org.jjv.instances.OperatorInstance;
import org.jjv.models.ClientEntity;
import org.jjv.models.ExtendedProviderEntity;
import org.jjv.models.Operator;

import java.util.ArrayList;
import java.util.List;

public class CollectOperators {
    static int clientId = ClientInstance.getSingle().id();
    public static void collectFromClientEntities(){
        List<ClientEntity> clientEntities = EntityInstance.getClientEntities();
        List<Operator> operators = new ArrayList<>();
        clientEntities.forEach( clientEntity -> {
            operators.add(new Operator(clientEntity.getName(), clientEntity.getRfc(), clientId));
        });
        OperatorInstance.addOperators(operators);
    }

    public static void collectFromProviderEntities(){
        List<ExtendedProviderEntity> providerEntities = EntityInstance.getExtendedProviderEntityList();
        List<Operator> operators = new ArrayList<>();
        providerEntities.forEach(providerEntity -> {
            operators.add(new Operator(providerEntity.getName(), providerEntity.getRfc(), clientId));
        });
        OperatorInstance.addOperators(operators);
    }
}
