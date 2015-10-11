package HypergraphFileSystem;

import java.util.ArrayList;
import java.util.HashSet;

class ContainerTypeSystem {
	// A container type system is an object which carries all of the container types which may be used to create containers.

	// An ArrayList is used to manage the container types.
	ArrayList<ContainerType> data;

	ContainerTypeSystem(){
		data = new ArrayList<ContainerType>();
		// Create the default "file" container type, which has containerId 0 and can contain no other containers.
		ContainerType type0 = new ContainerType(0, "file", new HashSet<Integer>());
		// Add it to the data ArrayList.
		this.addContainerType(type0);
	}

	// Add a container type to the list of container types.
	void addContainerType(ContainerType type){
		this.data.add(type);
	}

	// Remove a container type from the list of container types.
	void removeContainerType(ContainerType type){
		this.data.remove(type);
	}
}