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

Nous avons implémenté ici l'algorithme du recuit simulé (Simulated Annealing method)
C’est un des plus vieux algorithmes de recherche locale
Il est déduit de l’algorithme « generic-Move » qui se trouve dans la classe Main.java

## Execution
Pour lancer le projet, il suffit de lancer la class main.java

## Description des différentes classes

### Graph.java

La classe **Graph.class** permet de parser les données contenues dans un fichiers .COL dans une structure de donnée représentant un graph.

### State.java 

La classe **State.class** représente l'état d'un graph. C'est une structure arborescente, où un etat peut avoir n etats enfants, et 1 etat parent. Dans notre cas, chaque etat génerera un seul état, on pourra donc considerer qu'il s'agit d'une liste doublement chainée.


### Main.java 

La classe **Main.class** Contient le main du programme et les methodes en rapport avec le recuit simulé.
Les deux méthodes principales sont :

- MetaHeuristic(Graph g, int maxCol, int maxTries, int maxMoves, float baseTemp, float alpha)
  - Une implémentation de l'algorithme Méta-Heuristique générique, avec en plus, la géstion de la "température" pour le recuit simulé.
  - g : le graph sur lequel on travaille.
  - maxCol : le nombre de valeurs qu'un sommet du graph peut prendre, le nombre de couleur maximum en d'autres mots.
  - maxTries : Le nombre de fois qu'on va essayer de trouver une solution avant d'arreter.
  - maxMoves : Le nombre de changements qu'on peut faire sur un graph avant de recommencer.
  - baseTemp : la temperature à laquelle on démare.
  - alpha : utilisé pour la réduction de la temperature. Nous utilisons une réduction géometrique Temperature = Temperature * Alpha.


- SA_Move(State cur, int maxCol, float temperature) 
  - cur : L'etat courant
  - maxCol : Le nombre de valeurs qu'un sommet du graph peut prendre, passé à la classe State pour generer un voisin
  - temperature : La temperature actuelle.


