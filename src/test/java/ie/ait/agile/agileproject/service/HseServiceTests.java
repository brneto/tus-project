package ie.ait.agile.agileproject.service;

import ie.ait.agile.agileproject.entity.*;
import ie.ait.agile.agileproject.exception.ExceptionHandler;
import ie.ait.agile.agileproject.repository.*;
import ie.ait.agile.agileproject.service.impl.GpServiceImpl;
import ie.ait.agile.agileproject.service.impl.HseServiceImpl;
import ie.ait.agile.agileproject.service.impl.OSMServiceImpl;
import ie.ait.agile.agileproject.service.impl.PharmacistServiceImpl;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(HseServiceImpl.class)
class HseServiceTests {

    @Autowired
    private HseServiceImpl hseService;


    @MockBean
    private HseRepository hseRepository;

    @MockBean
    private GpRepository gpRepository;

    @MockBean
    private PharmacistRepository pharmacistRepository;

    @MockBean
    private OSMRepository osmRepository;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    void hseLogin01() {
        Hse hse = new Hse();
        hse.setId(1);
        hse.setUsername("Daniel");
        hse.setPassword("password");
        hse.setName("Manny");
        hse.setEmail("Dan07@gmail.com");
        hse.setActive(true);
        hse.setBadgeNo("A00232");

        given(hseRepository.findByUsername(hse.getUsername())).willReturn(hse);

        thenThrownBy(() -> hseService.hseDetails("Tarron")).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void createHse01() {
        // given
        Hse hse = new Hse();
        hse.setUsername("nameUnderTest");
        given(hseRepository.findByUsername(anyString())).willReturn(hse);

        // when
        ThrowingCallable callable = () -> hseService.createHse(hse);

        thenThrownBy(callable).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void createHse02() {
        // given
        Hse hse = new Hse();
        given(hseRepository.findByUsername(anyString())).willReturn(null);
        given(hseRepository.findByEmail(anyString())).willReturn(null);
        given(hseRepository.findByBadgeNo(anyString())).willReturn(null);
        given(hseRepository.save(hse)).willReturn(hse);

        // when
        Hse result = hseService.createHse(hse);

        then(result).isEqualTo(hse);
    }


    @Test
    void createGp01() {
        // given
        Gp gp = new Gp();
        gp.setUsername("nameUnderTest");
        given(gpRepository.findByUsername(anyString())).willReturn(gp);

        // when
        ThrowingCallable callable = () -> hseService.createGp(gp);

        thenThrownBy(callable).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void createGp02() {
        // given
        Gp gp = new Gp();
        given(gpRepository.findByUsername(anyString())).willReturn(null);
        given(gpRepository.findByEmail(anyString())).willReturn(null);
        given(gpRepository.findByBadgeNo(anyString())).willReturn(null);
        given(gpRepository.save(gp)).willReturn(gp);

        // when
        Gp result = hseService.createGp(gp);

        then(result).isEqualTo(gp);
    }


    @Test
    void createOsm01() {
        // given
        OSM osm = new OSM();
        osm.setUsername("nameUnderTest");
        given(osmRepository.findByUsername(anyString())).willReturn(osm);

        // when
        ThrowingCallable callable = () -> hseService.createOsm(osm);

        thenThrownBy(callable).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void createOsm02() {
        // given
        OSM osm = new OSM();
        given(osmRepository.findByUsername(anyString())).willReturn(null);
        given(osmRepository.findByEmail(anyString())).willReturn(null);
        given(osmRepository.findByBadgeNo(anyString())).willReturn(null);
        given(osmRepository.save(osm)).willReturn(osm);

        // when
        OSM result = hseService.createOsm(osm);

        then(result).isEqualTo(osm);
    }


    @Test
    void createPharmacist01() {
        // given
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setUsername("nameUnderTest");
        given(pharmacistRepository.findByUsername(anyString())).willReturn(pharmacist);

        // when
        ThrowingCallable callable = () -> hseService.createPharmacist(pharmacist);

        thenThrownBy(callable).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void createPharmscist02() {
        // given
        Pharmacist pharmacist = new Pharmacist();
        given(pharmacistRepository.findByUsername(anyString())).willReturn(null);
        given(pharmacistRepository.findByEmail(anyString())).willReturn(null);
        given(pharmacistRepository.findByBadgeNo(anyString())).willReturn(null);
        given(pharmacistRepository.save(pharmacist)).willReturn(pharmacist);

        // when
        Pharmacist result = hseService.createPharmacist(pharmacist);

        then(result).isEqualTo(pharmacist);
    }


    @Test
    void deactivateHse() {


        Hse hse= new Hse();
        hse.setName("Daniel");
        hse.setUsername("Danny01");
        hse.setPassword("Password");
        hse.setEmail("Ojeaburu@gmail.com");
        hse.setActive(true);
        hse.setBadgeNo("A0023");

        given(hseRepository.findByBadgeNo(hse.getBadgeNo())).willReturn(hse);
        thenThrownBy(() -> hseService.deactivateHse("A1343")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void deactivateGp() {

        Gp gp = new Gp();
        gp.setName("Daniel");
        gp.setUsername("Dwanna");
        gp.setPassword("password");
        gp.setActive(true);
        gp.setEmail("Ojeaburu@gmail.com");
        gp.setBadgeNo("G234");


        given(gpRepository.findByBadgeNo(gp.getBadgeNo())).willReturn(gp);
        thenThrownBy(() -> hseService.deactivateGp("A1343")).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void deactivatePatient() {

        Patient patient= new Patient();
        patient.setName("Daniel");
        patient.setActive(true);
        patient.setEmergencyId((long) 12332);
        patient.setEmail("Ojeaburu@gmail.com");
        patient.setUsername("Danny");
        patient.setPassword("password");



        given(patientRepository.findByUsername(patient.getUsername())).willReturn(patient);
        thenThrownBy(() -> hseService.deactivatePatient("Danni")).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void deactivatePharmacist() {
        // given
        Pharmacist pharmacist= new Pharmacist();
        pharmacist.setName("Daniel");
        pharmacist.setActive(true);
        pharmacist.setUsername("Daniel04");
        pharmacist.setPassword("password");
        pharmacist.setBadgeNo("Ph123");
        pharmacist.setEmail("Ojeaburu@gmail.com");


        given(pharmacistRepository.findByBadgeNo(pharmacist.getBadgeNo())).willReturn(pharmacist);
        thenThrownBy(() -> hseService.deactivatePharma("A1343")).isExactlyInstanceOf(ExceptionHandler.class);
    }

    @Test
    void deactivateOsm() {
        // given
       OSM osm = new OSM();
       osm.setName("Daniel");
       osm.setUsername("Daneil04");
       osm.setPassword("password");
       osm.setActive(true);
       osm.setEmail("Ojeaburu@gmail.com");
       osm.setBadgeNo("O1234");


        given(osmRepository.findByBadgeNo(osm.getBadgeNo())).willReturn(osm);
        thenThrownBy(() -> hseService.deactivateOsm("A1343")).isExactlyInstanceOf(ExceptionHandler.class);
    }


    @Test
    void hseUpdateUser01() {


        Hse hse= new Hse();
        hse.setName("Daniel");
        hse.setUsername("Danny01");
        hse.setPassword("Password");
        hse.setEmail("Ojeaburu@gmail.com");
        hse.setActive(true);
        hse.setBadgeNo("A0023");

        given(hseRepository.findByUsername(hse.getUsername())).willReturn(hse);
        thenThrownBy(() -> hseService.hseUpdateUser("Dwanna","Ayo","Ayodeji@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void hseUpdateUser02() {


        Hse hse= new Hse();
        hse.setName("Daniel");
        hse.setUsername("Danny01");
        hse.setPassword("Password");
        hse.setEmail("Ojeaburu@gmail.com");
        hse.setActive(true);
        hse.setBadgeNo("A0023");

        given(hseRepository.findByUsername(hse.getUsername())).willReturn(hse);
        thenThrownBy(() -> hseService.hseUpdateUser("Danny01","Ayo","Ojeaburu@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void gpUpdateUser01() {


        Gp gp= new Gp();
        gp.setName("Daniel");
        gp.setUsername("Danny01");
        gp.setPassword("Password");
        gp.setEmail("Ojeaburu@gmail.com");
        gp.setActive(true);
        gp.setBadgeNo("A0023");

        given(gpRepository.findByUsername(gp.getUsername())).willReturn(gp);
        thenThrownBy(() -> hseService.gpUpdateUser("Dwanna","Ayo","Ayodeji@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void gpUpdateUser02() {


        Gp gp= new Gp();
        gp.setName("Daniel");
        gp.setUsername("Danny01");
        gp.setPassword("Password");
        gp.setEmail("Ojeaburu@gmail.com");
        gp.setActive(true);
        gp.setBadgeNo("A0023");

        given(gpRepository.findByUsername(gp.getUsername())).willReturn(gp);
        thenThrownBy(() -> hseService.gpUpdateUser("Danny01","Ayo","Ojeaburu@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void pharmacistUpdateUser01() {


        Pharmacist pharma= new Pharmacist();
        pharma.setName("Daniel");
        pharma.setUsername("Danny01");
        pharma.setPassword("Password");
        pharma.setEmail("Ojeaburu@gmail.com");
        pharma.setActive(true);
        pharma.setBadgeNo("A0023");

        given(pharmacistRepository.findByUsername(pharma.getUsername())).willReturn(pharma);
        thenThrownBy(() -> hseService.pharmaUpdateUser("Dwanna","Ayo","Ayodeji@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void pharmacistUpdateUser02() {


        Pharmacist pharma= new Pharmacist();
        pharma.setName("Daniel");
        pharma.setUsername("Danny01");
        pharma.setPassword("Password");
        pharma.setEmail("Ojeaburu@gmail.com");
        pharma.setActive(true);
        pharma.setBadgeNo("A0023");

        given(pharmacistRepository.findByUsername(pharma.getUsername())).willReturn(pharma);
        thenThrownBy(() -> hseService.pharmaUpdateUser("Danny01","Ayo","Ojeaburu@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }
    @Test
    void patientUpdateUser01() {


        Patient patient= new Patient();
        patient.setName("Daniel");
        patient.setUsername("Danny01");
        patient.setPassword("Password");
        patient.setEmail("Ojeaburu@gmail.com");
        patient.setActive(true);
        

        given(patientRepository.findByUsername(patient.getUsername())).willReturn(patient);
        thenThrownBy(() -> hseService.patientUpdateUser("Dwanna","Ayo","Ayodeji@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void patientUpdateUser02() {


        Patient patient= new Patient();
        patient.setName("Daniel");
        patient.setUsername("Danny01");
        patient.setPassword("Password");
        patient.setEmail("Ojeaburu@gmail.com");
        patient.setActive(true);
        

        given(patientRepository.findByUsername(patient.getUsername())).willReturn(patient);
        thenThrownBy(() -> hseService.patientUpdateUser("Danny01","Ayo","Ojeaburu@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void osmUpdateUser01() {


        OSM osm= new OSM();
        osm.setName("Daniel");
        osm.setUsername("Danny01");
        osm.setPassword("Password");
        osm.setEmail("Ojeaburu@gmail.com");
        osm.setActive(true);
        osm.setBadgeNo("A0023");

        given(osmRepository.findByUsername(osm.getUsername())).willReturn(osm);
        thenThrownBy(() -> hseService.osmUpdateUser("Dwanna","Ayo","Ayodeji@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }

    @Test
    void osmUpdateUser02() {


        OSM osm= new OSM();
        osm.setName("Daniel");
        osm.setUsername("Danny01");
        osm.setPassword("Password");
        osm.setEmail("Ojeaburu@gmail.com");
        osm.setActive(true);
        osm.setBadgeNo("A0023");

        given(osmRepository.findByUsername(osm.getUsername())).willReturn(osm);
        thenThrownBy(() -> hseService.osmUpdateUser("Danny01","Ayo","Ojeaburu@gmail.com")).isExactlyInstanceOf(ExceptionHandler.class);


    }



}
