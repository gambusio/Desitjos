package com.github.gambusio.Desitjos.repositories;

import com.github.gambusio.Desitjos.entities.Item;

import java.util.ArrayList;

public interface ItemDB {
    ArrayList<Item> getItems();
    Item searchItem(Item item);
    void insertItem(Item item);
    void deleteItem(Item item);
}
