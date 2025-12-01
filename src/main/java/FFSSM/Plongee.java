package ffssm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plongee {
    public Site lieu;
    public DiplomeDeMoniteur chefDePalanquee;
    public LocalDate date;
    public int profondeur;
    public int duree;

    private final List<Licence> participants = new ArrayList<>();

    public Plongee(Site lieu, DiplomeDeMoniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
        this.lieu = lieu;
        this.chefDePalanquee = chefDePalanquee;
        this.date = date;
        this.profondeur = profondeur;
        this.duree = duree;
    }

    public void ajouteParticipant(Licence participant) {
        if (participant == null) throw new IllegalArgumentException("participant null");
        if (!participants.contains(participant)) participants.add(participant);
    }

    /** Une plongée est conforme si tous les participants ont une licence valide à la date de la plongée. */
    public boolean estConforme() {
        for (Licence l : participants) {
            if (l == null || !l.estValide(this.date)) return false;
        }
        return true;
    }

    public List<Licence> getParticipants() {
        return new ArrayList<>(participants);
    }

    @Override
    public String toString() {
        return "Plongee{" + "lieu=" + lieu + ", date=" + date + ", profondeur=" + profondeur + ", duree=" + duree + '}';
    }
}

