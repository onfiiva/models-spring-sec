package com.example.springmodels.controllers;

import com.example.springmodels.models.Employee;
import com.example.springmodels.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
public class userController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/employee")
    public String employeeMain(Model model)
    {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee-main";
    }
}
