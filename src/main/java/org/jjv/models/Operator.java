package org.jjv.models;

public record Operator (Integer id, String name, String rfc, Integer client){
    public Operator(String name, String rfc, Integer client) {
        this(null, name, rfc, client);
    }
}
