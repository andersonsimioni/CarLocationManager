package univali.andersonsimioni;

public class Car {
    private final CarModel Model;
    private final String Board;
    private final String Color;
    private final String Chassis;
    private final String Renavam;
    private int Mileage;

    /**
     * Calculate location price and return in float value
     * @param basePrice
     * @return
     */
    public float calculateDailyPrice(float basePrice){
        if(basePrice <= 0)
            throw new IllegalArgumentException("basePrice is equal or smaller than zero");

        int points = getModel().calculateAppreciationPoints();
        float value = points * basePrice;

        return value;
    }

    public CarModel getModel() {
        return Model;
    }

    public String getBoard() {
        return Board;
    }

    public String getColor() {
        return Color;
    }

    public String getChassis() {
        return Chassis;
    }

    public String getRenavam() {
        return Renavam;
    }

    public int getMileage() {
        return Mileage;
    }

    /**
     * Build string of client information to
     * display on screen or other device
     * @return
     */
    public String getDisplayInfo(){
        return "Car{\n" +
                "   model: " + getModel().getDisplayInfo() + ",\n" +
                "   board: " + getBoard() + ",\n" +
                "   color: " + getColor() + ",\n" +
                "   chassis: " + getChassis() + ",\n" +
                "   renavam: " + getRenavam() + ",\n" +
                "   mileage: " + getMileage() + "km,\n" +
                "}";
    }

    /**
     * Updates the car's mileage,
     * however if the new mileage is less than the previous ones,
     * an IllegalArgumentException will be triggered
     * @param newMileage
     */
    public void updateMileage(int newMileage){
        if(newMileage < this.Mileage)
            throw new IllegalArgumentException("adulterated odometer, new value is less than the old one");

        this.Mileage = newMileage;
    }

    /**
     * Check if constructor information is valid
     * @param model
     * @param board
     * @param color
     * @param chassis
     * @param renavam
     * @param mileage
     */
    private void validateInformation(CarModel model, String board, String color, String chassis, String renavam, int mileage){
        if(model == null)
            throw new IllegalArgumentException("model is null");
        if(board == null || board.isEmpty())
            throw new IllegalArgumentException("board is null or empty");
        if(color == null || color.isEmpty())
            throw new IllegalArgumentException("color is null or empty");
        if(chassis == null || chassis.isEmpty())
            throw new IllegalArgumentException("chassis is null or empty");
        if(renavam == null || renavam.isEmpty())
            throw new IllegalArgumentException("renavam is null or empty");
        if(mileage < 0)
            throw new IllegalArgumentException("mileage is smaller than zero");
    }

    public Car(CarModel model, String board, String color, String chassis, String renavam, int mileage) {
        Model = model;
        Board = board;
        Color = color;
        Chassis = chassis;
        Renavam = renavam;
        Mileage = mileage;
    }
}
