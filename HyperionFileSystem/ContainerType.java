package HypergraphFileSystem;

import java.util.HashSet;

class ContainerType {
	// A container type defines the rules which containers of that type obey, in particular which other container types they may contain.

	// Each container type gets a unique identifier, called a typeId, from the file system which creates it.
	Integer typeId;

	// Each container type has a name, which need not be unique, which is displayed to the user.
	String name;

	// This determines which containers be contained by containers of the type in question.
	HashSet<Integer> typesContained;

	ContainerType(Integer typeId, String name, HashSet<Integer> typesContained){
		this.typeId = typeId;
		this.name = name;
		this.typesContained = typesContained;
	}
}