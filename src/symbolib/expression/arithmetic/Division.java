package symbolib.expression.arithmetic;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe est la représentation d'une expression de la division arithmétique.
 */
public class Division extends ArithmeticBinaryExpression {

	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private static final String SYMBOL = "/";
	
	/**
	 * Constructeur de la classe Division.
	 * @param leftOperand  L'expression arithmétique de l'opérande gauche.
	 * @param rightOperand L'expression arithmétique de l'opérande droite.
	 */
	public Division(ArithmeticExpression leftOperand, ArithmeticExpression rightOperand) {
		super(leftOperand, rightOperand);
		this.classNumber = Division.count++;
		this.uuid = UUID.randomUUID();
	}
	
	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitDivision(this);
	}

	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}
	
	/**
     * Renvoie le nombre d'instances de la classe Division créées.
     * @return Le nombre d'instances de la classe Division créées.
     */
	public static int getCount() {
		return count;
	}
	
	/**
     * Renvoie le numéro de classe de l'instance de la classe Division.
     * @return Le numéro de classe de l'instance de la classe Division.
     */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe Division.
     * @return L'UUID de l'instance de la classe Division.
     */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
     * Renvoie le symbole de l'opération de division.
     * @return Le symbole de l'opération de division.
     */
	public static String getSymbol() {
		return Division.SYMBOL;
	}
	
}
