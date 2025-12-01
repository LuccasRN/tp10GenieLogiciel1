package ffssm;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlongeeAndClubTest {

    @Test
    public void testPlongeeEstConformeEtClubNonConforme() {
        Plongeur p1 = new Plongeur("Dupont", "Jean", "123", LocalDate.of(1990,1,1));
        Plongeur p2 = new Plongeur("Durand", "Marie", "456", LocalDate.of(1992,2,2));
        Club club = new Club(null, "ClubTest");
        LocalDate delivranceP1 = LocalDate.of(2024, 1, 1);
        LocalDate delivranceP2 = LocalDate.of(2023, 1, 1); // licence expirée à la date de la plongée
        Licence l1 = p1.ajouteLicence("L1", delivranceP1, club);
        Licence l2 = p2.ajouteLicence("L2", delivranceP2, club);

        DiplomeDeMoniteur moniteur = new DiplomeDeMoniteur(p1, 10);
        Plongee plongee = new Plongee(new Site("S1","d"), moniteur, LocalDate.of(2024, 6, 1), 20, 40);
        plongee.ajouteParticipant(l1);
        plongee.ajouteParticipant(l2);

        club.organisePlongee(plongee);

        assertFalse(plongee.estConforme());
        assertTrue(club.plongeesNonConformes().contains(plongee));

        // rendre conforme en ajoutant une licence valide pour p2
        Licence l2new = p2.ajouteLicence("L2b", LocalDate.of(2024,5,1), club);
        // remplacer participants: simulate that participant uses new licence
        Plongee p2mod = new Plongee(plongee.lieu, plongee.chefDePalanquee, plongee.date, plongee.profondeur, plongee.duree);
        p2mod.ajouteParticipant(l1);
        p2mod.ajouteParticipant(l2new);
        club.organisePlongee(p2mod);

        assertTrue(p2mod.estConforme());
        assertFalse(club.plongeesNonConformes().contains(p2mod));
    }
}
