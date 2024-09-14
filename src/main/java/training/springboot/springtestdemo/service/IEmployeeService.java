package training.springboot.springtestdemo.service;

import training.springboot.springtestdemo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
}
