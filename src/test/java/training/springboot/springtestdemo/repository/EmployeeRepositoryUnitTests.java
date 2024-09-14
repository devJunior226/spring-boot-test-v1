package training.springboot.springtestdemo.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import training.springboot.springtestdemo.entity.Employee;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryUnitTests {

    /**
     * #################### NOTES #############################
     * ## @DataJpaTest is used for testing Jpa repositories
     * It sets up a lightweight environnement specifically for testing databased-related
     * components without loading the entire application context.
     * This annotation dosen't load other Spring beans into ApplicationContext.
     *
     * ## @Rollback controls the transactional behavior of test methods.
     * When applied to a test method, it specifies whether the transactional should be
     * rolled back after the test method has completed execution.
     *
     * If @Rollback(true), the transaction will be rolled back after the test method
     * finishes, ensuring that changes made during the test do not affect the database
     * state.
     *
     * If @Rollback(false), no transaction rollback, and the changes made during the
     * test will be persisted in the database.
     */

    @Autowired
   private EmployeeRepository employeeRepository;

    /** Saving an employee test: it's works */
    @Test
   @DisplayName("Test 1: Save an employee Test")
   @Order(1)
   @Rollback(value = false)
   public void should_successfully_save_an_employee() {
       Employee employee = Employee.builder()
               .firstName("John")
               .lastName("Doe")
               .email("john@doe.com")
               .build();

       employeeRepository.save(employee);

       /** Verification */
       System.out.println(employee);
       Assertions.assertThat(employee.getId()).isGreaterThan(0);
   }

    /** Doesn't work */
    @Test
    @Order(2)
    public void should_return_an_employee() {
        //long employeeId = 1;
        Employee employee = employeeRepository.findById(1L).get();
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isEqualTo(1L);

    }

    /** Doesn't work */
    @Test
    @Order(3)
    public void should_return_all_employees() {
        /** Action */
        List<Employee> employees = employeeRepository.findAll();

        System.out.println(employees);
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    /** Doesn't work */
    @Test
    @Order(4)
    @Rollback(value = false)
    public void should_update_an_employee() {
        Employee employee = employeeRepository.findById(1L).get();
        employee.setEmail("john@doe.com");
        Employee updatedEmployee = employeeRepository.save(employee);

        /** Then */
        System.out.println(updatedEmployee);
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("john@doe.com");
    }

    /** Test passed successsfully */
    @Test
    @Order(5)
    @Rollback(value = false)
    public void should_delete_an_employee() {
        //Action
        employeeRepository.deleteById(1L);
        Optional<Employee> employeeOptional = employeeRepository.findById(1L);

        //Verify
        Assertions.assertThat(employeeOptional).isEmpty();
    }
}
