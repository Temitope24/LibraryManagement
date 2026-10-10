import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);

        Library library = new Library();

        while (true) {
            System.out.println("=== Library Management Système ===");
            System.out.println("Bienvenue dans ta bibliotèque !");
            System.out.println();

            System.out.println("1. Ajouter un livre" + 
                            "\n2. Afficher les livres" + 
                            "\n3. Rechercher un livre" +
                            "\n4. Inscrire un membre" +
                            "\n5. Afficher les membres" + 
                            "\n6. Emprunter un livre" + 
                            "\n7. Retourner un livre" + 
                            "\n8. Afficher les emprunts" + 
                            "\n0. Quitter");
            System.out.println();
            System.out.print("Votre choix : ");
            int choix;

            try {
                choix = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier.");
                scanner.nextLine();
                continue;
            }

            if (choix < 0 || choix > 8) {
                System.out.println("Entrez un nombre valide.");
            } 
            else if (choix == 0) {
                break;
            }
            else {
                switch(choix) {
                    case 1 -> {
                        System.out.println("=== Entrez les informations du livre ===");
                        System.out.print("Livre id : ");
                        int livreId;

                        try {
                            livreId = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Veuillez entrer un nombre entier.");
                            scanner.nextLine();
                            continue; 
                        }
                        
                        System.out.print("Titre : ");
                        String titre = scanner.nextLine();
                        System.out.print("Auteur : ");
                        String auteur = scanner.nextLine();
                        
                        Book book = new Book(livreId, titre, auteur, true);
                        System.out.println(library.ajouterLivre(book)); 
                        System.out.println();
                    }

                    case 2 -> library.afficherLivres();

                    case 3 -> {
                        System.out.print("Entrez le titre ou un mot-clé : ");
                        String query = scanner.nextLine();
                        library.rechercherLivres(query);
                    }

                    case 4 -> {
                        System.out.println("=== Veuillez entrez les informations du membre ===");
                        System.out.print("ID : ");

                        int membreId;

                        try {
                            membreId = scanner.nextInt();
                            scanner.nextLine();
                        } 
                        catch (InputMismatchException e) {
                            System.out.println("Veuillez entrer un nombre entier.");
                            scanner.nextLine();
                            continue; 
                        }

                        System.out.print("Nom : ");
                        String nomMembre = scanner.nextLine();
                        System.out.print("Email : ");
                        String emailMembre = scanner.nextLine();
                        
                        Member membre = new Member(membreId, nomMembre, emailMembre);
                        System.out.println(library.ajouterMembre(membre));
                        System.out.println();
                    }

                    case 5 -> library.afficherMembres();

                    case 6 -> {
                        System.out.println("=== Entrez les informations du livre à emprunter ===");
                        System.out.print("Titre : ");
                        String titre = scanner.nextLine();
                        System.out.print("Membre id : ");
                        
                        int membreId;
                        try {
                            membreId = scanner.nextInt();
                            scanner.nextLine();
                        } 
                        catch (InputMismatchException e) {
                            System.out.println("Veuillez entrer un nombre entier.");
                            scanner.nextLine();
                            continue; 
                        }

                        Member membre = library.rechercherMembre(membreId);
                        System.out.print("Date d'emprunt : ");
                        String dateEmprunt = scanner.nextLine();
                        try {
                            System.out.println(library.emprunterLivre(titre, membre, dateEmprunt));
                        }
                        catch (BookNotAvailableException e) {
                            System.out.println("Il y a eu une problème : \n" + e.getMessage());
                            System.out.println();
                        }
                        System.out.println();
                        
                    }

                    case 7 -> {
                        System.out.print("Titre du livre à retourner : ");
                        String titre = scanner.nextLine();
                        System.out.println(library.retournerLivre(titre));
                        System.out.println();
                    }

                    case 8 -> library.afficherEmprunts();
                }
            }
            
        }

        scanner.close();

    }
}
