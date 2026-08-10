package ir.irancelllabs.hr.service;


import ir.irancelllabs.hr.dtos.CreateEmployeeRequest;
import ir.irancelllabs.hr.dtos.EmployeeResponse;
import ir.irancelllabs.hr.mapper.EmployeeMapper;
import ir.irancelllabs.hr.model.Employee;
import ir.irancelllabs.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;

    public  Employee creatEmployee (CreateEmployeeRequest createEmployeeRequest){
        Employee employee=  employeeMapper.toEntity(createEmployeeRequest);

        return  employeeRepository.save(employee);
    }


//    public List<Employee> getEmployee (){
//
//        return  employeeRepository.findAll();    }

    public List<EmployeeResponse> getEmployee (){
        List<Employee> employees=employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses =new ArrayList<>();
        for(Employee employee:employees){

            employeeResponses.add(employeeMapper.toResponse(employee)) ;
        }


        return  employeeResponses;    }



    public Employee GetEmployeeById(Long id){

        return employeeRepository.findById(id).orElseThrow();

    }








    public List<Employee> GetEmployeeWithSalary(Double salary){
        return employeeRepository.findAll().stream().filter(employee -> employee.getSalary()>=salary).toList();

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