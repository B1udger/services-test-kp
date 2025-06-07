package com.example.kindergarten.repository;

import com.example.kindergarten.model.Child;
import java.util.*;

/**
 * В паметта имплементация на {@link ChildRepository}.
 */
public class InMemoryChildRepository implements ChildRepository {
    private final Map<String, Child> storage = new HashMap<>();

    @Override
    public void save(Child child) {
        if (child == null) throw new IllegalArgumentException("Child cannot be null");
        storage.put(child.getId(), child);
    }

    @Override
    public List<Child> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Child> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return storage.containsKey(id);
    }
}
