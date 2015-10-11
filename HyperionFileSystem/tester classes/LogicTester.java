package HypergraphFileSystem;

import java.util.HashSet;

public class LogicTester{

	public static void main(String[] arg){

		//Container type system example
		System.out.println("Create a new container type system, read the properties of the default type,"
				+" then create a new type.");
		ContainerTypeSystem typesys = new ContainerTypeSystem();
		System.out.println("Type ID: "+typesys.data.get(0).typeId);
		System.out.println("Name: "+typesys.data.get(0).name);
		System.out.println("Types contained: "+typesys.data.get(0).typesContained+"\n");
		System.out.println("Add a new contianer type named \"folder\" which can contain folders and files.");
		ContainerType type1 = new ContainerType(1, "folder", new HashSet<Integer>());
		type1.typesContained.add(0);
		type1.typesContained.add(1);
		typesys.data.add(type1);
		System.out.println("Type ID: "+typesys.data.get(1).typeId);
		System.out.println("Name: "+typesys.data.get(1).name);
		System.out.println("Types contained: "+typesys.data.get(1).typesContained+"\n");

		//Type 0 container (file) example
		System.out.println("Create a new type 0 container, otherwise known as a file, named \"test document\".");
		Container file0 = new Container(0, 0, "test document", "root/.../dir");
		System.out.println("Container ID: "+file0.containerId);
		System.out.println("Name: "+file0.name);
		System.out.println("Type ID: "+file0.typeId);
		System.out.println("Directory: "+file0.path+"\n");
		System.out.println("Rename file to \"text document\".");
		file0.name = "text document";
		System.out.println("Name: "+file0.name+"\n");

		//Type 1 container (traditional folder) example
		System.out.println("Create a new type 1 container named \"business notes\".");
		Container folder0 = new Container(1, 1, "business notes", "");
		System.out.println("Container ID: "+folder0.containerId);
		System.out.println("Name: "+folder0.name);
		System.out.println("Type ID: "+folder0.typeId);
		System.out.println("Items: "+folder0.items+"\n");
		System.out.println("Add the file \"text document\" to the folder \"business notes\".");
		folder0.AddItem(file0);
		System.out.println("Items: "+folder0.items+"\n");
		System.out.println("It is also possible to have a folder contain itself."+"\n"
				+"We now add the folder \"business notes\", which has container ID 1, to its own set of contained items.");
		folder0.AddItem(folder0);
		System.out.println("Items: "+folder0.items);
	}
}