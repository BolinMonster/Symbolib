package symbolib.builder;

import java.lang.reflect.Constructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import symbolib.expression.Expression;
import symbolib.expression.SupportedTypeExpression;
import symbolib.expression.arithmetic.Addition;
import symbolib.expression.arithmetic.ArithmeticExpression;
import symbolib.expression.arithmetic.Division;
import symbolib.expression.arithmetic.Multiplication;
import symbolib.expression.arithmetic.Negation;
import symbolib.expression.arithmetic.NumericConstant;
import symbolib.expression.arithmetic.Subtraction;
import symbolib.expression.functional.Variable;
import symbolib.expression.rational.Concatenation;
import symbolib.expression.rational.KleeneStar;
import symbolib.expression.rational.LitteralConstant;
import symbolib.expression.rational.RationalExpression;
import symbolib.expression.rational.Union;
import symbolib.loader.Element;

/**
 * Classe implémentant l'interface ExpressionBuilder pour la construction
 * d'expressions mathématiques et rationnelles standard.
 */
public class StdExpressionBuilder implements ExpressionBuilder {

	/**
	 * Map associant chaque type d'expression pris en charge à une autre map
	 * associant le symbol de l'opération aux classes d'expressions correspondantes.
	 */
	private static Map<SupportedTypeExpression, Map<String, Class<? extends Expression>>> symbolToClassMap = new HashMap<SupportedTypeExpression, Map<String, Class<? extends Expression>>>() {
		private static final long serialVersionUID = 1L;
		{
			put(SupportedTypeExpression.ARITHMETIC, new HashMap<String, Class<? extends Expression>>() {
				private static final long serialVersionUID = 1L;
				{
					put(Negation.getSymbol(), Negation.class);
					put(Addition.getSymbol(), Addition.class);
					put(Subtraction.getSymbol(), Subtraction.class);
					put(Multiplication.getSymbol(), Multiplication.class);
					put(Division.getSymbol(), Division.class);
				}
			});
			put(SupportedTypeExpression.FUNCTIONAL, new HashMap<String, Class<? extends Expression>>() {
				private static final long serialVersionUID = 1L;
				{
					put(Negation.getSymbol(), Negation.class);
					put(Addition.getSymbol(), Addition.class);
					put(Subtraction.getSymbol(), Subtraction.class);
					put(Multiplication.getSymbol(), Multiplication.class);
					put(Division.getSymbol(), Division.class);
				}
			});
			put(SupportedTypeExpression.RATIONAL, new HashMap<String, Class<? extends Expression>>() {
				private static final long serialVersionUID = 1L;
				{
					put(KleeneStar.getSymbol(), KleeneStar.class);
					put(Concatenation.getSymbol(), Concatenation.class);
					put(Union.getSymbol(), Union.class);
				}
			});
		}
	};
	/**
	 * Map associant chaque type d'expression pris en charge à une pile
	 * d'expressions en cours de construction.
	 */
	private Map<SupportedTypeExpression, Stack<Expression>> expressionStackMap;
	/**
	 * Type d'expression en cours de construction.
	 */
	private SupportedTypeExpression currentExpressionType;
	/**
	 * Élément (balise XML) d'expression en cours de construction.
	 */
	private Element currentElement;

	/**
	 * Constructeur par défaut de la classe StdExpressionBuilder. Initialise les
	 * piles d'expressions pour chaque type d'expression pris en charge.
	 */
	public StdExpressionBuilder() {
		this.currentExpressionType = SupportedTypeExpression.NONE;
		this.currentElement = Element.NONE;
		this.expressionStackMap = new HashMap<SupportedTypeExpression, Stack<Expression>>() {
			private static final long serialVersionUID = 1L;
			{
				put(SupportedTypeExpression.ARITHMETIC, new Stack<Expression>());
				put(SupportedTypeExpression.FUNCTIONAL, new Stack<Expression>());
				put(SupportedTypeExpression.RATIONAL, new Stack<Expression>());
			}
		};
	}

	@Override
	public void startExpression() {
		currentElement = Element.EXPRESSION;
	}

	@Override
	public void endExpression() {
		currentElement = Element.NONE;
	}

	@Override
	public void startArithmetic() {
		currentElement = Element.ARITHMETIC;
		currentExpressionType = SupportedTypeExpression.ARITHMETIC;
	}

	@Override
	public void endArithmetic() {
		currentElement = Element.NONE;
	}

