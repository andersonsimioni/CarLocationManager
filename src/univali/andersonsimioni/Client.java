package univali.andersonsimioni;

import jdk.jfr.Timespan;

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
    private final LocalDate RegisterDate; //Date of client registration in "Car Location Service"

    public String getName() {
        return Name;
    }

    public String getCpf() {
        return Cpf;
    }

    public LocalDate getBirth() {
        return Birth;
    }

    public LocalDate getRegisterDate() {
        return RegisterDate;
    }

    /**
     * Check if constructor and update params is valid,
     * if not throw argument exception
     */
    private void ValidateInformation(String phone, String name, String cpf, LocalDate birth){
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
     * Update client informations
     */
    public void Update(String phone, String name, LocalDate birth){
        ValidateInformation(phone, name, "NO NEED CPF", birth);

        this.Phone = phone;
        this.Name = name;
        this.Birth = birth;
        this.UpdateInfoDate = LocalDate.now();
    }

    public Client(String phone, String name, String cpf, LocalDate birth) {
        ValidateInformation(phone, name, cpf, birth);
        Phone = phone;
        Name = name;
        Cpf = cpf;
        Birth = birth;

        this.RegisterDate = LocalDate.now();
        this.UpdateInfoDate = LocalDate.now();
    }
}
