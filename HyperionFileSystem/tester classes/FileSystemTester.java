package HypergraphFileSystem;

import java.util.HashSet;

public class FileSystemTester{

	public static void main(String[] arg){

		// File system example
		System.out.println("Create a new file system.\n");
		FileSystem fileSys = new FileSystem();

		//Container type system example
		System.out.println("Examine the default container type system.");
		System.out.println("Type IDs in use: "+fileSys.activeTypeIds());
		System.out.println("Read the properties of the default type, then create a new type.");
		System.out.println("Type ID: 0");
		System.out.println("Name: "+fileSys.getTypeName(0));
		System.out.println("Types contained: "+fileSys.getTypesContained(0));
		System.out.println("Add a new contianer type named \"folder\" which can contain folders and files.");
		fileSys.addContainerType(1, "folder", new HashSet<Integer>());
		fileSys.addTypeContained(1, 0);
		fileSys.addTypeContained(1, 1);
		System.out.println("Type ID: 1");
		System.out.println("Name: "+fileSys.getTypeName(1));
		System.out.println("Types contained: "+fileSys.getTypesContained(1));
		System.out.println("Check the type IDs in use again.");
		System.out.println("Type IDs in use: "+fileSys.activeTypeIds()+"\n");

		//Type 0 container (file) example
		System.out.println("Create a new type 0 container, otherwise known as a file, named \"test document\".");
		fileSys.addContainer(0, 0, "test document", "/home/testdoc.odt");
		System.out.println("Container ID: 0");
		System.out.println("Name: "+fileSys.getContainerName(0));
		System.out.println("Type ID: "+fileSys.getContainerTypeId(0));
		System.out.println("Directory: "+fileSys.getContainerPath(0));
		System.out.println("Rename file to \"text document\".");
		fileSys.setContainerName(0, "text document");
		System.out.println("Name: "+fileSys.getContainerName(0)+"\n");

		//Type 1 container (traditional folder) example
		System.out.println("Create a new type 1 container named \"business notes\".");
		fileSys.addContainer(1, 1, "business notes", "");
		System.out.println("ContainerId: 1");
		System.out.println("Name: "+fileSys.getContainerName(1));
		System.out.println("Type ID: "+fileSys.getContainerTypeId(1));
		System.out.println("Items: "+fileSys.getContainerItems(1)+"\n");
		System.out.println("Add the file \"text document\" to the folder \"business notes\".");
		fileSys.addContainerItem(1,0);
		System.out.println("Items: "+fileSys.getContainerItems(1)+"\n");
		System.out.println("It is also possible to have a folder contain itself."+"\n"
				+"We now add the folder \"business notes\", which has container ID 1, to its own set of contained items.");
		fileSys.addContainerItem(1,1);
		System.out.println("Items: "+fileSys.getContainerItems(1));
	}
}