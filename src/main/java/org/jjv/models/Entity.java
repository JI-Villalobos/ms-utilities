package org.jjv.models;

import org.jjv.utils.EntityNature;

public class Entity {
    private String name;
    private String rfc;
    private EntityNature nature;
    private boolean isProvider;
    private boolean isClient;
    private boolean isContractor;
    private String regime;
    private String description;

    public Entity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient, boolean isContractor, String regime, String description) {
        this.name = name;
        this.rfc = rfc;
        this.nature = nature;
        this.isProvider = isProvider;
        this.isClient = isClient;
        this.isContractor = isContractor;
        this.regime = regime;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public EntityNature getNature() {
        return nature;
    }

    public void setNature(EntityNature nature) {
        this.nature = nature;
    }

    public boolean isProvider() {
        return isProvider;
    }

    public void setProvider(boolean provider) {
        isProvider = provider;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public boolean isContractor() {
        return isContractor;
    }

    public void setContractor(boolean contractor) {
        isContractor = contractor;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
