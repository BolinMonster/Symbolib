package symbolib.expression.rational;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe est la représentation d'une expression rationelle qui est une constante littérale.
 */
public class LitteralConstant implements RationalExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private String value;
	
	/**
	 * Constructeur de la classe LitteralConstant.
	 * @param value La valeur de la constante littérale.
	*/
	public LitteralConstant(String value) {
		super();
	    this.classNumber = LitteralConstant.count++;
        this.uuid = UUID.randomUUID();
        this.value = value;
	}
	
	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitLitteralConstant(this);
	}

	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}
	
	/**
	 * Retourne le nombre d'instances de LitteralConstant créées.
	 * @return Le nombre d'instances de LitteralConstant créées.
	*/
	public static int getCount() {
		return count;
	}
	
	/**
	 * Retourne le numéro de classe de l'objet LitteralConstant.
	 * @return Le numéro de classe de l'objet LitteralConstant.
	*/
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
	 * Retourne l'UUID de l'objet LitteralConstant.
	 * @return L'UUID de l'objet LitteralConstant.
	*/
	public UUID getUuid() {
		return uuid;
	}
	
	/**
	 * Retourne la valeur de la constante littérale.
	 * @return La valeur de la constante littérale.
	*/
	public String getValue() {
		return value;
	}
	
}
