package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.serviceinterfaces.FamilyAllowanceService;

import static org.junit.jupiter.api.Assertions.*;

class FamilyAllowanceServiceImplTest {

    private final FamilyAllowanceService service = new FamilyAllowanceServiceImpl();

    @Test
    void testAllowanceForLowSalary() {
        double allowance = service.calculateAllowance(5, 5000.0);
        assertEquals(1200.0, allowance);
    }

    @Test
    void testAllowanceForHighSalary() {
        double allowance = service.calculateAllowance(4, 9000.0);
        assertEquals(710.0, allowance);
    }

    @Test
    void testAllowanceForZeroChildren() {
        double allowance = service.calculateAllowance(0, 7000.0);
        assertEquals(0.0, allowance);
    }

    @Test
    void testAllowanceForMoreThanSixChildren() {
        double allowance = service.calculateAllowance(8, 5000.0);
        assertEquals(1350.0, allowance);
    }
}