package xxl.app.edit;

import xxl.core.Calculator;
import pt.tecnico.uilib.menus.Command;

class DoRemoveSheet extends Command<Calculator>
{
	DoRemoveSheet(Calculator receiver)
	{
		super("remover spreadsheets", receiver);
		addIntegerField("remove_sheets", "número máximo de usuários:");
	}

	protected final void execute()
	{
		int user_max = integerField("remove_sheets");

		_receiver.removeSheets(user_max);
	}
}