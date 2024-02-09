package ie.ait.agile.agileproject.service;


import ie.ait.agile.agileproject.entity.Pharmacist;

public interface PharmacistService extends BaseService<Pharmacist> {

    Pharmacist details(String username);

    Pharmacist findByUsername(String username);

    Pharmacist findByEmail(String email);

    Pharmacist findByBadgeNo(String badgeNo);

    Pharmacist updatePassword(String username,String oldPassword,String newPassword);
}