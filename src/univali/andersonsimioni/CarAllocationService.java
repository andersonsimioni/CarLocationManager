package univali.andersonsimioni;

import java.time.LocalDate;
import java.util.ArrayList;

public class CarAllocationService {
    //region VARIABLES
    private float BasePrice;
    private final String Name;
    private final ArrayList<Client> Clients;
    private final ArrayList<CarBrand> WorldCars;
    private final ArrayList<Car> FleetOfCars;
    private final ArrayList<AllocationOfCar> Allocations;
    //endregion

    /**
     * Find client registration class instance by cpf,
     * if not found throw IllegalArgumentExcpetion
     * @param cpf
     * @return
     */
    private Client findClient(String cpf){
        for(Client client:Clients)
            if(client.getCpf().equals(cpf))
                return client;

        throw new IllegalArgumentException("Client not found");
    }

    /**
     * Find available cars by brandName, modelName and modelYear to allocate,
     * if not found throw IllegalArgumentException
     * @param brandName
     * @param modelName
     * @param modelYear
     * @return
     */
    public Car findCarToAllocate(String brandName, String modelName, int modelYear){
        for(Car car:FleetOfCars)
            if(car.getModel().getName().equals(modelName) &&
               car.getModel().getModelYear() == modelYear &&
               car.getModel().getBrand().getName() == brandName &&
               carIsAvailable(car))
                return car;

        throw new IllegalArgumentException("No cars available for allocation");
    }

    /**
     * Return a list of cars brand, model and year in string
     * @return
     */
    public String getAvailableCarsList(){
        String list = "";

        for (Car car:FleetOfCars)
            if(carIsAvailable(car))
                list += car.getModel().getBrand().getName() + " " + car.getModel().getName() + " " + car.getModel().getModelYear() + "\n";

        return list;
    }

    /**
     * Check if car is available to aLlocation,
     * if not available return false
     * @param car
     * @return
     */
    private boolean carIsAvailable(Car car){
        if(car == null)
            throw new IllegalArgumentException("car is null");

        for(AllocationOfCar allocation: Allocations)
            if(allocation.getCar().getRenavam().equals(car.getRenavam()) &&
               allocation.getCar().getChassis().equals(car.getChassis()) &&
               allocation.getCar().getBoard().equals(car.getBoard()) &&
               allocation.isReturned() == false)
                return false;

        return true;
    }

    /**
     * Check if client exist by cpf
     * @return
     */
    private boolean clientExist(String cpf){
        if(cpf == null || cpf.isEmpty())
            throw new IllegalArgumentException("cpf is null or empty");

        for(Client client:Clients)
            if(client.getCpf().equals(cpf))
                return true;

        return false;
    }

    /**
     * Check if car brand exist by name
     * @param name
     * @return
     */
    private boolean carBrandExist(String name){
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("name is null or empty");

        for(CarBrand brand:this.WorldCars)
            if(brand.getName().equals(name))
                return true;

        return false;
    }

    /**
     * Find car in fleet by board, chassis and renavam,
     * if exist return true, if not return false
     * @param board
     * @param chassis
     * @param renavam
     * @return
     */
    private boolean carExistInFleet(String board, String chassis, String renavam){
        if(board == null || board.isEmpty())
            throw new IllegalArgumentException("board is null or empty");
        if(chassis == null || chassis.isEmpty())
            throw new IllegalArgumentException("chassis is null or empty");
        if(renavam == null || renavam.isEmpty())
            throw new IllegalArgumentException("renavam is null or empty");

        for(Car car:FleetOfCars)
            if(car.getBoard().equals(board) && car.getChassis().equals(chassis) && car.getRenavam().equals(renavam))
                return true;

        return false;
    }

    /**
     * Find car int fleet by board, chassis, renavam.
     * if not found return function,
     * if any vehicle with cloned information is found an exception is returned
     * @param board
     * @param chassis
     * @param renavam
     * @return
     */
    private void validateCarExistInFleet(String board, String chassis, String renavam){
        if(board == null || board.isEmpty())
            throw new IllegalArgumentException("board is null or empty");
        if(chassis == null || chassis.isEmpty())
            throw new IllegalArgumentException("chassis is null or empty");
        if(renavam == null || renavam.isEmpty())
            throw new IllegalArgumentException("renavam is null or empty");

        for(Car car:FleetOfCars) {
            if (car.getBoard().equals(board))
                throw new IllegalArgumentException("Board already registered in another vehicle");
            if (car.getChassis().equals(chassis))
                throw new IllegalArgumentException("Chassis already registered in another vehicle");
            if (car.getRenavam().equals(renavam))
                throw new IllegalArgumentException("Renavam already registered in another vehicle");
        }
    }

    /**
     * Find car brand by name and return class instance in world cars
     * @param name
     * @return
     */
    private CarBrand findCarBrand(String name){
        for(CarBrand brand:WorldCars)
            if(brand.getName().equals(name))
                return brand;

        throw new IllegalArgumentException("Car brand does not exist");
    }

    /**
     * Check if client have open allocation yet
     * @param client
     * @return
     */
    private boolean clientHaveOpenAllocation(Client client){
        if(client == null)
            throw new IllegalArgumentException("invalid client, client is null");

        for(AllocationOfCar allocation: Allocations)
            if(allocation.isReturned() == false)
                if(allocation.getClient().getCpf().equals(client.getCpf()))
                    return true;

        return false;
    }

    /**
     * Find open car allocation of client,
     * if not found throw IllegalArgumentException
     * @param clientCpf
     * @return
     */
    private AllocationOfCar findClientOpenAllocationOfCar(String clientCpf){
        if(clientCpf == null || clientCpf.isEmpty())
            throw new IllegalArgumentException("invalid client cpf");

        for(AllocationOfCar allocation: Allocations)
            if(allocation.isReturned() == false)
                if(allocation.getClient().getCpf().equals(clientCpf))
                    return allocation;

        throw new IllegalArgumentException("Allocation of client not found");
    }

