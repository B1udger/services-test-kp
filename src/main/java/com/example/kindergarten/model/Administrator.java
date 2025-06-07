package com.example.kindergarten.model;

import java.util.List;

/**
 * Представя администратор, който може да управлява
 * децата и родителите в системата и да извежда ранкинга.
 */
public interface Administrator {
    /**
     * Добавя ново дете в системата.
     * @param child детето за добавяне (не може да е null)
     */
    void addChild(Child child);

    /**
     * Премахва дете по неговия код.
     * @param childId код на детето (не може да е празен или null)
     */
    void removeChild(String childId);

    /**
     * Добавя нов родител в системата.
     * @param parent родителят за добавяне (не може да е null)
     */
    void addParent(Parent parent);

    /**
     * Премахва родител по неговия код.
     * @param parentId код на родителя (не може да е празен или null)
     */
    void removeParent(String parentId);

    /**
     * Връща списък от всички кандидати (деца) сортирани по точки.
     * @return сортиран по точки списък от деца
     */
    List<Child> getRanking();
}
