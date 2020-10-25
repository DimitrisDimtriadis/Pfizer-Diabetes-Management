package gr.codehub.teamOne.repository.lib;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public abstract class Repository<T, K> implements IRepository<T, K> {

    private EntityManager entityManager;

    public Repository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findById(K id) {
        try {
            entityManager.getTransaction().begin();
            T t = entityManager.find(getEntityClass(), id);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }



    @Override
    public Optional<T> save(T t) {
        try{
           entityManager.getTransaction().begin();
           entityManager.persist(t);
           entityManager.getTransaction().commit();
           return Optional.of(t);
        } catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean deletedById(K id) {

        T persistentInstance = entityManager.find(getEntityClass(), id);

        if (persistentInstance != null){

            try{
                entityManager.getTransaction().begin();
                entityManager.remove(persistentInstance);
                entityManager.getTransaction().commit();
            }catch(Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

    public abstract Class<T> getEntityClass();
    public abstract String getEntityClassName();

}