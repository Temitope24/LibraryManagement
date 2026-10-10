public abstract class LibraryItem {
    private int id;
    private String titre;

    public LibraryItem(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public int getId() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
