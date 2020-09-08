package univali.andersonsimioni;

public class Main {

    public static void main(String[] args) {
        /* execute unit tests */
	    //univali.andersonsimioni.UnitTest.CarTest.testAllFunctions();
	    //univali.andersonsimioni.UnitTest.CarBrandTest.testAllFunctions();
        //univali.andersonsimioni.UnitTest.ClientTest.testAllFunctions();
        //univali.andersonsimioni.UnitTest.CarModelTest.testAllFunctions();

        /* execute system tests */
        //univali.andersonsimioni.SystemTest.UseSimulation.run();

        /*All tests*/
        univali.andersonsimioni.UnitTest.CarAllocationServiceTest.testAllFunctions();
    }
}
