package model.move;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.enums.EnumPokemonType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    private Move move;
    private static final String MOVE_NAME = "Thunderbolt";
    private static final int POWER = 90;
    private static final int ACCURACY = 100;
    private static final int POWER_POINT = 15;
    private static final CategoryMove CATEGORY = CategoryMove.SPECIAL; 
    private static final EnumPokemonType TYPE = EnumPokemonType.ELECTRIC; 

    @BeforeEach
    void setUp() {
        move = new Move(MOVE_NAME, POWER, POWER_POINT, ACCURACY, CATEGORY, TYPE);
    }

    @Test
    void testConstructor_ValidParameters() {
        assertEquals(MOVE_NAME, move.getName());
        assertEquals(POWER, move.getPower());
        assertEquals(POWER_POINT, move.getPowerPoint());
        assertEquals(TYPE, move.getType());
    }

    @Test
    void testConstructor_InvalidPower_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Move(MOVE_NAME, 350, POWER_POINT, ACCURACY, CATEGORY, TYPE)
        );
        assertEquals("The power need to be between 15 and 300, and he should be a multiple of 5 and is 350", exception.getMessage());
    }

    @Test
    void testConstructor_InvalidAccuracy_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Move(MOVE_NAME, POWER, POWER_POINT, 110, CATEGORY, TYPE)
        );
        assertEquals("The precision is a percentage rate, it need to be between 0 and 100", exception.getMessage());
    }

    @Test
    void testIsUsable_InitialPP_ReturnsTrue() {
        assertTrue(move.isUsable());
    }

    @Test
    void testIsUsable_NoPPLeft_ReturnsFalse() {
        for (int i = 0; i < POWER_POINT; i++) {
            move.substractPP();
        }
        assertFalse(move.isUsable());
    }

    @Test
    void testSubstractPP_DecreasesPowerPoint() {
        int initialPP = move.getPowerPoint();
        move.substractPP();
        assertEquals(initialPP - 1, move.getPowerPoint());
    }

    @Test
    void testInstance_UniqueName() {
        Move newMove = Move.instance("Flamethrower", 90, 15, 100, CATEGORY, TYPE);
        assertEquals("Flamethrower", newMove.getName());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Move.instance("Flamethrower", 90, 15, 100, CATEGORY, TYPE)
        );
        assertEquals("The move name is already taken", exception.getMessage());
    }

    @Test
    void testEqualsAndHashCode() {
        Move moveCopy = new Move(MOVE_NAME, POWER, POWER_POINT, ACCURACY, CATEGORY, TYPE);
        assertEquals(move, moveCopy);
        assertEquals(move.hashCode(), moveCopy.hashCode());
    }
    
    @Test
    void testGetMaxPowerPoint() {
        assertEquals(POWER_POINT, move.getMaxPowerPoint());
    }
    
    /*
     * superfluo
    @Test
    void testToString() {
        String expected = "Move [name=" + MOVE_NAME + ", power=" + POWER + ", accuracy=" + ACCURACY + ", categoryMove=" + CATEGORY + "]";
        assertEquals(expected, move.toString());
    }
	*/
    
}

