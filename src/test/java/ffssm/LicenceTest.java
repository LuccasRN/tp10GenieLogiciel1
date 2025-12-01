package ffssm;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LicenceTest {

    @Test
    public void testEstValide() {
        Plongeur p = new Plongeur("Dupont", "Jean", "123", LocalDate.of(1990,1,1));
        Club c = new Club(null, "Club1");
        LocalDate delivrance = LocalDate.of(2024, 6, 1);
        Licence l = p.ajouteLicence("L1", delivrance, c);

        assertTrue(l.estValide(delivrance));                          // au jour de délivrance
        assertTrue(l.estValide(delivrance.plusMonths(6)));            // dans l'année
        assertTrue(l.estValide(delivrance.plusYears(1)));             // exactement à la fin de l'année
        assertFalse(l.estValide(delivrance.minusDays(1)));            // avant délivrance
        assertFalse(l.estValide(delivrance.plusYears(1).plusDays(1)));// un jour après la validité
    }
}
