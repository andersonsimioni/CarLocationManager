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
        CarModel model = new CarModel(brand, "March S", 1, 2015,2016, CarModel.Types.Hatch, CarModel.Fuels.Flex, 5);
        brand.registerNewModel(model);
        Car newLocationCar = new Car(model, "MFW-7033", "Graphit", "A254SDCASD", "164549616161", 90000);

        System.out.println(newLocationCar.getDisplayInfo());

        System.out.println("Simulating location price calculation.. base price used: 1.12");
        float price = newLocationCar.calculateDailyPrice(1.12f);
        String carCompleteName = newLocationCar.getModel().getName() + " " + newLocationCar.getModel().getModelYear();
        System.out.println("Price for " + carCompleteName + ": U$" + price + " per day");
    }
}
