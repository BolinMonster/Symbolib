package symbolib.expression.rational;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe représente une expression de concaténation dans une expression rationelle.
 */
public class Concatenation extends RationalBinaryExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private static final String SYMBOL = "+";
	
	/**
	 * Constructeur de la classe Concatenation.
	 * @param leftOperand  L'expression rationelle de l'opérande gauche.
	 * @param rightOperand L'expression rationelle de l'opérande droite.
	 */
	public Concatenation(RationalExpression leftOperand, RationalExpression rightOperand) {
		super(leftOperand, rightOperand);
		this.classNumber = Concatenation.count++;
        this.uuid = UUID.randomUUID();
	}

	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}

	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitConcatenation(this);
	}
	
	/**
     * Renvoie le nombre d'instances de la classe Concatenation créées.
     * @return Le nombre d'instances de la classe Concatenation créées.
     */
	public static int getCount() {
		return count;
	}
	
	/**
     * Renvoie le numéro de classe de l'instance de la classe Concatenation.
     * @return Le numéro de classe de l'instance de la classe Concatenation.
     */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe Concatenation.
     * @return L'UUID de l'instance de la classe Concatenation.
     */
	public UUID getUuid() {
		return uuid;
	}
	
    /**
     * Renvoie le symbole de la concaténation.
     * @return Le symbole de la concaténation.
     */
	public static String getSymbol() {
		return Concatenation.SYMBOL;
	}
	
}
