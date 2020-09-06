package univali.andersonsimioni;

public class CarModel {
    /**
     * Depending on the type of vehicle,
     * its value may be higher
     */
    enum Types{
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

    private final String Name;
    private final int LuxuryLevel;
    private final int BuildYear;
    private final int ModelYear;
    private final Types Type;

    /**
     * Calculates the vehicle's average rating score
     * to have a calculation basis for your rental
     * @return
     */
    public int calculateAppreciationPoints(){
        int year = (this.ModelYear - 2000);
        int points = this.LuxuryLevel * 4 + year * 3 + Type.getValue() * 2;

        return points;
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

    /**
     * Check if constructor information is valid
     */
    private void validateInformation(String name, int luxuryLevel, int buildYear, int modelYear, Types type){
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

        if(buildYear > modelYear)
            throw new IllegalArgumentException("buildYear is bigger than modelYear");
    }

    public CarModel(String name, int luxuryLevel, int buildYear, int modelYear, Types type) {
        validateInformation(name, luxuryLevel, buildYear, modelYear, type);

        this.Name = name;
        this.LuxuryLevel = luxuryLevel;
        this.BuildYear = buildYear;
        this.ModelYear = modelYear;
        this.Type = type;
    }
}
