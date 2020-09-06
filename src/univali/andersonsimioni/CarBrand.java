package univali.andersonsimioni;

import java.util.ArrayList;

public class CarBrand {
    private final String Name;
    private final String Nationality;
    private final int LuxuryLevel;
    private final ArrayList<CarModel> Models;

    /**
     * Register new car model in array list of models,
     * if car already exist throw new IllegalArgumentException
     * @param model
     */
    public void registerNewModel(CarModel model){
        if(model == null)
            throw new IllegalArgumentException("model is null");

        if(existCarModel(model.getName(), model.getModelYear()))
            throw new IllegalArgumentException("Car model already exist");

        Models.add(model);
    }

    /**
     * Find car model by name and model year,
     * if exist return car model class instance in arrayList of models,
     * if not exist throw IllegalArgumentException
     * @param modelName
     * @param modelYear
     * @return
     */
    public CarModel getModel(String modelName, int modelYear){
        for (CarModel model:Models)
            if(model.getName().equals(modelName) && model.getModelYear() == modelYear)
                return model;

        throw new IllegalArgumentException("Model not found");
    }

    /**
     * Check if car model exist by name and model year,
     * if exist return true, if not return false
     * @param modelName
     * @param modelYear
     * @return
     */
    public boolean existCarModel(String modelName, int modelYear){
        for (CarModel _model:Models)
            if(_model.getName().equals(modelName) && _model.getModelYear() == modelYear)
                return true;

        return false;
    }

    /**
     * Calculates the vehicle model average rating score
     * to have a calculation basis for your rental,
     * if not found model throw IllegalArgumentException
     * @return
     */
    public int calculateAppreciationPoints(String modelName, int modelYear){
        if(existCarModel(modelName, modelYear) == false)
            throw new IllegalArgumentException("model not found");

        CarModel model = getModel(modelName, modelYear);
        int points = model.calculateAppreciationPoints();
        points += this.LuxuryLevel;

        return points;
    }

    public String getName() {
        return Name;
    }

    public String getNationality() {
        return Nationality;
    }

    public int getLuxuryLevel() {
        return LuxuryLevel;
    }

    public ArrayList<CarModel> getModels() {
        return Models;
    }

    /**
     * Build string of client information to
     * display on screen or other device
     * @return
     */
    public String getDisplayInfo(){
        String info = "CarBrand{\n" +
                      "   name: " + getName() + ",\n" +
                      "   luxuryLevel: " + getLuxuryLevel() + ",\n" +
                      "   Nationality: " + getNationality() + ",\n" +
                      "   models: {\n";
        for (CarModel model:Models)
            info += model.getDisplayInfo() + ",";

        info += "\n   }\n}";

        return info;
    }

    /**
     * Check if constructor information is valid
     */
    private void validateInformation(String name, String nationality, int luxuryLevel){
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("name is null or empty");
        if(nationality == null || nationality.isEmpty())
            throw new IllegalArgumentException("nationality is null or empty");

        if(luxuryLevel <= 0)
            throw new IllegalArgumentException("Invalid luxury level");
    }

    public CarBrand(String name, String nationality, int luxuryLevel) {
        validateInformation(name, nationality, luxuryLevel);

        this.Name = name;
        this.Nationality = nationality;
        this.LuxuryLevel = luxuryLevel;
        this.Models = new ArrayList<CarModel>();
    }
}
