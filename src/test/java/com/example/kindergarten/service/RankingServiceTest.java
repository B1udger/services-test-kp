package com.example.kindergarten.service;

import com.example.kindergarten.model.Child;
import com.example.kindergarten.model.Parent;
import com.example.kindergarten.repository.ChildRepository;
import com.example.kindergarten.repository.InMemoryChildRepository;
import com.example.kindergarten.repository.InMemoryParentRepository;
import com.example.kindergarten.repository.ParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RankingServiceTest {

    private ChildRepository childRepo;
    private ParentRepository parentRepo;
    private RankingService rankingService;

    private Child childNoExtras;
    private Child childDisability;
    private Child childTwin;
    private Child childSibling;
    private Child childAll;

    private Parent parent1;
    private Parent parent2;

    @BeforeEach
    void setUp() {
        childRepo = new InMemoryChildRepository();
        parentRepo = new InMemoryParentRepository();

        childNoExtras   = new Child("C1", "Иван Иванов", false, false, null);
        childDisability = new Child("C2", "Мария Петрова", true,  false, null);
        childTwin       = new Child("C3", "Георги Стоянов", false, true,  null);
        childSibling    = new Child("C4", "Анна Георгиева", false, false, "C1");
        childAll        = new Child("C5", "Петър Димитров", true,  true,  "C2");

        childRepo.save(childNoExtras);
        childRepo.save(childDisability);
        childRepo.save(childTwin);
        childRepo.save(childSibling);
        childRepo.save(childAll);

        // Само C5 ще получава родителски точки
        parent1 = new Parent("P1", "Родител 1", Arrays.asList("C5"));
        parent2 = new Parent("P2", "Родител 2", Arrays.asList("C5"));

        parentRepo.save(parent1);
        parentRepo.save(parent2);

        rankingService = new RankingService(childRepo, parentRepo);
    }

    @Test
    void calculatePoints_childWithoutAnyExtras_shouldReturnZero() {
        assertEquals(0, rankingService.calculatePointsForChild(childNoExtras));
    }

    @Test
    void calculatePoints_childWithDisability_shouldReturnTwo() {
        assertEquals(2, rankingService.calculatePointsForChild(childDisability));
    }

    @Test
    void calculatePoints_childWithTwin_shouldReturnOne() {
        assertEquals(1, rankingService.calculatePointsForChild(childTwin));
    }

    @Test
    void calculatePoints_childWithSiblingOnly_shouldReturnOne() {
        assertEquals(1, rankingService.calculatePointsForChild(childSibling));
    }

    @Test
    void calculatePoints_childWithAllCriteria_shouldReturnSix() {
        assertEquals(6, rankingService.calculatePointsForChild(childAll));
    }

    @Test
    void calculatePoints_childNotInRepo_shouldThrow() {
        Child unknown = new Child("CX", "Непознато", false, false, null);
        assertThrows(IllegalArgumentException.class,
                () -> rankingService.calculatePointsForChild(unknown));
    }

    @Test
    void rankAllChildren_shouldSortCorrectly() {
        List<Child> ranked = rankingService.rankAllChildren();
        assertEquals(childAll, ranked.get(0));
        assertEquals(childDisability, ranked.get(1));
        List<Child> mid = ranked.subList(2, 4);
        assertTrue(mid.contains(childTwin) && mid.contains(childSibling));
        assertEquals(childNoExtras, ranked.get(4));
    }

    @Test
    void rankAllChildren_equalPointsRandomized() {
        Child a = new Child("A", "А", false, false, null);
        Child b = new Child("B", "Б", false, false, null);
        ChildRepository cr = new InMemoryChildRepository();
        ParentRepository pr = new InMemoryParentRepository();
        cr.save(a);
        cr.save(b);
        RankingService rs = new RankingService(cr, pr);

        boolean seen = false;
        for (int i = 0; i < 20; i++) {
            List<Child> list = rs.rankAllChildren();
            if (list.get(0).equals(b) && list.get(1).equals(a)) {
                seen = true;
                break;
            }
        }
        assertTrue(seen);
    }

    @Test
    void rankAllChildren_emptyRepo_shouldReturnEmpty() {
        RankingService empty = new RankingService(
                new InMemoryChildRepository(),
                new InMemoryParentRepository());
        assertTrue(empty.rankAllChildren().isEmpty());
    }
}
