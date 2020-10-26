package gr.codehub.teamOne.repository;

import gr.codehub.teamOne.model.Doctors;
import gr.codehub.teamOne.repository.lib.IRepository;
import gr.codehub.teamOne.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class DoctorRepository extends Repository<Doctors, Long> {
    public DoctorRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class getEntityClass() {
       return Doctors.class;
    }

    @Override
    public String getEntityClassName() {
        return Doctors.class.getName();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    //JBQL
//    public List<Customer> findByAddress(String address) {
//        List<Customer> cs = entityManager.createQuery("from Customer c WHERE c.address = :address ")
//                .setParameter("address", address)
//                .getResultList();
//        return cs;
//    }
}
