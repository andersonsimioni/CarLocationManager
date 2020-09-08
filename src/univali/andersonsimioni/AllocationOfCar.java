package univali.andersonsimioni;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class AllocationOfCar {
    //region VARIABLES
    private boolean Returned;
    private LocalDate ReturnedDate;
    private final Car Car;
    private final Client Client;
    private final LocalDate StartDate;
    private final LocalDate ReturnDate;
    private int TripDistanceKm;
    private final float Deposit;
    private final CarAllocationService CarAllocationService;
    //endregion

    //region GETTERS
    public Car getCar() {
        return Car;
    }

    public Client getClient() {
        return Client;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public LocalDate getReturnDate() {
        return ReturnDate;
    }

    public CarAllocationService getCarAllocationService() {
        return CarAllocationService;
    }

    public boolean isReturned() {
        return Returned;
    }

    /**
     * If car not returned yet,
     * throw IllegalStateException
     * @return
     */
    public LocalDate getReturnedDate() {
        if(this.Returned == false)
            throw new IllegalStateException("Car has not returned yet");

        return ReturnedDate;
    }
    //endregion

    /**
     * Finalizes the allocation of the vehicle and returns
     * the message of the allocation payment status
     * @param allocationDurationDays
     * @return
     */
    public String returnCar(long allocationDurationDays){
        if(this.Returned)
            throw new IllegalArgumentException("The vehicle had already returned and the allocation ended");
        if(allocationDurationDays <= 0)
            throw new IllegalArgumentException("allocationDurationDays is equal or smaller than zero");

        float realPrice = this.calculateValue(allocationDurationDays);
        float estimatedPrice = this.calculateEstimatedValue();
        float returnedValue = (estimatedPrice - realPrice);
        float penalty = this.calculatePenalty(allocationDurationDays);
        long estimatedDays = this.calculateDurationDays();

        this.Returned = true;
        this.ReturnedDate = LocalDate.now();

        if(allocationDurationDays > estimatedDays)
            return "price for allocation is U$"+realPrice+", the customer suffered a penalty of U$"+penalty+" for exceeding the return date";

        if(allocationDurationDays < estimatedDays)
            return  "price for allocation is U$" + realPrice + ", vehicle returned before the estimated date, value returned: U$" + returnedValue;

        return "price for allocation is U$" + realPrice + ", client ";
    }

    /**
     * calculate allocation total duration days
     * @return
     */
    public long calculateDurationDays(){
        Period duration = Period.between(this.StartDate, this.ReturnDate);
        return duration.getDays();
    }

    /**
     * calculate penalty value to client pay
     * @param durationDays
     * @return
     */
    public float calculatePenalty(float durationDays){
        Long normalDuration = (long)Period.between(this.StartDate, this.ReturnDate).getDays();
        if(durationDays > normalDuration)
        {
            float penalty = (float)(durationDays - normalDuration) * 0.10f;
            return penalty;
        }

        return  0f;
    }

    /**
     * Calculate price of allocation corresponding by days
     * @param days
     * @return
     */
    public float calculateValue(long days){
        Long normalDuration = (long)Period.between(this.StartDate, this.ReturnDate).getDays();

        float basePrice = this.CarAllocationService.getBasePrice();
        float dayPrice = this.Car.calculateDailyPrice(basePrice);
        float totalPrice = (dayPrice * (float)days);

        //If the car exceeds 500km of distance from the rental company,
        // it will be taxed at 1% per km
        if(this.TripDistanceKm > 500)
            totalPrice += (float)(TripDistanceKm - 500) * 0.01f;

        //If the customer delivers the car after the agreed date,
        // it will be penalized with 10% per day
        totalPrice += calculatePenalty(days);

        return totalPrice;
    }

    /**
     * Calculates the rental price based on the scheduled day for the car's return
     * @return
     */
    public float calculateEstimatedValue(){
        Period duration = Period.between(this.StartDate, this.ReturnDate);
        long days = duration.getDays();
        float price = calculateValue(days);

        return price;
    }

    /**
     * Check if constructor information is valid
     */
    private void validateInformation(Car car, Client client, LocalDate returnDate, int tripDistanceKm,  float deposit, CarAllocationService carALlocationService){
        if(car == null)
            throw new IllegalArgumentException("car is null");
        if(client == null)
            throw new IllegalArgumentException("client is null");
        if(carALlocationService == null)
            throw new IllegalArgumentException("carAllocationService is null");
        if(returnDate == null)
            throw new IllegalArgumentException("returnDate is null");
        if(tripDistanceKm <= 0)
            throw new IllegalArgumentException("tripDistance is equal or smaller than zero");
        if(deposit < 0)
            throw new IllegalArgumentException("deposit is smaller than zero");

        Period duration = Period.between(LocalDate.now(), returnDate);
        if(duration.getDays() <= 0)
            throw new IllegalArgumentException("Invalid returnDate");
    }

    public AllocationOfCar(Car car, Client client, LocalDate returnDate, int tripDistanceKm,  float deposit, CarAllocationService carALlocationService) {
        validateInformation(car, client, returnDate, tripDistanceKm, deposit, carALlocationService);

        this.Car = car;
        this.Client = client;
        this.StartDate = LocalDate.now();
        this.ReturnDate = returnDate;
        this.Deposit = deposit;
        this.CarAllocationService = carALlocationService;
        this.Returned = false;
        this.TripDistanceKm = tripDistanceKm;
    }
}
