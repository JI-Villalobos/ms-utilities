package org.jjv.generators;

import org.jjv.instances.AccountInstance;
import org.jjv.instances.EntityInstance;
import org.jjv.instances.PathInstance;
import org.jjv.models.Account;
import org.jjv.models.ClientEntity;
import org.jjv.models.ExtendedProviderEntity;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TxtGenerator {
    static String sep = "";
    public static void generateAccountFile() throws IOException {
        List<Account> accounts = AccountInstance.get();
        PrintWriter printWriter = new PrintWriter(PathInstance.getPath(), StandardCharsets.UTF_8);
        accounts.forEach(account -> {
            printWriter.println(
                    account.subAccount() + "," + account.name() + "," + account.accountType() + "," + account.accountNature() + "," + account.satCode()
            );
        });
        printWriter.close();
    }

    public static void generateOperatorsFile() throws IOException{
        List<ClientEntity> clientEntities = EntityInstance.getClientEntities();
        List<ExtendedProviderEntity> providerEntities = EntityInstance.getExtendedProviderEntityList();
        PrintWriter printWriter = new PrintWriter(PathInstance.getPath(), StandardCharsets.UTF_8);
        if (clientEntities != null){
            clientEntities.forEach(client -> {
                printWriter.println(
                        client.getName() + "," + client.getRfc() + "," + client.getNature() + "," + client.getRegime()
                                + "," + "04" + "," + setResult(client.isProvider()) + "," + sep + "," + sep + "," + sep + "," + sep + "," + sep + "," + sep + "," + sep + ","
                                + setResult(client.isClient()) + "," + client.getClientAccount()  + "," + client.getIncomeAccount() + ","
                                + client.getPolicyType() + "," + client.getDescription() + "," + setResult(client.isContractor())
                );
            });
        }
        if (providerEntities != null){
            providerEntities.forEach(provider -> {
                printWriter.println(
                        provider.getName() + "," + provider.getRfc() + "," + provider.getNature() + "," + provider.getRegime()
                                + "," + provider.getNationality() + "," + setResult(provider.isProvider()) + "," + provider.getProviderAccount()
                                + "," + provider.getExpenseAccount() + "," + provider.getPolicyType() + "," + provider.getDescription()
                                + "," + provider.getCountry() + "," + provider.getDIOTOperation() + "," + provider.getTaxKey()
                                + "," + setResult(provider.isClient()) + "," + sep + "," + sep + "," + sep + "," + sep + "," + setResult(provider.isContractor())

                );
            });
        }
        printWriter.close();
    }

    public static void generateOperatorExampleModel() throws IOException {
        PrintWriter printWriter = new PrintWriter(PathInstance.getPath(), StandardCharsets.UTF_8);

        printWriter.println("Objeto=Terceros");
        printWriter.println("Delimitador=,");
        printWriter.println("CalificadorTexto=\"");
        printWriter.println("FormatoFechas=dd/mm/aaaa");
        printWriter.println("SeparadorLineas=|");
        printWriter.println("ExportarTipoReg=N");
        printWriter.println("PermitirImportar=S");
        printWriter.println("Campos_1=NOMBRE(200),RFC(18),TIPO_PERSONA(1),CLAVE_REGIMEN_FISCAL(3),TIPO(2)");
        printWriter.println("Campos_1=ES_PROVEEDOR(1),CUENTA_CXP(30),CUENTA_CXP_CUADRE(30),CLAVE_TIPO_POLIZA_COMPRAS(1),DESC_POLIZA_COMPRAS(200)");
        printWriter.println("Campos_1=PAIS_RESIDENCIA_DIOT(2),OPERACION_PREDET_DIOT(2),CLAVE_IMPUESTO_PREDET(20),ES_CLIENTE(1),CUENTA_CXC(30)");
        printWriter.println("Campos_1=CUENTA_CXC_CUADRE(30),CLAVE_TIPO_POLIZA_VENTAS(1),DESC_POLIZA_VENTAS(200),ES_CONTRATANTE(1)");

        printWriter.close();
    }

    private static String setResult(boolean result){
        if (result){
            return "S";
        } else {
            return "N";
        }
    }
}
