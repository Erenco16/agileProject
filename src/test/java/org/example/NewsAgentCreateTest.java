package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class NewsAgentCreateTest {

    @Test
    void TestInvalidOption(){
        NewsAgentCreate nc = new NewsAgentCreate();
        Exception e =  assertThrows(IllegalArgumentException.class, () -> {
            nc.ValidateOptionInput(4);

        });
        assertEquals("Option is out of bounds", e.getMessage());
    }

}