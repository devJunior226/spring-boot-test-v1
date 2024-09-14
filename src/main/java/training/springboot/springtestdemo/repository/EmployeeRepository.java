package training.springboot.springtestdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.springboot.springtestdemo.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Find an employee by his email
     * @param email
     * @return the employee
     */
    Optional<Employee> findByEmail(String email);
}
