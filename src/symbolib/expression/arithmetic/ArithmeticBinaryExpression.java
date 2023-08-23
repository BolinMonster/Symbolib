package symbolib.expression.arithmetic;

/**
 * Cette classe abstraite permet de représenter une expression arithmétique binaire.
 */
public abstract class ArithmeticBinaryExpression implements ArithmeticExpression {

	private ArithmeticExpression leftOperand;
	private ArithmeticExpression rightOperand;

	/**
	 * Constructeur de la classe ArithmeticBinaryExpression.
	 * @param leftOperand  L'opérande gauche de l'expression binaire.
	 * @param rightOperand L'opérande droite de l'expression binaire.
	 */
	public ArithmeticBinaryExpression(ArithmeticExpression leftOperand, ArithmeticExpression rightOperand) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	/**
	 * Renvoie l'opérande gauche de l'expression binaire.
	 * @return L'opérande gauche de l'expression binaire.
	 */
	public ArithmeticExpression getLeftOperand() {
		return leftOperand;
	}

	/**
	 * Renvoie l'opérande droite de l'expression binaire.
	 * @return L'opérande droite de l'expression binaire.
	 */
	public ArithmeticExpression getRightOperand() {
		return rightOperand;
	}

	/**
	 * Définit l'opérande gauche de l'expression binaire.
	 * @param leftOperand La nouvelle opérande gauche de l'expression binaire.
	 */
	public void setLeftOperand(ArithmeticExpression leftOperand) {
		this.leftOperand = leftOperand;
	}

	/**
	 * Définit l'opérande droit de l'expression binaire.
	 * @param rightOperand La nouvelle opérande droit de l'expression binaire.
	 */
	public void setRightOperand(ArithmeticExpression rightOperand) {
		this.rightOperand = rightOperand;
	}
	
}
