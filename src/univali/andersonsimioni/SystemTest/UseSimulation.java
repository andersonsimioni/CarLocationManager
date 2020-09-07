package univali.andersonsimioni.SystemTest;

import univali.andersonsimioni.*;

import java.time.LocalDate;

public class UseSimulation {
    public static void run(){
        /* sources */
        Client client1 = new Client("48988550026", "Anderson Simioni", "00000000000", LocalDate.parse("2000-05-02"));
        Client client2 = new Client( "48998484398", "Willian de Souza", "00000000000", LocalDate.parse("2001-12-27"));

        CarBrand brand = new CarBrand( "Volkswagen", "German", 1);
        CarModel model1 = new CarModel(brand, "Gol", 1, 2015, 2017, CarModel.Types.Hatch, CarModel.Fuels.Flex, 2);
        CarModel model2 = new CarModel(brand, "Voyage", 1, 2015, 2019, CarModel.Types.Sedan, CarModel.Fuels.Flex, 2);

        brand.registerNewModel(model1);
        brand.registerNewModel(model2);

        Car allocationCar1 = new Car(model1, "MFW-7033", "Black", "A254SDCASD", "164549616161", 90000);
        Car allocationCar2 = new Car(model2, "ABC-2021", "White", "UGSAD12312", "456788972131", 23000);

        /*
        System.out.println("Show registered clients:");
        System.out.println(client1.getDisplayInfo());
        System.out.println(client2.getDisplayInfo());

        System.out.println("Show registered cars:");
        System.out.println(allocationCar1.getDisplayInfo());
        System.out.println(allocationCar2.getDisplayInfo());*/

        System.out.println("Start allocation!");
        Allocation allocation = new Allocation(
                client2,
                allocationCar2,
                4,
                "Travel with family to south",
                200
        );
        System.out.println(allocation.getDisplayInfo());
        allocation.unallocate();
        System.out.println(allocation.getNote());
        System.out.println("Success!");


    }
}
