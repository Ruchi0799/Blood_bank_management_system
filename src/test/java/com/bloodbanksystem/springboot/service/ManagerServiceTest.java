package com.bloodbanksystem.springboot.service;

import com.bloodbanksystem.springboot.entity.Manager;
import com.bloodbanksystem.springboot.repository.ManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {

    @InjectMocks
    private ManagerService managerService;

    @Mock
    private ManagerRepository managerRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllManagers() {
        List<Manager> managers = new ArrayList<>();
        // Add some managers to the list

        when(managerRepository.findAll()).thenReturn(managers);

        List<Manager> result = managerService.getAllManagers();

        assertEquals(managers.size(), result.size());
        verify(managerRepository, times(1)).findAll();
    }

    @Test
    public void testGetManagerById() {
        int id = 1;
        Manager manager = new Manager(id, "Manager Name", "Manager Location", 1);

        when(managerRepository.findById(id)).thenReturn(Optional.of(manager));

        Manager result = managerService.getManagerById(id);

        assertEquals(manager, result);
        verify(managerRepository, times(1)).findById(id);
    }

    @Test
    public void testGetManagerByIdNotFound() {
        int id = 1;

        when(managerRepository.findById(id)).thenReturn(Optional.empty());

        Manager result = managerService.getManagerById(id);

        assertEquals(null, result);
        verify(managerRepository, times(1)).findById(id);
    }

    @Test
    public void testCreateManager() {
        Manager manager = new Manager(1, "Manager Name", "Manager Location", 1);

        when(managerRepository.save(manager)).thenReturn(manager);

        Manager result = managerService.createManager(manager);

        assertEquals(manager, result);
        verify(managerRepository, times(1)).save(manager);
    }

    @Test
    public void testUpdateManager() {
        int id = 1;
        Manager existingManager = new Manager(id, "Manager Name", "Manager Location", 1);
        Manager updatedManager = new Manager(id, "Updated Manager Name", "Updated Manager Location", 2);

        when(managerRepository.existsById(id)).thenReturn(true);
        when(managerRepository.save(updatedManager)).thenReturn(updatedManager);

        Manager result = managerService.updateManager(id, updatedManager);

        assertEquals(updatedManager, result);
        verify(managerRepository, times(1)).existsById(id);
        verify(managerRepository, times(1)).save(updatedManager);
    }

    @Test
    public void testUpdateManagerNotFound() {
        int id = 1;
        Manager updatedManager = new Manager(id, "Updated Manager Name", "Updated Manager Location", 2);

        when(managerRepository.existsById(id)).thenReturn(false);

        Manager result = managerService.updateManager(id, updatedManager);

        assertEquals(null, result);
        verify(managerRepository, times(1)).existsById(id);
    }

    @Test
    public void testDeleteManager() {
        int id = 1;

        managerService.deleteManager(id);

        verify(managerRepository, times(1)).deleteById(id);
    }
}
