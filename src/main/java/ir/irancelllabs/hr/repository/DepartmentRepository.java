package ir.irancelllabs.hr.repository;

import ir.irancelllabs.hr.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department getByName(String name);
}
