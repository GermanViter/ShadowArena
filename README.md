# Shadow Arena
> *Disclamer : tous les elements de style comme les couleurs de texte, les messages en lien avec l'histoire et l'affichage progressif du texte ont ete entierement genere par IA*
## Description
Le concept de ce jeu vient de [Donjons et Dragons](https://fr.wikipedia.org/wiki/Donjons_et_Dragons). La seule différence avec le jeu de société est que le maître du jeu est aléatoire. C'est-à-dire que les événements du jeu, comme les apparitions de monstres, sont totalement aléatoires. 

---

## Choix des héros :

| Type de héros | ATQ | HP  | SPE                   |
| ------------- | --- | --- | --------------------- |
| Mage          | 25  | 100 | régénération          |
| Warrior       | 20  | 150 | attaque lourde : +10  |
|               |     |     |                       |

---

## Structure du projet
Le projet est divisé en plusieurs packages qui ont des utilités différentes.
- **src.com.rpg.core** contient les classes abstraites cœurs du jeu qui définissent le fonctionnement de tous les éléments comme les personnages (mobs ou héros) et les interfaces.

- **src.com.rpg.entities**, comme le nom l'indique, contient les différentes entités qui héritent de la classe GameCharacter dans **core** comme *Mage*, *Warrior*, *Dragon*, *Goblin*. Ces classes contiennent des méthodes qui surchargent (override) des méthodes définies dans leurs interfaces ou dans leur classe parente.

- **src.com.rpg.main** contient la classe *Main.java* où se trouve la boucle principale du jeu et où tous les événements du jeu se déroulent.

- Pour faciliter la compilation du projet, un *Makefile* a été nécessaire.

---

## TODO
- Ajouter des items a recuperer
- Ajouter un systeme de defence
