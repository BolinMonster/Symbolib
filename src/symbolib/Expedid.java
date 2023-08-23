package symbolib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import symbolib.expression.Expression;
import symbolib.expression.SupportedTypeExpression;
import symbolib.expression.arithmetic.Addition;
import symbolib.expression.arithmetic.ArithmeticExpression;
import symbolib.expression.arithmetic.Division;
import symbolib.expression.arithmetic.Multiplication;
import symbolib.expression.arithmetic.Negation;
import symbolib.expression.arithmetic.NumericConstant;
import symbolib.expression.arithmetic.Subtraction;
import symbolib.expression.functional.Variable;
import symbolib.expression.rational.Concatenation;
import symbolib.expression.rational.KleeneStar;
import symbolib.expression.rational.LitteralConstant;
import symbolib.expression.rational.RationalExpression;
import symbolib.expression.rational.Union;
import symbolib.util.ExpressionLoader;
import symbolib.util.ExpressionSaver;
import symbolib.visitor.PrintVisitor;

/**
 * Expedid est un programme qui permet de manipuler des expressions arithmétiques, fonctionelles et rationnelles.
 * Il prend en charge les opérations rationnelles telles que la concaténation, l'union et la
 * fermeture étoile, les opérations arithmétiques/fonctionelles avec addition, soustraction, multiplication, division, constante numérique et variable(que pour fonctionnelle). 
 * Le programme permet de charger des expressions à partir de fichiers XML, de les
 * afficher, de les empiler et de les dépiler. Le type d'expression courant est défini par l'utilisateur
 * et peut être modifié à tout moment. Les commandes spéciales incluent !help, !quit, !load, !stack et !type.
 */
public class Expedid {
	
