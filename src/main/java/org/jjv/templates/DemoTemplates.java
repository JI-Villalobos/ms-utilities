package org.jjv.templates;

import org.jjv.models.Template;

import java.util.ArrayList;
import java.util.List;

import static org.jjv.utils.TemplateType.*;

public class DemoTemplates {
    private static final Template configPrefillTemplate = new Template(
            "Configuracion de clientes",
            Headers.ConfigFileHeaders,
            "Ejemplo de prellenado del archivo de \nconfiguracion de clientes para \nsu carga masiva",
            "E001", DEMO
    );

    public static List<Template> getDemoTemplates(){
        List<Template> templates = new ArrayList<>();
        templates.add(configPrefillTemplate);

        return templates;
    }
}
