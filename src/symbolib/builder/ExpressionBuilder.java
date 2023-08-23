package symbolib.builder;

import symbolib.expression.Expression;
import symbolib.expression.arithmetic.NumericConstant;
import symbolib.expression.functional.Variable;
import symbolib.expression.rational.LitteralConstant;

/**
 * Interface spécifiant les méthodes de construction d'un objet Expression
 */
public interface ExpressionBuilder {

    /**
     * Balise de démarrage de la construction d'une expression.
     */
    public void startExpression();

    /**
     * Balise de terminaison de la construction d'une expression.
     */
    public void endExpression();

    /**
     * Balise de démarrage de la construction d'une expression arithmétique.
     */
    public void startArithmetic();

    /**
     * Balise de terminaison de la construction d'une expression arithmétique.
     */
    public void endArithmetic();

    /**
     * Balise de démarrage de la construction d'une expression fonctionnelle.
     */
    public void startFunctional();

    /**
     * Balise de terminaison de la construction d'une expression fonctionnelle.
     */
    public void endFunctional();

    /**
     * Balise de démarrage de la construction d'une expression rationnelle.
     */
    public void startRational();

    /**
     * Balise de terminaison de la construction d'une expression rationnelle.
     */
    public void endRational();

    /**
     * Balise de démarrage de la construction d'une négation.
     */
    public void startNegation();

    /**
     * Balise de terminaison de la construction d'une négation.
     */
    public void endNegation();

    /**
     * Balise de démarrage de la construction d'une addition.
     */
    public void startAddition();

    /**
     * Balise de terminaison de la construction d'une addition.
     */
    public void endAddition();

    /**
     * Balise de démarrage de la construction d'une soustraction.
     */
    public void startSubtraction();

    /**
     * Balise de terminaison de la construction d'une soustraction.
     */
    public void endSubtraction();

    /**
     * Balise de démarrage de la construction d'une multiplication.
     */
    public void startMultiplication();

    /**
     * Balise de terminaison de la construction d'une multiplication.
     */
    public void endMultiplication();

    /**
     * Balise de démarrage de la construction d'une division.
     */
    public void startDivision();

    /**
     * Balise de terminaison de la construction d'une division.
     */
    public void endDivision();

    /**
     * Balise de démarrage de la construction d'une constante numérique.
     * @param nc La constante numérique.
     */
    public void startNumericConstant(NumericConstant nc);

    /**
     * Balise de terminaison de la construction d'une constante numérique.
     */
    public void endNumericConstant();

    /**
     * Balise de démarrage de la construction d'une variable.
     * @param v La variable.
     */
    public void startVariable(Variable v);

    /**
     * Balise de terminaison de la construction d'une variable.
     */
    public void endVariable();

    /**
     * Balise de démarrage de la construction d'une étoile de Kleene.
     */
    public void startKleeneStar();

    /**
     * Balise de terminaison de la construction d'une étoile de Kleene.
     */
    public void endKleeneStar();

    /**
     * Balise de démarrage de la construction d'une union.
     */
    public void startUnion();

    /**
     * Balise de terminaison de la construction d'une union.
     */
    public void endUnion();

    /**
     * Balise de démarrage de la construction d'une concaténation.
     */
    public void startConcatenation();

    /**
     * Balise de terminaison de la construction d'une concaténation.
     */
    public void endConcatenation();

    /**
     * Balise de démarrage de la construction d'une constante littérale.
     * @param lc La constante littérale.
     */
	public void startLitteralConstant(LitteralConstant lc);
	
    /**
     * Balise de terminaison de la construction d'une constante littérale.
     */
	public void endLitteralConstant();
	
	/**
	 * Retourne l'expression construite à partir du fichier.
	 * @return l'expression construite.
	 */
	public Expression getExpression();
	
}
