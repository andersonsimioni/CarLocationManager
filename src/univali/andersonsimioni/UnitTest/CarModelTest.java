package univali.andersonsimioni.UnitTest;

import univali.andersonsimioni.CarBrand;
import univali.andersonsimioni.CarModel;

public class CarModelTest {
    /**
     * Test all functions of CarModel class
     */
    public static void testAllFunctions(){
        CarBrand newBrand = new CarBrand("Toyota", "Japan", 3);
        CarModel newModel = new CarModel(newBrand,"Supra", 4, 1992, 1993, CarModel.Types.Sport, CarModel.Fuels.Gasoline, 4);

        System.out.println("Appreciation points: " + newModel.calculateAppreciationPoints());
        System.out.println(newModel.getDisplayInfo());
    }
}
