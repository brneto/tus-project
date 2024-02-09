package ie.ait.agile.agileproject.service;

import ie.ait.agile.agileproject.entity.OSM;
import ie.ait.agile.agileproject.exception.ExceptionHandler;
import ie.ait.agile.agileproject.repository.OSMRepository;
import ie.ait.agile.agileproject.service.impl.OSMServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(OSMServiceImpl.class)
class OsmServiceTests {

    @Autowired
    private OSMService osmService;

    @MockBean
    private OSMRepository osmRepository;

    @Test
    void osmLogin01() throws ExceptionHandler {
        OSM osm = new OSM();
        osm.setId(1);
        osm.setUsername("Daniel");
        osm.setPassword("password");
        osm.setName("Manny");
        osm.setEmail("Dan07@gmail.com");
        osm.setActive(true);
        osm.setBadgeNo("A00232");

        given(osmRepository.findByUsername(osm.getUsername())).willReturn(osm);

        thenThrownBy(() -> osmService.details("Biggy")).isExactlyInstanceOf(ExceptionHandler.class);
    }
}
