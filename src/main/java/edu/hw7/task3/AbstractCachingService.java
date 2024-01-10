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
        cachedNames.get(cachedIds.get(id).name()).remove(cachedIds.get(id));
        cachedAddresses.get(cachedIds.get(id).address()).remove(cachedIds.get(id));
        cachedPhones.get(cachedIds.get(id).phoneNumber()).remove(cachedIds.get(id));
        cachedIds.remove(id);
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
