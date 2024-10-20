package org.gk2k23.models;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class Item {
    private String name;
    private List<Item> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getChildren() {
        return children;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }
}
