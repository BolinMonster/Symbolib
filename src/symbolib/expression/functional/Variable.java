package symbolib.expression.functional;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Classe est la représentation d'une variable dans une expression fonctionelle.
 */
public class Variable implements FunctionalExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private String name;
	private Double value;
	
	/**
	 * Constructeur la classe Variable.
	 * @param name Le nom de la variable.
	 * @param value La valeur associée à la variable.
	*/
	public Variable(String name, Double value) {
		super();
	    this.classNumber = Variable.count++;
        this.uuid = UUID.randomUUID();
		this.name = name;
		this.value = value;
	}

	@Override
	public void accept(ExpressionVisitor ev) {
		ev.visitVariable(this);
	}
	
	@Override
	public String getIdentifiant() {
		return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
	}
	
	/**
     * Renvoie le nombre d'instances de la classe Variable créées.
     * @return Le nombre d'instances de la classe Variable créées.
     */
	public static int getCount() {
		return count;
	}
	
	/**
     * Renvoie le numéro de classe de l'instance de la classe Variable.
     * @return Le numéro de classe de l'instance de la classe Variable.
     */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe Variable.
     * @return L'UUID de l'instance de la classe Variable.
     */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
	 * Renvoie le nom de la variable.
	 * @return  le nom de la variable.
	 */
	public String getVariableName() {
		return name;
	}
	
	/**
	 * Renvoie la valeur de la variable.
	 * @return la valeur de la variable.
	 */
	public Double getValue() {
		return value;
	}
	
	/**
	 * Définit le nom de la variable.
	 * @param name le nom de la variable.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Définit la valeur de la variable.
	 * @param value la valeur de la variable (seuelement Double pris en charge).
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	
}
