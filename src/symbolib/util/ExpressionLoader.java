package symbolib.util;

import java.io.File;

import symbolib.builder.ExpressionBuilder;
import symbolib.builder.StdExpressionBuilder;
import symbolib.expression.Expression;
import symbolib.loader.XMLExpressionLoader;

/**
 * Cette classe utilitaire encapsule la logique de restauration d'une expression dans un fichier XML.
 */
public class ExpressionLoader {
	
	/**
	 * Constructeur privée de la classe utilitaire ExpressionLoader
	 */
	private ExpressionLoader() {
		
	}
	
	/**
	 * Charge une expression à partir d'un fichier XML.
	 * @param xmlFileName Le nom du fichier XML (sans l'extension).
	 * @return L'expression chargée à partir du fichier XML.
	 */
	public static Expression loadExpression(String xmlFileName) {
		try {
			final String completeFileName = xmlFileName + ".xml";
			// Vérification si le fichier existe
			File inputFile = new File("resources/xml/" + completeFileName);
			if (!inputFile.exists()) {
				System.err.println("Erreur : Le fichier \"" + completeFileName + "\" n'existe pas.");
				return null;
			}
			ExpressionBuilder expressionBuilder = new StdExpressionBuilder();
			XMLExpressionLoader.load(inputFile, expressionBuilder);
			return expressionBuilder.getExpression();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erreur : Erreur d'E/S lors du chargement de l'expression du fichier(" + xmlFileName + ")  : " + e.getMessage());
		}
		return null;
	}

}
