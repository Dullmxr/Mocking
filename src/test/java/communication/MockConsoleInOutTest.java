package communication;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class MockConsoleInOutTest{

    Scanner mockScanner = mock(Scanner.class);
    ConsoleInOut consoleInOut = new ConsoleInOut();

    @BeforeEach
    private void setUp(){
        consoleInOut.setScanner(mockScanner);
    }

    @Test
    void testGetInteger(){
        when(mockScanner.nextLine()).thenReturn("2");
        assertEquals(2, consoleInOut.getInteger());
    }


     @Test
    void testGetInputIntegerSecondTime() {
        when(mockScanner.nextLine()).thenReturn("Del", "2");
        assertEquals(2, consoleInOut.getInputInteger(null));
    }

    @Test
    void testGetListIndex() {
        List<String> options = List.of("One", "Two");
        when(mockScanner.nextLine()).thenReturn("1");
        assertEquals(1, consoleInOut.getListIndex(options));
    }

    @Test
    void testGetListIndexSecondTime() {
        List<String> options = List.of("One", "Two");
        when(mockScanner.nextLine()).thenReturn("3", "1");
        assertEquals(1, consoleInOut.getListIndex(options));
    }

    @Test
    void testGetListIndexWithQuestion() {
        List<String> options = List.of("One", "Two");
        when(mockScanner.nextLine()).thenReturn("1");
        assertEquals(1, consoleInOut.getListIndex("Choose an option:", options));
    }

    @Test
    void testGetPlayersCard() {
        List<String> hand = List.of("C3", "C4", "C5");
        when(mockScanner.nextLine()).thenReturn("1");
        assertEquals("C4", consoleInOut.getPlayersCard(hand).toString());
    }

    @Test
    void testGetYesOrNoYes() {
        when(mockScanner.nextLine()).thenReturn("Yes");
        assertEquals(YesOrNo.YES, consoleInOut.getYesOrNo(null));
    }

    @Test
    void testGetYesOrNoY() {
        when(mockScanner.nextLine()).thenReturn("Y");
        assertEquals(YesOrNo.YES, consoleInOut.getYesOrNo(null));
    }

    @Test
    void testGetYesOrNoNo() {
        when(mockScanner.nextLine()).thenReturn("N");
        assertEquals(YesOrNo.NO, consoleInOut.getYesOrNo(null));
    }

    @Test
    void testGetYesOrNoOther() {
        when(mockScanner.nextLine()).thenReturn("");
        assertEquals(YesOrNo.NO, consoleInOut.getYesOrNo(null));
    }

    @Test
    void testGetRows() {
        when(mockScanner.hasNextLine()).thenReturn(true, true, false);
        when(mockScanner.nextLine()).thenReturn("First Line", "Second Line");
        List<String> rows = consoleInOut.getRows();
        assertEquals(2, rows.size());
        assertEquals("First Line", rows.get(0));
        assertEquals("Second Line", rows.get(1));
    }

    @Test
    void testGetCSVRows() {
        when(mockScanner.hasNextLine()).thenReturn(true, true, false);
        when(mockScanner.nextLine()).thenReturn("First,Row", "Second,Row");
        List<String> rows = consoleInOut.getCSVRows();
        assertEquals(2, rows.size());
        assertEquals("First,Row", rows.get(0));
        assertEquals("Second,Row", rows.get(1));
    }

}
