package com.tlherr.Model;

public abstract class AbstractModel {
    protected int id;

    public abstract void save();
    public abstract void delete();

    public int getId() {
        return this.id;
    }
}
