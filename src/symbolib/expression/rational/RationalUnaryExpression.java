package symbolib.expression.rational;

/**
 * Cette classe abstraite permet de représenter une expression rationelle unaire.
 */
public abstract class RationalUnaryExpression implements RationalExpression {
	
	private RationalExpression operand;
	
	/**
	 * Constructeur de la classe RationalUnaryExpression.
	 * @param operand L'expression rationelle opérant sur cette expression unaire.
	 */
	public RationalUnaryExpression(RationalExpression operand) {
		super();
		this.operand = operand;
	}
	
	/**
	 * Renvoie l'opérande de cette expression unaire.
	 * @return L'opérande de cette expression unaire.
	 */
	public RationalExpression getOperand() {
		return operand;
	}
	
	/**
	 * Définit l'opérande de cette expression unaire.
	 * @param operand La nouvelle opérande de cette expression unaire.
	 */
	public void setOperand(RationalExpression operand) {
		this.operand = operand;
	}
	
}
