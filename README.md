# Test technique de Ciril Group
Test technique de Ciril Group, réalisé en Java et simulant un feu de forêt.

## Énoncé
La forêt est représentée par une grille de dimension h x l.

La dimension temporelle est discrétisée. Le déroulement de la simulation se fait donc étape par étape.

Dans l’état initial, une ou plusieurs cases sont en feu.

Si une case est en feu à l’étape t, alors à l’étape t+1 :

- Le feu s'éteint dans cette case (la case est remplie de cendre et ne peut ensuite plus brûler)

- Il y a une probabilité p que le feu se propage à chacune des 4 cases adjacentes

La simulation s’arrête lorsqu’il n’y a plus aucune case en feu

Les dimensions de la grille, la position des cases initialement en feu, ainsi que la probabilité de propagation, sont des paramètres du programme stockés dans un fichier de configuration (format libre).

![](https://i.imgur.com/1MjB8v3.jpeg)

## Informations retenues
- Initialisation selon un fichier de configuration
- Une case peut être allumée, en cendre ou boisée
- Une case allumée peut propager les flammes aux cases boisées adjacentes à l'étape suivante
- La simulation s'arrête s'il n'y a pas de cases en feu

## Fonctionnalités à implémenter
- ~~Initialisation de la grille~~
- ~~Classe "Case" comportant la logique de l'exercice~~
- ~~Propagation des flammes~~
- Tests unitaires
- Possibilité d'aller et venir entre les étapes (Memento)

### Initialisation de la grille et gestion de la simulation
Implémentation d'un service "ForestService" qui gère :
- L'initialisation de la grille
- La gestion de la simulation (début, fin)
- Plus tard, les étapes

## Configuration
On utilisera un fichier .json sous la forme suivante :
```
{
   "height":4,
   "width":4,
   "proba":0.5,
   "fireCoordinates":[
      {
         "x":0,
         "y":1
      },
      {
         "x":2,
         "y":1
      },
      {
         "x":4,
         "y":2
      },
      {
         "x":4,
         "y":4
      }
   ]
}
```
Où les données à rentrer sont :
- *height* et *width* qui gèrent la dimension de la grille. Elles ne peuvent pas être négatives, nulles ou supérieures à 25 (pour permettre une simulation pas trop longue).
- *proba* qui indique la probabilité de propagation du feu (de 0 à 1).
- *fireCoordinates* qui est un tableau de coordonnées x et y.

