package com.github.gambusio.Desitjos.repositories;

public class ItemDBFactory {
    private final String sDbType;

    public ItemDBFactory(String sDbType) {
        this.sDbType = sDbType.toUpperCase();
    }

    public ItemDB getDatabaseInstance() {
        if (sDbType.equals("CSV")) {
            return new ItemDBCsv();
        }
        return null;
    }
}
