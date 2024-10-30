package model.stats;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class StatsTest {

    private Stats stats;

    @BeforeEach
    void setUp() {
        stats = new Stats(50, 60, 70, 80, 10, 100);
    }

    @Test
    void testConstructor() {
        System.out.print(stats.toString());
        assertEquals(50, stats.getDmg(), "Incorrect damage");
        assertEquals(60, stats.getDef(), "Incorrect defense");
        assertEquals(70, stats.getDmgSpe(), "Incorrect special damage");
        assertEquals(80, stats.getDefSpe(), "Incorrect special defense");
        assertEquals(10, stats.getXpLevel(), "Incorrect XP level");
        assertEquals(100, Integer.parseInt(stats.pvOnPvMax().split("/")[0]), "Incorrect HP");
        assertEquals(100, Integer.parseInt(stats.pvOnPvMax().split("/")[1]), "Incorrect max HP");
    }

    @Test
    void testNoPv() {
        assertFalse(stats.noPv(), "The Pokémon should have HP.");
        stats.substractDamage(100);
        assertTrue(stats.noPv(), "The Pokémon should have no HP.");
    }

    @Test
    void testPvOnPvMax() {
        assertEquals("100/100", stats.pvOnPvMax(), "Incorrect HP over max HP.");
        stats.substractDamage(50);
        assertEquals("50/100", stats.pvOnPvMax(), "Incorrect HP over max HP after damage.");
    }

    @Test
    void testPvRatio() {
        assertEquals(1.0, stats.pvRatio(), "Incorrect HP ratio.");
        stats.substractDamage(50);
        assertEquals(0.5, stats.pvRatio(), 0.01, "Incorrect HP ratio after damage.");
    }

    @Test
    void testSubstractDamage() {
        assertFalse(stats.substractDamage(30), "The Pokémon should not be exhausted.");
        assertEquals("70", stats.pvOnPvMax().split("/")[0], "Incorrect HP after damage.");
        
        assertTrue(stats.substractDamage(80), "The Pokémon should be exhausted.");
        assertEquals("0", stats.pvOnPvMax().split("/")[0], "HP should be zero.");
    }

    @Test
    void testRestorePv() {
        stats.substractDamage(50);
        stats.restorePv();
        assertEquals("100", stats.pvOnPvMax().split("/")[0], "HP not correctly restored.");
    }

    @Test
    void testRandomStat() {
        Stats randomStats = Stats.RandomStat();
        assertNotNull(randomStats, "Random stats not generated.");
    }

    @Test
    void testEqualsAndHashCode() {
        Stats sameStats = new Stats(50, 60, 70, 80, 10, 100);
        Stats differentStats = new Stats(30, 40, 50, 60, 5, 80);

        assertEquals(stats, sameStats, "Stats objects are not equal.");
        assertNotEquals(stats, differentStats, "Unexpectedly equal Stats objects.");
        assertEquals(stats.hashCode(), sameStats.hashCode(), "Hash codes of Stats objects do not match.");
    }
}

