package org.jjv.templates;

import org.jjv.models.Template;
import java.util.ArrayList;
import java.util.List;

import static org.jjv.utils.TemplateType.*;

public class BasicTemplate {
    private static final Template CFDIModelTemplate = new Template(
            "Plantilla de CFDIS para carga de cuentas",
            Headers.CFDIFileHeaders,
            "Muestra las columnas que debe \ncontener y el orden de estas",
            "A001", BASIC);

    private static final Template configTemplate = new Template(
            "Plantilla para la carga de configuracion",
            Headers.ConfigFileHeaders,
            "Muestra los elementos necesarios \npara registrar de forma masiva \nla configuracion de los clientes",
            "A002", BASIC
    );

    public static List<Template> getBasicTemplates(){
        List<Template> templates = new ArrayList<>();
        templates.add(CFDIModelTemplate);
        templates.add(configTemplate);

        return templates;
    }
}
