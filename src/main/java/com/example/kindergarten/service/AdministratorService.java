package com.example.kindergarten.service;

import com.example.kindergarten.model.Administrator;
import com.example.kindergarten.model.Child;
import com.example.kindergarten.model.Parent;
import com.example.kindergarten.repository.ChildRepository;
import com.example.kindergarten.repository.ParentRepository;

import java.util.List;

/**
 * Стандартна имплементация на {@link Administrator},
 * която работи върху in-memory репозиторита и използва RankingService.
 */
public class AdministratorService implements Administrator {
    private final ChildRepository childRepo;
    private final ParentRepository parentRepo;
    private final RankingService rankingService;

    public AdministratorService(ChildRepository childRepo, ParentRepository parentRepo) {
        if (childRepo == null || parentRepo == null) {
            throw new IllegalArgumentException("Не може да има null репозиторий");
        }
        this.childRepo = childRepo;
        this.parentRepo = parentRepo;
        this.rankingService = new RankingService(childRepo, parentRepo);
    }

    @Override
    public void addChild(Child child) {
        if (child == null) throw new IllegalArgumentException("Child cannot be null");
        childRepo.save(child);
    }

    @Override
    public void removeChild(String childId) {
        childRepo.deleteById(childId);
    }

    @Override
    public void addParent(Parent parent) {
        if (parent == null) throw new IllegalArgumentException("Parent cannot be null");
        parentRepo.save(parent);
    }

    @Override
    public void removeParent(String parentId) {
        parentRepo.deleteById(parentId);
    }

    @Override
    public List<Child> getRanking() {
        return rankingService.rankAllChildren();
    }
}
