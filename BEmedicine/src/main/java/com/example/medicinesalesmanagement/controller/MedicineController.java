package com.example.medicinesalesmanagement.controller;

import com.example.medicinesalesmanagement.dto.EmployeeDto;
import com.example.medicinesalesmanagement.dto.MedicineDto;
import com.example.medicinesalesmanagement.dto.MedicineUpdateDto;
import com.example.medicinesalesmanagement.model.*;
import com.example.medicinesalesmanagement.repository.IRoleRepository;
import com.example.medicinesalesmanagement.service.DetailType.IDetailTypeService;
import com.example.medicinesalesmanagement.service.Employee.IEmployeeService;
import com.example.medicinesalesmanagement.service.Medicine.IMedicineService;
import com.example.medicinesalesmanagement.service.TypeMedicine.ITypeMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/medicine")
public class MedicineController {
    @Autowired
    IMedicineService iMedicineService;
    @Autowired
    IDetailTypeService iDetailTypeService;
    @Autowired
    IEmployeeService iEmployeeService;
    @Autowired
    ITypeMedicineService iTypeMedicineService;
    @Autowired
    private IRoleRepository roleRepository;
    @GetMapping("/listSearchName")
    public ResponseEntity<List<Medicine>> findAll(@RequestParam String name){
        List<Medicine> medicineList = iMedicineService.findMedicineByNameContaining(name);
        if(medicineList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicineList,HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Medicine>> findAll(){
        List<Medicine> medicineList = iMedicineService.findAll();
        if(medicineList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicineList,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Medicine> create(@RequestBody MedicineDto medicineDto){
        if(medicineDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Medicine medicine = new Medicine();

        medicine.setNameMedicine(medicineDto.getName());
        medicine.setUnit(medicineDto.getUnit());
        medicine.setPrice(medicineDto.getPrice());
        medicine.setQuantity(medicineDto.getQuantity());
        medicine.setProductionDate(medicineDto.getProductionDate());
        medicine.setExpirationDate(medicineDto.getExpirationDate());
        Employee employee = iEmployeeService.findById(medicineDto.getId_employee());
        medicine.setEmployee(employee);

        iMedicineService.save(medicine);

        Medicine medicine1 = iMedicineService.findById(medicine.getId_medicine());

        for (Integer id: medicineDto.getTypeMedicine()) {

            TypeMedicine typeMedicine = iTypeMedicineService.findById(id);

            DetailType detailType = new DetailType();
            detailType.setMedicine(medicine1);
            detailType.setTypeMedicine(typeMedicine);
            iDetailTypeService.save(detailType);
        }
        return new ResponseEntity<>(medicine,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> detail(@PathVariable Integer id){
        Medicine medicine = iMedicineService.findById(id);
        if (medicine == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicine,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Medicine> update(@PathVariable Integer id, @RequestBody MedicineUpdateDto medicineUpdateDto){
        System.out.println(medicineUpdateDto);
        Medicine medicine1 = iMedicineService.findById(id);
        if(medicine1 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Medicine medicine = new Medicine();
        medicine.setId_medicine(medicineUpdateDto.getId_medicine());
        medicine.setNameMedicine(medicineUpdateDto.getNameMedicine());
        medicine.setUnit(medicineUpdateDto.getUnit());
        medicine.setPrice(medicineUpdateDto.getPrice());
        medicine.setQuantity(medicineUpdateDto.getQuantity());
        medicine.setProductionDate(medicineUpdateDto.getProductionDate());
        medicine.setExpirationDate(medicineUpdateDto.getExpirationDate());
//        if(!medicineUpdateDto.getNameMedicine().isEmpty()){
//            System.out.println(medicineUpdateDto.getEmployee());
//            EmployeeDto employeeDto = medicineUpdateDto.getEmployee();
//            Set<Role> roles = new HashSet<>();
//            if(employeeDto.getRole() == 1){
//                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                roles.add(userRole);
//            }else if (employeeDto.getRole() == 2) {
//                Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                roles.add(userRole);
//            }
//            Employee employee = new Employee();
//            employee.setId_employee(employeeDto.getId_employee());
//            employee.setName(employeeDto.getName());
//            employee.setGender(employeeDto.isGender());
//            employee.setPhoneNumber(employeeDto.getPhoneNumber());
//            employee.setAddress(employeeDto.getAddress());
//            employee.setUserName(employeeDto.getUserName());
//            employee.setPassword(employeeDto.getPassword());
//            employee.setSalary(employeeDto.getSalary());
//            employee.setDayOfWork(employeeDto.getDayOfWork());
//            employee.setRole(roles);
//            iEmployeeService.save(employee);
//        }
        Employee employee = iEmployeeService.findById(medicineUpdateDto.getEmployee().getId_employee());
        medicine.setEmployee(employee);

        iMedicineService.save(medicine);
        return new ResponseEntity<>(medicine,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Medicine> delete(@PathVariable Integer id){
        Medicine medicine = iMedicineService.findById(id);
        if(medicine == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iMedicineService.delete(id);
        return new ResponseEntity<>(medicine,HttpStatus.NO_CONTENT);
    }
    @GetMapping("/findMedicineById_type_medicineContaining/{id}")
    public ResponseEntity<List<Medicine>> findMedicineById_type_medicineContaining(@PathVariable Integer id){
        List<Medicine> medicineList = iMedicineService.findMedicineById_type_medicineContaining(id);
        if(medicineList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicineList,HttpStatus.OK);
    }
}
