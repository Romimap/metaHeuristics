# Codage d une metaheuristique dédiée au coloriage de graphe - MAX-CSP

Projet de métaheuristique dédiée à SAT réalisé durant le semestre 2 de notre Master Informatique IMAGINA.

Ce projet va permettre de mettre en pratique nos connaissances concernant les meta-heuristique.  
Nous allons appliquer l'exercice 3 de cette feuille de TD: http://www.lirmm.fr/~trombetton/cours/TD21.pdf

- Le support de cours : http://www.lirmm.fr/~trombetton/cours/local.pdf

## Auteurs principaux
Auteurs principaux: 
* **Romain Fournier** _alias_ [@Romimap](https://github.com/Romimap)
* **Émery Bourget-Vecchio** _alias_ [@EmeryBV](https://github.com/EmeryBV)

Auteurs secondaires: 
* **Clément Potin**
* **Melvin Bardin**
* **Malika Lin-wee-kuan**
* **Maël Bonneaud**



##Description de l'algorithme utilisé

Nous avons implémenté ici l'algorithme du recuit simulé (Simulatedf Annealing method)
C’est un des plus vieux algorithmes de recherche locale
Il est déduit de l’algorithme « generic-Move » qui se trouve dans la classe Main.java

##Execution
Pour lancer le projet, il suffit de lancer la class main.java

## Description des différentes classes

###Classe Graph.java
La classe **Graph.class** permet de lire et de stocker les données contenues dans des fichiers avec l'extension.col 

###Classe Main.java 
La classe **Main.class** permet d'exécuter le programme et contient également les méthodes permettant d'appliquer l'algorithme Métropolis - recuit simulé-
Les deux méthodes principales sont :

- MetaHeuristic(Graph g, int maxCol, int maxTries, int maxMoves, float baseTemp, float alpha);
- SA_Move(State cur, int maxCol, float temperature) 

###Classe State.java 
La classe **State.class** permet de représenter l'état d'un graph.Il stocke son état précédent, ses états enfants et le sommet qui a été changé.

