package ir.irancelllabs.hr.service;


import ir.irancelllabs.hr.dtos.CreateEmployeeRequest;
import ir.irancelllabs.hr.dtos.EmployeeResponse;
import ir.irancelllabs.hr.exception.ResourceNotFoundException;
import ir.irancelllabs.hr.mapper.EmployeeMapper;
import ir.irancelllabs.hr.model.Department;
import ir.irancelllabs.hr.model.Employee;
import ir.irancelllabs.hr.repository.DepartmentRepository;
import ir.irancelllabs.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    DepartmentRepository departmentRepository;

    EmployeeMapper employeeMapper;

    public  Employee creatEmployee (Employee employee){
        return  employeeRepository.save(employee);
    }

    @Transactional
    public  EmployeeResponse creatEmployee2 (CreateEmployeeRequest createEmployeeRequest){

        Employee employee=  employeeMapper.toEntity(createEmployeeRequest);
        Integer departmentId = employee.getDepartment().getId();
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + departmentId));
        employee.setDepartment(department);
        return  employeeMapper.toResponse(employeeRepository.save(employee));
    }


    public List<Employee> getEmployee (){

        return  employeeRepository.findAll();    }

    public List<EmployeeResponse> getEmployee2 (){
        List<Employee> employees=employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses =new ArrayList<>();
        for(Employee employee:employees){

            employeeResponses.add(employeeMapper.toResponse(employee)) ;
        }


        return  employeeResponses;    }



    public Employee GetEmployeeById(Long id){

        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));

    }
    @Transactional(readOnly = true)
    public List<Employee> GetEmployeeByNationalId(String id){
        Employee employee = employeeRepository.getByNationalId(id);
        return employee == null ? List.of() : List.of(employee);

    }
    @Transactional(readOnly = true)
    public List<Employee> GetEmployeeByEmail(String email){

        return employeeRepository.getByEmail(email);

    }

    @Transactional(readOnly = true)
    public List<Employee> GetEmployeeByDepartment(String name){

        Department department = departmentRepository.getByName(name);
        if(department == null) return List.of();
        return employeeRepository.getByDepartment(department);

    }










    public List<Employee> GetEmployeeWithSalary(BigDecimal salary){
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getSalary() != null && employee.getSalary().compareTo(salary) >= 0)
                .toList();

    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }


    //    @Autowired
//    EmployeeRepository employeeRepository;
//
//
//
//    public Employee CreatEmployee(Employee employee){
//        return employeeRepository.save(employee);
//
//    }
//
//    public List<Employee> GetEmployees(){
//        return employeeRepository.findAll();
//
//    }
//
//    public Employee GetEmployeeById(Long id){
//
//        return employeeRepository.findById(id).orElseThrow();
//
//    }
//    public List<Employee> GetEmployeeWithSalary(Double salary){
//        return employeeRepository.findAll().stream().filter(employee -> employee.getSalary()>=salary).toList();
//
//    }
//    public void deleteEmployee(Long id){
//
//        employeeRepository.deleteById(id);
//
//    }
//
//    public void updateEmployee(Long id,Employee updatedEmployee) {
//
//
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        employee.setFirstName(updatedEmployee.getFirstName());
//        employee.setLastName(updatedEmployee.getLastName());
//        employee.setEmail(updatedEmployee.getEmail());
//        employee.setDepartment(updatedEmployee.getDepartment());
//        employee.setSalary(updatedEmployee.getSalary());
//
//        Employee save = employeeRepository.save(employee);
//
//    }

}
