package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @DisplayName("Mock Test")
    @Test
    void calculateFee() {
        GitHubService g = mock(GitHubService.class);
        when(g.getLines("10,3")).thenReturn(10);
        when(g.getWMC("10,3")).thenReturn(3);
        g.getLines("10,3");
        verify(g,times(1)).getLines("10,3");
        verify(g,times(0)).getWMC("10,3");
        g.getWMC("11,1");
        verify(g,times(1)).getLines("10,3");
        verify(g,times(1)).getWMC("11,1");
    }

    @DisplayName("Coverage Test")
    @Test
    void calculateCoverage() {

        assertAll(
                ()->assertThrows(IllegalArgumentException.class,()->Main.calculateFee(1,10,10,10,"1,3")),
                ()->assertEquals(400,Main.calculateFee(2,3,4,70,"1,3")),
                ()->assertEquals(200,Main.calculateFee(2,3,4,70,"5000,3")),
                ()->assertEquals(250,Main.calculateFee(2,10,4,70,"5000,3")),
                ()->assertEquals(250,Main.calculateFee(2,3,9,90,"1,3")),
                ()->assertEquals(150,Main.calculateFee(2,3,11,90,"1,3")),
                ()->assertEquals(300,Main.calculateFee(3,3,5,110,"1,3")),
                ()->assertEquals(200,Main.calculateFee(3,3,11,110,"1,3")),
                ()->assertEquals(250,Main.calculateFee(3,3,16,10,"1,60")),
                ()->assertEquals(50,Main.calculateFee(3,3,16,10,"5000,60")),
                ()->assertEquals(250,Main.calculateFee(4,3,10,10,"5000,60")),
                ()->assertEquals(150,Main.calculateFee(4,3,16,10,"5000,60")),
                ()->assertEquals(250,Main.calculateFee(4,3,21,10,"1,3")),
                ()->assertEquals(200,Main.calculateFee(4,3,21,10,"1000,60")),
                ()->assertEquals(50,Main.calculateFee(4,3,21,10,"5000,60")),
                ()->assertEquals(250,Main.calculateFee(4,3,21,10,"1,3"))
        );
    }


}