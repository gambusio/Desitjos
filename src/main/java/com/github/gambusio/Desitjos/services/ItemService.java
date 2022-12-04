package com.github.gambusio.Desitjos.services;

import com.github.gambusio.Desitjos.entities.Item;
import com.github.gambusio.Desitjos.repositories.ItemDB;
import com.github.gambusio.Desitjos.repositories.ItemDBCsv;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemService {
    ItemDB itemDB = new ItemDBCsv();

    public ItemService() {
        //TODO: utilizar el patron Factory para definir el tipo de base de datos a utilizar
    }

    public ArrayList<Item> getItems() { return itemDB.getItems();}

    public void insertItem(Item item) {
        if (searchItem(item.getsUrl()) != null) {
            return;
        }
        //TODO: utilizar el patrón Builder para inicializar las propiedades del item
        itemDB.insertItem(item);
    }

    public Item searchItem(String sUrl) {
        Item item = new Item();
        item.setsUrl(sUrl);
        return itemDB.searchItem(item);
    }

    public void deleteItem(String sUrl) {
        Item item = new Item();
        item.setsUrl(sUrl);
        itemDB.deleteItem(item);
    }
}
