package HypergraphFileSystem;

import java.io.*;

class CommandLineInterface{
	// This class creates a command line interface which allows a user to create and modify file systems.

	// The command line is always looking at a particular file system.
	// If fileSys is null the user is forced to create or load a valid file system.
	FileSystem fileSys = null;

	// Use BufferedReader to get user input as a string called "inputString".
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String inputString;

	// Store the path of the current sql file as a string.
	String sqlFilePath = "";

	// Store the current container's container ID.
	Integer currentId;

	// When exit becomes true the CommandLineInterface loop will end.
	Boolean exit = false;

	CommandLineInterface(){
		System.out.println("Hypergraph file system command line");
		while(exit == false){
			while(fileSys == null){
				System.out.println("No file system loaded.");
				System.out.println("Input the path of a sql database file with \"path \" followed by the full path or type \"new\" to create a new file system.");
				try{
					inputString = reader.readLine();
				}catch(IOException initFail){
					initFail.printStackTrace();
				}
				if(inputString.equals("new")){
					fileSys = new FileSystem();
					currentId = fileSys.rootId;
					System.out.println("New filesystem created");
				}
				if(inputString.startsWith("path ")){
					sqlFilePath = inputString.substring(5);
					System.out.println("Atempting to load the file system at "+sqlFilePath);
					// Dummy stand-in for actual loading.
					fileSys = new FileSystem();
					currentId = fileSys.rootId;
				}
			}
			System.out.print("@"+fileSys.getContainerName(currentId)+": ");
			try{
				inputString = reader.readLine();
			}catch(IOException commandFail){
				commandFail.printStackTrace();
			}
			response(inputString);
		}
	}

	void response(String inputString){
		// For general information on specific commands, see the help command below.

		// Administrative commands for the CLI follow.

		// test
		if(inputString.equals("test")){
			System.out.println("Yay! You input the test command.");
			return;
		}

		// help
		if(inputString.equals("help")){
			System.out.println("Below is a list of available commands.");
			System.out.println("close - Close out of the current file system.");
			System.out.println("do [containerName] - Open file [containerName].");
			System.out.println("doid [containerId] - Open file [containerId].");
			System.out.println("exit - Exit this shell.");
			System.out.println("go [containerName] - Switch the current container to [containerName].");
			System.out.println("goid [containerId] - Switch the current container to [containerId].");
			System.out.println("help - Print this message.");
			System.out.println("home - Return to the root container.");
			System.out.println("save [path] - Save the current file system to the given [path].");
			System.out.println("see (v) - Print the items in the current container. The (v)erbose option shows container IDs.");
			System.out.println("test - Print a test message.");
			return;
		}

		// exit
		if(inputString.equals("exit")){
			exitCommandLineInterface(sqlFilePath);
			return;
		}

		// close
		if(inputString.equals("close")){
			closeFileSystem(sqlFilePath);
			return;
		}

		// save [path]
		// TODO: Ask for confirmation when overwriting an existing file.
		if(inputString.startsWith("save ")){
			saveFileSystem(inputString.substring(5));
		}

		// Commands for navigating the file system follow.

		// see (v)

		// home

		// go [containerName]
		
		// goid [containerId]

		// do [containerName]

		// doid [containerId]

		// Commands for manipulating the root of the file system follow.

		// Commands for manipulating the container type system follow.

		// addContainerType
		
		// removeContainerType
		
		// activeTypeIds
		
		// Commands for manipulating a particular container type follow.
		
		// No valid command was input.
		System.out.println("Invalid command. Use \"help\" to see a list of available commands.");
	}

	void loadFileSystem(String sqlFilePath){
		return; // TODO: Implement loading file system from sql database.
	}

	void saveFileSystem(String sqlFilePath){
		return; // TODO: Implement saving file system to sql database.
	}

	Boolean saveDialog(String sqlFilePath){
		Boolean saveComplete = false;
		String pathCandidate = null;
		if(sqlFilePath.equals("")){
			System.out.println("Would you like to save the current file system? (y/n)");
			try{
				inputString = reader.readLine();
			}catch(IOException commandFail){
				commandFail.printStackTrace();
			}
			if(inputString.equals("y")){
				while(saveComplete == false){
					System.out.println("Enter a path, including file name, where the file system will be saved.");
					System.out.println("Input \"a\" to abort.");
					try{
						pathCandidate = reader.readLine();
					}catch(IOException commandFail){
						commandFail.printStackTrace();
					}
					if(pathCandidate.equals("a")){
						return false; // Tell caller to abort the whole operation.
					}
					System.out.println("Is the path " + pathCandidate + " correct? (y/n)");
					try{
						inputString = reader.readLine();
					}catch(IOException commandFail){
						commandFail.printStackTrace();
					}
					if(inputString.equals("y")){
						saveFileSystem(pathCandidate);
						return true; // Tell caller save dialog finished normally.
					}
					if(inputString.equals("n")){
						return true; // Tell caller save dialog finished normally.
					}else{
						System.out.println("Invalid choice.");
					}
				}
			}
		}else{
			while(saveComplete == false){
				System.out.println("Would you like to save the current file system to " + sqlFilePath +
						"? (y/n)");
				System.out.println("Input \"a\" to abort.");
				try{
					inputString = reader.readLine();
				}catch(IOException commandFail){
					commandFail.printStackTrace();
				}
				if(inputString.equals("a")){
					return false; // Tell caller to abort the whole operation.
				}
				if(inputString.equals("y")){
					saveFileSystem(sqlFilePath);
					return true; // Tell caller save dialog finished normally.
				}
				if(inputString.equals("n")){
					return true; // Tell caller save dialog finished normally.
				}else{
					System.out.println("Invalid choice.");
				}
			}
		}
		return false; // Tell caller to abort the whole operation.
	}

	void closeFileSystem(String sqlFilePath){
		Boolean closeComplete = false;
		while(closeComplete == false){
			System.out.println("Are you sure you want to close the current file system? (y/n)");
			try{
				inputString = reader.readLine();
			}catch(IOException commandFail){
				commandFail.printStackTrace();
			}
			if(inputString.equals("y")){
				Boolean success = saveDialog(sqlFilePath);
				if(success == false){
					System.out.println("Close operation aborted.");
					return;
				}
				if(success == true){
					fileSys = null;
					System.out.println("File system closed.");
					return;
				}
			}
			if(inputString.equals("n")){
				System.out.println("Close operation cancelled.");
				return;
			}else{
				System.out.println("Invalid choice.");
			}
		}
	}

	void exitCommandLineInterface(String sqlFilePath){
		Boolean exitComplete = false;
		while(exitComplete == false){
			System.out.println("Are you sure you want to exit the Hypergraph File System command line? (y/n)");
			try{
				inputString = reader.readLine();
			}catch(IOException commandFail){
				commandFail.printStackTrace();
			}
			if(inputString.equals("y")){
				Boolean success = saveDialog(sqlFilePath);
				if(success == false){
					System.out.println("Exit operation aborted.");
					return;
				}
				if(success == true){
					exit = true;
					System.out.println("Goodbye.");
					return;
				}
			}
			if(inputString.equals("n")){
				System.out.println("Exit operation cancelled.");
				return;
			}else{
				System.out.println("Invalid choice.");
			}
		}
	}
}