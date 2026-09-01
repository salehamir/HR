package ir.irancelllabs.hr.dtos;

import ir.irancelllabs.hr.model.Department;
import ir.irancelllabs.hr.validation.NationalId;
import jakarta.validation.constraints.Email;

import java.math.BigDecimal;

public class CreateEmployeeRequest {

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private Department department;
    @NationalId
    private String nationalId;
    private BigDecimal salary;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
