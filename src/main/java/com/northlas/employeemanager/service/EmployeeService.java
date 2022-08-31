package com.northlas.employeemanager.service;

import com.northlas.employeemanager.model.Employee;
import com.northlas.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());

        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployee(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new IllegalStateException("Employee by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id) {
        boolean exists = employeeRepo.existsById(id);
        if (!exists) throw new IllegalStateException("Employee by id " + id + " was not found");
        employeeRepo.deleteById(id);
    }
}
