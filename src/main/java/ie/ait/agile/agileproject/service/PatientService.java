package ie.ait.agile.agileproject.service;

import ie.ait.agile.agileproject.entity.Patient;
import ie.ait.agile.agileproject.entity.Prescription;

import java.util.Date;
import java.util.List;

public interface PatientService extends BaseService<Patient> {

    Patient details(String username);

    Patient findByUsername(String username);

    Patient findByEmail(String email);
    
    Patient updatePassword(String username,String oldPassword,String newPassword);
    Prescription createPrescription(Patient patient, String description, Date date);
    List<Prescription> findAllPrescription(String username);


}
