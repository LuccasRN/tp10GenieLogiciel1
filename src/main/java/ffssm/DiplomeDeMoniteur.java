package ffssm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiplomeDeMoniteur {
    private final int numeroDiplome;
    private final Plongeur possesseur;
    private final List<Embauche> emplois = new ArrayList<>();

    public DiplomeDeMoniteur(Plongeur possesseur, int numeroDiplome) {
        this.numeroDiplome = numeroDiplome;
        this.possesseur = possesseur;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur ou null s'il n'en a pas
     */
    public Club employeurActuel() {
        if (emplois.isEmpty()) return null;
        Embauche last = emplois.get(emplois.size() - 1);
        if (last.getFin() == null) return last.getEmployeur();
        return null;
    }

    /** Enregistrer une nouvelle embauche pour cet employeur */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {
        if (employeur == null || debutNouvelle == null) throw new IllegalArgumentException("param null");
        // terminer la précédente si elle n'est pas terminée
        if (!emplois.isEmpty()) {
            Embauche prev = emplois.get(emplois.size() - 1);
            if (prev.getFin() == null) {
                // fixer la fin à la veille du début de la nouvelle embauche
                prev.setFin(debutNouvelle.minusDays(1));
            }
        }
        Embauche e = new Embauche(debutNouvelle, this, employeur);
        emplois.add(e);
    }

    public List<Embauche> emplois() {
        return Collections.unmodifiableList(emplois);
    }

    public Plongeur getPossesseur() {
        return possesseur;
    }

    public int getNumeroDiplome() {
        return numeroDiplome;
    }

    @Override
    public String toString() {
        return "DiplomeDeMoniteur{" + "num=" + numeroDiplome + ", possesseur=" + possesseur + '}';
    }
}
