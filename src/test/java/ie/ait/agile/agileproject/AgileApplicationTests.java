package ie.ait.agile.agileproject;


import ie.ait.agile.agileproject.repository.HseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AgileApplicationTests extends MySqlContainerBase {

    @Autowired
    private HseRepository hseRepository;

    @Test
    void shouldContextLoads() {
        then(hseRepository).isNotNull();
    }

}
