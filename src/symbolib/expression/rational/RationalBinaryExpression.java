package symbolib.expression.rational;

/**
 * Cette classe abstraite permet de représenter une expression rationelle binaire.
 */
public abstract class RationalBinaryExpression implements RationalExpression {
	
	private RationalExpression leftOperand;
	private RationalExpression rightOperand;
	
	/**
	 * Constructeur de la classe RationalBinaryExpression.
	 * @param leftOperand  L'opérande gauche de l'expression binaire.
	 * @param rightOperand L'opérande droite de l'expression binaire.
	 */
	public RationalBinaryExpression(RationalExpression leftOperand, RationalExpression rightOperand) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	/**
	 * Renvoie l'opérande gauche de l'expression binaire.
	 * @return L'opérande gauche de l'expression binaire.
	 */
	public RationalExpression getLeftOperand() {
		return leftOperand;
	}
	
	/**
	 * Renvoie l'opérande droite de l'expression binaire.
	 * @return L'opérande droite de l'expression binaire.
	 */
	public RationalExpression getRightOperand() {
		return rightOperand;
	}
	
	/**
	 * Définit l'opérande gauche de l'expression binaire.
	 * @param leftOperand La nouvelle opérande gauche de l'expression binaire.
	 */
	public void setLeftOperand(RationalExpression leftOperand) {
		this.leftOperand = leftOperand;
	}
	
	/**
	 * Définit l'opérande droite de l'expression binaire.
	 * @param rightOperand La nouvelle opérande droit de l'expression binaire.
	 */
	public void setRightOperand(RationalExpression rightOperand) {
		this.rightOperand = rightOperand;
	}
	
}
