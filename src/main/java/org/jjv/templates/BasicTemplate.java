package org.jjv.templates;

import org.jjv.models.Template;
import java.util.ArrayList;
import java.util.List;

import static org.jjv.utils.TemplateType.*;

public class BasicTemplate {
    private static final Template CFDIModelTemplate = new Template(
            "Plantilla de CFDISpara carga de cuentas",
            Headers.CFDIFileHeaders,
            "Muestra las columnas que debe contener y el orden de estas",
            "A001", BASIC);

    private static final Template configTemplate = new Template(
            "Plantilla para la craga de configuracion",
            Headers.ConfigFileHeaders,
            "Muestra los elementos necesarion para poder registrar de forma masiva la configuración de las clientes",
            "A002", BASIC
    );

    public static List<Template> gatBasicTemplates(){
        List<Template> templates = new ArrayList<>();
        templates.add(CFDIModelTemplate);
        templates.add(configTemplate);

        return templates;
    }
}
