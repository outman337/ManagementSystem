package com.backend.management.controller;

import com.backend.management.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class MoveController {

    private final MoveService moveService;

    @Autowired
    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @PutMapping(value = "/moveIn")
    public void moveIn(String username, String apartmentNumber) {
        moveService.moveIn(username, apartmentNumber);
    }

    @PutMapping(value = "/moveInAndAssignNewOwner")
    public void moveInAndAssignNewOwner(String username, String apartmentNumber, String newOwnerUsername) {
        moveService.moveInAndAssignNewOwner(username, apartmentNumber, newOwnerUsername);
    }

    @PutMapping(value = "/moveOut")
    public void moveOut(String username) {
        moveService.moveOut(username);
    }

    @PutMapping(value = "/moveOutAndAssignNewOwner")
    public void moveOutAndAssignNewOwner(String username, String newOwnerUsername) {
        moveService.moveOutAndAssignNewOwner(username, newOwnerUsername);
    }

    @GetMapping("getFlatmates")
    public Set<String> getFlatmates(String username) {
        return moveService.getFlatmates(username);
    }

    @GetMapping("findApartmentsWithVacancy")
    public List<String> findApartmentsWithVacancy() {return moveService.findApartmentsWithVacancy(); }

    @GetMapping("getTenantsWithoutApartments")
    public List<String> getTenantsWithoutApartments() {return moveService.getTenantsWithoutApartments();}

    @GetMapping("getTenantsWithApartments")
    public List<String> getTenantsWithApartments() {return moveService.getTenantsWithApartments();}

    @GetMapping("getApartmentInfoByUsername")
    public MoveService.ApartmentInfo getApartmentInfoByUsername(String username) {
        return moveService.getApartmentAndTenantsByUsername(username);
    }

    @GetMapping("getApartmentInfoByApartmentNumber")
    public MoveService.ApartmentInfo getApartmentInfoByApartmentNumber(String apartmentNumber) {
        return moveService.getApartmentAndTenantsByApartmentNumber(apartmentNumber);
    }

    @GetMapping("getAllApartments")
    public List<String> getAllApartments() {return moveService.getAllApartments();}

    @GetMapping("getAllTenants")
    public List<String> getAllTenants() {return moveService.getAllTenants();}

}
