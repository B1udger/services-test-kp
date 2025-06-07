package com.example.kindergarten.model;

import java.util.*;

/**
 * Представя родител, който може да има едно или повече деца.
 * Родителят се идентифицира по уникално ID и име.
 */
public class Parent {
    /** Уникален код на родителя (примерно "P1"). */
    private final String id;

    /** Името на родителя. */
    private final String name;

    /** Списък с ID-тата на децата му. */
    private final List<String> childrenIds;

    /**
     * Създава родител.
     *
     * @param id          уникален код (задължително)
     * @param name        име (задължително)
     * @param childrenIds началeн списък с деца (може да е null)
     * @throws IllegalArgumentException ако id или name са празни
     */
    public Parent(String id, String name, List<String> childrenIds) {
        if (id == null || id.isBlank())    throw new IllegalArgumentException("№ на родителя не може да е празен");
        if (name == null || name.isBlank())throw new IllegalArgumentException("Име не може да е празно");
        this.id = id;
        this.name = name;
        this.childrenIds = (childrenIds != null)
                ? new ArrayList<>(childrenIds)
                : new ArrayList<>();
    }

    /** Връща кода на родителя. */
    public String getId() { return id; }

    /** Връща името на родителя. */
    public String getName() { return name; }

    /** Връща списък с ID-тата на децата (само за четене). */
    public List<String> getChildrenIds() {
        return Collections.unmodifiableList(childrenIds);
    }

    /**
     * Добавя дете по неговия код, ако още не е в списъка.
     *
     * @param childId код на детето (не може да е празен)
     */
    public void addChild(String childId) {
        if (childId == null || childId.isBlank())
            throw new IllegalArgumentException("№ на детето не може да е празен");
        if (!childrenIds.contains(childId)) {
            childrenIds.add(childId);
        }
    }

    /** Премахва дете по код; ако го няма – нищо не става. */
    public void removeChild(String childId) {
        childrenIds.remove(childId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parent other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }

    @Override
    public String toString() {
        return "Parent{id='" + id + "', name='" + name + "', children=" + childrenIds + "}";
    }
}
