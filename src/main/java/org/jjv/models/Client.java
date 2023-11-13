package org.jjv.models;

public record Client(Integer id, String name, Integer organization) {
    public Client(String name, Integer organization) {
        this(null, name, organization);
    }
}
