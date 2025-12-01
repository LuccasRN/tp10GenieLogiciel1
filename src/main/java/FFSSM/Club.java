package ffssm;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class Club {
    @Getter @Setter public DiplomeDeMoniteur president;
    @Getter @Setter public String nom;
    @Getter @Setter public String adresse;
    @Getter @Setter public String telephone;

    private final Set<Plongee> plongees = new HashSet<>();

    public Club(DiplomeDeMoniteur president, String nom) {
        this.president = president;
        this.nom = nom;
    }

    /**
     * Calcule l'ensemble des plongées non conformes organisées par ce club.
     * Une plongée est conforme si tous les plongeurs de la palanquée ont une licence valide à la date de la plongée
     */
    public Set<Plongee> plongeesNonConformes() {
        Set<Plongee> res = new HashSet<>();
        for (Plongee p : plongees) {
            if (!p.estConforme()) res.add(p);
        }
        return res;
    }

    /** Enregistre une nouvelle plongée organisée par ce club */
    public void organisePlongee(Plongee p) {
        if (p == null) throw new IllegalArgumentException("plongee null");
        plongees.add(p);
    }

    public Set<Plongee> getPlongees() {
        return new HashSet<>(plongees);
    }

    @Override
    public String toString() {
        return "Club{" + "président=" + president + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }
}
