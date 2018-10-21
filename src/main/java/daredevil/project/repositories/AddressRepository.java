package daredevil.project.repositories;

import daredevil.project.models.Address;

public interface AddressRepository {
    void createAddress(Address address);
    Address getAddressById(int id);
    void updateAddress(int id, Address address);
    void deleteAddress(int id);
}
