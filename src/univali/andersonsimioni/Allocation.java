package univali.andersonsimioni;

import java.time.LocalDate;

public class Allocation {
    private final LocalDate AllocationTime;
    private final Client Client;
    private final Car Car;
    private LocalDate UnallocateTime;
    private int PretenseOfTime;
    private String Goal;
    private float SecurityDeposit;

    public Allocation(univali.andersonsimioni.Client client, univali.andersonsimioni.Car car, int pretenseOfTime, String goal, float securityDeposit) {
        this.validateInputs(car,client,pretenseOfTime,securityDeposit);
        AllocationTime = LocalDate.now();
        Client = client;
        Car = car;
        PretenseOfTime = pretenseOfTime;
        Goal = goal;
        SecurityDeposit = securityDeposit;

        car.setStatus(univali.andersonsimioni.Car.AvailableStatus.Allocated);
    }

    private void validateInputs(Car car, Client client, int pretenseOfTime, float securityDeposit){
        if(car == null)
            throw new IllegalArgumentException("Car is null");

        if(car.getStatus() == "Allocated")
            throw new IllegalArgumentException("Car already allocated");

        if(client == null)
            throw new IllegalArgumentException("Client is null");

        if(pretenseOfTime <= 0)
            throw new IllegalArgumentException("Pretense of time is invalid, just 1 on higher accepted");

        if(securityDeposit <= car.calculateDailyPrice(1.12f)*pretenseOfTime)
            throw new IllegalArgumentException("Security Deposit is lower than minimum, minimum required value: "+(car.calculateDailyPrice(1.12f)*pretenseOfTime));
    }

    public LocalDate getUnallocateTime() {
        return UnallocateTime;
    }

    public void setUnallocateTime(LocalDate unallocateTime) {
        UnallocateTime = unallocateTime;
    }

    public int getPretenseOfTime() {
        return PretenseOfTime;
    }

    public void setPretenseOfTime(int pretenseOfTime) {
        PretenseOfTime = pretenseOfTime;
    }

    public String getGoal() {
        return Goal;
    }

    public void setGoal(String goal) {
        Goal = goal;
    }

    public float getSecurityDeposit() {
        return SecurityDeposit;
    }

    public void setSecurityDeposit(float securityDeposit) {
        SecurityDeposit = securityDeposit;
    }
}
