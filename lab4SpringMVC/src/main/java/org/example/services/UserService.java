package org.example.services;

import org.example.dto.UserDto;

import java.util.List;

public interface UserService {
    public UserDto save(UserDto entity);
    public void deleteById(Long id);
    public void deleteByEntity(Long id);
    public void deleteAll();
    public UserDto update(UserDto entity, Long oldId);
    public UserDto getById(long id);
    public List<UserDto> getAll();
}

