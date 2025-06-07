package com.example.kindergarten.repository;

import com.example.kindergarten.model.Parent;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс за достъп до данните за {@link Parent}.
 */
public interface ParentRepository {
    /**
     * Записва или обновява родител.
     * @param parent родител (не може да е null)
     */
    void save(Parent parent);

    /**
     * Връща всички записани родители.
     */
    List<Parent> findAll();

    /**
     * Търси родител по ID.
     */
    Optional<Parent> findById(String id);

    /**
     * Изтрива родител по ID.
     */
    void deleteById(String id);

    /**
     * Проверява дали съществува родител с този ID.
     */
    boolean existsById(String id);
}
