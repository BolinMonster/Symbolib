package symbolib.expression.arithmetic;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe est la représentation d'une expression de négation arithmétique.
 */
public class Negation extends ArithmeticUnaryExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private static final String SYMBOL = "~";
	
	/**
	 * Constructeur de la classe Negation.
	 * @param operand  L'expression arithmétique de l'opérande.
	 */
	public Negation(ArithmeticExpression operand) {
		super(operand);
		this.classNumber = Negation.count++;
		this.uuid = UUID.randomUUID();
	}

	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitNegation(this);
	}
	
	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}
	
	/**
	 * Renvoie le nombre d'instances de la classe Negation créées jusqu'à présent.
	 * @return Le nombre d'instances de la classe Negation créées jusqu'à présent.
	 */
	public static int getCount() {
		return count;
	}
	
	/**
	 * Renvoie le numéro de classe de cette instance de la classe Negation.
	 * @return Le numéro de classe de cette instance de la classe Negation.
	 */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe Negation.
     * @return L'UUID de l'instance de la classe Negation.
     */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
     * Renvoie le symbole de l'opération de négation.
     * @return Le symbole de l'opération de négation.
     */
	public static String getSymbol() {
		return Negation.SYMBOL;
	}

}
