package com.example.kindergarten.service;

import com.example.kindergarten.model.Child;
import com.example.kindergarten.model.Parent;
import com.example.kindergarten.repository.ChildRepository;
import com.example.kindergarten.repository.ParentRepository;

import java.util.*;

/**
 * Клас, който изчислява точките на всяко дете и ги подрежда
 * според набраните точки (най-много – най-отпред).
 * Ако две деца имат еднакъв брой, ги разбърква на случаен принцип.
 */
public class RankingService {
    private final ChildRepository childRepo;
    private final ParentRepository parentRepo;
    private final Random rnd = new Random();

    /**
     * @param childRepo  къде са записани децата
     * @param parentRepo къде са записани родителите
     */
    public RankingService(ChildRepository childRepo, ParentRepository parentRepo) {
        if (childRepo == null || parentRepo == null)
            throw new IllegalArgumentException("И двете хранилища са задължителни");
        this.childRepo = childRepo;
        this.parentRepo = parentRepo;
    }

    /**
     * Пресмята колко точки има единично дете.
     */
    public int calculatePointsForChild(Child c) {
        if (c == null) throw new IllegalArgumentException("Дете трябва да бъде предоставено");
        if (!childRepo.existsById(c.getId())) throw new IllegalArgumentException("Непознато дете");
        int pts = 0;
        if (c.isHasDisability()) pts += 2;
        if (c.isTwin())            pts += 1;
        if (c.getSiblingId() != null) pts += 1;
        for (Parent p : parentRepo.findAll()) {
            if (p.getChildrenIds().contains(c.getId())) {
                pts += 1; // по 1 точка за всеки „работещ“ родител
            }
        }
        return pts;
    }

    /**
     * Връща всички деца, сортирани по точки
     * (и разбърква равните).
     */
    public List<Child> rankAllChildren() {
        List<Child> a = new ArrayList<>(childRepo.findAll());
        a.sort((c1, c2) -> {
            int p1 = calculatePointsForChild(c1);
            int p2 = calculatePointsForChild(c2);
            if (p1 != p2) return Integer.compare(p2, p1);
            return rnd.nextBoolean() ? -1 : 1;
        });
        return a;
    }
}
