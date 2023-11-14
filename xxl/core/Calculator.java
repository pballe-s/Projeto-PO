package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import xxl.app.exception.InvalidCellRangeException;
import xxl.core.exception.ImportFileException;
import xxl.core.exception.ImpossibleRangeException;
import xxl.core.exception.IncorrectBinaryFunctionException;
import xxl.core.exception.IncorrectIntervalFunctionException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

// FIXME import classes

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {
	private LinkedList<Spreadsheet> _sheets;
  /** The current spreadsheet. */
  	private Spreadsheet _activeSpreadsheet;
  	private String _filename;
  	private User _user;
  
  // FIXME add more fields and methods if needed
  
	public Calculator()
	{
		_sheets = new LinkedList<Spreadsheet>();
		_activeSpreadsheet = null;
		_filename = null;
		_user = null;
	}

  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be null.
   */
  public final Spreadsheet getSpreadsheet() {
    return _activeSpreadsheet;
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
		if (_filename == null)
		{
			throw new MissingFileAssociationException();
		}	
		if (_activeSpreadsheet.getChanged() == true)
		{
			_activeSpreadsheet.setChanged(false);
			ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(_filename));
			obOut.writeObject(_activeSpreadsheet);
			obOut.close();
		}
	}
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException, InvalidCellRangeException {
	if (filename.length() >= 5)
		_filename = filename;
	else 
		throw new InvalidCellRangeException(filename);
	save();
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException, FileNotFoundException, IOException, ClassNotFoundException {
	ObjectInputStream obIn = new ObjectInputStream(new FileInputStream(filename));
	_activeSpreadsheet = (Spreadsheet)obIn.readObject();
	obIn.close();
  }
  
  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   * @throws InvalidCellRangeException
   */
  public void importFile(String filename) throws ImportFileException, ImpossibleRangeException {
    try {
		Parser parser = new Parser();
		_activeSpreadsheet = parser.parseFile(filename);
    } catch (IOException | UnrecognizedEntryException | IncorrectBinaryFunctionException | IncorrectIntervalFunctionException e) {
      throw new ImportFileException(filename, e);
    }
  }

  public void createNewSpreadsheet(int rows, int columns)
  {
	_activeSpreadsheet = new Spreadsheet(rows, columns);
	_sheets.add(_activeSpreadsheet);
  }

  public void createUser(String name)
  {
	if (_user == null)
	{
		_user = new User(name);
	}
  }

  public String getFilename()
  {
	return _filename;
  }

  public void setFilename(String filename)
  {
	_filename = filename;
  }

  public void removeSheets(int user_max)
  {
	for (Spreadsheet s : _sheets)
	{
		if (s.getUsers().size() > user_max)
			_sheets.remove(s);
	}
  }
}
