package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Manager;
import com.bloodbanksystem.springboot.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Manager getManagerById(int id) {
        Optional<Manager> managerOptional = managerRepository.findById(id);
        return managerOptional.orElse(null);
    }

    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public Manager updateManager(int id, Manager manager) {
        if (managerRepository.existsById(id)) {
            manager.setManagerId(id);
            return managerRepository.save(manager);
        }
        return null;
    }

    public void deleteManager(int id) {
        managerRepository.deleteById(id);
    }
}
