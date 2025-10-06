package parking;

public class Address {
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private String zipCode;

    public Address(String streetAddress1, String streetAddress2, String city, String state, String zipCode) {
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getAddressInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(streetAddress1);
        if (streetAddress2 != null && !streetAddress2.isBlank()) {
            sb.append(", ").append(streetAddress2);
        }
        sb.append(", ").append(city)
          .append(", ").append(state)
          .append(" ").append(zipCode);
        return sb.toString();
    }

    @Override
    public String toString() {
        return getAddressInfo();
    }
}
