package services;

import entities.Employee;
import entities.Leave;
import enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.repositoryinterfaces.LeaveRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class LeaveServiceImplTest {

    @Mock
    private LeaveRepository leaveRepository;

    @InjectMocks
    private LeaveServiceImpl leaveService;

    private Leave leave;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize a sample Leave object for testing
        leave = new Leave();
        Employee employee = new Employee();
        leave.setRequestId(UUID.randomUUID());
        leave.setEmployee(employee);
        leave.setStartDate(java.sql.Date.valueOf(LocalDate.of(2024, 10, 20)));
        leave.setEndDate(java.sql.Date.valueOf(LocalDate.of(2024, 10, 25)));
        leave.setReason("Vacation");
        leave.setStatus(Status.IN_PROGRESS);  // Assuming you have a Status enum defined
    }

    @Test
    void testSave() {
        when(leaveRepository.save(any(Leave.class))).thenReturn(leave);

        Leave savedLeave = leaveService.save(leave);

        assertNotNull(savedLeave);
        assertEquals(leave.getRequestId(), savedLeave.getRequestId());
        verify(leaveRepository, times(1)).save(leave);
    }

    @Test
    void testFindById() {
        when(leaveRepository.findById(leave.getRequestId())).thenReturn(Optional.of(leave));

        Optional<Leave> foundLeave = leaveService.findById(leave.getRequestId());

        assertTrue(foundLeave.isPresent());
        assertEquals(leave.getRequestId(), foundLeave.get().getRequestId());
        verify(leaveRepository, times(1)).findById(leave.getRequestId());
    }

    @Test
    void testFindAll() {
        when(leaveRepository.findAll()).thenReturn(Arrays.asList(leave));

        List<Leave> allLeaves = leaveService.findAll();

        assertFalse(allLeaves.isEmpty());
        assertEquals(1, allLeaves.size());
        assertEquals(leave.getRequestId(), allLeaves.get(0).getRequestId());
        verify(leaveRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(leaveRepository.update(any(Leave.class))).thenReturn(leave);

        Leave updatedLeave = leaveService.update(leave);

        assertNotNull(updatedLeave);
        assertEquals(leave.getRequestId(), updatedLeave.getRequestId());
        verify(leaveRepository, times(1)).update(leave);
    }

    @Test
    void testDeleteById() {
        doNothing().when(leaveRepository).deleteById(leave.getRequestId());

        assertDoesNotThrow(() -> leaveService.deleteById(leave.getRequestId()));
        verify(leaveRepository, times(1)).deleteById(leave.getRequestId());
    }

    @Test
    void testFindAllByEmployeeId() {
        UUID employeeId = UUID.randomUUID();  // Sample employee ID
        when(leaveRepository.findAllByEmployeeId(employeeId)).thenReturn(Arrays.asList(leave));

        List<Leave> employeeLeaves = leaveService.findAllByEmployeeId(employeeId);

        assertFalse(employeeLeaves.isEmpty());
        assertEquals(1, employeeLeaves.size());
        verify(leaveRepository, times(1)).findAllByEmployeeId(employeeId);
    }
}