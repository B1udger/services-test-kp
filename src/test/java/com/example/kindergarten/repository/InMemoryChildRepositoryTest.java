package com.example.kindergarten.repository;

import com.example.kindergarten.model.Child;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryChildRepositoryTest {

    private InMemoryChildRepository repo;
    private Child c1, c2;

    @BeforeEach
    void setUp() {
        repo = new InMemoryChildRepository();
        c1 = new Child("C1", "Ivan", false, false, null);
        c2 = new Child("C2", "Maria", true, false, null);
        repo.save(c1);
        repo.save(c2);
    }

    @Test
    void save_null_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> repo.save(null));
    }

    @Test
    void findAll_returnsAllSavedChildren() {
        List<Child> all = repo.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(c1));
        assertTrue(all.contains(c2));
    }

    @Test
    void findById_existingId_returnsOptionalWithChild() {
        Optional<Child> opt = repo.findById("C1");
        assertTrue(opt.isPresent());
        assertEquals(c1, opt.get());
    }

    @Test
    void findById_nonExistingId_returnsEmptyOptional() {
        Optional<Child> opt = repo.findById("UNKNOWN");
        assertFalse(opt.isPresent());
    }

    @Test
    void existsById_existingAndNonExisting() {
        assertTrue(repo.existsById("C2"));
        assertFalse(repo.existsById("XXX"));
    }

    @Test
    void deleteById_existing_removesChild() {
        assertTrue(repo.existsById("C1"));
        repo.deleteById("C1");
        assertFalse(repo.existsById("C1"));
        assertEquals(1, repo.findAll().size());
    }
}
