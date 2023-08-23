package symbolib.expression.rational;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe représente une expression d'union dans une expression rationelle.
 */
public class Union extends RationalBinaryExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private static final String SYMBOL = "·";
	
	/**
	 * Constructeur de la classe Union.
	 * @param leftOperand  L'expression rationelle de l'opérande gauche.
	 * @param rightOperand L'expression rationelle de l'opérande droite.
	 */
	public Union(RationalExpression leftOperand, RationalExpression rightOperand) {
		super(leftOperand, rightOperand);
		this.classNumber = Union.count++;
        this.uuid = UUID.randomUUID();
	}

	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}

	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitUnion(this);

	}
	
	/**
     * Renvoie le nombre d'instances de la classe Union créées.
     * @return Le nombre d'instances de la classe Union créées.
     */
	public static int getCount() {
		return count;
	}
	
	/**
     * Renvoie le numéro de classe de l'instance de la classe Union.
     * @return Le numéro de classe de l'instance de la classe Union.
     */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe Union.
     * @return L'UUID de l'instance de la classe Union.
     */
	public UUID getUuid() {
		return uuid;
	}
	
    /**
     * Renvoie le symbole de l'union.
     * @return Le symbole de l'union.
     */
	public static String getSymbol() {
		return Union.SYMBOL;
	}

}
