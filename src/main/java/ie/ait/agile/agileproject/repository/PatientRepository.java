package ie.ait.agile.agileproject.repository;

import ie.ait.agile.agileproject.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByUsername(String username);

    Patient findByEmail(String Email);


}
