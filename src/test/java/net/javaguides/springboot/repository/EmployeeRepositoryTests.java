package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    //metoda adnotata cu @BeforeEach se va executa inaintea oricarei metode @Test, practic vom avea la fiecare metoda test un obiect employee initializat
    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Horia")
                .lastName("Dascalu")
                .email("horia.dascalu12@gmail.com")
                .build();
    }

    //Test Junit pt salvarea unui Employee
    @DisplayName("Test Junit pt salvarea unui Employee")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();

        //when -> action or the behaviour that we are going to save
        Employee savedEmployee = employeeRepository.save(employee);

        //then -> verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    //Test Junit pt
    @Test
    @DisplayName("Test Junit pt findAll Employees")
    public void givenEmployeesList_whenFindAll_thenEmployeesList(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("Andreea")
                .lastName("Popescu")
                .email("andreea.popescu04@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //when -> action or the behaviour that we are going to save
        List<Employee> employees = employeeRepository.findAll();

        //then -> verify the output
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
    }

    //Test Junit pt findById
    @Test
    @DisplayName("Test JUnit pt findById")
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when -> action or the behaviour that we are going to save
        Employee employeeById = employeeRepository.findById(employee.getId()).get();

        //then -> verify the output
        assertThat(employeeById).isNotNull();
        assertThat(employee.getId()).isNotNull();
    }

    //Test Junit pt findByEmail
    @Test
    @DisplayName("Test JUnit pt findByEmail")
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when -> action or the behaviour that we are going to save
        Employee employeeByEmail = employeeRepository.findByEmail(employee.getEmail()).get();

        //then -> verify the output
        assertThat(employeeByEmail).isNotNull();
        assertThat(employeeByEmail.getEmail()).isEqualTo("horia.dascalu12@gmail.com");
    }

    //Test Junit pt update Employee
    @Test
    @DisplayName("JUnit test pt update Employee")
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when -> action or the behaviour that we are going to save
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("horia@gmail.com");
        savedEmployee.setFirstName("Andrei");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        //then -> verify the output
        assertThat(savedEmployee.getId()).isEqualTo(updatedEmployee.getId());
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Andrei");
        assertThat(updatedEmployee.getEmail()).isEqualTo("horia@gmail.com");

    }

    //Test Junit pt delete Employee
    @Test
    @DisplayName("Test JUnit pentru delete Employee")
    public void givenEmployeeObject_whenDeleteEmployee_thenRemoveEmployee(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when -> action or the behaviour that we are going to save
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        //then -> verify the output
        assertThat(optionalEmployee).isEmpty();

    }

    //Test Junit pt testare metoda din repo facuta cu JPQL
    @Test
    @DisplayName("Test JUnit pentru metoda facuta cu JPQL")
    public void givenEmployeeFirstNameAndLastName_whenFindByFirstNameAndLastName_thenReturnEmployeeObject(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when -> action or the behaviour that we are going to save
        Optional<Employee> employeeByFirstNameAndLastName = employeeRepository.findByFirstNameAndLastName(employee.getFirstName(),employee.getLastName());

        //then -> verify the output
        assertThat(employeeByFirstNameAndLastName).isNotEmpty();

    }

    //Test Junit pt metoda din repo facuta cu JPQL si parametrii(@Param)
    @Test
    @DisplayName("Test JUnit pentru metoda facuta cu JPQL si params")
    public void givenEmployeeLastNameAndEmail_whenFindByLastNameAndEmail_thenReturnEmployee(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when -> action or the behaviour that we are going to save
        Optional<Employee> employeeByLastNameAndEmail = employeeRepository.findByLastNameAndEmail(employee.getLastName(),employee.getEmail());

        //then -> verify the output
        assertThat(employeeByLastNameAndEmail).isNotEmpty();
    }

    //Test Junit pt metoda din repo cu native query
    @Test
    @DisplayName("Test JUnit pentru findByFirstNameAndLastName cu native query")
    public void givenEmployeeFirstNameAndLastName_findByFirstNameAndLastNameNative_thenReturnEmployeeObject(){
        //given -> precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Horia")
//                .lastName("Dascalu")
//                .email("horia.dascalu12@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when -> action or the behaviour that we are going to save
        Optional<Employee> employeeByFirstNameAndLastName = employeeRepository.findByFirstNameAndLastNameNative(employee.getFirstName(),employee.getLastName());

        //then -> verify the output
        assertThat(employeeByFirstNameAndLastName).isNotEmpty();

    }
}
