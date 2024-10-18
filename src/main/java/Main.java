//import entities.Department;
//import entities.Employee;

import entities.Admin;
import entities.Department;
import entities.Employee;
import entities.Leave;
import jakarta.enterprise.inject.spi.CDI;
import repositories.AdminRepositoryImpl;
import repositories.EmployeeRepositoryImpl;
import repositories.LeaveRepositoryImpl;
import repositories.repositoryinterfaces.AdminRepository;
import repositories.repositoryinterfaces.EmployeeRepository;
import repositories.repositoryinterfaces.LeaveRepository;
import services.AdminServiceImpl;
import services.EmployeeServiceImpl;
import services.LeaveServiceImpl;
import services.serviceinterfaces.AdminService;
import services.serviceinterfaces.EmployeeService;
import services.serviceinterfaces.LeaveService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {


//        LeaveRepository leaveRepository = new LeaveRepositoryImpl();
//        LeaveService leaveService = new LeaveServiceImpl(leaveRepository);
//        Leave leave = new Leave();
//        leave.setRequestId(UUID.randomUUID());
//        leave.setStartDate(java.sql.Date.valueOf(LocalDate.of(2024, 10, 20)));
//        leave.setEndDate(java.sql.Date.valueOf(LocalDate.of(2024, 10, 25)));
//        leave.setReason("Vacation");
//
//
//        EmployeeRepository adminRepository = new EmployeeRepositoryImpl();
//        EmployeeService adminService = new EmployeeServiceImpl(adminRepository);
//        Employee employee = adminService.findById(UUID.fromString("03039994-950f-4751-a584-323094cd3e87")).get();
//
//        leave.setEmployee(employee);
//        Leave savedLeave = leaveService.save(leave);
//        System.out.println("Saved Leave: " + savedLeave);
//        Department dep1 = new Department("HR departement");
//
//        Employee emp1 = new Employee(
//                "123 Main St",                    // address
//                "password123",                     // password
//                "john_doe",                        // username
//                dep1,                        // department
//                50000.0,                           // salary
//                LocalDate.of(1990, 1, 15),         // dateOfBirth
//                "SSN123456789",                    // socialSecurityNumber
//                LocalDate.of(2020, 5, 20),         // hireDate
//                4.5,                               // performanceRating
//                15.0,                              // leaveBalance
//                2                                  // children_number
//        );
//
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(dep1);
//        entityManager.persist(emp1);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();

//
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();

        // Initialize AdminRepository and AdminService
//        AdminRepository adminRepository = new AdminRepositoryImpl();
//        AdminService adminService = new AdminServiceImpl(adminRepository);
//
//
//           Optional<Employee> admin = adminService.findById(UUID.fromString("97570ba4-d9d4-4fdc-9675-fbd24e260884"));
//        System.out.println(admin);



    }
}
