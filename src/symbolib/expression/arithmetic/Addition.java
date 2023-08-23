package symbolib.expression.arithmetic;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe est la représentation d'une expression de l'addition arithmétique.
 */
public class Addition extends ArithmeticBinaryExpression {

	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private static final String SYMBOL = "+";

	/**
	 * Constructeur de la classe Addition.
	 * @param leftOperand  L'expression arithmétique de l'opérande gauche.
	 * @param rightOperand L'expression arithmétique de l'opérande droite.
	 */
	public Addition(ArithmeticExpression leftOperand, ArithmeticExpression rightOperand) {
		super(leftOperand, rightOperand);
		this.classNumber = Addition.count++;
		this.uuid = UUID.randomUUID();
	}
	
	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitAddition(this);
	}
	
	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}

	/**
	 * Renvoie le nombre d'instances de la classe Addition créées jusqu'à présent.
	 * @return Le nombre d'instances de la classe Addition créées jusqu'à présent.
	 */
	public static int getCount() {
		return count;
	}

	/**
	 * Renvoie le numéro de classe de cette instance de la classe Addition.
	 * @return Le numéro de classe de cette instance de la classe Addition.
	 */
	public int getClassNumber() {
		return classNumber;
	}

	/**
	 * Renvoie l'UUID généré pour cette instance de la classe Addition.
	 * @return L'UUID généré pour cette instance de la classe Addition.
	 */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
	 * Renvoie le symbole de l'addition.
	 * @return Le symbole de l'addition.
	 */
	public static String getSymbol() {
		return Addition.SYMBOL;
	}

}
