package daredevil.project.repositories.base;

import daredevil.project.Exceptions.CantCreateAddressException;
import daredevil.project.models.Address;

public interface AddressRepository {
    void createAddress(Address address) throws CantCreateAddressException;
    Address getAddressById(int id);
    void updateAddress(int id, Address address);
    void deleteAddress(int id);
}
