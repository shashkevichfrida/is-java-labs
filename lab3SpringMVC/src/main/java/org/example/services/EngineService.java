package org.example.services;

import java.util.List;

public interface EngineService<T, U, H, N> {
    public H save(H entity);
    public void deleteById(Long id);
    public void deleteByEntity(Long id);
    public void deleteAll();
    public H update(H entity, Long oldId);
    public H getById(Long id);
    public List<H> getAll();
    public List<N> getAllByVId(Long entityId);
}
