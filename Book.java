public class Book extends LibraryItem implements Borrowable, Searchable {
    private String auteur;
    private boolean disponible;

    public Book(int id, String titre, String auteur, boolean disponible) {
        super(id, titre);
        this.auteur = auteur;
        this.disponible = disponible;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setDisponible(boolean dispo) {
        this.disponible = dispo;
    }

    @Override
    public boolean borrow() {
        if (!this.disponible) {
            return false;
        }
        else {
            this.disponible = false;
            return true;
        }
    }

    @Override
    public boolean returnItem() {
        if (!this.disponible) {
            this.disponible = true;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isAvailable() {
        return disponible;
    }

    @Override
    public boolean matches(String query) {
        query = query.toLowerCase();

        if(this.auteur.toLowerCase().contains(query) || this.getTitre().toLowerCase().contains(query)) {
            return true;
        }

        return false;
    }
}