	@Override
	public void startFunctional() {
		currentElement = Element.FUNCTIONAL;
		currentExpressionType = SupportedTypeExpression.FUNCTIONAL;
	}

	@Override
	public void endFunctional() {
		currentElement = Element.NONE;
	}

	@Override
	public void startRational() {
		currentElement = Element.RATIONAL;
		currentExpressionType = SupportedTypeExpression.RATIONAL;
	}

	@Override
	public void endRational() {
		currentElement = Element.NONE;
	}

	@Override
	public void startNegation() {
		currentElement = Element.NEGATION;
	}

	@Override
	public void endNegation() {
		final Class<? extends Expression> clazz = (Class<? extends Expression>) symbolToClassMap
				.get(currentExpressionType).get(Negation.getSymbol());
		performOperation(clazz, 1, ArithmeticExpression.class);
		currentElement = Element.NONE;
	}

	@Override
	public void startAddition() {
		currentElement = Element.ADDITION;
	}

	@Override
	public void endAddition() {
		final Class<? extends Expression> clazz = (Class<? extends Expression>) symbolToClassMap
				.get(currentExpressionType).get(Addition.getSymbol());
		performOperation(clazz, 2, ArithmeticExpression.class);
		currentElement = Element.NONE;
	}

	@Override
	public void startSubtraction() {
		currentElement = Element.SUBTRACTION;
	}

	@Override
	public void endSubtraction() {
		final Class<? extends Expression> clazz = (Class<? extends Expression>) symbolToClassMap
				.get(currentExpressionType).get(Subtraction.getSymbol());
		performOperation(clazz, 2, ArithmeticExpression.class);
		currentElement = Element.NONE;
	}

	@Override
	public void startMultiplication() {
		currentElement = Element.MULTIPLICATION;
	}

	@Override
	public void endMultiplication() {
		final Class<? extends Expression> clazz = (Class<? extends Expression>) symbolToClassMap
				.get(currentExpressionType).get(Multiplication.getSymbol());
		performOperation(clazz, 2, ArithmeticExpression.class);
		currentElement = Element.NONE;
	}

	@Override
	public void startDivision() {
		currentElement = Element.DIVISION;
	}

	@Override
	public void endDivision() {
		final Class<? extends Expression> clazz = (Class<? extends Expression>) symbolToClassMap
				.get(currentExpressionType).get(Division.getSymbol());
		performOperation(clazz, 2, ArithmeticExpression.class);
		currentElement = Element.NONE;
	}

	@Override
	public void startNumericConstant(NumericConstant nc) {
		currentElement = Element.NUMERIC_CONSTANT;
		if (expressionStackMap.containsKey(currentExpressionType)
				&& currentExpressionType == SupportedTypeExpression.ARITHMETIC
				|| currentExpressionType == SupportedTypeExpression.FUNCTIONAL && nc != null) {
			expressionStackMap.get(currentExpressionType).push(nc);
		}
	}

	@Override
	public void endNumericConstant() {
		currentElement = Element.NONE;
	}

	@Override
	public void startVariable(Variable v) {
		currentElement = Element.VARIABLE;
		if (expressionStackMap.containsKey(currentExpressionType)
				&& currentExpressionType == SupportedTypeExpression.FUNCTIONAL && v != null) {
			expressionStackMap.get(currentExpressionType).push(v);
		}
	}

	@Override
	public void endVariable() {
		currentElement = Element.NONE;
	}

	@Override
	public void startKleeneStar() {
		currentElement = Element.KLEENE_STAR;
	}

	@Override
	public void endKleeneStar() {
		final Class<? extends Expression> clazz = (Class<? extends Expression>) symbolToClassMap
				.get(currentExpressionType).get(KleeneStar.getSymbol());
		performOperation(clazz, 1, RationalExpression.class);
		currentElement = Element.NONE;
	}

	@Override
	public void startUnion() {
		currentElement = Element.UNION;
	}

	@Override
	public void endUnion() {
		final Class<? extends Expression> clazz = (Class<? extends Expression>) symbolToClassMap
				.get(currentExpressionType).get(Union.getSymbol());
		performOperation(clazz, 2, RationalExpression.class);
		currentElement = Element.NONE;
	}

	@Override
	public void startConcatenation() {
		currentElement = Element.CONCATENATION;
	}

	@Override
	public void endConcatenation() {
		final Class<? extends Expression> clazz = (Class<? extends Expression>) symbolToClassMap
				.get(currentExpressionType).get(Concatenation.getSymbol());
		performOperation(clazz, 2, RationalExpression.class);
		currentElement = Element.NONE;
	}

