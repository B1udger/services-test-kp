package com.example.kindergarten.repository;

import com.example.kindergarten.model.Parent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryParentRepositoryTest {

    private InMemoryParentRepository repo;
    private Parent p1, p2;

    @BeforeEach
    void setUp() {
        repo = new InMemoryParentRepository();
        p1 = new Parent("P1", "Parent One", Arrays.asList("C1", "C2"));
        p2 = new Parent("P2", "Parent Two", null);
        repo.save(p1);
        repo.save(p2);
    }

    @Test
    void save_null_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> repo.save(null));
    }

    @Test
    void findAll_returnsAllSavedParents() {
        List<Parent> all = repo.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(p1));
        assertTrue(all.contains(p2));
    }

    @Test
    void findById_existingId_returnsOptionalWithParent() {
        Optional<Parent> opt = repo.findById("P1");
        assertTrue(opt.isPresent());
        assertEquals(p1, opt.get());
    }

    @Test
    void findById_nonExistingId_returnsEmptyOptional() {
        Optional<Parent> opt = repo.findById("UNKNOWN");
        assertFalse(opt.isPresent());
    }

    @Test
    void existsById_existingAndNonExisting() {
        assertTrue(repo.existsById("P2"));
        assertFalse(repo.existsById("XXX"));
    }

    @Test
    void deleteById_existing_removesParent() {
        assertTrue(repo.existsById("P1"));
        repo.deleteById("P1");
        assertFalse(repo.existsById("P1"));
        assertEquals(1, repo.findAll().size());
    }
}
