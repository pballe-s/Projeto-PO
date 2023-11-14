package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.app.exception.OurCommandException;
import xxl.core.Spreadsheet;
import xxl.core.exception.ImpossibleRangeException;
import xxl.core.exception.UnrecognizedEntryException;
// FIXME import classes

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

  DoCut(Spreadsheet receiver) {
    super(Label.CUT, receiver);
    addStringField("Gama_cut", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      _receiver.cut(stringField("Gama_cut"));
    }
    catch(UnrecognizedEntryException | ImpossibleRangeException e){
      throw new InvalidCellRangeException(e.getMessage());
    }
  }
}
