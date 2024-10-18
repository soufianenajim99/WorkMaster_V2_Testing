package services;

import entities.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.JobOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.repositoryinterfaces.DepartmentRepository;
import repositories.repositoryinterfaces.JobOfferRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceImplTest {


    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Mock
    DepartmentRepository departmentRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentService = new DepartmentServiceImpl(departmentRepositoryMock);
    }

    @Test
    void testSavingDepartment_usingMock() {
        // Arrange
        Department department = new Department();
        when(departmentRepositoryMock.save(department)).thenReturn(department);

        // Act
        Department result = departmentService.save(department);

        // Assert
        assertEquals(department, result);
        verify(departmentRepositoryMock, times(1)).save(department);
    }

    @Test
    void findById() {
        // Arrange
        UUID id = UUID.randomUUID();
        Department department = new Department();
        when(departmentRepositoryMock.findById(id)).thenReturn(Optional.of(department));

        // Act
        Optional<Department> result = departmentService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(department, result.get());
        verify(departmentRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findAll() {
        // Arrange
        Department department1 = new Department();
        Department department2 = new Department();
        List<Department> departmentList = Arrays.asList(department1, department2);
        when(departmentRepositoryMock.findAll()).thenReturn(departmentList);

        // Act
        List<Department> result = departmentService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(department1));
        assertTrue(result.contains(department2));
        verify(departmentRepositoryMock, times(1)).findAll();
    }

    @Test
    void update() {
        // Arrange
        Department department = new Department();
        when(departmentRepositoryMock.update(department)).thenReturn(department);

        // Act
        Department result = departmentService.update(department);

        // Assert
        assertEquals(department, result);
        verify(departmentRepositoryMock, times(1)).update(department);
    }

    @Test
    void deleteById() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        departmentService.deleteById(id);

        // Assert
        verify(departmentRepositoryMock, times(1)).deleteById(id);
    }
}