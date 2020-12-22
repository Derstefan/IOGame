package org.iogame.core;

import org.junit.jupiter.api.AfterEach;

/**
 * Every test that relies on Id's starting at 0
 * should extends from this test.
 * It will clear the internal id counter after each test.
 */
public class BaseIdTest {

    @AfterEach
    public void tearDown() {
        Id.clear();
    }
}
