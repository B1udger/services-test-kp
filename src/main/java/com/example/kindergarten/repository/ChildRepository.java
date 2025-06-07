package com.example.kindergarten.repository;

import com.example.kindergarten.model.Child;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс за достъп до данните за {@link Child}.
 * Реализира CRUD операции в хранилище.
 */
public interface ChildRepository {
    /**
     * Записва или обновява дете.
     * @param child детето (не може да е null)
     * @throws IllegalArgumentException ако child е null
     */
    void save(Child child);

    /**
     * Връща всички записани деца.
     * @return списък с {@link Child}, никога null
     */
    List<Child> findAll();

    /**
     * Търси дете по ID.
     * @param id уникалния код (може да е null)
     * @return {@link Optional} с дете или празен ако не съществува
     */
    Optional<Child> findById(String id);

    /**
     * Изтрива дете по ID.
     * @param id код на детето (може да е null)
     */
    void deleteById(String id);

    /**
     * Проверява дали съществува дете с този ID.
     * @param id код на детето (може да е null)
     * @return true ако има такова дете
     */
    boolean existsById(String id);
}
