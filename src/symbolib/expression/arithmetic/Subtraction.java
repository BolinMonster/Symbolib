package symbolib.expression.arithmetic;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe est la représentation d'une expression de soustraction arithmétique.
 */
public class Subtraction extends ArithmeticBinaryExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private static final String SYMBOL = "-";
	
	/**
	 * Constructeur de la classe Subtraction.
	 * @param leftOperand  L'expression arithmétique de l'opérande gauche.
	 * @param rightOperand L'expression arithmétique de l'opérande droite.
	 */
	public Subtraction(ArithmeticExpression leftOperand, ArithmeticExpression rightOperand) {
		super(leftOperand, rightOperand);
		this.classNumber = Subtraction.count++;
		this.uuid = UUID.randomUUID();
	}

	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitSubtraction(this);
	}
	
	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}
	
	/**
     * Renvoie le nombre d'instances de la classe Subtraction créées.
     * @return Le nombre d'instances de la classe Subtraction créées.
     */
	public static int getCount() {
		return count;
	}
	
	/**
     * Renvoie le numéro de classe de l'instance de la classe Subtraction.
     * @return Le numéro de classe de l'instance de la classe Subtraction.
     */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe Subtraction.
     * @return L'UUID de l'instance de la classe Subtraction.
     */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
     * Renvoie le symbole de l'opération de soustraction.
     * @return Le symbole de l'opération de soustraction.
     */
	public static String getSymbol() {
		return Subtraction.SYMBOL;
	}
	
}
