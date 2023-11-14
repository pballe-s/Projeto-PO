package xxl.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
// FIXME import classes
import xxl.core.exception.UnavailableFileException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("open_fileName", Message.openFile());
  }
  
  @Override
  protected final void execute() throws CommandException {
    
      try {
      _receiver.load(stringField("open_fileName"));
      } catch (UnavailableFileException | IOException | ClassNotFoundException e ) {
      throw new FileOpenFailedException(e);
      }
    
  }
}
