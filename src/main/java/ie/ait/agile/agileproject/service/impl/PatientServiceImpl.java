package ie.ait.agile.agileproject.service.impl;

import ie.ait.agile.agileproject.entity.Patient;
import ie.ait.agile.agileproject.entity.Prescription;
import ie.ait.agile.agileproject.exception.ExceptionHandler;
import ie.ait.agile.agileproject.repository.PatientRepository;
import ie.ait.agile.agileproject.repository.PrescriptionRepository;
import ie.ait.agile.agileproject.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;

    PatientServiceImpl(PatientRepository patientRepository,PrescriptionRepository prescriptionRepository) {
        this.patientRepository = patientRepository;
        this.prescriptionRepository=prescriptionRepository;
    }

    @Override
    public Patient details(String username) {
        if (patientRepository.findByUsername(username) == null) {
            throw new ExceptionHandler("Patient does not exist");
        } else {
            Patient patient=patientRepository.findByUsername(username);

            if(patient.isActive()==false){
                throw new ExceptionHandler("Patient has been deactivated");
            }
            else {
            return patientRepository.findByUsername(username);}
        }
    }

    @Override
    public Patient findByUsername(String username) {
        return patientRepository.findByUsername(username);
    }

    @Override
    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }


    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    @Override
    public Patient updatePassword(String username, String oldPassword, String newPassword) {

    	Patient patient = patientRepository.findByUsername(username);

        if (patient == null||patient.isActive()==false) {
            throw new ExceptionHandler("Patient doesnt exist");
        } else {
            if (patient.getPassword().equals(oldPassword)) {
                if (oldPassword != newPassword) {
                	patient.setPassword(newPassword);
                    return patientRepository.save(patient);

                } else {
                    throw new ExceptionHandler("New password cannot be the same as old password");
                }

            } else {
                throw new ExceptionHandler("Old password doesnt match ");
            }
        }

    }

    @Override
    public Prescription createPrescription(Patient patient, String description, Date date) {
        if(patientRepository.findByUsername(patient.getUsername())==null){
            throw new ExceptionHandler("patient doesnt exist");
        }
        else{
            Prescription prescription= new Prescription();
            prescription.setDescription(description.trim());
            prescription.setPatient(patient);
            prescription.setDate(date);

            prescriptionRepository.save(prescription);
            return prescription;

        }




    }

    @Override
    public List<Prescription> findAllPrescription(String username) {

        Patient patient= patientRepository.findByUsername(username);
        if(patient==null){
            throw new ExceptionHandler("Patient doesnt exist");
        }
        else{
            List<Prescription> pre=prescriptionRepository.findAllByPatient(patient);
            return pre;

        }


    }
    

}
