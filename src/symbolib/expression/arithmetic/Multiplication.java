package symbolib.expression.arithmetic;

import java.util.UUID;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette classe est la représentation d'une expression de la multiplication arithmétique.
 */
public class Multiplication extends ArithmeticBinaryExpression {
	
	private static int count = 0;
	private int classNumber;
	private UUID uuid;
	private static final String SYMBOL = "*";
	
	/**
	 * Constructeur de la classe Multiplication.
	 * @param leftOperand  L'expression arithmétique de l'opérande gauche.
	 * @param rightOperand L'expression arithmétique de l'opérande droite.
	 */
	public Multiplication(ArithmeticExpression leftOperand, ArithmeticExpression rightOperand) {
		super(leftOperand, rightOperand);
		this.classNumber = Multiplication.count++;
		this.uuid = UUID.randomUUID();
	}
	
	@Override
    public void accept(ExpressionVisitor ev) {
		ev.visitMultiplication(this);
    }

    @Override
    public String getIdentifiant() {
        return this.getClass().getName() + "_" + getCount() + "_" + getUuid();
    }

	/**
     * Renvoie le nombre d'instances de la classe Multiplication créées.
     * @return Le nombre d'instances de la classe Multiplication créées.
     */
	public static int getCount() {
		return count;
	}
	
	/**
     * Renvoie le numéro de classe de l'instance de la classe Multiplication.
     * @return Le numéro de classe de l'instance de la classe Multiplication.
     */
	public int getClassNumber() {
		return classNumber;
	}
	
	/**
     * Renvoie l'UUID de l'instance de la classe Multiplication.
     * @return L'UUID de l'instance de la classe Multiplication.
     */
	public UUID getUuid() {
		return uuid;
	}
	
    /**
     * Renvoie le symbole de la multiplication.
     * @return Le symbole de la multiplication.
     */
    public static String getSymbol() {
        return Multiplication.SYMBOL;
    }

}
