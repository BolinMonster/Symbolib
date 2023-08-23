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
 * Visiteur pour l'évaluation d'une expression rationelle
 */
public class RationalEvaluationVisitor implements ExpressionVisitor {

	private String regex;
	
	/**
	 * Constructeur de la classe RationalEvaluationVisitor.
	 * Initialise la regex(constante littérale) à vide.
	 */
	public RationalEvaluationVisitor() {
		this.regex = new String();
	}

	@Override
	public void visitNegation(Negation n) {
		/** Non utilisé car ce visiteur sert seulement pour une expression rationelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitAddition(Addition a) {
		/** Non utilisé car ce visiteur sert seulement pour une expression rationelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitSubtraction(Subtraction s) {
		/** Non utilisé car ce visiteur sert seulement pour une expression rationelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitMultiplication(Multiplication m) {
		/** Non utilisé car ce visiteur sert seulement pour une expression rationelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitDivision(Division d) {
		/** Non utilisé car ce visiteur sert seulement pour une expression rationelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitVariable(Variable v) {
		/** Non utilisé car ce visiteur sert seulement pour une expression rationelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitNumericConstant(NumericConstant nc) {
		/** Non utilisé car ce visiteur sert seulement pour une expression rationelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitLitteralConstant(LitteralConstant lc) {
		this.regex = lc.getValue();
	}

	@Override
	public void visitKleeneStar(KleeneStar k) {
		k.getOperand().accept(this);
	}

	@Override
	public void visitConcatenation(Concatenation i) {
		i.getLeftOperand().accept(this);
		String leftValue = getRegex();
		i.getRightOperand().accept(this);
		String rightValue = getRegex();
		this.regex = leftValue + rightValue;
	}

	@Override
	public void visitUnion(Union u) {
		u.getLeftOperand().accept(this);
		String leftValue = getRegex();
		u.getRightOperand().accept(this);
		String rightValue = getRegex();
		this.regex = leftValue + rightValue;
	}
	
	/**
	 * Renvoie la constante littérale qui est une expression régulière.
	 * @return la constante littérale qui est une expression régulière.
	 */
	public String getRegex() {
		return regex;
	}

}
