package ffssm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Plongeur extends Personne {
    @Getter @Setter private GroupeSanguin groupeSanguin;
    private final List<Licence> licences = new ArrayList<>();

    public Plongeur(String nom, String prenom, String numeroINSEE, LocalDate naissance) {
        super(nom);
        this.prenom = prenom;
        this.numeroINSEE = numeroINSEE;
        this.naissance = naissance;
    }

    public Licence ajouteLicence(String numero, LocalDate delivrance, Club club) {
        Licence l = new Licence(this, numero, delivrance, club);
        licences.add(l);
        return l;
    }

    public List<Licence> getLicences() {
        return new ArrayList<>(licences);
    }

    /** retourne la dernière licence si elle existe, sinon null */
    public Licence derniereLicence() {
        if (licences.isEmpty()) return null;
        return licences.get(licences.size() - 1);
    }

    @Override
    public String toString() {
        return "Plongeur{" + "nom=" + nom + ", prenom=" + prenom + '}';
    }
}
