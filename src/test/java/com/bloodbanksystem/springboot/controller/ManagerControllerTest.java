package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Manager;
import com.bloodbanksystem.springboot.service.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManagerControllerTest {

    @InjectMocks
    private ManagerController managerController;

    @Mock
    private ManagerService managerService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllManagers() {
        int managerId = 1;
        Manager manager = new Manager();
        manager.setManagerId(managerId);
        List<Manager> managers = new ArrayList<>();
        managers.add(manager);

        when(managerService.getAllManagers()).thenReturn(managers);

        List<Manager> result = managerController.getAllManagers();

        assertEquals(managers.size(), result.size());
        verify(managerService, times(1)).getAllManagers();
    }

    @Test
    public void testGetManagerById() {
        int managerId = 1;
        Manager manager = new Manager();
        manager.setManagerId(managerId);

        when(managerService.getManagerById(managerId)).thenReturn(manager);

        ResponseEntity<Manager> response = managerController.getManagerById(managerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(manager, response.getBody());
        verify(managerService, times(1)).getManagerById(managerId);
    }

    @Test
    public void testGetManagerByIdNotFound() {
        int managerId = 1;

        when(managerService.getManagerById(managerId)).thenReturn(null);

        ResponseEntity<Manager> response = managerController.getManagerById(managerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(managerService, times(1)).getManagerById(managerId);
    }

    @Test
    public void testCreateManager() {
        Manager manager = new Manager();
        manager.setManagerId(1);

        when(managerService.createManager(any(Manager.class))).thenReturn(manager);

        ResponseEntity<Manager> response = managerController.createManager(manager);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(manager, response.getBody());
        verify(managerService, times(1)).createManager(any(Manager.class));
    }

    @Test
    public void testUpdateManager() {
        int managerId = 1;
        Manager manager = new Manager();
        manager.setManagerId(managerId);

        when(managerService.updateManager(eq(managerId), any(Manager.class))).thenReturn(manager);

        ResponseEntity<Manager> response = managerController.updateManager(managerId, manager);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(manager, response.getBody());
        verify(managerService, times(1)).updateManager(eq(managerId), any(Manager.class));
    }

    @Test
    public void testUpdateManagerNotFound() {
        int managerId = 1;

        when(managerService.updateManager(eq(managerId), any(Manager.class))).thenReturn(null);

        ResponseEntity<Manager> response = managerController.updateManager(managerId, new Manager());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(managerService, times(1)).updateManager(eq(managerId), any(Manager.class));
    }

    @Test
    public void testDeleteManager() {
        int managerId = 1;

        ResponseEntity<Void> response = managerController.deleteManager(managerId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(managerService, times(1)).deleteManager(managerId);
    }
}
