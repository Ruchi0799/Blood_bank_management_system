package com.bloodbanksystem.springboot.controller;

import com.bloodbanksystem.springboot.entity.Manager;
import com.bloodbanksystem.springboot.service.ManagerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
@Api(tags = "Manager APIs")
public class ManagerController {


    @Autowired
    ManagerService managerService;

    @GetMapping
    public List<Manager> getAllManagers() {
        return managerService.getAllManagers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable int id) {
        Manager manager = managerService.getManagerById(id);
        if (manager != null) {
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager) {
        Manager createdManager = managerService.createManager(manager);
        return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable int id, @RequestBody Manager manager) {
        Manager updatedManager = managerService.updateManager(id, manager);
        if (updatedManager != null) {
            return new ResponseEntity<>(updatedManager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable int id) {
        managerService.deleteManager(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
