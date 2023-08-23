# Symbolib

Symbolib est une bibliothèque écrit en Java qui permet de manipulation d’expressions symboliques.
L'objectif de ce projet est de mettre en pratique nos compétences en architecture logicielle.

## Configuration requise

Avant d'exécuter l'application Symbolib, assurez-vous d'avoir les éléments suivants installés sur votre système :

- [Eclipse](https://www.eclipse.org/) - Un environnement de développement intégré (IDE) pour Java.
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) - Version 8 ou supérieure.

Voici comment utiliser Symbolib dans Eclipse :

1. Clonez ou téléchargez le code source de l'application depuis le référentiel GitHub de Symbolib.
2. Ouvrez Eclipse et importez le projet Symbolib dans votre espace de travail.
3. Naviguez jusqu'aux classes `Expedid.java`, `Calc.java` et `Nullable.java` dans le dossier src du projet.
4. Cliquez avec le bouton droit sur l'une de ces classes et sélectionnez "Run As" > "Java Application" dans le menu contextuel.
5. Un des programmes sera alors exécutée et vous pourrez interagir avec celui-ci via la console.

## Expedid
Expedid est un programme qui permet de choisir un type d’expressions, de saisir une expression sur ce type et sauvegarder ou charger le résultat depuis un fichier. 
L’éditeur fonctionne en ligne de commande à la manière d’un interpréteur très simple. La saisie des expressions se fera selon la notation polonaise inverse (de façon post-fixe). 
L’éditeur disposera pour cela d’une pile contenant les expressions déjà saisies. Cette pile sera manipulée en fonction des saisies de l’utilisateur.

### Utilisation

Le programme Expedid offre un certain nombre de fonctionnalités que vous pouvez utiliser en entrant des commandes dans la console. Voici quelques exemples de commandes disponibles :

- `!help` : Affiche la liste des commandes disponibles.
- `!quit` : Quitte le programme Expedid.
- `!load <nom_du_fichier>` : Charge une expression à partir d'un fichier (sans l'extension=.
- `!type` : Affiche le type d'expression actuellement sélectionné.
- `!type <type>` : Modifie le type d'expression actuellement sélectionné.
- `!stack` : Afficha la pile du type d'expression en cours.
- `!save <nom_du_fichier>` : Sauvegarde l'expression actuellement sélectionnée dans un fichier.
- `<opérateur>` : Crée une nouvelle expression en utilisant un opérateur.
opérateur pour l'expression arithmétique & fonctionnelle :  ~ : Négation, + : Addition,  - : Soustraction, * : Multiplication, / : Division
Symbole pour l'expression rationelle : * : étoile de Kleene, + : Union, · : Concaténation
- `<opérande>` : Crée une nouvelle expression en utilisant un opérateur et deux opérandes.
opérateur pour l'expression arithmétique & fonctionnelle : constante numérique, variable(expression fonctionnelle)
Symbole pour l'expression rationelle : constante littérale ou 1(mot vide)

## Calc

 Calc est un programme qui permet d'évaluer une expression arithmétique ou fonctionnelle à partir d'un fichier XML contenant l'expression.

### Utilisation

La classe Calc offre la possibilité d'évaluer une expression arithmétique à partir d'un fichier. 

```java calc filename [value]```

Le premier paramètre correspond au chemin d’un fichier contenant une expression soit de type arithmétique
(arith), soit de type fonction (function). 
Le second paramètre doit être fourni uniquement dans le second cas uniquement et correspondra à la valeur affectée à la variable ”x” pour le calcul. 
Le programme affiche le résultat de l’évaluation de l’expression dans le terminal le résultat avant de se terminer. 
Tout autre type d’expression déclenchera une erreur.

Lancement avec le script bash : 
```calc filename [value]```

## Nullable

La classe Nullable offre la possibilité de si le langage de l’expression contient le mot vide représentant par "1".

### Utilisation

```java nullable filename```

