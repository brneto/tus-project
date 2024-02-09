package ie.ait.agile.agileproject.repository;

import ie.ait.agile.agileproject.entity.Gp;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GpRepository extends JpaRepository<Gp, Integer> {
    Gp findByUsername(String username);

    Gp findByEmail(String email);

    Gp findByBadgeNo(String badgeNo);
}