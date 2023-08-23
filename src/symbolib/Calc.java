package symbolib;

import symbolib.expression.Expression;
import symbolib.expression.arithmetic.ArithmeticExpression;
import symbolib.expression.functional.FunctionalExpression;
import symbolib.util.ExpressionLoader;
import symbolib.visitor.ArithmeticFunctionalEvaluationVisitor;

/**
 * Ce programme permet d'évaluer une expression arithmétique et fonctionnelle fournit à partir d'une fichier XML contenant l'expression.
 */
public class Calc {
	
	/**
	 * Méthode principale du programme. 
	 * Elle charge une expression à partir d'un fichier XML spécifié en ligne de commande,
	 * évalue l'expression et affiche le résultat.
	 * @param args Les arguments de ligne de commande.
	 * args[0] doit contenir le nom du fichier XML contenant l'expression à évaluer.
	 * args[1] (optionnel) doit contenir la valeur à utiliser pour évaluer une expression de fonction.
	 * Si non fourni, seul l'évaluation de l'expression arithmétique sera effectuée.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: java Calc <filename> [<value>]");
			return;
		}
		String fileName = args[0];
		if (fileName.endsWith(".xml")) {
			System.err.println(
					"Erreur : Ne pas inclure l'extension \".xml\" dans le nom de fichier. L'extension sera ajoutée automatiquement.");
			return;
		}
		final Expression expression = ExpressionLoader.loadExpression(fileName);
		final ArithmeticFunctionalEvaluationVisitor arithmeticFunctionalEvaluationVisitor = new ArithmeticFunctionalEvaluationVisitor();
		if (expression instanceof ArithmeticExpression) {
			expression.accept(arithmeticFunctionalEvaluationVisitor);
			double result = arithmeticFunctionalEvaluationVisitor.getValue();
			System.out.println("Résultat de l'évaluation de l'expression arithmétique : " + result);
		} else if (expression instanceof FunctionalExpression) {
			if (args.length != 2) {
				System.err.println("Erreur : Nombre de paramètres incorrect pour une expression de fonction.");
				return;
			}
			double value;
			try {
				value = Double.parseDouble(args[1]);
				expression.accept(arithmeticFunctionalEvaluationVisitor);
				arithmeticFunctionalEvaluationVisitor.setValue(value);
				double result = arithmeticFunctionalEvaluationVisitor.getValue();
				System.out.println("Résultat de l'évaluation de l'expression fonctionelle : " + result);
			} catch (NumberFormatException e) {
				System.err.println("Erreur : La valeur doit être un nombre valide pour une expression de fonction.");
				return;
			}
		} else {
			System.err.println("Erreur : Type d'expression non pris en charge, seulement arith ou func");
		    throw new UnsupportedOperationException("Type d'expression non pris en charge, seulement arith ou func");
		}
	}
	
}
