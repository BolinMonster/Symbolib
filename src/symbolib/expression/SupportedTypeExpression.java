package symbolib.expression;

import symbolib.expression.arithmetic.ArithmeticExpression;
import symbolib.expression.functional.FunctionalExpression;
import symbolib.expression.rational.RationalExpression;

/**
 * Enumération représentant les types d'expressions pris en charge.
 * Chaque type est associé à une classe d'expression, ainsi qu'à une abréviation et un nom.
 */
public enum SupportedTypeExpression {
	
	/**
	 * Type d'expression nul.
	*/
	NONE(null, null, null),
	/**
	 * Type d'expression arithmétique.
	*/
	ARITHMETIC(ArithmeticExpression.class, "arith", "arithmétique"),
	/**
	 * Type d'expression fonctionnelle.
	*/
	FUNCTIONAL(FunctionalExpression.class, "func", "fonctionnelle"),
	/**
	 * Type d'expression rationnelle.
	*/
	RATIONAL(RationalExpression.class, "rat", "rationelle");

	private Class<? extends Expression> expressionClass;
	private String abbreviation;
	private String description;

	/**
	 * Constructeur SupportedTypeExpression
	 * @param expressionClass La classe d'expression associée au type.
	 * @param abbreviation L'abréviation du type.
	 * @param description La description du type.
	 */
	private SupportedTypeExpression(Class<? extends Expression> expressionClass, String abbreviation,
			String description) {
		this.expressionClass = expressionClass;
		this.abbreviation = abbreviation;
		this.description = description;
	}

	/**
	 * Retourne la classe d'expression associée au type.
	 * @return La classe d'expression associée au type.
	 */
	public Class<? extends Expression> getExpressionClass() {
		return expressionClass;
	}

	/**
	 * Retourne l'abréviation du type.
	 * @return L'abréviation du type.
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * Retourne la description du type.
	 * @return La description du type.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Retourne le type d'expression correspondant à l'abréviation donnée.
	 * @param abbreviation L'abréviation du type recherché.
	 * @return Le type d'expression correspondant à l'abréviation donnée, ou null si aucun type n'est trouvé.
	 */
	public static SupportedTypeExpression findByAbbreviation(String abbreviation) {
		for (SupportedTypeExpression type : SupportedTypeExpression.values()) {
			if (type.getAbbreviation() != null && type.getAbbreviation().equals(abbreviation)) {
				return type;
			}
		}
		return null;
	}
	
	/**
	 * Vérifie si le type est valide.
	 * @param type Le type à vérifier.
	 * @return true si le type est valide, false sinon.
	*/
    public static boolean isValidStringType(String type) {
		return SupportedTypeExpression.findByAbbreviation(type) != null;
	}
	
    /**
     * Retourne le type d'expression correspondant à la classe donnée.
     * @param expressionClass La classe d'expression recherchée.
     * @return Le type d'expression correspondant à la classe donnée, ou null si aucun type n'est trouvé.
     */
    public static SupportedTypeExpression findByClass(Class<? extends Expression> expressionClass) {
        for (SupportedTypeExpression type : SupportedTypeExpression.values()) {
            if (type.getExpressionClass() != null && (type.getExpressionClass().equals(expressionClass)
                    || type.getExpressionClass().isAssignableFrom(expressionClass))) {
                return type;
            }
        }
        return null;
    }
    
    /**
     * Vérifie si la classe d'expression spécifiée est valide.
     * @param expressionClass la classe d'expression à vérifier
     * @return true si la classe est valide, false sinon
     */
    public static boolean isValidClassType(Class<? extends Expression> expressionClass) {
		return SupportedTypeExpression.findByClass(expressionClass) != null;
	}

}
