package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.ImpossibleRangeException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.app.exception.OurCommandException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

  DoCopy(Spreadsheet receiver) {
    super(Label.COPY, receiver);
    addStringField("Gama_copy", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      _receiver.copy(stringField("Gama_copy"));
    }
    catch(UnrecognizedEntryException | ImpossibleRangeException e){
      throw new InvalidCellRangeException(e.getMessage());
    }
  }
}
