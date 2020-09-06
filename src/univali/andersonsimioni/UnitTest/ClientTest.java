package univali.andersonsimioni.UnitTest;

import univali.andersonsimioni.Client;
import java.time.LocalDate;

public class ClientTest {

    /**
     * Test all functions of Client class
     */
    public static void testAllFunctions(){
        Client newClient = new Client(
                        "48988550026",
                        "Anderson Simioni",
                        "00000000000",
                        LocalDate.parse("2000-05-02"));

        System.out.println("Creating new client");
        System.out.println(newClient.getDisplayInfo());
        System.out.println("\nUpdating information of client");
        newClient.update("4832414581", "Willian", LocalDate.parse("2000-05-02"));
        System.out.println(newClient.getDisplayInfo());
    }

}
