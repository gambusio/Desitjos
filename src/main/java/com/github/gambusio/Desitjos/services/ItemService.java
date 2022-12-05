package com.github.gambusio.Desitjos.services;

import com.github.gambusio.Desitjos.entities.Item;
import com.github.gambusio.Desitjos.entities.ItemBuilder;
import com.github.gambusio.Desitjos.repositories.ItemDB;
import com.github.gambusio.Desitjos.repositories.ItemDBFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemService {
    private final ItemDB itemDB;

    public ItemService() {
        String sDbType = "CSV";
        ItemDBFactory itemDBFactory = new ItemDBFactory(sDbType);
        itemDB = itemDBFactory.getDatabaseInstance();
    }

    public ArrayList<Item> getItems() { return itemDB.getItems();}

    public void insertItem(Item item) {
        if (searchItem(item.getsUrl()) != null) {
            return;
        }
        item = checkItem(item);
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

    private Item checkItem(Item item) {
        ItemBuilder itemBuilder = new ItemBuilder(item.getsUrl());

        return itemBuilder.withName(item.getsName())
                .withDescription(item.getsDescription())
                .withPicUrl(item.getsPicUrl())
                .withType(item.getItemType())
                .withPrice(item.getPrice().toString()).build();
    }
}
