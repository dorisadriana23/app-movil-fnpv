package com.adriana.fnpv21.Entity;

public class Cargo {
    private String id;
    private String code;
    private String descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
