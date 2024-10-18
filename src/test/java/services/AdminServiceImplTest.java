package services;

import entities.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.repositoryinterfaces.AdminRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @InjectMocks
    AdminServiceImpl adminService;

    @Mock
    AdminRepository adminRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adminService = new AdminServiceImpl(adminRepositoryMock);
    }

    @Test
    void testSavingAdmin_usingMock() {
        Admin admin = new Admin();
        when(adminRepositoryMock.save(admin)).thenReturn(admin);

        Admin result = adminService.save(admin);

        assertEquals(admin, result);
        verify(adminRepositoryMock, times(1)).save(admin);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        Admin admin = new Admin();
        when(adminRepositoryMock.findById(id)).thenReturn(Optional.of(admin));

        Optional<Admin> result = adminService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(admin, result.get());
        verify(adminRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findAll() {
        Admin admin1 = new Admin();
        Admin admin2 = new Admin();
        List<Admin> adminList = Arrays.asList(admin1, admin2);
        when(adminRepositoryMock.findAll()).thenReturn(adminList);

        List<Admin> result = adminService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(admin1));
        assertTrue(result.contains(admin2));
        verify(adminRepositoryMock, times(1)).findAll();
    }

    @Test
    void update() {
        Admin admin = new Admin();
        when(adminRepositoryMock.update(admin)).thenReturn(admin);

        Admin result = adminService.update(admin);

        assertEquals(admin, result);
        verify(adminRepositoryMock, times(1)).update(admin);
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();

        adminService.deleteById(id);

        verify(adminRepositoryMock, times(1)).deleteById(id);
    }
}