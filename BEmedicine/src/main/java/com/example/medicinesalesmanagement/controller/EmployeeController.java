package com.example.medicinesalesmanagement.controller;


import com.example.medicinesalesmanagement.dto.EmployeeDto;
import com.example.medicinesalesmanagement.model.ERole;
import com.example.medicinesalesmanagement.model.Employee;
import com.example.medicinesalesmanagement.model.Role;
import com.example.medicinesalesmanagement.repository.IRoleRepository;
import com.example.medicinesalesmanagement.security.jwt.JwtUtils;
import com.example.medicinesalesmanagement.service.Employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService iEmployeeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IRoleRepository roleRepository;
    @GetMapping("/listSearchName")
    public ResponseEntity<List<Employee>> findAll(@RequestParam String name){
        List<Employee> employeeList = iEmployeeService.findEmployeeByNameContaining(name);
        if(employeeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> findAll(){
        List<Employee> employeeList = iEmployeeService.findAll();
        if(employeeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody EmployeeDto employeeDto){
        if(employeeDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Set<Role> roles = new HashSet<>();
        if(employeeDto.getRole() == 1){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }else if (employeeDto.getRole() == 2){
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        System.out.println(employeeDto);
        Employee employee = new Employee();

        if(employeeDto.getName().equals(""))employee.setName("");
        else employee.setName(employeeDto.getName());

        employee.setGender(employeeDto.isGender());

        if(employeeDto.getPhoneNumber().equals(""))employee.setPhoneNumber("");
        else employee.setPhoneNumber(employeeDto.getPhoneNumber());

        if(employeeDto.getAddress().equals(""))employee.setAddress("");
        else employee.setAddress(employeeDto.getAddress());
        employee.setUserName(employeeDto.getUserName());
        employee.setPassword(encoder.encode(employeeDto.getPassword()));
        employee.setSalary(employeeDto.getSalary());
        employee.setDayOfWork(employeeDto.getDayOfWork());
        employee.setRole(roles);

        iEmployeeService.save(employee);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> detail(@PathVariable Integer id){
        Employee employee = iEmployeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto){
        Employee employee1 = iEmployeeService.findById(id);
        if(employee1 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Set<Role> roles = new HashSet<>();
        if(employeeDto.getRole() == 1){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }else if (employeeDto.getRole() == 2){
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        Employee employee = new Employee();
        employee.setId_employee(employeeDto.getId_employee());
        employee.setName(employeeDto.getName());
        employee.setGender(employeeDto.isGender());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setAddress(employeeDto.getAddress());
        employee.setUserName(employeeDto.getUserName());
        employee.setPassword(employeeDto.getPassword());
        employee.setSalary(employeeDto.getSalary());
        employee.setDayOfWork(employeeDto.getDayOfWork());
        employee.setRole(roles);

        iEmployeeService.save(employee);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Integer id){
        Employee employee = iEmployeeService.findById(id);
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iEmployeeService.delete(id);
        return new ResponseEntity<>(employee,HttpStatus.NO_CONTENT);
    }
}
