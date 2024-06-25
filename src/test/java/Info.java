import com.github.javafaker.Faker;

public class Info {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private int phoneNumber;
    private int ssn;
    private int age;
    private int salary;
    private String department;
    private String email;

    private  String userName;

    Faker faker = new Faker();

    Info(){
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.address = faker.address().streetAddress();
        this.city = faker.address().city();
        this.state = faker.address().state();
        this.zipCode = faker.address().zipCode();
        this.phoneNumber = faker.phoneNumber().hashCode();
        this.ssn = faker.number().numberBetween(100000000, 999999999);


        this.userName =faker.name().username();

        this.age = faker.number().numberBetween(1,100);
        this.salary = faker.number().numberBetween(10000,25000);
        this.department = faker.commerce().department();
        this.email = faker.internet().emailAddress();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getSsn() {
        return ssn;
    }

    /*public Faker getFaker() {
        return faker;
    }*/

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }
}
