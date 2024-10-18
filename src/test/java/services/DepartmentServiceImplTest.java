package services;

import entities.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.repositoryinterfaces.DepartmentRepository;
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
        Department department = new Department();
        when(departmentRepositoryMock.save(department)).thenReturn(department);
        Department result = departmentService.save(department);
        assertEquals(department, result);
        verify(departmentRepositoryMock, times(1)).save(department);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        Department department = new Department();
        when(departmentRepositoryMock.findById(id)).thenReturn(Optional.of(department));
        Optional<Department> result = departmentService.findById(id);
        assertTrue(result.isPresent());
        assertEquals(department, result.get());
        verify(departmentRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findAll() {
        Department department1 = new Department();
        Department department2 = new Department();
        List<Department> departmentList = Arrays.asList(department1, department2);
        when(departmentRepositoryMock.findAll()).thenReturn(departmentList);
        List<Department> result = departmentService.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(department1));
        assertTrue(result.contains(department2));
        verify(departmentRepositoryMock, times(1)).findAll();
    }

    @Test
    void update() {
        Department department = new Department();
        when(departmentRepositoryMock.update(department)).thenReturn(department);
        Department result = departmentService.update(department);
        assertEquals(department, result);
        verify(departmentRepositoryMock, times(1)).update(department);
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        departmentService.deleteById(id);
        verify(departmentRepositoryMock, times(1)).deleteById(id);
    }
}