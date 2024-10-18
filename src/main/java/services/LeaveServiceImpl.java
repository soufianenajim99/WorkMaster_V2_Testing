package services;

import entities.Leave;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repositories.repositoryinterfaces.LeaveRepository;
import services.serviceinterfaces.LeaveService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;

    @Inject
    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    @Transactional
    public Leave save(Leave leave) {
        return leaveRepository.save(leave);
    }

    @Override
    public Optional<Leave> findById(UUID id) {
        return leaveRepository.findById(id);
    }

    @Override
    public List<Leave> findAll() {
        return leaveRepository.findAll();
    }

    @Override
    @Transactional
    public Leave update(Leave leave) {
        return leaveRepository.update(leave);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        leaveRepository.deleteById(id);
    }

    @Override
    public List<Leave> findAllByEmployeeId(UUID employeeId) {
        return leaveRepository.findAllByEmployeeId(employeeId);
    }
}
