package symbolib.loader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import symbolib.builder.ExpressionBuilder;
import symbolib.expression.arithmetic.NumericConstant;
import symbolib.expression.functional.Variable;
import symbolib.expression.rational.LitteralConstant;

/**
 * Cette classe est utilisée pour charger des expressions à partir de fichiers XML.
*/
public class XMLExpressionLoader {
	
	/**
	 * Charge les expressions à partir du fichier XML spécifié.
	 * @param xmlFile le fichier XML contenant les expressions à charger.
	 * @param expressionBuilder le constructeur d'expressions utilisé pour créer les expressions à partir des données chargées.
	*/
	public static void load(File xmlFile, ExpressionBuilder expressionBuilder)  {
		/*
		ClassLoader classLoader = XMLDrawableLoader.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(xmlFileName);
		InputSource is = new InputSource(inputStream);
		*/
		InputSource is = null;
		BufferedInputStream bis = null;
		try {
		    bis = new BufferedInputStream(new FileInputStream(xmlFile));
		    is = new InputSource(bis);
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    try {
		        if (bis != null) {
		            bis.close();
		        }
		    } catch (IOException io) {
		    	io.printStackTrace();
		    }
		}
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setValidating(true);
		SAXParser sp = null;
		XMLReader xr = null;
		try {
			sp = spf.newSAXParser();
			xr = sp.getXMLReader();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		HandlerImplementation handler = new HandlerImplementation(expressionBuilder);
		xr.setContentHandler(handler);
		xr.setErrorHandler((ErrorHandler) handler);
		try {
			xr.parse(is);
		} catch(IOException | SAXException e) {
			e.printStackTrace();
		}
		try {
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Classe interne privée implémentant les interfaces ContentHandler et ErrorHandler, 
	 * qui sont toutes deux utilisées pour gérer les événements d'analyse du fichier XML.
	 */
	private static class HandlerImplementation extends DefaultHandler implements ContentHandler, ErrorHandler {
		
		List<Map.Entry<String, Attributes>> stackElement = new ArrayList<>();
		private ExpressionBuilder expressionBuilder;
		
		public HandlerImplementation(ExpressionBuilder expressionBuilder) {
			this.expressionBuilder = expressionBuilder;
		}
		
		/**
		 * @return le constructeur d'expression
		 */
		public ExpressionBuilder getExpressionBuilder() {
			return this.expressionBuilder; 
		}
		
		public void characters(char[] ch, int start, int length) throws SAXException {
			Map.Entry<String, Attributes> map = stackElement.get(stackElement.size() - 1);
	        final String top = map.getKey();
	        Attributes atts = map.getValue();
	        final String buf = new String(ch, start, length);
		}
		
		public void endDocument() throws SAXException {
			stackElement.clear();
		}
		
		public void endElement(String url, String localName, String qName) throws SAXException {
			if (qName.equals(Element.EXPRESSION.getTag())) {
				expressionBuilder.endExpression();
			} else if (qName.equals(Element.ARITHMETIC.getTag())) {
				expressionBuilder.endArithmetic();
			} else if (qName.equals(Element.FUNCTIONAL.getTag())) {
				expressionBuilder.endFunctional();
			} else if (qName.equals(Element.RATIONAL.getTag())) {
				expressionBuilder.endRational();
			} else if (qName.equals(Element.NEGATION.getTag())) {
				expressionBuilder.endNegation();
			} else if (qName.equals(Element.ADDITION.getTag())) {
				expressionBuilder.endAddition();
			} else if (qName.equals(Element.SUBTRACTION.getTag())) {
				expressionBuilder.endSubtraction();
			} else if (qName.equals(Element.MULTIPLICATION.getTag())) {
				expressionBuilder.endMultiplication();
			} else if (qName.equals(Element.DIVISION.getTag())) {
				expressionBuilder.endDivision();
			} else if (qName.equals(Element.KLEENE_STAR.getTag())) {
				expressionBuilder.endKleeneStar();
			} else if (qName.equals(Element.UNION.getTag())) {
				expressionBuilder.endUnion();
			} else if (qName.equals(Element.CONCATENATION.getTag())) {
				expressionBuilder.endConcatenation();
			} else if (qName.equals(Element.NUMERIC_CONSTANT.getTag())) {
				expressionBuilder.endNumericConstant();
			} else if (qName.equals(Element.VARIABLE.getTag())) {
				expressionBuilder.endVariable();
			} else if (qName.equals(Element.LITTERAL_CONSTANT.getTag())) {
				expressionBuilder.endLitteralConstant();
			}
			stackElement.remove(stackElement.size() - 1);
		}
		
		public void startDocument() {
			stackElement = new ArrayList<>();
		}

		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			if (qName.equals(Element.EXPRESSION.getTag())) {
				expressionBuilder.startExpression();
			} else if (qName.equals(Element.ARITHMETIC.getTag())) {
				expressionBuilder.startArithmetic();
			} else if (qName.equals(Element.FUNCTIONAL.getTag())) {
				expressionBuilder.startFunctional();
			} else if (qName.equals(Element.RATIONAL.getTag())) {
				expressionBuilder.startRational();
			} else if (qName.equals(Element.NEGATION.getTag())) {
				expressionBuilder.startNegation();
			} else if (qName.equals(Element.ADDITION.getTag())) {
				expressionBuilder.startAddition();
			} else if (qName.equals(Element.SUBTRACTION.getTag())) {
				expressionBuilder.startSubtraction();
			} else if (qName.equals(Element.MULTIPLICATION.getTag())) {
				expressionBuilder.startMultiplication();
			} else if (qName.equals(Element.DIVISION.getTag())) {
				expressionBuilder.startDivision();
			} else if (qName.equals(Element.KLEENE_STAR.getTag())) {
				expressionBuilder.startKleeneStar();
			} else if (qName.equals(Element.UNION.getTag())) {
				expressionBuilder.startUnion();
			} else if (qName.equals(Element.CONCATENATION.getTag())) {
				expressionBuilder.startConcatenation();
			} else if (qName.equals(Element.NUMERIC_CONSTANT.getTag())) {
				Double value;
				try {
					value = Double.parseDouble(atts.getValue("value"));
					final NumericConstant nc = new NumericConstant(value);
					expressionBuilder.startNumericConstant(nc);
				} catch(NumberFormatException e) {
					e.printStackTrace();
				}
			} else if (qName.equals(Element.VARIABLE.getTag())) {
				String name = null;
				Double value = null;
				try {
					name = atts.getValue("name");
					value = Double.parseDouble(atts.getValue("value"));
					final Variable variable = new Variable(name, value);
					expressionBuilder.startVariable(variable);
				} catch(NumberFormatException e) {
					e.printStackTrace();
				}
				
			} else if (qName.equals(Element.LITTERAL_CONSTANT.getTag())) {
				String value;
				try {
					value = atts.getValue("value");
					final LitteralConstant lc = new LitteralConstant(value);
					expressionBuilder.startLitteralConstant(lc);
				} catch(NumberFormatException e) {
					e.printStackTrace();
				}
			}
			final Map.Entry<String, Attributes> entry = new AbstractMap.SimpleEntry<>(qName, atts);
			stackElement.add(entry);
		}
	}
}

