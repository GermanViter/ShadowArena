# Shadow Arena
```Text
 ____  _               _                _                         
/ ___|| |__   __ _  __| | _____      __/ \   _ __ ___ _ __   __ _ 
\___ \| '_ \ / _` |/ _` |/ _ \ \ /\ / / _ \ | '__/ _ \ '_ \ / _` |
 ___) | | | | (_| | (_| | (_) \ V  V / ___ \| | |  __/ | | | (_| |
|____/|_| |_|\__,_|\__,_|\___/ \_/\_/_/   \_\_|  \___|_| |_|\__,_|
                                                                  
```
## Sources

- Documentation Gson : [https://google.github.io/gson/UserGuide.html](https://google.github.io/gson/UserGuide.html)
- Couleurs ANSI en Java : [https://stackoverflow.com/questions/35673302/java-typewriter-effect](https://stackoverflow.com/questions/35673302/java-typewriter-effect)
- Mot-clé `instanceof` : [https://www.w3schools.com/java/ref_keyword_instanceof.asp](https://www.w3schools.com/java/ref_keyword_instanceof.asp)
- Makefile (manuel GNU) : [https://www.gnu.org/software/make/manual/make.html#Simple-Makefile](https://www.gnu.org/software/make/manual/make.html#Simple-Makefile)
- Makefile (commandes shell) : [https://stackoverflow.com/questions/10024279/how-to-use-shell-commands-in-makefile](https://stackoverflow.com/questions/10024279/how-to-use-shell-commands-in-makefile)
- Concept du jeu (inspiration) : [https://fr.wikipedia.org/wiki/Donjons_et_Dragons](https://fr.wikipedia.org/wiki/Donjons_et_Dragons)

## Description
Le concept de ce jeu vient de [donjon et dragons](https://fr.wikipedia.org/wiki/Donjons_et_Dragons). La seule différence avec le jeu de société c'est que le maître du jeu est random. C'est à dire que les événements du jeu comme les apparitions de monstres sont totalement aléatoires.

---

## Choix de héros

| Type de héros | ATQ | HP  | Mana | SPE                                              |
| ------------- | --- | --- | ---- | ------------------------------------------------ |
| Mage          | 40  | 400 | 600  | `regenerate()` : soigne 50% HP (coûte 80 mana)  |
| Knight        | 35  | 450 | 600  | `heavyAttack()` : 85 dégâts fixes (coûte 80 mana)|
| Berserker     | 50+ | 500 | 200  | `enableBerserkMode()` : dégâts x2, HP x2 pour 3 tours (coûte 100 mana) |

> **Défense** : disponible pour tous les héros. Bloque 100% des dégâts du prochain tour. Coûte **60 mana**.

---

## Bestiaire

| Monstre          | HP  | Dégâts par tour |
| ---------------- | --- | --------------- |
| Gobelin Sournois | 80  | 15              |
| Serpent Géant    | 220 | 25              |
| Troll des Montagnes | 200 | 20           |
| Cerbère          | 160 | 28              |
| Dragon Ancien    | 350 | 35              |

Chaque monstre possède un tableau de **4 messages d'attaque aléatoires** (`getRandomAttackMessage()`).

---

## Système d'objets

| Item          | Type        | Effet                                      | Obtention                              |
| ------------- | ----------- | ------------------------------------------ | -------------------------------------- |
| Potion de soin | HealingItem | +80 HP                                    | Donnée 1 fois quand HP < 50% du max   |
| Potion de mana | HealingItem | +50 Mana                                  | Donnée 1 fois quand Mana < 50% du max |
| Grenade       | AttackItem  | 150 dégâts sur la cible                    | Droppée sur chaque monstre vaincu     |
| Amulette      | BuffItem    | +40 dégâts par attaque, mais -10 HP/tour  | Apparition aléatoire (1/20 de chance) |

---

## Système de progression (XP & Niveaux)

- Chaque monstre vaincu donne **50 XP**.
- Un **level up** se produit à chaque **150 XP** accumulés.
- À chaque niveau :
  - `damage += 5`
  - `maxHP += 20` (et HP restaurés au max)
  - `maxMana += 10` (et Mana restauré au max)

---

## Système de sauvegarde (JSON via Gson)

- La sauvegarde se fait automatiquement après chaque monstre vaincu.
- Les fichiers sont stockés dans le dossier `users_info/<nom>.json`.
- **`PlayerSave.java`** : classe de données qui contient l'état complet de la partie :
  - Le héros (type + stats)
  - L'inventaire
  - Le nombre de monstres vaincus
  - Les flags de progression (`lowHealthPotionGiven`, `lowManaPotionGiven`, `amuletteGiven`)
- **`SaveManager.java`** : gère la sérialisation/désérialisation avec **Gson** et des `JsonDeserializer` custom pour reconstruire les bonnes sous-classes (`Mage`, `Knight`, etc.) depuis le JSON.

---

## Structure du projet

Le projet est divisé en plusieurs packages qui ont des utilités différentes.

- ### **`src.com.rpg.core`** contient les classes abstraites cœurs du jeu :
  - `GameCharacter.java` (abstraite) : attributs communs à tous les personnages (`name`, `currentHP`, `maxHP`, `mana`, `maxMana`, `isDefending`, `isAmuletteActive`). Méthode abstraite `attack()`.
  - `GameItem.java` (abstraite) : attributs communs à tous les items (`name`, `description`, `message`). Méthode abstraite `use()`.
  - `ConsoleColors.java` : constantes pour les codes ANSI de couleur.
  - `ConsoleUtils.java` : méthodes utilitaires `slowPrint()` et `slowPrintNoLine()` pour l'effet typewriter.
  - `PlayerSave.java` : contient l'état de jeu à sauvegarder.
  - `SaveManager.java` : lecture/écriture JSON avec Gson.
  - Interfaces : `MageAbilities`, `KnightAbilities`, `BerserkerAbilities`.

- ### **`src.com.rpg.entities`** contient les entités qui héritent de `GameCharacter` :
  - `Hero.java` (abstraite) : ajoute `damage`, `level`, `experience`, `levelUp()`, `gainExperience()`.
  - `Mage`, `Knight`, `Berserker` : héros jouables, implémentent leur interface respective.
  - `Monster.java` (abstraite) : ajoute `attackMessages[]` et `isDead`.
  - `Goblin`, `GiantSnake`, `Troll`, `Cerberus`, `Dragon` : monstres avec leurs propres stats et dégâts.

- ### **`src.com.rpg.items`** contient la hiérarchie des items :
  - `AttackItem.java` (abstraite) → `Grenade.java`
  - `HealingItem.java` (abstraite) → `Potion.java`, `ManaPotion.java`
  - `BuffItem.java` (abstraite) → `Amulette.java`

- ### **`src.com.rpg.main`** contient `Main.java` avec la boucle de combat principale et la logique de jeu.

- ### Un **Makefile** est fourni pour faciliter la compilation (`make compile && make run`).

---

## Concepts POO démontrés

1. **Abstraction** : `GameCharacter` et `GameItem` sont abstraites — on ne peut pas les instancier directement.
2. **Encapsulation** : tous les attributs sont `private`, accessibles uniquement via getters/setters.
3. **Héritage** : hiérarchie à plusieurs niveaux, ex: `GameItem` → `AttackItem` → `Grenade`, ou `GameCharacter` → `Hero` → `Mage`.
4. **Polymorphisme** : `attack()` et `use()` sont redéfinies (`@Override`) dans chaque sous-classe. Le `instanceof` dans `Main.java` permet d'appeler la bonne capacité spéciale.
5. **Interfaces** : `MageAbilities` (`regenerate()`), `KnightAbilities` (`heavyAttack()`), `BerserkerAbilities` (`enableBerserkMode()`, `disableBerserkMode()`).

---


