package com.example.kindergarten.service;

import com.example.kindergarten.model.Child;
import com.example.kindergarten.model.Parent;
import com.example.kindergarten.repository.InMemoryChildRepository;
import com.example.kindergarten.repository.InMemoryParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorServiceTest {

    private AdministratorService admin;
    private Child c1, c2;
    private Parent p1;

    @BeforeEach
    void setUp() {
        var childRepo = new InMemoryChildRepository();
        var parentRepo = new InMemoryParentRepository();
        admin = new AdministratorService(childRepo, parentRepo);

        c1 = new Child("C1", "Иван", false, false, null);
        c2 = new Child("C2", "Мария", true, false, null);
        p1 = new Parent("P1", "Родител", List.of("C2"));
    }

    @Test
    void addAndRemoveChild() {
        // първо repo е празно
        assertTrue(admin.getRanking().isEmpty());

        admin.addChild(c1);
        admin.addChild(c2);

        List<Child> list = admin.getRanking();
        // и двете са налични
        assertEquals(2, list.size());
        assertTrue(list.contains(c1) && list.contains(c2));

        // премахваме C1
        admin.removeChild("C1");
        list = admin.getRanking();
        assertEquals(1, list.size());
        assertEquals(c2, list.get(0));
    }

    @Test
    void addAndRemoveParentAffectsRanking() {
        admin.addChild(c1);
        admin.addChild(c2);

        // без родители: disability дава 2, останалите 0
        List<Child> before = admin.getRanking();
        assertEquals(c2, before.get(0));
        assertEquals(c1, before.get(1));

        // добавяме родител на C1 --> дава +1 точка
        admin.addParent(p1);
        List<Child> after = admin.getRanking();
        // сега C1 (1 точка) е пред C2 (2 точки -> остава зад кавички? actually 2 >1)
        // поправяме очакването: C2 има 2, C1 има 1, значи остава преди или зад?
        // Disability=2 > C1=1 => C2 пред C1
        assertEquals(c2, after.get(0));
        assertEquals(c1, after.get(1));

        // премахваме родител -> връщаме първоначално положение
        admin.removeParent("P1");
        List<Child> restore = admin.getRanking();
        assertEquals(before, restore);
    }

    @Test
    void getRankingEmpty() {
        assertTrue(admin.getRanking().isEmpty());
    }

    @Test
    void constructorRejectsNullRepos() {
        assertThrows(IllegalArgumentException.class, () -> new AdministratorService(null, new InMemoryParentRepository()));
        assertThrows(IllegalArgumentException.class, () -> new AdministratorService(new InMemoryChildRepository(), null));
    }
}
