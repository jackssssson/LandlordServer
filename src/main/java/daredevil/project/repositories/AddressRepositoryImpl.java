package daredevil.project.repositories;

import daredevil.project.models.Address;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public AddressRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createAddress(Address address) {

    }

    @Override
    public Address getAddressById(int id) {
        return null;
    }

    @Override
    public void updateAddress(int id, Address address) {

    }

    @Override
    public void deleteAddress(int id) {

    }
}
