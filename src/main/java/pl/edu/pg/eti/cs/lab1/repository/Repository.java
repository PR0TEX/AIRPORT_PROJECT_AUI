package pl.edu.pg.eti.cs.lab1.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {
    Optional<E> find(K key);

    List<E> findAll();

    void create(E entity);

    void delete(K key);

    void update(E entity);


}
