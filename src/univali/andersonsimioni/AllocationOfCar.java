package univali.andersonsimioni;

import java.time.Duration;
import java.time.LocalDate;

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
     * Calculate price of allocation corresponding by days
     * @param days
     * @return
     */
    private float calculateValue(long days){
        Long normalDuration = Duration.between(this.StartDate, this.ReturnDate).toDays();

        float basePrice = this.CarAllocationService.getBasePrice();
        float dayPrice = this.Car.calculateDailyPrice(basePrice);
        float totalPrice = (dayPrice * (float)days);

        //If the car exceeds 500km of distance from the rental company,
        // it will be taxed at 1% per km
        if(this.TripDistanceKm > 500)
            totalPrice += (float)(TripDistanceKm - 500) * 0.01f;

        //If the customer delivers the car after the agreed date,
        // it will be penalized with 10% per day
        if(days > normalDuration)
        {
            float penalty = (float)(days - normalDuration) * 0.10f;
            totalPrice += penalty;
        }

        return totalPrice;
    }

    /**
     * Calculates the rental price based on the scheduled day for the car's return
     * @return
     */
    private float calculateEstimatedValue(){
        Duration duration = Duration.between(this.StartDate, this.ReturnDate);
        long days = duration.toDays();
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
        if(deposit <= 0)
            throw new IllegalArgumentException("deposit is equal or smaller than zero");

        Duration duration = Duration.between(LocalDate.now(), returnDate);
        if(duration.getSeconds() <= 0)
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

        float estimated = calculateEstimatedValue();
        if(estimated >= deposit)
            throw new IllegalArgumentException("Deposit value must be greater than U$" + estimated + "");
    }
}
