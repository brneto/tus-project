package ie.ait.agile.agileproject.repository;

import ie.ait.agile.agileproject.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer> {
    Pharmacist findByUsername(String username);

    Pharmacist findByEmail(String email);

    Pharmacist findByBadgeNo(String badgeNo);
}
