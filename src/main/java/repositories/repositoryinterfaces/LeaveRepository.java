package repositories.repositoryinterfaces;

import entities.Leave;

import java.util.List;
import java.util.UUID;

public interface LeaveRepository extends GenericRepository<Leave>{
    List<Leave> findAllByEmployeeId(UUID employeeId);
}
