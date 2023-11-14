package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;

import java.util.ArrayList;

import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Cell;
// FIXME import classes

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException{
    ArrayList<Cell> cutBuffer = (ArrayList<Cell>)_receiver.getCutBufferSpreadsheet();
	for (Cell c : cutBuffer)
	{
		_display.addLine(c.toString());
	}
  }
}
