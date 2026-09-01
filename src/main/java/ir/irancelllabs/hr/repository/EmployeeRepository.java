package ir.irancelllabs.hr.repository;

import ir.irancelllabs.hr.model.Department;
import ir.irancelllabs.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Employee getByNationalId(String nationalId);

    @Query("select e from Employee e where e.email=:email")
    List<Employee> getByEmail(String email);

    List<Employee> getByDepartment(Department department);
}