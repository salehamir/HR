package ir.irancelllabs.hr.controller;

import ir.irancelllabs.hr.dtos.CreateEmployeeRequest;
import ir.irancelllabs.hr.dtos.EmployeeResponse;
import ir.irancelllabs.hr.exception.ResourceNotFoundException;
import ir.irancelllabs.hr.model.Employee;
import ir.irancelllabs.hr.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

//    @PostMapping
//    public Employee creatEmployee(   @RequestBody Employee employee) {
//
//        return  employeeService.creatEmployee(employee);
//    }

    @GetMapping
    public ResponseEntity<List<Employee>>  getEmployees(){
        return  ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployees(@PathVariable Long id){

        return  ResponseEntity.status(HttpStatus.OK).body(employeeService.GetEmployeeById(id));
    }


    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeesWithSalary(@RequestParam BigDecimal salary){

        return  ResponseEntity.status(HttpStatus.OK).body(employeeService.GetEmployeeWithSalary(salary));
    }

    @GetMapping("/get/{key}/{value}")
    public ResponseEntity<List<Employee>> getEmployees(
            @PathVariable String key,
            @PathVariable String value) {
        List<Employee> employees;

        if (key.equalsIgnoreCase("email")) {
            employees = employeeService.GetEmployeeByEmail(value);
        } else if (key.equalsIgnoreCase("nationalId")) {
            employees = employeeService.GetEmployeeByNationalId(value);
        } else if (key.equalsIgnoreCase("department")) {
            employees = employeeService.GetEmployeeByDepartment(value);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Unsupported employee search key: " + key);
        }

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No employees found with " + key + ": " + value);
        }

        return ResponseEntity.ok(employees);

    }

    @PostMapping
    public EmployeeResponse creatEmployee(  @Valid @RequestBody CreateEmployeeRequest employee) {

        return  employeeService.creatEmployee2(employee);
    }


//    @GetMapping
//    public ResponseEntity<List<EmployeeResponse>>  getEmployees(){
//        return  ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee2());
//    }


//    @DeleteMapping("{id}")
//    public ResponseEntity deleteEmployee(@PathVariable Long id){
//        employeeService.deleteEmployee(id);
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

























//
//    @Autowired
//    EmployeeService employeeService;
//
//
//    @PostMapping
//    public ResponseEntity<Employee> creatEmployee(@RequestBody Employee employee){
//
//        return  ResponseEntity.status(HttpStatus.CREATED).body(employeeService.CreatEmployee(employee));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Employee>> getEmployees(){
//
//        return  ResponseEntity.status(HttpStatus.OK).body(employeeService.GetEmployees());
//    }
//

//

//
//    @DeleteMapping("{id}")
//    public ResponseEntity deleteEmployee(@PathVariable Long id){
//        employeeService.deleteEmployee(id);
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(
//            @PathVariable Long id,
//            @RequestBody Employee updateEmployee) {
//
//        employeeService.updateEmployee(id, updateEmployee);
//
//        return ResponseEntity.ok(updateEmployee);
//    }
//


}
