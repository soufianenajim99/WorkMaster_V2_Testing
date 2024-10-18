package services;

import entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.repositoryinterfaces.EmployeeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepositoryMock);
    }

    @Test
    void testSavingEmployee_usingMock() {
        Employee employee = new Employee();
        when(employeeRepositoryMock.save(employee)).thenReturn(employee);
        Employee result = employeeService.save(employee);

        assertEquals(employee, result);
        verify(employeeRepositoryMock, times(1)).save(employee);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        Employee employee = new Employee();
        when(employeeRepositoryMock.findById(id)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
        verify(employeeRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findAll() {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        when(employeeRepositoryMock.findAll()).thenReturn(employeeList);

        List<Employee> result = employeeService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(employee1));
        assertTrue(result.contains(employee2));
        verify(employeeRepositoryMock, times(1)).findAll();
    }

    @Test
    void update() {
        Employee employee = new Employee();
        when(employeeRepositoryMock.update(employee)).thenReturn(employee);

        Employee result = employeeService.update(employee);

        assertEquals(employee, result);
        verify(employeeRepositoryMock, times(1)).update(employee);
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();

        employeeService.deleteById(id);

        verify(employeeRepositoryMock, times(1)).deleteById(id);
    }
}