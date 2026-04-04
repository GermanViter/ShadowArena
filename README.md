# 🛡️ Shadow Arena

---

## 🎮 Concept du Jeu
**Shadow Arena** est un RPG inspire de [donjons et dragons](https://fr.wikipedia.org/wiki/Donjons_et_Dragons) ou le maitre du jeu est aleatoire. 


### Fonctionnalités Clés :
- **Système de Combat Tour par Tour** : Attaquez, utilisez vos capacités spéciales ou défendez-vous.
- **Vagues Aléatoires** : Chaque tour, un nouvel ennemi peut apparaitre.
- **Effets Visuels ASCII** : Utilisation de couleurs ANSI pour une interface dynamique.
- **Effet Typewriter** : Le texte s'affiche lettre par lettre pour eviter d'etre desoriente.
- **Condition de Victoire** : Survivez à 3 éliminations de monstres pour gagner.

---

## 🛠️ Concepts POO Démontrés

1.  **Abstraction** : Utilisation de la classe `abstract GameCharacter`. On ne peut pas créer un "personnage" générique ; il doit être une entité concrète (Héros ou Monstre).
2.  **Encapsulation** : Les attributs sensibles (`hp`, `name`, `isDefending`) sont `private`. L'accès et la modification se font via des méthodes sécurisées comme `takeDamage()` ou des getters/setters.
3.  **Héritage** : Une hiérarchie claire : `GameCharacter` -> `Hero` -> `Warrior`. Cela permet de réutiliser le code commun à tous les personnages.
4.  **Polymorphisme / Override** : La méthode `attack()` est définie dans la classe parente mais chaque sous-classe (Mage, Dragon) la surcharge pour définir son propre style de combat.
5.  **Interfaces** : Utilisation de `Regenerable` et `HeavyAttack` pour définir des capacités uniques qui ne s'appliquent qu'à certains héros.

---

## ⚔️ Classes de Héros

| Classe  | HP  | ATQ | Capacité Spéciale |
| :---    | :-- | :-- | :--- |
| **Mage**    | 400 | 25  | **Régénération** : Soigne tous les HP (1 seule utilisation). |
| **Warrior** | 450 | 20  | **Attaque Lourde** : Inflige +10 dégâts (1 seule utilisation). |

---

## 🏗️ Structure du Projet

```text
src/com/rpg/
├── core/             # Logique centrale, Classes abstraites, Interfaces, Utilitaires
│   ├── GameCharacter.java
│   ├── ConsoleColors.java
│   ├── ConsoleUtils.java    # Gère l'effet typewriter
│   └── ...
├── entities/         # Définition des héros et des monstres
│   ├── Hero.java
│   ├── Warrior.java
│   ├── Monster.java
│   └── ...
└── main/             # Point d'entrée et boucle de jeu
    └── Main.java
```

---

## 🚀 Comment Lancer le Jeu

### Prérequis
- Java JDK 8 ou supérieur.

### Compilation et Exécution
Utilisez le **Makefile** inclus pour une gestion simplifiée :

```bash
# Pour compiler le projet
make compile

# Pour lancer le jeu
make run
```

---

## 📝 À propos du Projet
> *Note : Les éléments esthétiques (couleurs, affichage progressif) et certains messages narratifs ont ete completement genere pas IA*

