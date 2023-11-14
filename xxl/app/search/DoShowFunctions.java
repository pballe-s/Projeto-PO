package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.app.exception.OurCommandException;
import xxl.core.Cell;
import java.util.ArrayList;
// FIXME import classes

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("show_func", Message.searchFunction());
  }

  @Override
  protected final void execute() throws CommandException{
    String str = stringField("show_func");
	ArrayList<Cell> list = (ArrayList<Cell>)_receiver.getEqualFuntion(str);

	for (Cell c : list)
	{
		_display.addLine(c.toString());
	}
	_display.display();
  }
}
