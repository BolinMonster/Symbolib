package symbolib.visitor;

import symbolib.expression.arithmetic.Addition;
import symbolib.expression.arithmetic.Division;
import symbolib.expression.arithmetic.Multiplication;
import symbolib.expression.arithmetic.Negation;
import symbolib.expression.arithmetic.NumericConstant;
import symbolib.expression.arithmetic.Subtraction;
import symbolib.expression.functional.Variable;
import symbolib.expression.rational.Concatenation;
import symbolib.expression.rational.KleeneStar;
import symbolib.expression.rational.LitteralConstant;
import symbolib.expression.rational.Union;

/**
 * Cette interface est utilisé pour visitor chaque noeud représentant une expression dans l'arbre contenant les expressions.
 */
public interface ExpressionVisitor {
	
	/**
	 * Visite une négation dans l'arbre d'expression.
	 * @param n la négation à visiter
	*/
	public void visitNegation(Negation n);
	/**
	 * Visite une addition dans l'AST.
	 * @param a l'addition à visiter
	*/
	public void visitAddition(Addition a);
	/**
	 * Visite une soustraction dans l'arbre d'expression.
	 * @param s la soustraction à visiter
	*/
	public void visitSubtraction(Subtraction s);
	/**
	 * Visite une multiplication dans l'arbre d'expression.
	 * @param m la multiplication à visiter
	*/
	public void visitMultiplication(Multiplication m);
	/**
	 * Visite une division dans l'arbre d'expression.
	 * @param d la division à visiter
	*/
	public void visitDivision(Division d);
	/**
	 * Visite une variable dans l'arbre d'expression.
	 * @param v la variable à visiter
	*/
	public void visitVariable(Variable v);
	/**
	 * Visite une constante numérique dans l'arbre d'expression.
	 * @param nc la constante numérique à visiter
	*/
	public void visitNumericConstant(NumericConstant nc);
	/**
	 * Visite une constante littérale dans l'arbre d'expression.
	 * @param lc la constante littérale à visiter
	*/
	public void visitLitteralConstant(LitteralConstant lc);
	/**
	 * Visite une étoile de Kleene dans l'arbre d'expression.
	 * @param k l'étoile de Kleene à visiter
	*/
	public void visitKleeneStar(KleeneStar k);
	/**
	 * Visite une concaténation dans l'arbre d'expression.
	 * @param i la concaténation à visiter
	*/
	public void visitConcatenation(Concatenation i);
	/**
	 * Visite une union dans l'arbre d'expression.
	 * @param u l'union à visiter
	*/
	public void visitUnion(Union u);
	
}
