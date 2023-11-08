package edu.hw3.task5;

import java.util.Comparator;

public class AscComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact firstContact, Contact secondContact) {
        String comparingName1 = !firstContact.surname().isEmpty() ? firstContact.surname() : firstContact.name();
        String comparingName2 = !secondContact.surname().isEmpty() ? secondContact.surname() : secondContact.name();
        return comparingName1.compareTo(comparingName2);
    }
}
