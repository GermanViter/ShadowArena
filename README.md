# Shadow Arena
```Text
 ____  _               _                _                         
/ ___|| |__   __ _  __| | _____      __/ \   _ __ ___ _ __   __ _ 
\___ \| '_ \ / _` |/ _` |/ _ \ \ /\ / / _ \ | '__/ _ \ '_ \ / _` |
 ___) | | | | (_| | (_| | (_) \ V  V / ___ \| | |  __/ | | | (_| |
|____/|_| |_|\__,_|\__,_|\___/ \_/\_/_/   \_\_|  \___|_| |_|\__,_|
                                                                  
```

---

## Pour commencer, mes sources:
- Documentation pour l'utilisation de gson : [https://google.github.io/gson/UserGuide.html](https://google.github.io/gson/UserGuide.html)
- Documentation pour les couleurs ANSI : [https://stackoverflow.com/questions/35673302/java-typewriter-effect](https://stackoverflow.com/questions/35673302/java-typewriter-effect)
- Documentation pour le `instanceof` : [https://www.w3schools.com/java/ref_keyword_instanceof.asp](https://www.w3schools.com/java/ref_keyword_instanceof.asp)
- Documentation pour le Makefile :
  - [https://www.gnu.org/software/make/manual/make.html#Simple-Makefile](https://www.gnu.org/software/make/manual/make.html#Simple-Makefile)
  - [https://stackoverflow.com/questions/10024279/how-to-use-shell-commands-in-makefile](https://stackoverflow.com/questions/10024279/how-to-use-shell-commands-in-makefile)


## Concept du Jeu
**Shadow Arena** est un RPG inspire de [donjons et dragons](https://fr.wikipedia.org/wiki/Donjons_et_Dragons) ou le maitre du jeu est aleatoire. 


### Fonctionnalités Clés :
- **Système de Mana** : Toutes les capacités spéciales et la défense consomment du mana. Gérez vos ressources avec soin !
- **Inventaire & Loot** : Récupérez des **Grenades** sur les cadavres de vos ennemis et recevez une **Potion de survie** unique si vos points de vie tombent trop bas.
- **Vagues Aléatoires** : Affrontez un bestiaire varié allant du simple Gobelin au terrifiant Cerbère.
- **Effets Visuels & Typewriter** : Interface dynamique avec couleurs ANSI et affichage progressif du texte pour une immersion totale.

---

## ⚔️ Classes de Héros

| Classe  | HP  | Mana | Capacité Spéciale |
| :---    | :-- | :--- | :--- |
| **Mage**    | 400 | 200  | **Régénération** (80 Mana) : Soigne tous les HP. |
| **Knight** | 450 | 200  | **Attaque Lourde** (80 Mana) : Inflige +10 dégâts bonus. |
| **Berserker** | 500 | 200 | **Rage Berserk** (100 Mana) : HP augmentés et dégâts doublés pendant 3 tours. |

*Note : La **Défense** consomme 60 Mana et bloque 100% des dégâts du prochain tour.*

---

## Bestiaire de l'Arène
L'arène génère aléatoirement l'un des monstres suivants :
- **Gobelin Sournois** (50 HP) : Faible mais vicieux.
- **Serpent Géant** (80 HP) : Rapide et venimeux.
- **Troll des Montagnes** (150 HP) : Une brute massive et résistante.
- **Cerbère** (200 HP) : Le gardien des enfers, trois têtes et des flammes.
- **Dragon Ancien** (300 HP) : Le plus dur

---

## Système d'Objets
- **Potion de Soin** : Restaure 80 HP (donnée une seule fois si HP < 50%).
- **Grenade** : Inflige 150 points de dégâts massifs à la cible.

---

## Concepts POO Démontrés
1.  **Abstraction** : Classe `GameCharacter` et `GameItem` abstraites.
2.  **Encapsulation** : Usage rigoureux de `private` avec getters/setters.
3.  **Héritage** : Hiérarchie complexe (`GameItem` -> `AttackItem` -> `Grenade`).
4.  **Polymorphisme** : Surcharge de `attack()` et `use()` selon l'entité ou l'objet.
5.  **Interfaces** : `MageAbilities`, `KnightAbilities` et `BerserkerAbilities`.

---

## Structure du Projet
``` Text
.
└── com
    └── rpg
        ├── core
        │   ├── ConsoleColors.java
        │   ├── ConsoleUtils.java
        │   ├── GameCharacter.java
        │   ├── GameItem.java
        │   ├── KnightAbilities.java
        │   ├── MageAbilities.java
        │   └── BerserkerAbilities.java
        ├── entities
        │   ├── Berserker.java
        │   ├── Cerberus.java
        │   ├── Dragon.java
        │   ├── GiantSnake.java
        │   ├── Goblin.java
        │   ├── Hero.java
        │   ├── Knight.java
        │   ├── Mage.java
        │   ├── Monster.java
        │   ├── Troll.java
        │   └── ...
        ├── items
        │   ├── AttackItem.java
        │   ├── Grenade.java
        │   ├── HealingItem.java
        │   └── Potion.java
        └── main
            └── Main.java
```
---

## Lancement
```bash
make compile && make run
```
