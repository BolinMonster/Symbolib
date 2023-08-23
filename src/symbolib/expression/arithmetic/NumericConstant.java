package symbolib.expression.arithmetic;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe est la représentation d'une expression arithmétique qui est une constante numérique.
 */
public class NumericConstant implements ArithmeticExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private Double value;
	
	/**
	 * Constructeur de la classe NumericConstant.
	 * Il initialise la valeur de la constante numérique, le numéro de classe et l'UUID.
	 * @param value La valeur de la constante numérique.
	*/
	public NumericConstant(Double value) {
		super();
		this.value = value;
		this.classNumber = NumericConstant.count++;
		this.uuid = UUID.randomUUID();
	}

	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitNumericConstant(this);
	}

	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}
	
	/**
     * Renvoie le nombre d'instances de la classe NumericConstant créées.
     * @return Le nombre d'instances de la classe NumericConstant créées.
     */
	public static int getCount() {
		return count;
	}
	
	/**
     * Renvoie le numéro de classe de l'instance de la classe NumericConstant.
     * @return Le numéro de classe de l'instance de la classe NumericConstant.
     */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe NumericConstant.
     * @return L'UUID de l'instance de la classe NumericConstant.
     */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
	 * Renvoie la valeur de la constante numérique.
	 * @return La valeur de la constante numérique.
	 */
	public Double getValue() {
		return this.value;
	}
	
	/**
	 * Définit la valeur de la constante numérique.
	 * @param value La valeur à définir pour la constante numérique (seulement Double pris en charge).
	 */
	public void setValue(Double value) {
		this.value = value;
	}

}
