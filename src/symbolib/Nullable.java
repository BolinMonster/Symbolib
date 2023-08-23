package symbolib;

import symbolib.expression.Expression;
import symbolib.expression.SupportedTypeExpression;
import symbolib.util.ExpressionLoader;
import symbolib.visitor.RationalEvaluationVisitor;

/**
 * Ce programme permet de tester si l'expression contient le mot vide représenter par "1" dans une expression fournit par un fichier XML.
 */
public class Nullable {

	/**
	 * Méthode principale du programme.
	 * Elle charge une expression à partir d'un fichier XML. 
	 * Si l'expression est une expression rationnelle, elle est évalué. 
	 * Elle affiche "vrai" si le langage de l'expression contient le mot vide
	 * (représenté par "1") sinon "false".
	 * @param args Les arguments de ligne de commande. Le nom du fichier XML
	 * contenant l'expression doit être passé en tant qu'argument unique.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Nullable <filename>");
			return;
		}
		final String fileName = args[0];
		if (fileName.endsWith(".xml")) {
			System.err.println(
					"Erreur : Ne pas inclure l'extension \".xml\" dans le nom de fichier. L'extension sera ajoutée automatiquement.");
			return;
		}
		Expression expression = ExpressionLoader.loadExpression(fileName);
		SupportedTypeExpression expressionType = SupportedTypeExpression.findByClass(expression.getClass());
		if (expressionType == null || expressionType != SupportedTypeExpression.RATIONAL) {
			System.out.println("L'expression du fichier " + fileName + " n'est pas une expression rationelle.");
			return;
		}
		RationalEvaluationVisitor rationalEvaluationVisitor = new RationalEvaluationVisitor();
		expression.accept(rationalEvaluationVisitor);
		String regex = rationalEvaluationVisitor.getRegex();
		if (regex.matches("1")) {
			System.out.println("vrai");
		} else {
			System.out.println("faux");
		}
	}

}