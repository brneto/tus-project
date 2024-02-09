package ie.ait.agile.agileproject.service;

import ie.ait.agile.agileproject.entity.Gp;
import ie.ait.agile.agileproject.entity.Patient;
import ie.ait.agile.agileproject.entity.Prescription;
import ie.ait.agile.agileproject.exception.ExceptionHandler;
import ie.ait.agile.agileproject.repository.PatientRepository;
import ie.ait.agile.agileproject.repository.PrescriptionRepository;
import ie.ait.agile.agileproject.service.impl.PatientServiceImpl;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(PatientServiceImpl.class)
class PatientServiceTest {


    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private PrescriptionRepository prescriptionRepository;

    @Test
    void patientLogin01() throws ExceptionHandler {

        given(patientRepository.findByUsername(any(String.class))).willReturn(null);

        // when
        ThrowingCallable callable = () -> patientService.details("Tarrn");

        thenThrownBy(callable).isExactlyInstanceOf(ExceptionHandler.class);
    }
    @Test
    void updatePatientPassword() throws ExceptionHandler {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setUsername("edlee14");
        patient.setPassword("aaaaaaa");
        patient.setName("Manny");
        patient.setEmail("Dan07@gmail.com");
        patient.setActive(true);

        given(patientRepository.findByUsername(patient.getUsername())).willReturn(patient);

        thenThrownBy(() -> patientService.updatePassword("Biggy","password","password1")).isExactlyInstanceOf(ExceptionHandler.class);
    }
    @Test
    void updatePatientPassword02() throws ExceptionHandler {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setUsername("edlee14");
        patient.setPassword("aaaaaaa");
        patient.setName("Manny");
        patient.setEmail("Dan07@gmail.com");
        patient.setActive(true);

        given(patientRepository.findByUsername(patient.getUsername())).willReturn(patient);

        thenThrownBy(() -> patientService.updatePassword("Biggy","password12","password1")).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void updatePatientPassword04() throws ExceptionHandler {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setUsername("edlee14");
        patient.setPassword("password");
        patient.setName("Manny");
        patient.setEmail("Dan07@gmail.com");
        patient.setActive(true);

        given(patientRepository.findByUsername(patient.getUsername())).willReturn(patient);

        thenThrownBy(() -> patientService.updatePassword("Biggy","password","password")).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void createPrescription01() throws ExceptionHandler {


        Patient patient= new Patient();
        patient.setUsername("Daniel04");

        Patient patient1= new Patient();
        patient.setUsername("Daniel02");


        Date date= new Date();





        given(patientRepository.findByUsername("Daniel04")).willReturn(patient);

        // when
        ThrowableAssert.ThrowingCallable callable = () -> patientService.createPrescription(patient1,"This is",date);

        thenThrownBy(callable).isExactlyInstanceOf(ExceptionHandler.class);
    }





    @Test
    void getPrescription() throws ExceptionHandler {

        given(patientRepository.findByUsername(any(String.class))).willReturn(null);

        // when
        ThrowingCallable callable = () -> patientService.findAllPrescription("Tarn");

        thenThrownBy(callable).isExactlyInstanceOf(ExceptionHandler.class);
    }








}
