package gr.codehub.teamOne.resource.impl;

import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.repository.UserRepository;
import gr.codehub.teamOne.repository.util.JpaUtil;
import gr.codehub.teamOne.representation.DoctorExpiredDTO;
import gr.codehub.teamOne.resource.DoctorExpiredResource;
import gr.codehub.teamOne.security.AccessRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DoctorExpiredResourcImpl extends ServerResource implements DoctorExpiredResource {
    private UserRepository userRepository;
    private EntityManager em;
    @Override
    protected void doInit() throws ResourceException {

        try {
            em = JpaUtil.getEntityManager();
        } catch (Exception e) {
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }
    @Override
    public List<DoctorExpiredDTO> showDoctorExpired(AccessRole accessRole) {
       List<Users> doctorsEx= (List<Users>) userRepository.getExpiredDoc(AccessRole.ROLE_DOCTOR);
       List<DoctorExpiredDTO> doctorExpiredDTOS=new ArrayList<>();
       doctorsEx.forEach(doc->doctorExpiredDTOS.add(DoctorExpiredDTO.getDocExpiredLog(doc)));




        return doctorExpiredDTOS ;
    }
}
