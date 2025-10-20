package parking;

public class Address {
    private String street;
    private String apartment;
    private String city;
    private String state;
    private String zip;

    public Address(String street, String apartment, String city, String state, String zip) {
        this.street = street;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return street + (apartment.isEmpty() ? "" : ", " + apartment) + ", " + city + ", " + state + " " + zip;
    }
}
