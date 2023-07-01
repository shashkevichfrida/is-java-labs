package org.example.services;

import java.util.List;

public interface CarModelService<T, V, H, S> {
    public H save(H entity);
    public void deleteById(long id);
    public void deleteByEntity(Long id);
    public void deleteAll();
    public H update(H entity, Long oldId);
    public H getById(Long id);
    public List<H> getAll();
    public V getAllByVId(Long carModelId);
    public S getAllByName(Long id);
}

