package ffssm;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiplomeEmbaucheTest {

    @Test
    public void testEmbaucheEtEmployeurActuel() {
        Plongeur poss = new Plongeur("Martin", "Paul", "999", LocalDate.of(1985,3,3));
        DiplomeDeMoniteur dipl = new DiplomeDeMoniteur(poss, 1);

        Club c1 = new Club(dipl, "Club A");
        Club c2 = new Club(dipl, "Club B");

        dipl.nouvelleEmbauche(c1, LocalDate.of(2022,1,1));
        assertEquals(c1, dipl.employeurActuel());

        // nouvelle embauche: la précédente doit être terminée (fin = veille)
        dipl.nouvelleEmbauche(c2, LocalDate.of(2023,5,10));
        assertEquals(c2, dipl.employeurActuel());

        // vérification des dates des emplois
        assertEquals(2, dipl.emplois().size());
        Embauche first = dipl.emplois().get(0);
        assertNotNull(first.getFin());
        assertEquals(LocalDate.of(2023,5,9), first.getFin());
        Embauche second = dipl.emplois().get(1);
        assertNull(second.getFin());
        assertEquals(c2, second.getEmployeur());
    }
}
