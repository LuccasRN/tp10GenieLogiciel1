package ffssm;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

public class Licence {
    @Getter @Setter public Plongeur possesseur;
    public String numero;
    @Getter @Setter public LocalDate delivrance;
    @Getter @Setter public Club club;

    public Licence(Plongeur possesseur, String numero, LocalDate delivrance, Club club) {
        this.possesseur = possesseur;
        this.numero = numero;
        this.delivrance = delivrance;
        this.club = club;
    }

    /** Une licence est valide pendant un an à compter de sa délivrance (inclus). */
    public boolean estValide(LocalDate d) {
        if (d == null || delivrance == null) return false;
        LocalDate fin = delivrance.plusYears(1);
        // valide si d est dans l'intervalle [delivrance, delivrance.plusYears(1)]
        return (!d.isBefore(delivrance)) && (!d.isAfter(fin));
    }

    @Override
    public String toString() {
        return String.format("Licence[%s - %s - %s]", numero, delivrance, possesseur != null ? possesseur.getNom() : "null");
    }
}

