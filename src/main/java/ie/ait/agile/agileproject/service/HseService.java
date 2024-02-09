package ie.ait.agile.agileproject.service;

import ie.ait.agile.agileproject.entity.*;

import java.util.List;

public interface HseService extends BaseService<Hse> {

    Hse hseDetails(String username);

    Hse createHse(Hse hse);

    Gp createGp(Gp gp);

    Pharmacist createPharmacist(Pharmacist pharma);

    OSM createOsm(OSM osm);

    Hse findByUsername(String username);

    Hse findByEmail(String email);

    Hse findByBadgeNo(String badge);

    Hse deactivateHse(String badgeNo);

    Gp deactivateGp(String badgeNo);

    Patient deactivatePatient(String username);

    OSM deactivateOsm(String badgeNo);

    Pharmacist deactivatePharma(String badgeNo);



    Hse hseUpdateUser(String username,String name,String email);
    Gp gpUpdateUser(String username,String name,String email);
    Pharmacist pharmaUpdateUser(String username,String name,String email);
    OSM osmUpdateUser(String username,String name,String email);
    Patient patientUpdateUser(String username,String name,String email);



}
