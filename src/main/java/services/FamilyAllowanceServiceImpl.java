package services;

import services.serviceinterfaces.FamilyAllowanceService;

public class FamilyAllowanceServiceImpl implements FamilyAllowanceService {
    private static final double SALARY_THRESHOLD_1 = 6000.0;
    private static final double SALARY_THRESHOLD_2 = 8000.0;
    private static final double ALLOWANCE_FIRST_GROUP_LOW = 300.0;
    private static final double ALLOWANCE_SECOND_GROUP_LOW = 150.0;
    private static final double ALLOWANCE_FIRST_GROUP_HIGH = 200.0;
    private static final double ALLOWANCE_SECOND_GROUP_HIGH = 110.0;

    @Override
    public double calculateAllowance(int numberOfChildren, double salary) {
        if (numberOfChildren <= 0) return 0;

        double totalAllowance = 0.0;
        int cappedChildren = Math.min(numberOfChildren, 6);  // Max 6 children are eligible

        for (int i = 1; i <= cappedChildren; i++) {
            if (salary < SALARY_THRESHOLD_1) {  // Salary < 6000 DH
                totalAllowance += (i <= 3 ? ALLOWANCE_FIRST_GROUP_LOW : ALLOWANCE_SECOND_GROUP_LOW);
            } else if (salary > SALARY_THRESHOLD_2) {  // Salary > 8000 DH
                totalAllowance += (i <= 3 ? ALLOWANCE_FIRST_GROUP_HIGH : ALLOWANCE_SECOND_GROUP_HIGH);
            }
        }
        return totalAllowance;
    }
}
