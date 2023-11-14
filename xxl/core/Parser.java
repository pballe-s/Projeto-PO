package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import xxl.core.exception.ImpossibleRangeException;
import xxl.core.exception.IncorrectBinaryFunctionException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.IncorrectIntervalFunctionException;
class Parser {

  private Spreadsheet _spreadsheet;
  
  Parser() {
  }

  Parser(Spreadsheet spreadsheet) {
    _spreadsheet = spreadsheet;
  }

  Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException /* More Exceptions? */, IncorrectBinaryFunctionException, IncorrectIntervalFunctionException, ImpossibleRangeException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      parseDimensions(reader);

      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }

    return _spreadsheet;
  }

  private void parseDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException{
    int rows = -1;
    int columns = -1;
    
    for (int i = 0; i < 2; i++) {
      String[] dimension = reader.readLine().split("=");
      if (dimension[0].equals("linhas"))
        rows = Integer.parseInt(dimension[1]);
      else if (dimension[0].equals("colunas"))
        columns = Integer.parseInt(dimension[1]);
      else
        throw new UnrecognizedEntryException(dimension[0] + "=" + dimension[1]);
    }

    if (rows <= 0 || columns <= 0)
      throw new UnrecognizedEntryException("Dimensões inválidas para a folha");

    _spreadsheet = new Spreadsheet(rows, columns);
  }

  private void parseLine(String line) throws UnrecognizedEntryException /*, more exceptions? */, IncorrectBinaryFunctionException, IncorrectIntervalFunctionException, ImpossibleRangeException{
    String[] components = line.split("\\|");

    if (components.length == 1) // do nothing
      return;
    
    if (components.length == 2) {
      String[] address = components[0].split(";");
      Content content = parseContent(components[1]);
      _spreadsheet.insert(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
    } else
      throw new UnrecognizedEntryException("Wrong format in line: " + line);
  }

  // parse the begining of an expression
  Content parseContent(String contentSpecification) throws UnrecognizedEntryException, IncorrectBinaryFunctionException, IncorrectIntervalFunctionException, ImpossibleRangeException{
    char c = contentSpecification.charAt(0);

    if (c == '=')
      return parseContentExpression(contentSpecification.substring(1));
    else
      return parseLiteral(contentSpecification);
  }

  private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
    if (literalExpression.charAt(0) == '\'')
      return new LiteralString(literalExpression);
    else {
      try {
        int val = Integer.parseInt(literalExpression);
        return new LiteralInt(val);
      } catch (NumberFormatException nfe) {
        throw new UnrecognizedEntryException("Número inválido: " + literalExpression);
      }
    }
  }

  // contentSpecification is what comes after '='
  private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException /* more exceptions */, IncorrectBinaryFunctionException, IncorrectIntervalFunctionException, ImpossibleRangeException {
    if (contentSpecification.contains("("))
      return parseFunction(contentSpecification);
    // It is a reference
    String[] address = contentSpecification.split(";");
    return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1].trim()), _spreadsheet);
  }

  private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException, IncorrectBinaryFunctionException, IncorrectIntervalFunctionException /*more exceptions */, ImpossibleRangeException {
    String[] components = functionSpecification.split("[()]");
    if (components[1].contains(","))
      return parseBinaryFunction(components[0], components[1]);
        
    return parseIntervalFunction(components[0], components[1]);
  }

  private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException, IncorrectBinaryFunctionException /* , more Exceptions */ {
    String[] arguments = args.split(",");
    Content arg0 = parseArgumentExpression(arguments[0]);
    Content arg1 = parseArgumentExpression(arguments[1]);
    
    return switch (functionName) {
      case "ADD" -> new Add(arg0, arg1);
      case "SUB" -> new Sub(arg0, arg1);
      case "MUL" -> new Mul(arg0, arg1);
      case "DIV" -> new Div(arg0, arg1);
      default -> throw new IncorrectBinaryFunctionException(functionName);
    };
  }

  private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
    if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
      String[] address = argExpression.split(";");
      return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1].trim()), _spreadsheet);
      // pode ser diferente do anterior em parseContentExpression
    } else
      return parseLiteral(argExpression);
  }

  private Content parseIntervalFunction(String functionName, String rangeDescription)
    throws UnrecognizedEntryException, IncorrectIntervalFunctionException /* , more exceptions ? */, ImpossibleRangeException {
    Range range = _spreadsheet.buildRange(rangeDescription);
    return switch (functionName) {
      case "CONCAT" -> new Concat(range);
      case "COALESCE" -> new Coalesce(range);
      case "PRODUCT" -> new Product(range);
      case "AVERAGE" -> new Average(range);
	  case "MIN" -> new Min(range);
      default -> throw new IncorrectIntervalFunctionException(functionName);
    };
  }

  /* Na classe Spreadsheet preciso de algo com a seguinte funcionalidade
  Range createRange(String range) throws ? {
    String[] rangeCoordinates;
    int firstRow, firstColumn, lastRow, lastColumn;
    
    if (range.indexOf(':') != -1) {
      rangeCoordinates = range.split("[:;]");
      firstRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = Integer.parseInt(rangeCoordinates[1]);
      lastRow = Integer.parseInt(rangeCoordinates[2]);
      lastColumn = Integer.parseInt(rangeCoordinates[3]);
    } else {
      rangeCoordinates = range.split(";");
      firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
    }

    // check if coordinates are valid
    // if yes
    return new Range with firstRow, firstColumn, lastRow, lastColumn and spreadsheet;
  }
  */
  
}
