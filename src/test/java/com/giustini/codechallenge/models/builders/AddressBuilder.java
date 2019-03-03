package com.giustini.codechallenge.models.builders;

import com.giustini.codechallenge.models.Address;

public class AddressBuilder {

    private Address address;

    public static AddressBuilder anAddressBuilder() {
        return new AddressBuilder();
    }

    private AddressBuilder() {
        this.address = new Address();
    }

    public AddressBuilder withId(Integer id) {
        address.setId(id);
        return this;
    }

    public AddressBuilder withStreet(String street) {
        address.setStreet(street);
        return this;
    }

    public AddressBuilder withState(String state) {
        address.setState(state);
        return this;
    }

    public AddressBuilder withCity(String city) {
        address.setCity(city);
        return this;
    }

    public AddressBuilder withCountry(String country) {
        address.setCountry(country);
        return this;
    }

    public AddressBuilder withZip(String zip) {
        address.setZip(zip);
        return this;
    }

    public Address build() {
        return address;
    }
}
