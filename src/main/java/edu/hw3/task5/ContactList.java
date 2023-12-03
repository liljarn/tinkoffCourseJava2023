package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ContactList {
    private static final String ASC_ORDER = "ASC";
    private static final String DESC_ORDER = "DESC";

    private ContactList() {
    }

    private static Contact getInfo(String[] personInfo) {
        if (personInfo.length == 1) {
            return new Contact(personInfo[0]);
        }
        return new Contact(personInfo[0], personInfo[1]);
    }

    public static List<Contact> parseContact(String[] peopleNames, String sortType) {
        if (peopleNames == null || peopleNames.length == 0) {
            return Collections.emptyList();
        }
        List<Contact> contacts = new ArrayList<>();
        for (String name : peopleNames) {
            if (!name.matches("^([A-Z][a-z]*)( [A-Z][a-z]*)?$")) {
                throw new IllegalArgumentException("Incorrect name or surname");
            }
            contacts.add(getInfo(name.split(" ")));
        }
        if (sortType.equals(ASC_ORDER)) {
            contacts.sort(new AscComparator());
        }
        if (sortType.equals(DESC_ORDER)) {
            contacts.sort(new DescComparator());
        }
        return contacts;
    }
}
