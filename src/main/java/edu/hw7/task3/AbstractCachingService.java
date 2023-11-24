package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractCachingService implements PersonDatabase {
    private final Map<Integer, Person> cachedIds = new HashMap<>();
    private final Map<String, List<Person>> cachedNames = new HashMap<>();
    private final Map<String, List<Person>> cachedAddresses = new HashMap<>();
    private final Map<String, List<Person>> cachedPhones = new HashMap<>();

    @Override
    public void add(Person person) {
        cachedIds.put(person.id(), person);
        cachedNames.computeIfAbsent(person.name(), name -> new ArrayList<>()).add(person);
        cachedAddresses.computeIfAbsent(person.address(), address -> new ArrayList<>()).add(person);
        cachedPhones.computeIfAbsent(person.phoneNumber(), phone -> new ArrayList<>()).add(person);
    }

    @Override
    public void delete(int id) {
        Person person = cachedIds.remove(id);
        if (person == null) {
            return;
        }
        List<Person> names = cachedNames.get(person.name());
        if (names != null) {
            names.removeIf(p -> p.id() == id);
            if (names.isEmpty()) {
                cachedNames.remove(person.name());
            }
        }
        List<Person> addresses = cachedAddresses.get(person.address());
        if (addresses != null) {
            addresses.removeIf(p -> p.id() == id);
            if (addresses.isEmpty()) {
                cachedAddresses.remove(person.address());
            }
        }
        List<Person> phones = cachedPhones.get(person.phoneNumber());
        if (phones != null) {
            phones.removeIf(p -> p.id() == id);
            if (phones.isEmpty()) {
                cachedPhones.remove(person.phoneNumber());
            }
        }
    }

    @Override
    public List<Person> findByName(String name) {
        if (!cachedNames.containsKey(name)) {
            return null;
        }
        return cachedNames.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        if (!cachedAddresses.containsKey(address)) {
            return null;
        }
        return cachedAddresses.get(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        if (!cachedPhones.containsKey(phone)) {
            return null;
        }
        return cachedPhones.get(phone);
    }
}
