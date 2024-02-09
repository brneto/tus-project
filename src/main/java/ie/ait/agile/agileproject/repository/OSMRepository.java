package ie.ait.agile.agileproject.repository;

import ie.ait.agile.agileproject.entity.OSM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OSMRepository extends JpaRepository<OSM, Integer> {
    OSM findByUsername(String username);

    OSM findByEmail(String email);

    OSM findByBadgeNo(String badge);
}
