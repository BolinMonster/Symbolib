package symbolib.loader;

import java.util.HashMap;
import java.util.Map;

import symbolib.expression.SupportedTypeExpression;
import symbolib.expression.arithmetic.Addition;
import symbolib.expression.arithmetic.Division;
import symbolib.expression.arithmetic.Multiplication;
import symbolib.expression.arithmetic.Negation;
import symbolib.expression.arithmetic.Subtraction;
import symbolib.expression.rational.Concatenation;
import symbolib.expression.rational.KleeneStar;
import symbolib.expression.rational.Union;

/**
 * La classe Element représente un élément XML et définit sa balise et ses attributs.
 */
public enum Element {
	
	/**
	 * Aucun élément.
	 */
	NONE,
	/**
	 * Elément racine.
	 * Balise : "symbolib".
	 * Attributs : aucun.
	 */
	ROOT("symbolib", null),
	/**
	 * Elément expression.
	 * Balise : "expression".
	 * Attributs : aucun.
	 */
	EXPRESSION("expression", null),
	/**
	 * Elément d'expression arithmétique.
	 * Balise : "arith".
	 * Attributs : aucun.
	 */
	ARITHMETIC(SupportedTypeExpression.ARITHMETIC.getAbbreviation(), null),
	/**
	 * Elément d'expression fonctionnelle.
	 * Balise : "func".
	 * Attributs : aucun.
	 */
	FUNCTIONAL(SupportedTypeExpression.FUNCTIONAL.getAbbreviation(), null),
	/**
	 * Elément d'expression rationnelle.
	 * Balise : "rational".
	 * Attributs : aucun.
	 */
	RATIONAL(SupportedTypeExpression.RATIONAL.getAbbreviation(), null),
	/**
	 * Elément de négation.
	 * Balise : "negation".
	 * Attributs : 
	 * - "symbol" : symbole de la négation.
	 */
	NEGATION("negation", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("symbol", Negation.getSymbol());
		}
	}),
	/**
	 * Elément d'addition.
	 * Balise : "addition".
	 * Attributs : 
	 * - "symbol" : symbole de l'addition.
	 */
	ADDITION("addition", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("symbol", Addition.getSymbol());
		}
	}),
	/**
	 * Elément de soustraction.
	 * Balise : "subtraction".
	 * Attributs : 
	 * - "symbol" : symbole de la soustraction.
	 */
	SUBTRACTION("subtraction", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("symbol", Subtraction.getSymbol());
		}
	}),
	/**
	 * Elément de multiplication.
	 * Balise : "multiplication".
	 * Attributs : 
	 * - "symbol" : symbole de la multiplication.
	 */
	MULTIPLICATION("multiplication", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("symbol", Multiplication.getSymbol());
		}
	}),
	/**
	 * Elément de division.
	 * Balise : "division".
	 * Attributs : 
	 * - "symbol" : symbole de la division.
	 */
	DIVISION("division", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("symbol", Division.getSymbol());
		}
	}),
	/**
	 * Elément de constante numérique.
	 * Balise : "numeric_constant".
	 * Attributs : 
	 * - "value" : valeur de la constante numérique.
	 */
	NUMERIC_CONSTANT("numeric_constant", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("value", Double.toString(0.0));
		}
	}),
	/**
	 * Elément de variable.
	 * Balise : "variable".
	 * Attributs :
	 * - "name" : nom de la variable.
	 * - "value" : valeur de la variable.
	 */
	VARIABLE("variable", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("name", Double.toString(0.0));
			put("value", Double.toString(0.0));
		}
	}),
	/**
	 * Elément d'opérateur d'étoile de Kleene.
	 * Balise : "kleene_star".
	 * Attributs :
	 * - "symbol" : symbole de l'opérateur d'étoile de Kleene.
	 */
	KLEENE_STAR("kleene_star", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("symbol", KleeneStar.getSymbol());
		}
	}),
	/**
	 * Elément d'opérateur d'union.
	 * Balise : "union".
	 * Attributs :
	 * - "symbol" : symbole de l'opérateur d'union.
	 */
	UNION("union", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("symbol", Union.getSymbol());
		}
	}),
	/**
	 * Elément d'opérateur de concaténation.
	 * Balise : "concatenation".
	 * Attributs :
	 * - "symbol" : symbole de l'opérateur de concaténation.
	 */
	CONCATENATION("concatenation", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("symbol", Concatenation.getSymbol());
		}
	}),
	/**
	 * Elément de constante littérale.
	 * Balise : "litteral_constant".
	 * Attributs :
	 * - "value" : valeur de la constante littérale.
	 */
	LITTERAL_CONSTANT("litteral_constant", new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("value", "");
		}
	});

	private String tag;
	private Map<String, String> attributes;
	
	/**
     * Constructeur par défaut : initialise la balise et les attributs à null.
     */
	private Element() {
		this.tag = null;
		this.attributes = null;
	}
	
    /**
     * Constructeur avec paramètres : initialise la balise et les attributs avec les paramètres spécifiés.
     * @param tag la balise de l'élément HTML
     * @param attributes les attributs de l'élément HTML
     */
	private Element(String tag, Map<String, String> attributes) {
		this.tag = tag;
		this.attributes = attributes;
	}
	
	/**
     * Renvoie la balise de l'élément XML.
     * @return la balise de l'élément XML
     */
	public String getTag() {
		return tag;
	}
	
	/**
     * Renvoie les attributs de l'élément XML.
     * @return les attributs de l'élément XML
     */
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	/**
     * Recherche et renvoie l'élément HTML correspondant à la balise spécifiée.
     * @param tag la balise de l'élément XML à rechercher
     * @return l'élément XML correspondant à la balise spécifiée, ou null si aucun élément ne correspond
     */
	public static Element findByTag(String tag) {
		for (Element element : Element.values()) {
			if (element.getTag() != null && element.getTag().equals(tag)) {
				return element;
			}
		}
		return null;
	}

}
