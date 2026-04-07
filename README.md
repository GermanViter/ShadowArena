# Shadow Arena

---

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
| **Berserker** | 500 | 200 | **Rage Berserk** (100 Mana) : Double les dégâts pendant 3 tours. Dégâts bonus si bas en HP. |

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
5.  **Interfaces** : `Regenerable`, `HeavyAttack` et `WariorAbilities`.

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
        │   ├── HeavyAttack.java
        │   ├── Regenerable.java
        │   └── WariorAbilities.java
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
