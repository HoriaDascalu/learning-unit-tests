package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    Optional<Employee> findByEmail(String email);

    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select e from Employee e where e.lastName =:last_name and e.email =:email")
    Optional<Employee> findByLastNameAndEmail(@Param("last_name") String lastName, @Param("email") String email);

    @Query(value = "select * from employees e where e.first_name = ?1 and e.last_name = ?2", nativeQuery = true)
    Optional<Employee> findByFirstNameAndLastNameNative(String firstName, String lastName);

}
