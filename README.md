# Évaluation JPA - PetStore

LOURGOUILLOUX Renaud (sblaaaf) - DEV B3 - EPSI

## Projet
 - VALIDATION du module ORM - JPA - Hibernate.
 - TP : Modélisation d'une animalerie avec respect strict du diagramme UML fourni.

**Détails d'implémentation :**
- Architecture multi-couches simple et isolée (package `petstore.entite`).
- Utilisation des relations `@OneToOne`, `@OneToMany` et `@ManyToOne` avec mise en place de méthodes de synchronisation bidirectionnelles (ex: `addProduct`, `addAnimal`).
- Utilisation de l'héritage `JOINED` (1 table par classe) pour les entités `Animal`, `Fish` et `Cat`.

## Choix Techniques

- **Java 21 & Hibernate 7.2.3** : Version récente et stable de Java + dernière version d'Hibernate. Imports standards actuels `jakarta.persistence.*` qui remplacent les anciens `javax.persistence.*`.
- **Base de données MariaDB** : SGBD léger, performant et facile en local via Docker pour les tests de développement.
- **persistence.xml** : Le type de transaction est défini sur `RESOURCE_LOCAL` car nous sommes dans une application Java SE simple (Standalone), sans serveur d'application externe pour gérer les transactions.
- **Génération du schéma (`drop-and-create`)** : La propriété `jakarta.persistence.schema-generation.database.action` est configurée sur `drop-and-create`. Idéal pour un env de test : réinitialise la base à zéro à chaque lancement  pour éviter les conflits de données lors de la création de données test.

## Consignes d'exécution

Pour tester :

1. **Base de données** : MariaDB accessible sur `localhost:3306`.
2. **Création du schéma** : DB vide nommée `petstore` (via la commande `CREATE DATABASE IF NOT EXISTS petstore;`).
3. **Configuration** : Accès (user: `root`, password: `root`) configurés dans : `src/main/resources/META-INF/persistence.xml`.
4. **Lancement** : Run `main` de la classe `fr.epsi_26.Main`.

**Actions réalisées :**
- Génération automatique des tables dans la base MariaDB.
- Insertion d'un jeu de données (au minimum 3 enregistrements par table).
- Requête JPQL pour lister les animaux appartenant à l'animalerie "petstore 2".