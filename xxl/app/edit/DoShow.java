package xxl.app.edit;

import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.core.exception.ImpossibleRangeException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
import java.util.List;
import xxl.core.Range;

// FIXME import classes

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("show_gama", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
	List<Cell> cells = null;
	Range range;
	String _range = stringField("show_gama");
	try
	{	
		range = _receiver.buildRange(_range);		
		cells = range.copyRange();
		for (Cell c : cells)
		{
				_display.addLine(c.toString());
		}
	}
    catch (UnrecognizedEntryException | ImpossibleRangeException ex)
	{
		throw new InvalidCellRangeException(stringField("show_gama"));
	}	
  }
}
