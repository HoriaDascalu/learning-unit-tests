package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Test Junit pt salvarea unui Employee
    @DisplayName("Test Junit pt salvarea unui Employee")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
        //given -> precondition or setup
        Employee employee = Employee.builder()
                .firstName("Horia")
                .lastName("Dascalu")
                .email("horia.dascalu12@gmail.com")
                .build();

        //when -> action or the behaviour that we are going to save
        Employee savedEmployee = employeeRepository.save(employee);

        //then -> verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }
}
