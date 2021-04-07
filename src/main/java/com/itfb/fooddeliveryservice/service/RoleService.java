package com.itfb.fooddeliveryservice.service;

import com.itfb.fooddeliveryservice.model.domain.Role;
import com.itfb.fooddeliveryservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> getByName(String name) {
        return roleRepository.findByName(name);
    }
}
