package com.example.kindergarten.repository;

import com.example.kindergarten.model.Parent;
import java.util.*;

/**
 * В паметта имплементация на {@link ParentRepository}.
 */
public class InMemoryParentRepository implements ParentRepository {
    private final Map<String, Parent> storage = new HashMap<>();

    @Override
    public void save(Parent parent) {
        if (parent == null) throw new IllegalArgumentException("Parent cannot be null");
        storage.put(parent.getId(), parent);
    }

    @Override
    public List<Parent> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Parent> findById(String id) {
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
