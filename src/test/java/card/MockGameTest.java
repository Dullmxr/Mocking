package card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MockGameTest {

    Scanner mockScanner;
    Game game;

    @BeforeEach
    void setUp() {
        mockScanner = mock(Scanner.class);
        game = new Game();
        game.setLoadScanner(mockScanner, null); 
        game.setOutputOff();
    }

    @Test
    void testGetComputerPlayersNamesDealer() {
        when(mockScanner.nextLine()).thenReturn("DEALER,Derek");
        List<String> names = game.getComputerPlayersNames();
        assertEquals("Derek", names.get(0));
    }

    @Test
    void testGetComputerPlayersNamesSecondComputer() {
        when(mockScanner.nextLine()).thenReturn("COMPUTER,Derek", "COMPUTER,Xi");
        List<String> names = game.getComputerPlayersNames();
        assertEquals("Xi", names.get(1));
    }

    @Test
    void testCreateComputerCompetitors() {
        when(mockScanner.nextLine()).thenReturn("DEALER,Derek", "COMPUTER,Xi");
        game.createComputerCompetitors(0);
        assertEquals("Derek", game.getPlayers().get(0).getName());
    }

    @Test
    void testGetInputInteger() {
        when(mockScanner.nextLine()).thenReturn("2");
        assertEquals(2, game.getConsoleInOut().getInputInteger());
    }

    @Test
    void testGetInputString() {
        when(mockScanner.nextLine()).thenReturn("Derek");
        assertEquals("Derek", game.getConsoleInOut().getInputString());
    }

    @Test
    void testGetNumberOfPlayers() {
        when(mockScanner.nextLine()).thenReturn("3");
        assertEquals(3, game.getNumberOfPlayers());
    }

    @Test
    void testInitiatePlayers() {
        when(mockScanner.nextLine()).thenReturn("3");
        game.initiatePlayers(0, null); 
        assertEquals(3, game.getPlayersSize());
    }

    @Test
    void testPlayerPlaysHandSnapYesMatchingCards() {
        when(mockScanner.nextLine()).thenReturn("YES");
        boolean result = game.playerPlaysHand("5H", "5H");
        assertEquals(true, result);
    }

    @Test
    void testPlayerPlaysHandSnapYesDifferentCards() {
        when(mockScanner.nextLine()).thenReturn("YES");
        boolean result = game.playerPlaysHand("5H", "6H");
        assertEquals(false, result);
    }

    @Test
    void testPlaySnapYesMatchingCards() {
        when(mockScanner.nextLine()).thenReturn("YES");
        boolean result = game.play("8D", "8D");
        assertEquals(true, result);
    }

    @Test
    void testPlaySnapYesDifferentCards() {
        when(mockScanner.nextLine()).thenReturn("YES");
        boolean result = game.play("8D", "9D");
        assertEquals(false, result);
    }

    @Test
    void testUserPlaysStick() {
        when(mockScanner.nextLine()).thenReturn("STICK");
        boolean result = game.userPlays();
        assertEquals(false, result);
    }

    @Test
    void testUserPlaysTwistAndBust() {
        when(mockScanner.nextLine()).thenReturn("TWIST");
        when(game.isBust()).thenReturn(true);
        boolean result = game.userPlays();
        assertEquals(true, result);
    }

    @Test
    void testUserPlaysTwistAndNotBust() {
        when(mockScanner.nextLine()).thenReturn("TWIST");
        when(game.isBust()).thenReturn(false);
        boolean result = game.userPlays();
        assertEquals(false, result);
    }

    @Test
    void testPlayStick() {
        when(mockScanner.nextLine()).thenReturn("STICK");
        boolean result = game.play();
        assertEquals(false, result);
    }

    @Test
    void testPlayTwistAndBust() {
        when(mockScanner.nextLine()).thenReturn("TWIST");
        when(game.isBust()).thenReturn(true);
        boolean result = game.play();
        assertEquals(true, result);
    }

    @Test
    void testPlayTwistAndNotBust() {
        when(mockScanner.nextLine()).thenReturn("TWIST");
        when(game.isBust()).thenReturn(false);
        boolean result = game.play();
        assertEquals(false, result);
    }
}
