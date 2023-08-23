package symbolib.visitor;

import symbolib.expression.arithmetic.Addition;
import symbolib.expression.arithmetic.Division;
import symbolib.expression.arithmetic.Multiplication;
import symbolib.expression.arithmetic.Negation;
import symbolib.expression.arithmetic.NumericConstant;
import symbolib.expression.arithmetic.Subtraction;
import symbolib.expression.functional.Variable;
import symbolib.expression.rational.Concatenation;
import symbolib.expression.rational.KleeneStar;
import symbolib.expression.rational.LitteralConstant;
import symbolib.expression.rational.Union;

/**
 * Cette classe représente une visiteur permettant d'afficher l'expression.
 */
public class PrintVisitor implements ExpressionVisitor {
	
	private String message;
	
	/**
	 * Constructeur de la classe PrintVisitor.
	 * Initialise le message de l'expression à vide.
	 */
	public PrintVisitor() {
		this.message = new String();
	}

	@Override
	public void visitNegation(Negation n) {
		n.getOperand().accept(this);
		final String operand = getMessage();
		this.message = operand + " " + Negation.getSymbol();
	}

	@Override
	public void visitAddition(Addition a) {
		a.getLeftOperand().accept(this);
		final String leftOperand = getMessage();
		a.getRightOperand().accept(this);
		final String rightOperand = getMessage();
		this.message = leftOperand + " " + rightOperand + " " + Addition.getSymbol();
	}

	@Override
	public void visitSubtraction(Subtraction s) {
		s.getLeftOperand().accept(this);
		final String leftOperand = getMessage();
		s.getRightOperand().accept(this);
		final String rightOperand = getMessage();
		this.message = leftOperand + " " + rightOperand + " " + Subtraction.getSymbol();
	}

	@Override
	public void visitMultiplication(Multiplication m) {
		m.getLeftOperand().accept(this);
		final String leftOperand = getMessage();
		m.getRightOperand().accept(this);
		final String rightOperand = getMessage();
		this.message = leftOperand + " " + rightOperand + " " + Multiplication.getSymbol();
	}

	@Override
	public void visitDivision(Division d) {
		d.getLeftOperand().accept(this);
		final String leftOperand = getMessage();
		d.getRightOperand().accept(this);
		final String rightOperand = getMessage();
		this.message = leftOperand + " " + rightOperand + " " + Division.getSymbol();
	}

	@Override
	public void visitVariable(Variable v) {
		// v.accept(this);
		this.message = v.getVariableName();
	}

	@Override
	public void visitNumericConstant(NumericConstant nc) {
		this.message = Double.toString(nc.getValue());
	}

	@Override
	public void visitLitteralConstant(LitteralConstant lc) {
		// lc.accept(this);
		this.message = lc.getValue();
	}
	
	@Override
	public void visitKleeneStar(KleeneStar k) {
		k.getOperand().accept(this);
		final String operand = getMessage();
		this.message = operand + " " + KleeneStar.getSymbol();
	}
	
	@Override
	public void visitConcatenation(Concatenation i) {
		i.getLeftOperand().accept(this);
		String leftOperand = getMessage();
		i.getRightOperand().accept(this);
		String rightOperand = getMessage();
		this.message = leftOperand + " " + rightOperand + " " + Concatenation.getSymbol();
	}

	@Override
	public void visitUnion(Union u) {
		u.getLeftOperand().accept(this);
		final String leftOperand = getMessage();
		u.getRightOperand().accept(this);
		final String rightOperand = getMessage();
		this.message = leftOperand + " " + rightOperand + " " + Union.getSymbol();
	}
	
	/**
	 * Renvoie le message
	 * @return le message
	 */
	public String getMessage() {
		return this.message;
	}
	
}