	/**
	 * La méthode principale qui est appelée au démarrage de l'application.
	 * @param args : les arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		Expedid.printHomescreen();
		SupportedTypeExpression currentExpressionType = null;		
		Map<SupportedTypeExpression, Map<String, Class<? extends Expression>>> symbolToClassMap = new HashMap<SupportedTypeExpression, Map<String, Class<? extends Expression>>>() {
			private static final long serialVersionUID = 1L;
			{
				put(SupportedTypeExpression.ARITHMETIC, new HashMap<String, Class<? extends Expression>>() {
					private static final long serialVersionUID = 1L;
					{
						put(Negation.getSymbol(), Negation.class);
						put(Addition.getSymbol(), Addition.class);
						put(Subtraction.getSymbol(), Subtraction.class);
						put(Multiplication.getSymbol(), Multiplication.class);
						put(Division.getSymbol(), Division.class);
					}
				});
				put(SupportedTypeExpression.FUNCTIONAL, new HashMap<String, Class<? extends Expression>>() {
					private static final long serialVersionUID = 1L;
					{
						put(Negation.getSymbol(), Negation.class);
						put(Addition.getSymbol(), Addition.class);
						put(Subtraction.getSymbol(), Subtraction.class);
						put(Multiplication.getSymbol(), Multiplication.class);
						put(Division.getSymbol(), Division.class);
					}
				});
				put(SupportedTypeExpression.RATIONAL, new HashMap<String, Class<? extends Expression>>() {
					private static final long serialVersionUID = 1L;
					{
						put(KleeneStar.getSymbol(), KleeneStar.class);
						put(Concatenation.getSymbol(), Concatenation.class);
						put(Union.getSymbol(), Union.class);
					}
				});
			}
		};
		Map<SupportedTypeExpression, Stack<Expression>> expressionStackMap = new HashMap<SupportedTypeExpression, Stack<Expression>>() {
			private static final long serialVersionUID = 1L;
			{
				put(SupportedTypeExpression.ARITHMETIC, new Stack<Expression>());
				put(SupportedTypeExpression.FUNCTIONAL, new Stack<Expression>());
				put(SupportedTypeExpression.RATIONAL, new Stack<Expression>());
			}
		};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		boolean run = true;
		while (run) {
			System.out.print("> ");
			System.out.flush();
			try {
				input = br.readLine();
				input = input.trim();
			} catch (IOException e1) {
				e1.printStackTrace();
				input = null;
				continue;
			}
			if (input.equals("!help")) {
				Expedid.displayCommands();
				continue;
			} else if (input.equals("!quit")) {
				run = false;
				continue;
			} else if (input.startsWith("!load")) {
				// Vérification si l'input contient un espace après "!load"
				final String[] parts = input.split(" ");
				if (parts.length < 2 || parts[1].isEmpty()) {
					System.out.println("Usage : !load <nom_du_fichier>");
				} else {
					// Si l'input commence par !load suivi d'un argument, on charge une
					// Expression depuis un fichier XML
					final String fileName = parts[1];
					Expression expression = ExpressionLoader.loadExpression(fileName);
					if (expression != null) {
						if (SupportedTypeExpression.isValidClassType(expression.getClass())) {
							currentExpressionType = SupportedTypeExpression.findByClass(expression.getClass());
							expressionStackMap.get(currentExpressionType).pop();
							expressionStackMap.get(currentExpressionType).push(expression);
							System.out.println("L'expression du fichier " + fileName + " a été chargé.");
						} else {
							System.out.println("Erreur : le type de l'expression chargée est invalide");
						}
					} else {
						System.out.println("Erreur : Impossible de charger l'expression depuis le fichier : " + fileName);
					}
				}
				continue;
			} else if (input.equals("!stack")) {
				if (expressionStackMap.get(currentExpressionType).size() == 0) {
					System.out.println("La pile est vide.");
				} else {
					printStackWithPrint(expressionStackMap.get(currentExpressionType), currentExpressionType);
				}
				continue;
			} else if (input.equals("!type")) {
				// Si l'input est !type, donc ça affiche les types d'Expressions supportés
				printExpressionTypes();
				continue;
			} else if (input.startsWith("!type ")) {
				// Si l'input commence par !type suivi d'un argument, donc ça change le type d'expression en cours
				final String[] parts = input.split(" ");
				final String newType = parts[1];
				final SupportedTypeExpression ste = SupportedTypeExpression.findByAbbreviation(newType);
				if (SupportedTypeExpression.isValidStringType(newType)) {
					currentExpressionType = ste;
					System.out.println("Le type d'expression a été modifié en : [" + currentExpressionType.getAbbreviation() + "]");
				} else {
					System.out.println("Erreur : Type d'expression invalide : [" + newType + "] (!type : pour consulter les types disponibles)");
				}
				continue;
			}
			if (currentExpressionType == null && SupportedTypeExpression.findByAbbreviation(input) == null) {
				System.out.println("Erreur : Type courant non définie, veuillez utiliser !type <type>");
				continue;
			}
			if (expressionStackMap.get(currentExpressionType).size() == 0) {
				System.out.println("La pile est vide.");
			}
			if (input.startsWith("!save")) {
				// Vérification si l'input contient un espace après "!save"
				final String[] parts = input.split(" ");
				if (parts.length < 2 || parts[1].isEmpty()) {
					System.out.println("Usage : !save <nom_du_fichier> (sans l'extension)");
				} else {
					// Si l'input commence par !save suivi d'un argument, donc ça enregistre l'expression en sommet de pile dans un fichier
					final String fileName = parts[1];
					if (expressionStackMap.get(currentExpressionType).isEmpty()) {
						System.out.println("Erreur : La pile est vide. Impossible de sauvegarder.");
					} else {
						Expression NodeExpression = expressionStackMap.get(currentExpressionType).peek();
						ExpressionSaver.saveExpression(fileName, NodeExpression, currentExpressionType);
					}
				}
				continue;
			} else if (input.equals("!clear")) {
				// Si l'input est !clear, donc ça vidange la pile
				final Stack<Expression> stack = expressionStackMap.get(currentExpressionType);
				stack.clear();
				System.out.println("La pile a été vidée.");
				continue;
			}
			if (currentExpressionType == SupportedTypeExpression.ARITHMETIC || currentExpressionType == SupportedTypeExpression.FUNCTIONAL) {
				if (input.matches("-?\\d+(\\.\\d+)?")) {
					// Si l'input est un nombre, donc c'est une constante
					Double value;
					try {
						value = Double.parseDouble(input);
						final Expression constant = new NumericConstant(value);
						expressionStackMap.get(currentExpressionType).push(constant);
						printStackWithPrint(expressionStackMap.get(currentExpressionType), currentExpressionType);
					} catch (NumberFormatException e) {
						// e.printStackTrace();
						System.out.println("Erreur : L'entrée saisie n'est pas un nombre valide : " + input);
					}
				} else if (input.matches("x") || input.matches("[a-zA-Z]+") && currentExpressionType == SupportedTypeExpression.FUNCTIONAL) {
					// Si l'input est une lettre ou un mot
					final Expression variable = new Variable(input, 0.0);
					expressionStackMap.get(currentExpressionType).push(variable);
					printStackWithPrint(expressionStackMap.get(currentExpressionType), currentExpressionType);
				} else if (input.equals(Negation.getSymbol())) {
					// Si l'input est un opérateur unaire
					if (expressionStackMap.get(currentExpressionType).size() >= 1) {
						final ArithmeticExpression operand = (ArithmeticExpression) expressionStackMap.get(currentExpressionType).pop();
						final Expression result = new Negation(operand);
						expressionStackMap.get(currentExpressionType).push(result);
						printStackWithPrint(expressionStackMap.get(currentExpressionType), currentExpressionType);
					} else {
						System.out.println("Erreur : La pile ne contient pas assez d'opérande pour effectuer cette opération.");
					}
				} else if (input.equals(Addition.getSymbol()) || input.equals(Subtraction.getSymbol())
						|| input.equals(Multiplication.getSymbol()) || input.equals(Division.getSymbol())) {
					if (expressionStackMap.get(currentExpressionType).size() >= 2) {
						final Class<? extends ArithmeticExpression> clazz = (Class<? extends ArithmeticExpression>) symbolToClassMap.get(currentExpressionType).get(input);
						try {
							final Constructor<? extends ArithmeticExpression> constructor = clazz
									.getConstructor(ArithmeticExpression.class, ArithmeticExpression.class);
							/*
							 * Notation Polonaise Inversée les opérateurs sont placés après les opérandes,
							 * ce qui signifie que l'opérande de droite (right operand) est le premier
							 * opérande (premier à être empilé) et l'opérande de gauche (left operand) est
							 * le deuxième opérande (deuxième à être empilé)
							 */
							final ArithmeticExpression leftOperand = (ArithmeticExpression) expressionStackMap
									.get(currentExpressionType).pop();
							final ArithmeticExpression rightOperand = (ArithmeticExpression) expressionStackMap
									.get(currentExpressionType).pop();
							final ArithmeticExpression result = constructor.newInstance(leftOperand, rightOperand);
							if (result != null) {
								expressionStackMap.get(currentExpressionType).push(result);
								printStackWithPrint(expressionStackMap.get(currentExpressionType), currentExpressionType);
							} else {
								System.out.println("Erreur : Une erreur est survenue, lors de cet opération binaire (" + input + ").");
							}
						} catch (ReflectiveOperationException e) {
							e.printStackTrace();
							System.out.println("Erreur : Une erreur est survenue lors de l'exécution de l'opération d'instanciation du constructeur, veuillez réessayer.");
						}
					} else {
						System.out.println("Erreur : La pile ne contient pas assez d'opérandes pour effectuer cette opération.");
					}
				} else {
					System.out.println("Erreur : Saisie invalide - veuillez entrer une constante (un chiffre pour l'arithmétique), une variable (si le type est 'func'), le symbole '~' pour la négation, ou les opérateurs classiques '+', '*', '/' et '-' pour les opérations arithmétiques.");
				}
			} else if (currentExpressionType == SupportedTypeExpression.RATIONAL) {
				if (input.matches("^[a-z]{1,20}|1$")) {
					final RationalExpression rationalExpression = new LitteralConstant(input);
					expressionStackMap.get(currentExpressionType).push(rationalExpression);
					printStackWithPrint(expressionStackMap.get(currentExpressionType), currentExpressionType);
				} else if (input.equals(KleeneStar.getSymbol())) {
					if (expressionStackMap.get(currentExpressionType).size() >= 1) {
						final RationalExpression operand = (RationalExpression) expressionStackMap.get(currentExpressionType).pop();
						final Expression result = new KleeneStar(operand);
						expressionStackMap.get(currentExpressionType).push(result);
						printStackWithPrint(expressionStackMap.get(currentExpressionType), currentExpressionType);
					} else {
						System.out.println("Erreur : La pile ne contient pas assez d'opérande pour effectuer cette opération.");
					}
				} else if (input.equals(Concatenation.getSymbol()) || input.equals(Union.getSymbol())) {
					if (expressionStackMap.get(currentExpressionType).size() >= 2) {
						final Class<? extends RationalExpression> clazz = (Class<? extends RationalExpression>) symbolToClassMap.get(currentExpressionType).get(input);
						try {
							final Constructor<? extends RationalExpression> constructor = clazz
									.getConstructor(RationalExpression.class, RationalExpression.class);
							final RationalExpression leftOperand = (RationalExpression) expressionStackMap
									.get(currentExpressionType).pop();
							final RationalExpression rightOperand = (RationalExpression) expressionStackMap
									.get(currentExpressionType).pop();
							final RationalExpression result = constructor.newInstance(leftOperand, rightOperand);
							if (result != null) {
								expressionStackMap.get(currentExpressionType).push(result);
								printStackWithPrint(expressionStackMap.get(currentExpressionType), currentExpressionType);
							} else {
								System.out.println("Erreur : Une erreur est survenue, lors de cet opération binaire (" + input + ").");
							}
						} catch (ReflectiveOperationException e) {
							e.printStackTrace();
							System.out.println("Erreur : Une erreur est survenue lors de l'exécution de l'opération d'instanciation du constructeur, veuillez réessayer.");
						}
					} else {
						System.out.println("Erreur : La pile ne contient pas assez d'opérandes pour effectuer cette opération.");
					}
				} else {
					System.out.println("Erreur : Saisie invalide - veuillez entrer une constante (composée de mots sur l'alphabet [a-z] et de longueur comprise entre 1 et 20, ou le mot vide '1'), '*', '+' pour l'opération d'intersection, '·' pour l'opération d'union, ou '~' pour la négation.");
				}
			} else {
				System.out.println("Erreur : Commande invalide. Tapez !help pour afficher la liste des commandes.");
			}
		}
		Stack<Expression> stack = null;
		for (SupportedTypeExpression key : expressionStackMap.keySet()) {
			stack = expressionStackMap.get(key);
			stack.clear();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			br = null;
		}
	}
	
	/**
	 * Affiche les types d'expressions supportés.
	 * Les types d'expressions sont affichés avec leur abréviation et leur description.
	 * @return void
	 */
	private static void printExpressionTypes() {
		System.out.println("Types d'expressions supportés :");
		for (SupportedTypeExpression type : SupportedTypeExpression.values()) {
			if (type == SupportedTypeExpression.NONE) continue;
			System.out.println("- " + type.getAbbreviation() + " : " + type.getDescription());
		}
	}
	
	/**
	 * Méthode privée qui affiche le contenu d'une pile d'expressions avec leur type d'expression courant.
	 * Les expressions sont affichées dans l'ordre inverse de leur position dans la pile, avec leur abréviation
	 * de type d'expression entre crochets, suivi du message d'affichage généré par le visiteur d'impression.
	 * @param stack La pile d'expressions à afficher.
	 * @param currentExpressionType Le type d'expression courant à afficher entre crochets.
	 */
	private static void printStackWithPrint(Stack<Expression> stack, SupportedTypeExpression currentExpressionType) {
		final PrintVisitor printVisitor = new PrintVisitor();
		final int size = stack.size();
		for (int i = size - 1; i >= 0; i--) {
			Expression expression = stack.get(i);
			expression.accept(printVisitor);
			System.out.println(i + " : " + "[" + currentExpressionType.getAbbreviation() + "]" + " " + printVisitor.getMessage());
		}
		System.out.println(" [" + currentExpressionType.getAbbreviation() + "]");
	}
	
	/**
	 * Affiche la liste des commandes disponibles dans l'application.
	 * Chaque commande est affichée avec une brève description de son utilité.
	 * @return void
	 */
	private static void displayCommands() {
		System.out.println("Commandes disponibles :");
		System.out.println("!type  - Permet de lister les types d'expressions pris en charge.");
		System.out.println("!stack - Permet de d'afficher la pile du type d'expression en cours.");
		System.out.println("!quit  - Permet de quitter l'application.");
		System.out.println("!load  - Permet de charger un fichier contenant une expression.");
		System.out.println("!save  - Permet de sauvegarder un fichier contenant une expression.");
		System.out.println("!help  - Affiche la liste des commandes disponibles.");
	}
	
	/**
	 * Affiche l'écran d'accueil de l'application Symbolib.
	 * Cet écran d'accueil comprend un en-tête stylisé avec le nom de l'application,
	 * une brève description de celle-ci, ainsi qu'un menu contenant les commandes disponibles.
	 * @return void
	 */
	private static void printHomescreen() {
		System.out.println(" _____                 _           _ _ _     ");
		System.out.println("/ ____|               | |         | (_) |    ");
		System.out.println("| (___  _   _ _ __ ___ | |__   ___ | |_| |__  ");
		System.out.println("\\___ \\| | | | '_ ` _ \\| '_ \\ / _ \\| | | '_ \\ ");
		System.out.println(" ____) | |_| | | | | | | |_) | (_) | | | |_) |");
		System.out.println("|_____/ \\__, |_| |_| |_|_.__/ \\___/|_|_|_.__/ ");
		System.out.println("         __/ |                                ");
		System.out.println("        |___/                                 ");
		System.out.println();
		System.out.println("Bienvenue dans l'application Symbolib!");
		System.out.println("Une bibliothèque de manipulation d’expressions symboliques.");
		System.out.println();
		System.out.println("******************************************************");
		System.out.println("*                                                    *");
		System.out.println("*                       MENU                         *");
		System.out.println("*                                                    *");
		System.out.println("******************************************************");
		System.out.println();
		Expedid.displayCommands();
		System.out.println();
	}
}
