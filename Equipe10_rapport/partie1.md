# Partie 1: JPacman avec CodeScene

## Question 1
CodeScene vous a-t-il aidé à identifier les cibles possibles pour le refactoring ? Si oui, donner la liste d'artéfacts (classes et méthodes) qui ont besoin de refactoring ?

**Réponse:**

Oui, CodeScene a permis d'identifier des cibles potentielles pour le refactoring dans le projet jpacman, même si l'état général du code est très bon.

### État global du projet

- **Code Health Score global** : 9,69 / 10
- **Hotspot Code Health** : 10
- **Average Code Health** : 9,6
- **Worst Performer** : 8,2 (Problematic)
- **Limite importante** : "Insufficient commit history to analyze technical debt" - l'historique de commits est trop limité pour une analyse complète de la dette technique.

### Hotspots identifiés (à surveiller, mais sans problème critique)

Les classes suivantes sont identifiées comme Hotspots (fichiers fréquemment modifiés), mais avec un score de santé parfait (10) :

| Classe | Lignes de Code | Commits |
|--------|----------------|---------|
| `Launcher.java` | 105 LoC | 1 commit |
| `LevelFactory.java` | 79 LoC | 1 commit |
| `Inky.java` | 61 LoC | 1 commit |
| `Clyde.java` | 57 LoC | 1 commit |
| `Pinky.java` | 46 LoC | 1 commit |
| `Blinky.java` | 45 LoC | 1 commit |
| `Ghost.java` | 29 LoC | 1 commit |

Ces classes ne nécessitent pas de refactoring immédiat selon CodeScene.

### Problèmes réels identifiés par CodeScene (cibles prioritaires)

| Classe | Méthode | Problème identifié |
|--------|---------|-------------------|
| `Navigation.java` | `findNearest` | Bumpy Road |
| `Navigation.java` | `addNewTargets` | Complex Conditional |
| `Navigation.java` | `addNewTargets` | Excess Number of Function Arguments |
| `MapParser.java` | `checkMapFormat` | Complex Method |
| `MapParser.java` | `makeGrid` | Excess Number of Function Arguments |
| `MapParser.java` | `addSquare` | Excess Number of Function Arguments |

### Analyse X-Ray de la classe CollisionInteractionMap (Worst Performer)

| Méthode | Fréquence de changement | Lignes de code | Complexité cyclomatique | Problèmes identifiés |
|---------|------------------------|----------------|------------------------|---------------------|
| `collide` | 2 | 20 | 9 | Complex Method |
| `getInheritance` | 1 | 22 | 14 | Complex Method, Bumpy Road Ahead |
| `addHandler` | 1 | 9 | 9 | Complex Method |
| `getMostSpecificClass` | 0 | 10 | 9 | Complex Method |

### Analyse de couplage (Change Coupling)

`LevelFactory.java` présente un fort couplage avec :

- `Blinky.java` (52 %)
- `Ghost.java` (51 %)
- `Clyde.java` (48 %)
- `Pinky.java` (48 %)
- `Level.java` (45 %)
- `Inky.java` (45 %)

### Analyse X-Ray de la classe Launcher

| Méthode | Lignes de code | Complexité |
|---------|----------------|------------|
| `Launcher.main` | 3 LoC | 1 |
| `Launcher.makeLevel` | 8 LoC | 3 |

Ces méthodes ne nécessitent aucun refactoring.

---

## Question 2
CodeScene vous a-t-il donné des recommandations ou des indices sur la façon de refactoriser les cibles proposées ? Comment serait-il possible de procéder avec le refactoring ?

**Réponse:**

Oui, CodeScene fournit des recommandations détaillées accompagnées d'exemples de refactoring pour chaque violation détectée.

### Recommandations spécifiques de CodeScene

| Problème | Méthode affectée | Recommandation |
|----------|------------------|----------------|
| Bumpy Road | `Navigation.findNearest` | **EXTRACT FUNCTION** pour séparer les responsabilités et réduire les « bumps » |
| Complex Conditional | `Navigation.addNewTargets` | **DECOMPOSE CONDITIONAL** en extrayant les conditions dans des méthodes nommées |
| Excess Arguments | `Navigation.addNewTargets` | **INTRODUCE PARAMETER OBJECT** pour regrouper les paramètres |
| Complex Method | `MapParser.checkMapFormat` | **EXTRACT FUNCTION** ou **DECOMPOSE CONDITIONAL** pour réduire la complexité |
| Excess Arguments | `MapParser.makeGrid` | **INTRODUCE PARAMETER OBJECT** |
| Excess Arguments | `MapParser.addSquare` | **INTRODUCE PARAMETER OBJECT** |

### Recommandations pour CollisionInteractionMap

| Problème | Méthode | Recommandation |
|----------|---------|----------------|
| Bumpy Road | `getInheritance` | **EXTRACT FUNCTION** pour séparer les responsabilités et réduire les niveaux d'imbrication |
| Complex Method | `getInheritance`, `getMostSpecificClass`, `collide`, `addHandler` | **EXTRACT FUNCTION** pour diviser la logique en fonctions plus petites et cohésives |
| Many Conditionals | Toutes les méthodes complexes | **DECOMPOSE CONDITIONAL** pour extraire les conditions complexes en méthodes nommées |

### Pistes de refactoring pour LevelFactory (couplage élevé)

Pour réduire le couplage fort de `LevelFactory.java` avec les classes Ghost :

- Appliquer le **principe d'inversion des dépendances**
- Introduire des **interfaces ou abstractions** pour les Ghosts
- Rendre le **pattern Factory plus modulaire**
- Appliquer le **principe de responsabilité unique (SRP)**

### Conclusion générale

Le projet jpacman présente une excellente qualité globale de code.

Les seuls refactorings réellement justifiés concernent :
- `Navigation.java`
- `MapParser.java`
- `CollisionInteractionMap.java`

Ces refactorings sont locaux, ciblés et bien guidés par CodeScene, principalement pour :
- Réduire la complexité
- Simplifier les conditions
- Améliorer la lisibilité
- Réduire le nombre de paramètres

**Aucun refactoring majeur ou urgent n'est requis**, mais ces améliorations permettraient d'augmenter la maintenabilité à long terme.

CodeScene indique également qu'il n'y a pas de recommandations structurelles majeures au-delà des refactorings ciblés sur les méthodes identifiées.
