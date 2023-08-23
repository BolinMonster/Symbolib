package symbolib.visitor;

import java.io.PrintStream;

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
import symbolib.loader.Element;

import java.util.Map;

/**
 * Cette classe implémente l'interface ExpressionVisitor pour sérialiser une expression en XML.
*/
public class XMLSerializerVisitor implements ExpressionVisitor {

	private PrintStream output;
	private int indentationLevel;
	
	/**
	 * Constructeur de la classe XMLSerializerVisitor avec en paramètre le flux d'écriture spécifié et un niveau d'indentation initial de 0.
	 * @param output le flux d'écriture pour la sortie XML
	*/
	public XMLSerializerVisitor(PrintStream output) {
		this.output = output;
		this.indentationLevel = 0;
	}

	@Override
	public void visitNegation(Negation n) {
		final Element element = Element.NEGATION;
		final Map<String, String> attributes = element.getAttributes();
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		final ArithmeticExpression operand = n.getOperand();
		operand.accept(this);
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitAddition(Addition a) {
		final Element element = Element.ADDITION;
		final Map<String, String> attributes = element.getAttributes();
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		final ArithmeticExpression leftOperand = a.getLeftOperand();
		final ArithmeticExpression rightOperand = a.getRightOperand();
		leftOperand.accept(this);
		rightOperand.accept(this);
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitSubtraction(Subtraction s) {
		final Element element = Element.SUBTRACTION;
		final Map<String, String> attributes = element.getAttributes();
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		final ArithmeticExpression leftOperand = s.getLeftOperand();
		final ArithmeticExpression rightOperand = s.getRightOperand();
		leftOperand.accept(this);
		rightOperand.accept(this);
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitMultiplication(Multiplication m) {
		final Element element = Element.MULTIPLICATION;
		final Map<String, String> attributes = element.getAttributes();
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		final ArithmeticExpression leftOperand = m.getLeftOperand();
		final ArithmeticExpression rightOperand = m.getRightOperand();
		leftOperand.accept(this);
		rightOperand.accept(this);
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitDivision(Division d) {
		final Element element = Element.DIVISION;
		final Map<String, String> attributes = element.getAttributes();
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		final ArithmeticExpression leftOperand = d.getLeftOperand();
		final ArithmeticExpression rightOperand = d.getRightOperand();
		leftOperand.accept(this);
		rightOperand.accept(this);
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitVariable(Variable v) {
		final Element element = Element.VARIABLE;
		final Map<String, String> attributes = element.getAttributes();
		attributes.put("name", v.getVariableName());
		attributes.put("value", Double.toString(v.getValue()));
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitNumericConstant(NumericConstant nc) {
		final Element element = Element.NUMERIC_CONSTANT;
		final Map<String, String> attributes = element.getAttributes();
		attributes.put("value", Double.toString(nc.getValue()));
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitLitteralConstant(LitteralConstant lc) {
		final Element element = Element.LITTERAL_CONSTANT;
		final Map<String, String> attributes = element.getAttributes();
		attributes.put("value", lc.getValue());
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitKleeneStar(KleeneStar k) {
		final Element element = Element.KLEENE_STAR;
		final Map<String, String> attributes = element.getAttributes();
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		final RationalExpression operand = k.getOperand();
		operand.accept(this);
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitConcatenation(Concatenation i) {
		final Element element = Element.CONCATENATION;
		final Map<String, String> attributes = element.getAttributes();
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		final RationalExpression leftOperand = i.getLeftOperand();
		final RationalExpression rightOperand = i.getRightOperand();
		leftOperand.accept(this);
		rightOperand.accept(this);
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}

	@Override
	public void visitUnion(Union u) {
		final Element element = Element.UNION;
		final Map<String, String> attributes = element.getAttributes();
		final StringBuilder sb = new StringBuilder();
		this.incrementIndent();
		ident(sb);
		sb.append("<").append(element.getTag());
		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
			}
		}
		final RationalExpression leftOperand = u.getLeftOperand();
		final RationalExpression rightOperand = u.getRightOperand();
		leftOperand.accept(this);
		rightOperand.accept(this);
		sb.append(" " + "/>\n");
		this.output.print(sb.toString());
		this.decrementIndent();
	}
	
	/**
	 * Écrit l'en-tête XML dans le flux de sortie avec la version et l'encodage spécifiés.
	 * @param version  La version de l'en-tête XML.
	 * @param encoding L'encodage de l'en-tête XML.
	 */
	public void writeXMLHeader(String version, String encoding) {
		final String header = String.format("<?xml version=\"%s\" encoding=\"%s\"?>\n", version, encoding);
		this.output.print(header);
	}
	
	/**
	 * Écrit la définition de type de document (DTD) dans le flux de sortie avec le nom et le chemin de fichier spécifiés.
	 * @param name     Le nom de la définition de type de document (DTD).
	 * @param filePath Le chemin du fichier de la définition de type de document (DTD).
	 */
	public void writeDocumentTypeDefinition(String name, String filePath) {
		final String dtd = String.format("<!DOCTYPE %s SYSTEM \"%s\">\n", name, filePath);
		this.output.print(dtd);
	}
	
	/**
	 * Incrémente le niveau d'indentation.
	 */
	private void incrementIndent() {
		indentationLevel++;
	}
	
	/**
	 * Décrémente le niveau d'indentation.
	 * @return void
	 */
	private void decrementIndent() {
		indentationLevel--;
	}
	
	/**
	 * Applique l'indentation en ajoutant des espaces dans le flux de sortie en fonction du niveau d'indentation actuel.
	 * @return void
	 */
	private void ident(StringBuilder sb) {
		final int nbSpace = 4;
		if (sb != null && this.indentationLevel > 0) {
			for (int i = 0; i < this.indentationLevel * nbSpace; i++) {
				sb.append(" ");
			}
		}
	}

}
