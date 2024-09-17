package training.springboot.springtestdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import training.springboot.springtestdemo.entity.Employee;
import training.springboot.springtestdemo.service.IEmployeeService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final IEmployeeService IEmployeeService;

    public EmployeeController(IEmployeeService IEmployeeService) {
        this.IEmployeeService = IEmployeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return IEmployeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return IEmployeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable("id") long id) {
        return new ResponseEntity<Optional<Employee>>
                (IEmployeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(IEmployeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        IEmployeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);

    }
}