## Library Management System
Application en ligne de commande développée en Java pour gérer les livres, les membres et les emprunts d'une bibliothèque.

# Fonctionnalités
- Ajouter et afficher des livres
- Rechercher un livre
- Inscrire et afficher des membres
- Emprunter et retourner des livres
- Afficher les informations sur les emprunts
- Gérer les erreurs grâce à des exceptions personnalisées
- Vérifier les saisies de l'utilisateur

# Technologies et concepts utilisés
- Java
- Programmation orientée objet (POO)
- Classes et objets
- Héritage et classes abstraites
- Interfaces
- Collections ArrayList
- Gestion des exceptions

# Structure du projet
- Main.java : point d'entrée de l'application et menu interactif
- Library.java : gestion des livres, des membres et des emprunts
- LibraryItem.java : classe abstraite représentant un élément de la bibliothèque
- Book.java : représentation d'un livre
- Member.java : représentation d'un membre
- Loan.java : gestion des emprunts
- Borrowable.java : interface liée à l'emprunt
- Searchable.java : interface liée à la recherche
- BookNotFoundException.java : exception pour les livres introuvables
- BookNotAvailableException.java : exception pour les livres indisponibles

# Installation et exécution

## Prérequis
Avoir le kit de développement Java (JDK) installé sur son ordinateur.

## Lancer l'application
- Cloner le dépôt GitHub
- Accéder au dossier du projet
- Compiler les fichiers Java
- Lancer l'application

## Objectif du projet
Ce projet a été réalisé pour mettre en pratique les concepts fondamentaux de Java et de la programmation orientée objet à travers le développement d'une application fonctionnelle.