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
 * Visiteur pour le calcul d'une expression arithmétique et fonctionelle
 */
public class ArithmeticFunctionalEvaluationVisitor implements ExpressionVisitor {

	private Double value;
	// fonction variable nom
	private String variableName;
	
	/**
	 * Constructeur du visiteur pour l'évaluation expression arithmétique et fonctionelle
	 */
	public ArithmeticFunctionalEvaluationVisitor() {
		this.value = 0.0;
		this.variableName = new String();
	}

	@Override
	public void visitNegation(Negation n) {
		n.getOperand().accept(this);
		value = 0 - getValue();
	}

	@Override
	public void visitAddition(Addition a) {
		a.getLeftOperand().accept(this);
		Double leftValue = getValue();
		a.getRightOperand().accept(this);
		Double rightValue = getValue();
		this.value = leftValue + rightValue;
	}

	@Override
	public void visitSubtraction(Subtraction s) {
		s.getLeftOperand().accept(this);
		Double leftValue = getValue();
		s.getRightOperand().accept(this);
		Double rightValue = getValue();
		this.value = leftValue - rightValue;
	}

	@Override
	public void visitMultiplication(Multiplication m) {
		m.getLeftOperand().accept(this);
		Double leftValue = getValue();
		m.getRightOperand().accept(this);
		Double rightValue = getValue();
		this.value = leftValue * rightValue;
	}

	@Override
	public void visitDivision(Division d) {
		d.getLeftOperand().accept(this);
		Double leftValue = getValue();
		d.getRightOperand().accept(this);
		Double rightValue = getValue();
		this.value = leftValue / rightValue;
	}

	@Override
	public void visitVariable(Variable v) {
		v.accept(this);
		v.setName("x");
		this.variableName = v.getVariableName();
		this.value = v.getValue();
	}

	@Override
	public void visitNumericConstant(NumericConstant nc) {
		this.value = nc.getValue();
	}

	@Override
	public void visitLitteralConstant(LitteralConstant lc) {
		/** Non utilisé car ce visiteur sert seulement pour une expression arithmétique ou fonctionnelle */
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void visitKleeneStar(KleeneStar k) {
		/** Non utilisé car ce visiteur sert seulement pour une expression arithmétique ou fonctionnelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitConcatenation(Concatenation i) {
		/** Non utilisé car ce visiteur sert seulement pour une expression arithmétique ou fonctionnelle */
		throw new UnsupportedOperationException();
	}

	@Override
	public void visitUnion(Union u) {
		/** Non utilisé car ce visiteur sert seulement pour une expression arithmétique ou fonctionnelle */
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @return le nom de la variable
	 */
	public String getVariableName() {
		return variableName;
	}
	
	/**
	 * @return la valeur de la variable
	 */
	public Double getValue() {
		return this.value;
	}
	
	/**
	 * @param value : la valeur d'affection à la variable
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
}
