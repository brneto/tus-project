package ie.ait.agile.agileproject.service.impl;

import ie.ait.agile.agileproject.entity.Gp;
import ie.ait.agile.agileproject.entity.Patient;
import ie.ait.agile.agileproject.entity.Pharmacist;
import ie.ait.agile.agileproject.exception.ExceptionHandler;
import ie.ait.agile.agileproject.repository.GpRepository;
import ie.ait.agile.agileproject.repository.PatientRepository;
import ie.ait.agile.agileproject.service.GpService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpServiceImpl implements GpService {

    private final GpRepository gpRepository;
    private final PatientRepository patientRepository;

    GpServiceImpl(GpRepository gpRepository,PatientRepository patientRepository) {
        this.gpRepository = gpRepository;
        this.patientRepository=patientRepository;

    }

    public Gp details(String username) {
        if (gpRepository.findByUsername(username) == null) {
            throw new ExceptionHandler("Gp does not exist");
        } else {
            Gp gp= gpRepository.findByUsername(username);
            if(gp.isActive()==false){
                throw new ExceptionHandler("Gp has been deactivated");
            }else{
            return gpRepository.findByUsername(username);}
        }

    }

    @Override
    public Gp findByUsername(String username) {
        return gpRepository.findByUsername(username);
    }

    @Override
    public Gp findByEmail(String email) {
        return gpRepository.findByEmail(email);
    }

    @Override
    public Gp findByBadgeNo(String badgeNo) {
        return gpRepository.findByBadgeNo(badgeNo);
    }

    @Override
    public Patient createPatient(Patient patient,Gp gp) {
        if (patientRepository.findByUsername(patient.getUsername()) != null) {
            throw new ExceptionHandler("Patient Username Already exist");
        } else if (patientRepository.findByEmail(patient.getEmail()) != null) {
            throw new ExceptionHandler("Patient Email Already exist");
        }
        else if(gpRepository.findByUsername(gp.getUsername())==null) {
            throw new ExceptionHandler("Gp with this username doesnt exist");
        }else
         {
             patient.setGp(gp);
            return patientRepository.save(patient);
        }


    }

    @Override
    public Gp updatePassword(String username, String oldPassword, String newPassword) {
        Gp gp = gpRepository.findByUsername(username);

        if (gp == null||gp.isActive()==false) {
            throw new ExceptionHandler("Gp doesn't exist");
        } else {
            if (gp.getPassword().equals(oldPassword)) {
                if (oldPassword != newPassword) {
                    gp.setPassword(newPassword);
                    return gpRepository.save(gp);

                } else {
                    throw new ExceptionHandler("New password cannot be the same as old password");
                }

            } else {
                throw new ExceptionHandler("Old password doesn't match ");
            }
        }
    }


    @Override
    public List<Gp> findAll() {
        return gpRepository.findAll();
    }
}
