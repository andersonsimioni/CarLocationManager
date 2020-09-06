package univali.andersonsimioni;

import java.time.LocalDate;

/**
 * The client's main role will be to rent cars
 * at a company that provides car rental services,
 * the company will also be able to register and update a client
 */
public class Client {
    private String Phone; //Contact phone number
    private String Name;
    private LocalDate Birth;
    private final String Cpf;
    private LocalDate UpdateInfoDate; //Date of last update client information
    private final LocalDate RegisterDate; //Date of client registration in "Car Alocation Service"

    public String getName() {
        return Name;
    }

    public String getCpf() {
        return Cpf;
    }

    public LocalDate getBirth() {
        return Birth;
    }

    public String getPhone() {
        return Phone;
    }

    public LocalDate getUpdateInfoDate() {
        return UpdateInfoDate;
    }

    public LocalDate getRegisterDate() {
        return RegisterDate;
    }

    /**
     * Build string of client information to
     * display on screen or other device
     * @return
     */
    public String getDisplayInfo(){
        return "Client{\n" +
                "   name: " + getName() + ",\n" +
                "   CPF: " + getCpf() + ",\n" +
                "   birth: " + getBirth().toString() + ",\n" +
                "   phone: " + getPhone() + ",\n" +
                "   register: " + getRegisterDate() + ",\n" +
                "   update info: " + getUpdateInfoDate().toString() +
                "\n}";
    }

    /**
     * Check if constructor and update params is valid,
     * if not throw argument exception
     */
    private void validateInformation(String phone, String name, String cpf, LocalDate birth){
        if(phone == null || phone.isEmpty())
            throw new IllegalArgumentException("phone is null or empty");
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("name is null or empty");
        if(cpf == null || cpf.isEmpty())
            throw new IllegalArgumentException("cpf is null or empty");

        if(birth == null)
            throw new IllegalArgumentException("birth");

        int yearValidation = (LocalDate.now().getYear() - birth.getYear());
        if(yearValidation <= 0)
            throw new IllegalArgumentException("Invalid birth, date is bigger than now");
        if(yearValidation >= 150)
            throw new IllegalArgumentException("Possible age cheat... is bigger than 150 years..");
    }

    /**
     * Update client information
     */
    public void update(String phone, String name, LocalDate birth){
        validateInformation(phone, name, "NO NEED CPF", birth);

        this.Phone = phone;
        this.Name = name;
        this.Birth = birth;
        this.UpdateInfoDate = LocalDate.now();
    }

    public Client(String phone, String name, String cpf, LocalDate birth) {
        validateInformation(phone, name, cpf, birth);
        this.Phone = phone;
        this.Name = name;
        this.Cpf = cpf;
        this.Birth = birth;

        this.RegisterDate = LocalDate.now();
        this.UpdateInfoDate = LocalDate.now();
    }
}
