package xxl.app.main;

import java.io.FileNotFoundException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
	
	addIntegerField("linhas", Message.lines());
    addIntegerField("colunas", Message.columns());
  }
  
  @Override
  protected final void execute() throws CommandException {
	if (_receiver.getSpreadsheet() != null && _receiver.getSpreadsheet().getChanged() && Form.confirm(Message.saveBeforeExit()))
	{
		DoSave ds = new DoSave(_receiver);
		ds.performCommand();
	}
    int linhas = integerField("linhas");
    int colunas = integerField("colunas");
    _receiver.createNewSpreadsheet(linhas, colunas);
  }
}
