package org.example.services;

import java.util.List;

public interface CarBrandService<T, K, U> {
    public K save(K entity);
    public void deleteById(long id);
    public void deleteByEntity(K entity, Long id);
    public void deleteAll();
    public K update(K entity, Long oldId);
    public K getById(long id);
    public List<K> getAll();
}


