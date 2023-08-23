package symbolib.expression.arithmetic;

/**
 * Cette classe abstraite permet de représenter une expression arithmétique unaire.
 */
public abstract class ArithmeticUnaryExpression implements ArithmeticExpression {

	private ArithmeticExpression operand;

	/**
	 * Constructeur de la classe ArithmeticUnaryExpression.
	 * @param operand L'expression arithmétique opérant sur cette expression unaire.
	 */
	public ArithmeticUnaryExpression(ArithmeticExpression operand) {
		super();
		this.operand = operand;
	}

	/**
	 * Renvoie l'opérande de cette expression unaire.
	 * @return L'opérande de cette expression unaire.
	 */
	public ArithmeticExpression getOperand() {
		return operand;
	}

	/**
	 * Définit l'opérande de cette expression unaire.
	 * @param operand La nouvelle opérande de cette expression unaire.
	 */
	public void setOperand(ArithmeticExpression operand) {
		this.operand = operand;
	}
	
}
