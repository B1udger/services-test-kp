package com.example.kindergarten.model;

/**
 * Представя дете, което кандидатства за място в детска градина.
 * Всяко дете има уникален номер, име и няколко „флага“, които му дават
 * допълнителни точки при класирането.
 */
public class Child {
    /** Уникален код на детето (примерно "C1", не може да е празен). */
    private final String id;

    /** Как се казва детето. */
    private final String name;

    /** дали е с увреждане (дава +2 точки) */
    private boolean hasDisability;

    /** дали е близнак (дава +1 точка) */
    private boolean isTwin;

    /** id на брат/сестра (дава +1 точка), или null ако няма */
    private String siblingId;

    /**
     * Създава дете с всички нужни данни.
     *
     * @param id            уникален код (задължително)
     * @param name          име (задължително)
     * @param hasDisability true ако има увреждане
     * @param isTwin        true ако е близнак
     * @param siblingId     код на брат/сестра, ако има; иначе null
     * @throws IllegalArgumentException ако id или name са празни
     */
    public Child(String id, String name, boolean hasDisability, boolean isTwin, String siblingId) {
        if (id == null || id.isBlank())    throw new IllegalArgumentException("№ на детето не може да е празен");
        if (name == null || name.isBlank())throw new IllegalArgumentException("Име не може да е празно");
        this.id = id;
        this.name = name;
        this.hasDisability = hasDisability;
        this.isTwin = isTwin;
        this.siblingId = (siblingId != null && !siblingId.isBlank()) ? siblingId : null;
    }

    /** Връща кода на детето. */
    public String getId() { return id; }

    /** Връща името на детето. */
    public String getName() { return name; }

    /** Да/Не – дали детето е с увреждане. */
    public boolean isHasDisability() { return hasDisability; }

    /** Задава дали е с увреждане. */
    public void setHasDisability(boolean hasDisability) { this.hasDisability = hasDisability; }

    /** Да/Не – дали детето е близнак. */
    public boolean isTwin() { return isTwin; }

    /** Задава дали детето е близнак. */
    public void setTwin(boolean isTwin) { this.isTwin = isTwin; }

    /** Връща кода на брат/сестра или null. */
    public String getSiblingId() { return siblingId; }

    /** Задава или премахва кода на брат/сестра. */
    public void setSiblingId(String siblingId) {
        this.siblingId = (siblingId != null && !siblingId.isBlank()) ? siblingId : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Child other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }

    @Override
    public String toString() {
        return "Child{id='" + id + "', name='" + name + "', disability=" + hasDisability +
                ", twin=" + isTwin + ", siblingId='" + siblingId + "'}";
    }
}
