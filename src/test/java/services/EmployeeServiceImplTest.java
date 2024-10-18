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
        // Arrange
        UUID id = UUID.randomUUID();
        Employee employee = new Employee();
        when(employeeRepositoryMock.findById(id)).thenReturn(Optional.of(employee));

        // Act
        Optional<Employee> result = employeeService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
        verify(employeeRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findAll() {
        // Arrange
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        when(employeeRepositoryMock.findAll()).thenReturn(employeeList);

        // Act
        List<Employee> result = employeeService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(employee1));
        assertTrue(result.contains(employee2));
        verify(employeeRepositoryMock, times(1)).findAll();
    }

    @Test
    void update() {
        // Arrange
        Employee employee = new Employee();
        when(employeeRepositoryMock.update(employee)).thenReturn(employee);

        // Act
        Employee result = employeeService.update(employee);

        // Assert
        assertEquals(employee, result);
        verify(employeeRepositoryMock, times(1)).update(employee);
    }

    @Test
    void deleteById() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        employeeService.deleteById(id);

        // Assert
        verify(employeeRepositoryMock, times(1)).deleteById(id);
    }
}