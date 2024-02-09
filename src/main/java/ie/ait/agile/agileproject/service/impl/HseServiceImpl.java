package ie.ait.agile.agileproject.service.impl;

import ie.ait.agile.agileproject.entity.*;
import ie.ait.agile.agileproject.exception.ExceptionHandler;
import ie.ait.agile.agileproject.repository.*;
import ie.ait.agile.agileproject.service.HseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HseServiceImpl implements HseService {

    private final HseRepository hseRepository;
    private final GpRepository gpRepository;
    private final PharmacistRepository pharmaRepository;
    private final OSMRepository osmRepository;
    private final PatientRepository patientRepository;

    HseServiceImpl(
            HseRepository hseRepository, GpRepository gpRepository,
            PharmacistRepository pharmacistRepository, OSMRepository osmRepository,PatientRepository patientRepository) {

        this.hseRepository = hseRepository;
        this.gpRepository = gpRepository;
        this.pharmaRepository = pharmacistRepository;
        this.osmRepository = osmRepository;
        this.patientRepository=patientRepository;
    }

    @Override
    public Hse hseDetails(String username) {
        if (hseRepository.findByUsername(username) == null) {
            throw new ExceptionHandler("Admin does not exist");
        } else {

            Hse hse=hseRepository.findByUsername(username);
            if(hse.isActive()==false){
                throw new ExceptionHandler("Admin has been deactivated");
            }
            else{
                return hse;
            }
        }
    }

    @Override
    public Hse createHse(Hse hse) {


        if (hseRepository.findByUsername(hse.getUsername()) != null) {
            throw new ExceptionHandler("Admin Already exist");
        } else if (hseRepository.findByEmail(hse.getEmail()) != null) {
            throw new ExceptionHandler("Email Already exist");
        } else if (hseRepository.findByBadgeNo(hse.getBadgeNo()) != null) {
            throw new ExceptionHandler("Badge Number Already exist");
        } else {
            return hseRepository.save(hse);
        }
        // TODO Auto-generated method stub

    }

    @Override
    public Gp createGp(Gp gp) {
        // TODO Auto-generated method stub


        if (gpRepository.findByUsername(gp.getUsername()) != null) {
            throw new ExceptionHandler("Gp already exist");
        } else if (gpRepository.findByEmail(gp.getEmail()) != null) {
            throw new ExceptionHandler("Email already exist");
        } else if (gpRepository.findByBadgeNo(gp.getBadgeNo()) != null) {
            throw new ExceptionHandler("Badge Number already exist");
        } else {
            return gpRepository.save(gp);
        }
        // TODO Auto-generated method stub

    }

    @Override
    public Pharmacist createPharmacist(Pharmacist pharma) {
        // TODO Auto-generated method stub


        if (pharmaRepository.findByUsername(pharma.getUsername()) != null) {
            throw new ExceptionHandler("Pharma already exist");
        } else if (pharmaRepository.findByEmail(pharma.getEmail()) != null) {
            throw new ExceptionHandler("Email already exist");
        } else if (pharmaRepository.findByBadgeNo(pharma.getBadgeNo()) != null) {
            throw new ExceptionHandler("Badge Number already exist");
        } else {
            return pharmaRepository.save(pharma);
        }
        // TODO Auto-generated method stub

    }

    @Override
    public OSM createOsm(OSM osm) {
        // TODO Auto-generated method stub


        if (osmRepository.findByUsername(osm.getUsername()) != null) {
            throw new ExceptionHandler("Other staff already exist");
        } else if (osmRepository.findByEmail(osm.getEmail()) != null) {
            throw new ExceptionHandler("Email already exist");
        } else if (osmRepository.findByBadgeNo(osm.getBadgeNo()) != null) {
            throw new ExceptionHandler("Badge Number already exist");
        } else {
            return osmRepository.save(osm);
        }
        // TODO Auto-generated method stub

    }

    @Override
    public List<Hse> findAll() {
        return hseRepository.findAll();
    }


    @Override
    public Hse findByUsername(String username) {
        // TODO Auto-generated method stub
        return hseRepository.findByUsername(username);
    }


    @Override
    public Hse findByEmail(String email) {
        // TODO Auto-generated method stub
        return hseRepository.findByEmail(email);
    }

    @Override
    public Hse findByBadgeNo(String badge) {
        // TODO Auto-generated method stub
        return hseRepository.findByBadgeNo(badge);
    }

    @Override
    public Hse deactivateHse(String badgeNo) {

        if(hseRepository.findByBadgeNo(badgeNo)==null){
            throw new ExceptionHandler("Badge Number does not exist");
        }
        else{
            Hse hse= hseRepository.findByBadgeNo(badgeNo);
            if(hse.isActive()==false){
                throw new ExceptionHandler("Hse has already been deactivated");

            }
            else{
                hse.setActive(false);
                return hseRepository.save(hse);
            }


        }

    }

    @Override
    public Gp deactivateGp(String badgeNo) {
        if(gpRepository.findByBadgeNo(badgeNo)==null){
            throw new ExceptionHandler("Badge Number does not exist");
        }
        else{

            Gp gp= gpRepository.findByBadgeNo(badgeNo);
            if(gp.isActive()==false){
                throw new ExceptionHandler("Gp has already been deactivated");
            }
            else{
                gp.setActive(false);
                return gpRepository.save(gp);
            }

        }
    }

    @Override
    public Patient deactivatePatient(String username) {
        if(patientRepository.findByUsername(username)==null){
            throw new ExceptionHandler("Username does not exist");
        }
        else{
            Patient patient= patientRepository.findByUsername(username);

            if(patient.isActive()==false){
                throw new ExceptionHandler("Patient has already been deactivated");
            }
            else{
                patient.setActive(false);
               return  patientRepository.save(patient);
            }

        }
    }

    @Override
    public OSM deactivateOsm(String badgeNo) {
        if(osmRepository.findByBadgeNo(badgeNo)==null){
            throw new ExceptionHandler("Badge Number does not exist");
        }
        else{
            OSM osm = osmRepository.findByBadgeNo(badgeNo);
            if(osm.isActive()==false){
                throw new ExceptionHandler("Osm has already been deactivated");
            }
            else{
                osm.setActive(false);
                return osmRepository.save(osm);
            }

        }
    }

    @Override
    public Pharmacist deactivatePharma(String badgeNo) {
        if(pharmaRepository.findByBadgeNo(badgeNo)==null){
            throw new ExceptionHandler("Badge Number does not exist");
        }
        else{
            Pharmacist pharmacist=pharmaRepository.findByBadgeNo(badgeNo);

            if(pharmacist.isActive()==false){
                throw new ExceptionHandler("Pharmacist has already been deactivated");
            }
            else{
                pharmacist.setActive(false);
                return pharmaRepository.save(pharmacist);
            }
        }
    }



    @Override
    public Hse hseUpdateUser(String username, String name, String email) {

        Hse hse= hseRepository.findByUsername(username);
        if(hse==null||hse.isActive()==false){
            throw new ExceptionHandler("Admin doesnt exist");
        }
        else if(hse.getEmail().toLowerCase().equals(email.toLowerCase())||hseRepository.findByEmail(email)!=null){
            throw new ExceptionHandler("Email already exist");
        }
        else{
            hse.setName(name);
            hse.setEmail(email);
            hseRepository.save(hse);
        }
        return hse;
    }

    @Override
    public Gp gpUpdateUser(String username, String name, String email) {
        Gp gp= gpRepository.findByUsername(username);
        if(gp==null||!gp.isActive()){
            throw new ExceptionHandler("Gp doesnt exist");
        }
        else if(gp.getEmail().toLowerCase().equals(email.toLowerCase())||gpRepository.findByEmail(email)!=null){
            throw new ExceptionHandler("Email already exist");
        }
        else{
            gp.setName(name);
            gp.setEmail(email);
            gpRepository.save(gp);
        }
        return gp;
    }

    @Override
    public Pharmacist pharmaUpdateUser(String username, String name, String email) {
        Pharmacist pharmacist= pharmaRepository.findByUsername(username);
        if(pharmacist==null||!pharmacist.isActive()){
            throw new ExceptionHandler("Pharmacist doesnt exist");
        }
        else if(pharmacist.getEmail().toLowerCase().equals(email.toLowerCase())||pharmaRepository.findByEmail(email)!=null){
            throw new ExceptionHandler("Email already exist");
        }
        else{
            pharmacist.setName(name);
            pharmacist.setEmail(email);
            pharmaRepository.save(pharmacist);
        }
        return pharmacist;
    }

    @Override
    public OSM osmUpdateUser(String username, String name, String email) {
        OSM osm= osmRepository.findByUsername(username);
        if(osm==null||osm.isActive()==false){
            throw new ExceptionHandler("OSM doesnt exist");
        }
        else if(osm.getEmail().toLowerCase().equals(email.toLowerCase())||osmRepository.findByEmail(email)!=null){
            throw new ExceptionHandler("Email already exist");
        }
        else{
            osm.setName(name);
            osm.setEmail(email);
            osmRepository.save(osm);
        }
        return osm;
    }

    @Override
    public Patient patientUpdateUser(String username, String name, String email) {
        Patient patient= patientRepository.findByUsername(username);
        if(patient==null||patient.isActive()==false){
            throw new ExceptionHandler("Patient doesnt exist");
        }
        else if(patient.getEmail().toLowerCase().equals(email.toLowerCase())||patientRepository.findByEmail(email)!=null){
            throw new ExceptionHandler("Email already exist");
        }
        else{
            patient.setName(name);
            patient.setEmail(email);
            patientRepository.save(patient);
        }
        return patient;
    }

}
