package symbolib.expression;

import symbolib.visitor.ExpressionVisitor;

/**
 * Cette interface permet de représenter une expression.
 */
public interface Expression {

	/**
	 * Renvoie l'identifiant de l'expression (l'identifiant du symbol).
	 * @return L'identifiant de l'expression (l'identifiant du symbol).
	 */
	public String getIdentifiant();

	/**
	 * Accepte un visiteur de noeuds pour effectuer une visite sur l'expression.
	 * @param ev Le visiteur de noeuds à accepter.
	 */
	public void accept(ExpressionVisitor ev);
}
