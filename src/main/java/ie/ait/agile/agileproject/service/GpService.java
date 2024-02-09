package ie.ait.agile.agileproject.service;


import ie.ait.agile.agileproject.entity.Gp;
import ie.ait.agile.agileproject.entity.Patient;
import ie.ait.agile.agileproject.entity.Pharmacist;

import java.util.List;

public interface GpService extends BaseService<Gp> {

    Gp details(String username);

    Gp findByUsername(String username);

    Gp findByEmail(String email);

    Gp findByBadgeNo(String badgeNo);

    Patient createPatient(Patient patient,Gp gp);

    Gp updatePassword(String username, String oldPassword, String newPassword);
}
