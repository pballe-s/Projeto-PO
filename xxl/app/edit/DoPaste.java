package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.exception.ImpossibleRangeException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

  DoPaste(Spreadsheet receiver) {
    super(Label.PASTE, receiver);
    addStringField("Gama_paste", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      _receiver.paste(stringField("Gama_paste"));
    }
    catch(UnrecognizedEntryException | ImpossibleRangeException e){
      throw new InvalidCellRangeException(e.getMessage());
    }
  }
}
