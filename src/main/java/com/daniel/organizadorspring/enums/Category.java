package com.daniel.organizadorspring.enums;

public enum Category {
    CONTAS_A_PAGAR("Contas a Pagar"), LAZER("Lazer"), ENTRADA("Entrada");

    private String value;

    private Category(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
    
}
