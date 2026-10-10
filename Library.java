import java.util.ArrayList;

public class Library {
    private ArrayList<Book> livres;
    private ArrayList<Loan> emprunts;
    private ArrayList<Member> membres;

    public Library() {
        this.livres = new ArrayList<>();
        this.emprunts = new ArrayList<>();
        this.membres = new ArrayList<>();
    }

    public String ajouterMembre(Member membre) {
        boolean idTrouve = false;

        for (Member member : membres) {
            if (member.getId() == membre.getId()) {
                idTrouve = true;
                break;
            }
        }

        if (idTrouve) {
            return "Cette id est déjà rattaché à un membre existant.";
        }
        else {
            this.membres.add(membre);
            return "Nouveau membre ajouté avec succés";
        }
    }

    public void afficherMembres() {
        if (membres.isEmpty()) {
            System.out.println("Aucun membre inscrit.");
            System.out.println();
            return;
        }
        for (Member membre : membres) {
            System.out.println(membre.getId() + " | " + membre.getNom() + " | " + membre.getEmail());
        }
        System.out.println();
    }

    public Member rechercherMembre(int id) {
        for (Member membre : membres) {
            if (membre.getId() == id) {
                return membre;
            }
        }
        return null;
    }

    public String ajouterLivre(Book livre) {
        boolean idTrouve = false;

        for (Book book : livres) {
            if (book.getId() == livre.getId()) {
                idTrouve = true;
                break;
            } 
        }

        if (idTrouve) {
            return "Ce livre existe déjà dans la bibliothèque.";
        }
        else {
            this.livres.add(livre);
            return "Le livre a bien été ajouté dans la bibliotèque.";
        }
    }

    public void ajouterEmprunt(Loan emprunt) {
        this.emprunts.add(emprunt);
    }

    public void afficherLivres() {
        if (livres.isEmpty()) {
            System.out.println("Aucun livre dans la bibliotèque.");
            System.out.println();
            return;
        }
        for(Book livre : this.livres) {
            System.out.println("Titre : " + livre.getTitre());
            System.out.println("Auteur : " + livre.getAuteur());

            if (livre.isAvailable()) {
                System.out.println("Disponible : oui");
            }
            else {
                System.out.println("Disponible : non");
            }
            
            System.out.println();
        }
    }

    public void afficherEmprunts() {
        if (emprunts.isEmpty()) {
            System.out.println("Aucun livre emprunté.");
            return;
        }
        System.out.println("=== Liste de emprunts ===");
        for (Loan emprunt : emprunts) {
            System.out.println("Livre : " + emprunt.getLivre().getTitre());
            System.out.println("Membre : " + emprunt.getMembre().getNom());
            System.out.println("Date d'emprunt : " + emprunt.getDateEmprunt());
            System.out.println();
        }
    }

    public void rechercherLivres(String query) {
        boolean livreTrouve = false;

        for (Book livre : livres) {
            if (livre.matches(query)) {
                livreTrouve = true;
                System.out.println("Livre trouvé : " + livre.getTitre());
                System.out.println();
            } 
        }

        if (!livreTrouve) {
            System.out.println("Aucun livre trouvé pour " + query);
            System.out.println();
        }
    }

    public Book rechercherLivreParTitre(String titre) throws BookNotFoundException {

        for (Book livre : this.livres) {
            if (livre.getTitre().equals(titre)) {
                return livre;
            }
        }

        throw new BookNotFoundException("le livre n'a pas été trouvé.");
    }

    public Book rechercherLivreParId(int id) {
        for (Book livre : this.livres) {
            if (livre.getId() == id) {
                return livre;
            }
        }

        return null;
    }

    public String emprunterLivre(String titre, Member membre, String dateEmprunt) throws BookNotAvailableException {
        if (membre != null) {
            for (Book livre : this.livres) {
                if (livre.getTitre().equals(titre)) {
                    boolean peutBorrow = livre.borrow();

                    if(peutBorrow) {
                        Loan emprunt = new Loan(livre, membre, dateEmprunt);
                        this.ajouterEmprunt(emprunt);
                        
                        return "Vous avez bien emprunté ce livre !";
                    }
                    else {
                        throw new BookNotAvailableException("Livre pas disponible.");
                    }
                }
            }

            return "Livre introuvable.";
        }

        return "Le membre n'existe pas.";
        
        

    }

    public String retournerLivre(String titre) {
        for (Book livre : this.livres) { // On cherche le livre qui possede le titre
            if (livre.getTitre().equals(titre)) { // On compare les livres pour chercher

                // Si on trouve ce livre avec le mêm titre, on declare des varaibles pour trouver s'il y a un emprunt correspondant
                boolean empruntTrouve = false;
                int empruntRetourneeIndex = 0;

                //On parcouurs la liste des emprunts
                for (Loan emprunt : emprunts) {
                    if (emprunt.getLivre().getTitre().equals(titre)) {//si cette emprunt correspond au titre de ce livre qu'on cherche
                        empruntTrouve = true; // alors ça a été emprunté
                        break; // on peut sortir du boucle
                    }
                    else { // sinon, on continue à chercher en gardant chaque indice d'emprunt
                        empruntRetourneeIndex++;
                    }
                }

                /*boolean peutRetourner = livre.returnItem();*/

                if (empruntTrouve) { // si on a trouvé l'emprunt

                    boolean peutRetourner = livre.returnItem(); // On essaie de rendre le livre et on récupère le résultat

                    if (peutRetourner) { // Si on peut retourner cette emprunt (livre available = false)
                        emprunts.remove(empruntRetourneeIndex); // On le supprime d'emprunts car il n'est plus un emprunt
                        return "Vous avez bien retourner ce livre !";
                    }
                    else { // Mais si on ne peut pas retourner
                        return "Il existe une incohérence avec ce livre."; // Alor il y a un problème quelque part avec ce livre car comment ça t'est emprunté mais on ne peut pas de reourner
                    }
                }
                else {
                    return "Cette emprunte n'existe pas."; // Si on n'a pas trouvé cette emprint, on le dit
                }
            }
        }

        return "Livre introuvable."; //Si aucun livre correspond

    }
}