    /**
     * Register client if not exist other client
     * with equal CPF, if exist throw IllegalArgumentException
     * @param client
     */
    public void registerClient(Client client){
        if(client == null)
            throw new IllegalArgumentException("client is null");
        if(clientExist(client.getCpf()))
            throw new IllegalArgumentException("Client with " + client.getCpf() + " already exist");

        this.Clients.add(client);
    }

    /**
     * Register new car brand into WorldCars arrayList,
     * if already exist car brand with equal name
     * throw IllegalArgumentException
     * @param newBrand
     */
    public void registerCarBrand(CarBrand newBrand){
        if(newBrand == null)
            throw new IllegalArgumentException("newBrand is null");
        if(carBrandExist(newBrand.getName()))
            throw new IllegalArgumentException("newBrand with " + newBrand.getName() + " name already exist");

        WorldCars.add(newBrand);
    }

    /**
     * Try to register new car model into existing brand,
     * if an error occurs, an exception will be triggered
     * @param brandName
     * @param newCarModel
     */
    public void registerCarModel(String brandName, CarModel newCarModel){
        if(carBrandExist(brandName) == false)
            throw new IllegalArgumentException(brandName + " Car brand does not exist");
        if(newCarModel == null)
            throw new IllegalArgumentException("newCarModel is null");

        try {
            findCarBrand(brandName).registerNewModel(newCarModel);
        }catch (IllegalArgumentException ex){
            throw ex;
        }
    }

    /**
     * Try register vehicle in fleet,
     * if any vehicle with cloned information is found an exception is returned
     * @param fleetCar
     */
    public void registerCarInFleet(Car fleetCar){
        if(fleetCar == null)
            throw new IllegalArgumentException("fleetCar is null");

        validateCarExistInFleet(fleetCar.getBoard(), fleetCar.getChassis(), fleetCar.getRenavam());
        FleetOfCars.add(fleetCar);
    }

    /**
     * Return simulating price of allocation
     * @return
     */
    public float calculateAllocationPrice(String clientCpf, Car allocateCar, long allocationDays, int tripDistanceKm){
        if(allocateCar == null)
            throw new IllegalArgumentException("please insert valid allocate car");
        if(carIsAvailable(allocateCar) == false)
            throw new IllegalArgumentException("Car is not available");
        if(clientExist(clientCpf) == false)
            throw new IllegalArgumentException("Client not found");
        if(allocationDays <= 0)
            throw new IllegalArgumentException("allocationDays is equal or smaller than zero");
        if(tripDistanceKm <= 0)
            throw new IllegalArgumentException("tripDistanceKm is equal or smaller than zero");

        Client client = findClient(clientCpf);
        LocalDate returnDate = LocalDate.now().plusDays(allocationDays);
        AllocationOfCar newAllocation = new AllocationOfCar(allocateCar, client, returnDate, tripDistanceKm, 0,this);

        return newAllocation.calculateEstimatedValue();
    }

    /**
     * Find client by cpf and car by brand, model and year,
     * if not found anything throw exception
     * @param clientCpf
     * @param allocateCar
     */
    public void allocateCar(String clientCpf, Car allocateCar, long allocationDays, int tripDistanceKm, float deposit){
        if(clientCpf == null || clientCpf.isEmpty())
            throw new IllegalArgumentException("client cpf is null or empty");
        if(allocateCar == null)
            throw new IllegalArgumentException("please insert valid allocate car");

        try {
            Client client = findClient(clientCpf);
            if(clientHaveOpenAllocation(client))
                throw new IllegalArgumentException("The customer still owns a rented vehicle");
            if(carIsAvailable(allocateCar) == false)
                throw new IllegalArgumentException("Car is not available");

            LocalDate returnDate = LocalDate.now().plusDays(allocationDays);
            AllocationOfCar newAllocation = new AllocationOfCar(allocateCar, client, returnDate, tripDistanceKm, deposit,this);

            float minimumDeposit = newAllocation.calculateEstimatedValue();
            if(deposit <= minimumDeposit)
                throw new IllegalArgumentException("fail to register allocation, minimum deposit is: U$" + minimumDeposit);

            Allocations.add(newAllocation);
        }catch (IllegalArgumentException ex){
            throw ex;
        }
    }

    /**
     * Closes the allocation of the customer's current car
     * and informs him of the amount to be paid corresponding
     * to the days he spent with the vehicle
     * @param clientCpf
     * @return payment and allocation status message to client
     */
    public String returnCar(String clientCpf, long allocationDurationDays){
        if(clientExist(clientCpf) == false)
            throw new IllegalArgumentException("client not found");
        if(allocationDurationDays <= 0)
            throw new IllegalArgumentException("allocationDurationDays is equal or smaller than zero");

        AllocationOfCar allocation = findClientOpenAllocationOfCar(clientCpf);
        return allocation.returnCar(allocationDurationDays);
    }

    public String getName() {
        return Name;
    }

    public float getBasePrice() {
        return BasePrice;
    }

    public CarAllocationService(String name, float basePrice) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("name is null or empty");
        if(basePrice <= 0)
            throw new IllegalArgumentException("basePrice is equal or smaller than zero");

        this.Name = name;
        this.BasePrice = basePrice;
        this.Clients = new ArrayList<Client>();
        this.WorldCars = new ArrayList<CarBrand>();
        this.FleetOfCars = new ArrayList<Car>();
        this.Allocations = new ArrayList<AllocationOfCar>();
    }
}
