package univali.andersonsimioni;

import java.time.LocalDate;

public class CarModel {
    /**
     * Depending on the type of vehicle,
     * its value may be higher
     */
    public enum Types{
        Hatch (1),
        Sedan (3),
        SUV (6),
        Coupe (13),
        Sport (18);

        private final int value;
        public int getValue(){
            return value;
        }
        private Types(int value){
            this.value = value;
        }
    }

    public enum Fuels{
        Flex,
        Alcohol,
        Gasoline,
        Methanol,
        NaturalGas,
    }

    private final CarBrand Brand;
    private final String Name;
    private final int LuxuryLevel;
    private final int BuildYear;
    private final int ModelYear;
    private final int NumberOfPorts;
    private final Fuels Fuel;
    private final Types Type;

    /**
     * Calculates the vehicle's average rating score
     * to have a calculation basis for your rental
     * @return
     */
    public int calculateAppreciationPoints(){
        int nowYear = LocalDate.now().getYear();

        float year = 1f / (nowYear == this.ModelYear ? (0.5f) : (nowYear - this.ModelYear));
        year *= 15;
        int points = this.LuxuryLevel * 4 + (int)year + Type.getValue() * 2;

        return points;
    }

    public CarBrand getBrand() {
        return Brand;
    }

    public String getName() {
        return Name;
    }

    public int getLuxuryLevel() {
        return LuxuryLevel;
    }

    public int getBuildYear() {
        return BuildYear;
    }

    public int getModelYear() {
        return ModelYear;
    }

    public Types getType() {
        return Type;
    }

    public int getNumberOfPorts() {
        return NumberOfPorts;
    }

    public Fuels getFuel() {
        return Fuel;
    }

    /**
     * Build string of client information to
     * display on screen or other device
     * @return
     */
    public String getDisplayInfo(){
        return "CarModel{\n" +
                "   brand: " + getBrand().getName() + ",\n" +
                "   name: " + getName() + ",\n" +
                "   luxuryLevel: " + getLuxuryLevel() + ",\n" +
                "   buildYear: " + getBuildYear() + ",\n" +
                "   modelYear: " + getModelYear() + ",\n" +
                "   type: " + getType().toString() + ",\n" +
                "   numberOfPorts: " + getNumberOfPorts() + ",\n" +
                "   fuel: " + getFuel().toString() + ",\n" +
                "}";
    }

    /**
     * Check if constructor information is valid
     */
    private void validateInformation(CarBrand brand, String name, int luxuryLevel, int buildYear, int modelYear, Types type, Fuels fuel, int numberOfPorts){
        if(brand == null)
            throw new IllegalArgumentException("brand is null");
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("name is null or empty");
        if(luxuryLevel <= 0)
            throw new IllegalArgumentException("luxuryLevel is smaller than zero");
        if(buildYear <= 0)
            throw new IllegalArgumentException("buildYear is smaller than zero");
        if(modelYear <= 0)
            throw new IllegalArgumentException("modelYear is smaller than zero");
        if(type == null)
            throw new IllegalArgumentException("type is null");
        if(fuel == null)
            throw new IllegalArgumentException("fuel is null");
        if(numberOfPorts <= 0)
            throw new IllegalArgumentException("numberOfPorts is zero or smaller than zero");

        if(buildYear > modelYear)
            throw new IllegalArgumentException("buildYear is bigger than modelYear");
    }

    public CarModel(CarBrand brand, String name, int luxuryLevel, int buildYear, int modelYear, Types type, Fuels fuel, int numberOfPorts) {
        validateInformation(brand, name, luxuryLevel, buildYear, modelYear, type, fuel, numberOfPorts);

        this.Brand = brand;
        this.Name = name;
        this.LuxuryLevel = luxuryLevel;
        this.BuildYear = buildYear;
        this.ModelYear = modelYear;
        this.Type = type;
        this.Fuel = fuel;
        this.NumberOfPorts = numberOfPorts;
    }
}
