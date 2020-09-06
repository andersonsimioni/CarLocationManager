package univali.andersonsimioni.UnitTest;

import univali.andersonsimioni.CarBrand;
import univali.andersonsimioni.CarModel;

public class CarBrandTest {
    /**
     * Test all functions of CarBrand class
     */
    public static void testAllFunctions(){
        CarBrand newBrand = new CarBrand("BMW", "Germany", 8);
        CarModel newModelToRegister = new CarModel(newBrand, "M3", 12, 2015, 2016, CarModel.Types.Sport);
        newBrand.registerNewModel(newModelToRegister);

        System.out.println(newBrand.getDisplayInfo());

        CarModel model = newBrand.getModel("M3", 2016);
        System.out.println("\nSearched model: ");
        System.out.println(model.getDisplayInfo());
    }
}
