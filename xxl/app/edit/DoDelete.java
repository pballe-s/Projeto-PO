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
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

  DoDelete(Spreadsheet receiver) {
    super(Label.DELETE, receiver);
    addStringField("Gama_del", Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException{
    
    try{
      _receiver.clear(stringField("Gama_del"));
    
  }
  catch(UnrecognizedEntryException | ImpossibleRangeException e){
    throw new InvalidCellRangeException(e.getMessage());
  }
  
  }
}
