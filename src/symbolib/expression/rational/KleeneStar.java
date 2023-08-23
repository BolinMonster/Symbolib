package symbolib.expression.rational;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe est la représentation d'une expression rationelle qui est l'étoile de Kleene.
 */
public class KleeneStar extends RationalUnaryExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private static final String SYMBOL = "*";
	
	/**
	 * Constructeur de la classe KleeneStar.
	 * @param operand  L'expression rationelle de l'opérande.
	 */
	public KleeneStar(RationalExpression operand) {
		super(operand);
		this.classNumber = KleeneStar.count++;
        this.uuid = UUID.randomUUID();
	}

	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}

	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitKleeneStar(this);
	}
	
	/**
	 * Renvoie le nombre d'instances de la classe KleeneStar créées jusqu'à présent.
	 * @return Le nombre d'instances de la classe KleeneStar créées jusqu'à présent.
	 */
	public static int getCount() {
		return count;
	}
	
	/**
	 * Renvoie le numéro de classe de cette instance de la classe KleeneStar.
	 * @return Le numéro de classe de cette instance de la classe KleeneStar.
	 */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe KleeneStar.
     * @return L'UUID de l'instance de la classe KleeneStar.
     */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
     * Renvoie le symbole de l'opération de l'étoile de Kleene.
     * @return Le symbole de l'opération de l'étoile de Kleene.
     */
	public static String getSymbol() {
		return KleeneStar.SYMBOL;
	}


}
