package symbolib.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

import symbolib.expression.Expression;
import symbolib.expression.SupportedTypeExpression;
import symbolib.loader.Element;
import symbolib.visitor.XMLSerializerVisitor;

/**
 * Cette classe utilitaire encapsule la logique de sauvegarde d'une expression dans un fichier XML.
 */
public class ExpressionSaver {
	
	/**
	 * Constructeur privée de la classe utilitaire ExpressionSaver
	 */
	private ExpressionSaver() {
		
	}
	
	/**
	 * Sauvegarde une expression dans un fichier XML.
	 * @param xmlFileName Le nom du fichier XML dans lequel sauvegarder l'expression.
	 * @param expression L'expression à sauvegarder.
	 * @param currentExpressionType Le type d'expression actuel.
	 */
	public static void saveExpression(String xmlFileName, Expression expression, SupportedTypeExpression currentExpressionType) {
		// Vérification du format du nom de fichier
		if (!xmlFileName.matches("[a-zA-Z0-9_-]+")) {
			System.err.println(
					"Erreur : Format du nom de fichier incorrect. Le nom de fichier ne peut contenir que des lettres, des chiffres, des tirets bas (_) ou des tirets (-).");
			return;
		}
		// Vérification de l'extension du nom de fichier
		if (xmlFileName.endsWith(".xml")) {
			System.err.println(
					"Erreur : Ne pas inclure l'extension \".xml\" dans le nom de fichier. L'extension sera ajoutée automatiquement.");
			return;
		}
		final boolean autoFlush = true;
		final Charset encoding = Charset.forName("UTF-8");
		try {
			// Vérification si le dossier "resources/xml" existe, sinon le créer
			/*File resourcesDir = new File("resources/xml");
			if (!resourcesDir.exists()) {
				boolean created = resourcesDir.mkdirs();
				if (!created) {
					System.err.println("Erreur : Impossible de créer le dossier \"resources/xml\".");
					return;
				}
			}*/
			xmlFileName = "resources/xml/" + xmlFileName + ".xml";
			File outputFile = new File(xmlFileName);
			// Vérification si le fichier existe déjà
			if (outputFile.exists()) {
				System.err.println("Erreur : Le fichier \"" + xmlFileName
						+ " existe déjà. Veuillez choisir un autre nom de fichier.");
				return;
			}
			final FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
			final PrintStream output = new PrintStream(fileOutputStream, autoFlush, encoding.toString());
			final XMLSerializerVisitor xmlSerializeVisitor = new XMLSerializerVisitor(output);
			final String fileNamePathToDocumentTypeDefinition = "resources/xml/symbolib.dtd";
			xmlSerializeVisitor.writeXMLHeader("1.0", encoding.toString());
			xmlSerializeVisitor.writeDocumentTypeDefinition("symbolib", fileNamePathToDocumentTypeDefinition);
			final Element rootElement = Element.ROOT;
			final Element expressionElement = Element.EXPRESSION;
	        final Element typeExpressionElement = Element.findByTag(currentExpressionType.getAbbreviation());
	        output.print("<" + rootElement.getTag() + ">\n");
	        output.print("<" + expressionElement.getTag() + ">\n");
	        output.print("<" + typeExpressionElement.getTag() + ">\n");
			expression.accept(xmlSerializeVisitor);
			output.print("</" + typeExpressionElement.getTag() + ">\n");
			output.print("</" + expressionElement.getTag() + ">\n");
			output.print("</" + rootElement.getTag() + ">\n");
			output.close();
			System.out.println("Expression sauvegardée dans le fichier : " + xmlFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Erreur : Fichier non trouvé lors de la sauvegarde de l'expression dans un fichier : "
					+ e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erreur : Erreur d'E/S lors de la sauvegarde de l'expression dans un fichier("
					+ xmlFileName + ") : " + e.getMessage());
		}
	}

}
