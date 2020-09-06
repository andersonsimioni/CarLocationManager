package univali.andersonsimioni.UnitTest;

import univali.andersonsimioni.Car;
import univali.andersonsimioni.CarBrand;
import univali.andersonsimioni.CarModel;

public class CarTest {
    /**
     * Test all functions of car class
     */
    public static void testAllFunctions(){
        CarBrand brand = new CarBrand("Nissan", "Japan", 1);
        CarModel model = new CarModel(brand, "March S", 1, 2015,2017, CarModel.Types.Hatch, CarModel.Fuels.Flex, 5);
        brand.registerNewModel(model);
        Car newALlocationCar = new Car(model, "MFW-7033", "Graphit", "A254SDCASD", "164549616161", 90000);

        System.out.println(newALlocationCar.getDisplayInfo());

        System.out.println("Simulating ALlocation price calculation.. base price used: 1.12");
        float price = newALlocationCar.calculateDailyPrice(1.12f);
        System.out.println("Price for " + newALlocationCar.getFullNameInformation() + ": U$" + price + " per day");
    }
}
