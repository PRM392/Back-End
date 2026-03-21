package org.foodie_tour;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FoodieTourApplicationTests {

    @Test
    void mainClassExists() {
        FoodieTourApplication app = new FoodieTourApplication();
        assertNotNull(app);
    }
}
