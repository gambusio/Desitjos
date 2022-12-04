package com.github.gambusio.Desitjos.repositories;

import com.github.gambusio.Desitjos.entities.Item;
import com.github.gambusio.Desitjos.entities.ItemType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemDBCsv implements ItemDB {
    public final String dataFile = "dataFile.csv";
    @Override
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            Scanner scanner = new Scanner((new File(dataFile)));

            while (scanner.hasNext()) {
                String sActualItem = scanner.nextLine();
                String[]  parts = sActualItem.split(";");
                Item item = new Item();
                item.setsName(parts[0]);
                item.setsDescription(parts[1]);
                item.setsUrl(parts[2]);
                item.setsPicUrl(parts[3]);
                item.setItemType(ItemType.valueOf(parts[4]));
                item.setPrice(parts[5]);
                items.add(item);
            }

        } catch (FileNotFoundException e) {
            System.out.println("[Read Error] " + e.getMessage());
        }
        return items;

    }

    /**
     * Search an item and if is not in the datafile then return null
     * An item is identified by having a unique sURL
     * @param item
     * @return
     */
    @Override
    public Item searchItem(Item item) {
        ArrayList<Item> items = getItems();

        for (Item i : items) {
            if (i.getsUrl().equalsIgnoreCase(item.getsUrl())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void insertItem(Item item) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dataFile, true);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(formatItem(item));
            printStream.flush();
            printStream.close();
        } catch (Exception e) {
            System.out.println("[Write Error]: " + e.getMessage());
        }
    }

    @Override
    public void deleteItem(Item item) {
        ArrayList<Item> items = getItems();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getsUrl().equalsIgnoreCase(item.getsUrl())) {
                items.remove(i);
            }
        }

        try {
            PrintStream printStream = new PrintStream(dataFile);

            for (Item actualItem : items) {
                printStream.println(formatItem(actualItem));
            }
            printStream.flush();
            printStream.close();

        } catch (Exception e) {
            System.out.println("[Write Error]" + e.getMessage());
        }
    }

    private String formatItem(Item item) {
        return item.getsName() + ";"
                + item.getsDescription() + ";"
                + item.getsUrl() + ";"
                + item.getsPicUrl() + ";"
                + item.getItemType() + ";"
                + item.getPrice();
    }
}