	@Override
	public void startLitteralConstant(LitteralConstant lc) {
		currentElement = Element.LITTERAL_CONSTANT;
		if (expressionStackMap.containsKey(currentExpressionType)
				&& currentExpressionType == SupportedTypeExpression.RATIONAL && lc != null) {
			expressionStackMap.get(currentExpressionType).push(lc);
		}
	}

	@Override
	public void endLitteralConstant() {
		currentElement = Element.NONE;
	}

	@Override
	public Expression getExpression() {
		if (currentExpressionType == null || !expressionStackMap.containsKey(currentExpressionType))
			return null;
		Stack<Expression> expressionStack = expressionStackMap.get(currentExpressionType);
		if (expressionStack == null)
			return null;
		return !expressionStack.isEmpty() ? expressionStack.peek() : null;
	}

	/**
	 * Effectue une opération avec les opérandes présents dans la pile
	 * d'expressions, en utilisant la classe spécifiée et le nombre d'opérandes
	 * requis.
	 * 
	 * @param clazz            La classe de l'opération à effectuer.
	 * @param operatorCount    Le nombre d'opérandes requis pour cette opération.
	 * @param clazzConstructor La classe du constructeur à utiliser pour instancier
	 *                         la classe d'opération. Peut être n'importe quelle
	 *                         sous-classe de Expression.
	 * @throws ReflectiveOperationException Si une erreur survient lors de
	 *                                      l'instanciation de la classe
	 *                                      d'opération.
	 */
	private <T extends Expression> void performOperation(Class<T> clazz, int operatorCount,
			Class<? extends Expression> clazzConstructor) {
		if (expressionStackMap.get(currentExpressionType).size() >= operatorCount) {
			try {
				Constructor<T> constructor;
				T result = null;
				if (operatorCount == 2) {
					constructor = clazz.getConstructor(clazzConstructor, clazzConstructor);
					Expression rightOperand = (Expression) expressionStackMap.get(currentExpressionType).pop();
					Expression leftOperand = (Expression) expressionStackMap.get(currentExpressionType).pop();
					result = constructor.newInstance(leftOperand, rightOperand);
				} else if (operatorCount == 1) {
					constructor = clazz.getConstructor(clazzConstructor);
					Expression operand = (Expression) expressionStackMap.get(currentExpressionType).pop();
					result = constructor.newInstance(operand);
				}
				if (result != null) {
					expressionStackMap.get(currentExpressionType).push(result);
				} else {
					System.out.println("Une erreur est survenue, lors du chargement de cette opération.");
				}
			} catch (ReflectiveOperationException e) {
				e.printStackTrace();
				System.out.println(
						"Une erreur est survenue lors de l'exécution de l'opération d'instanciation du constructeur, veuillez réessayer.");
			}
		} else {
			System.out.println("La pile ne contient pas assez d'opérandes pour effectuer cette opération. ("
					+ expressionStackMap.get(currentExpressionType).size() + " --> >= " + operatorCount + ")");
		}
	}

	/**
	 * Renvoie une copie de la Map qui associe les types d'expression supportés à
	 * une Map associant les symboles (String) aux classes d'expression
	 * correspondantes.
	 * 
	 * @return Une copie de la Map qui associe les types d'expression supportés à
	 *         une Map associant les symboles (String) aux classes d'expression
	 *         correspondantes.
	 */
	public static Map<SupportedTypeExpression, Map<String, Class<? extends Expression>>> getSymbolToClassMap() {
		return new HashMap<SupportedTypeExpression, Map<String, Class<? extends Expression>>>(symbolToClassMap);
	}

	/**
	 * Renvoie une copie de la Map qui associe les types d'expression supportés à
	 * une pile d'expressions.
	 * @return Une copie de la Map qui associe les types d'expression supportés à
	 *         une pile d'expressions.
	 */
	public Map<SupportedTypeExpression, Stack<Expression>> getExpressionStackMap() {
		return new HashMap<SupportedTypeExpression, Stack<Expression>>(expressionStackMap);
	}

	/**
	 * Renvoie le type d'expression actuel.
	 * @return Le type d'expression actuel.
	 */
	public SupportedTypeExpression getCurrentExpressionType() {
		return currentExpressionType;
	}

	/**
	 * Renvoie l'élément actuel.
	 * @return L'élément actuel.
	 */
	public Element getCurrentElement() {
		return currentElement;
	}

}
