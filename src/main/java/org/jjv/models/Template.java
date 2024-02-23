package org.jjv.models;

import org.jjv.utils.TemplateType;

public record Template(String name, String[] values, String description, String code, TemplateType templateType) {
    public Template(String name, String description, String code, TemplateType templateType) {
        this(name, new String[0], description, code, templateType);
    }
}
