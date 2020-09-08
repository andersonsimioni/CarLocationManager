package univali.andersonsimioni.UnitTest;

import univali.andersonsimioni.*;

import java.time.LocalDate;

public class CarAllocationServiceTest {
    private static void createTestBrandsAndModels(CarAllocationService service){
        CarBrand ford = new CarBrand("Ford", "USA", 2);
        CarBrand bmw = new CarBrand("Bmw", "Germany", 8);
        CarBrand nissan = new CarBrand("Nissan", "Japan", 2);
        CarBrand toyota = new CarBrand("Toyota", "Japan", 4);
        CarBrand volkswagen = new CarBrand("Volkswagen", "Germany", 2);

        service.registerCarBrand(bmw);
        service.registerCarBrand(ford);
        service.registerCarBrand(nissan);
        service.registerCarBrand(toyota);
        service.registerCarBrand(volkswagen);

        CarModel march = new CarModel(nissan, "March S", 1, 2015, 2016, CarModel.Types.Hatch, CarModel.Fuels.Flex, 5);
        CarModel gtr = new CarModel(nissan, "Gtr", 10, 2015, 2016, CarModel.Types.Sport, CarModel.Fuels.Gasoline, 5);
        CarModel gol = new CarModel(volkswagen, "Gol 1.0", 1, 2020, 2020, CarModel.Types.Hatch, CarModel.Fuels.Flex, 5);
        CarModel Voyage = new CarModel(volkswagen, "Voyage", 3, 2018, 2019, CarModel.Types.Hatch, CarModel.Fuels.Flex, 5);
        CarModel i320 = new CarModel(bmw, "320i", 12, 2015, 2016, CarModel.Types.Coupe, CarModel.Fuels.Gasoline, 5);
        CarModel supra = new CarModel(toyota, "Supra", 8, 1993, 1993, CarModel.Types.Sport, CarModel.Fuels.Gasoline, 3);
        CarModel fiesta = new CarModel(ford, "Fiesta", 1, 2015, 2016, CarModel.Types.Hatch, CarModel.Fuels.Flex, 5);
        CarModel mustang = new CarModel(ford, "Mustang", 12, 2015, 2016, CarModel.Types.Coupe, CarModel.Fuels.Gasoline, 5);

        service.registerCarModel("Ford", fiesta);
        service.registerCarModel("Ford", mustang);
        service.registerCarModel("Bmw", i320);
        service.registerCarModel("Nissan", march);
        service.registerCarModel("Nissan", gtr);
        service.registerCarModel("Toyota", supra);
        service.registerCarModel("Volkswagen", gol);
        service.registerCarModel("Volkswagen", Voyage);

        Car fleetCar1 = new Car( march, "MFW-7033", "Blue", "31AAS21515", "1311161651", 13000);
        Car fleetCar2 = new Car( Voyage, "MCC-5534", "Black", "16sdf1s1d", "345346346", 66111);
        Car fleetCar3 = new Car( fiesta, "ACO-7335", "Red", "31dsf1s1fe", "562342411", 32165);
        Car fleetCar4 = new Car( supra, "MAW-7036", "White", "xcv5w4we1", "234625747", 9848);
        Car fleetCar5 = new Car( i320, "ABC-1237", "Graphit", "s6f546wef9", "467967961", 6843);

        service.registerCarInFleet(fleetCar1);
        service.registerCarInFleet(fleetCar2);
        service.registerCarInFleet(fleetCar3);
        service.registerCarInFleet(fleetCar4);
        service.registerCarInFleet(fleetCar5);

    }

    private static void createTestClients(CarAllocationService service){
        service.registerClient(new Client("48988550026", "Anderson Simioni", "00000000000", LocalDate.parse("2000-05-02")));
        service.registerClient(new Client("48988550026", "Willian de Souza", "11111111111", LocalDate.parse("2000-05-02")));
    }

    public static void testAllFunctions(){
        CarAllocationService serviceTest = new CarAllocationService("MegaAutos", 0.45f);
        createTestBrandsAndModels(serviceTest);
        createTestClients(serviceTest);

        System.out.println("Available cars to allocate:");
        System.out.println(serviceTest.getAvailableCarsList());

        Car allocateCar = serviceTest.findCarToAllocate("Nissan", "March S", 2016);
        float price = serviceTest.calculateAllocationPrice("00000000000", allocateCar, 12, 1000);


        System.out.println("Starting allocating..");
        System.out.println("minimum deposit is: U$" + price);
        serviceTest.allocateCar("00000000000", allocateCar, 12, 1000, price + 1);

        System.out.println("Ending allocating..");
        String returnMessage = serviceTest.returnCar("00000000000", 1);
        System.out.println(returnMessage);
    }
}
