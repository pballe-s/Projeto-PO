package xxl.app.exception;

import pt.tecnico.uilib.menus.CommandException;

public class OurCommandException extends CommandException
{
	public OurCommandException(String message)
	{
		super(message);
	}
}