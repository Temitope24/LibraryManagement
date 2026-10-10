public class Loan {
    private Book livre;
    private Member membre;
    private String dateEmprunt;

    public Loan(Book livre, Member membre, String dateEmprunt) {
        this.livre = livre;
        this.membre = membre;
        this.dateEmprunt = dateEmprunt;
    }

    public Book getLivre() {
        return this.livre;
    }

    public Member getMembre() {
        return this.membre;
    }

    public String getDateEmprunt() {
        return this.dateEmprunt;
    }
}
