package daredevil.project.repositories;

import daredevil.project.models.Address;
import org.hibernate.Session;
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
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            session.save(address);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "address");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address getAddressById(int id) {
        Address result;

        try (
                Session session = sessionFactory.openSession()

        ) {
            session.beginTransaction();
            result = session.get(Address.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void updateAddress(int id, Address address) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            Address addressToChange = session.get(Address.class, id);

            addressToChange.setCity(address.getCity());
            addressToChange.setCountry(address.getCountry());
            addressToChange.setEntrance(address.getEntrance());
            addressToChange.setFlat(address.getFlat());
            addressToChange.setFloor(address.getFloor());
            addressToChange.setStreet(address.getStreet());
            addressToChange.setStreetNumber(address.getStreetNumber());

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAddress(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(getAddressById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
