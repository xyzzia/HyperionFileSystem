package HypergraphFileSystem;

import java.util.ArrayList;
import java.util.HashSet;

class FileSystem{
	// This class handles the behavior of the file system.  Only this class and the Adapter class are called by the CommandLineInterface class.

	// A particular file system will use a container type system to determine which containers are allowed to contain each other.
	ContainerTypeSystem typeSys;

	// An ArrayList is used to manage the existing containers in the file system.
	ArrayList<Container> containers;

	// A default container is used as the "root" of the file system.
	Integer rootId;

	FileSystem(){
		this.typeSys = new ContainerTypeSystem();
		this.containers = new ArrayList<Container>();
		addContainer(0, 0, "default file", "");
		this.rootId = 0;
	}

	// These methods are used for manipulating the root of the file system.

	// Get the root container's container ID.
	Integer getRootId(Integer rootId){
		return this.rootId;
	}

	// Set the root container's container ID.
	void setRootId(Integer rootId){
		this.rootId = rootId;
	}

	// These methods are used for manipulating the container type system.

	// Add a container type.
	void addContainerType(Integer typeId, String typename, HashSet<Integer> typesContained){
		this.typeSys.addContainerType(new ContainerType(typeId, typename, typesContained));
	}

	// Remove a container type by giving its typeId, which is supposed to be unique.
	void removeContainerType(Integer typeId){
		for(ContainerType type : this.typeSys.data){
			if(type.typeId == typeId){
				this.typeSys.removeContainerType(type);
				break;
			}
		}
	}

	// Get a list of typeIDs in use.
	ArrayList<Integer> activeTypeIds(){
		ArrayList<Integer> lis = new ArrayList<Integer>();
		for(ContainerType type : this.typeSys.data){
			lis.add(type.typeId);
		}
		return lis;
	}

	// These methods are for manipulating a particular container type.

	// Get the name for a given typeId.
	String getTypeName(Integer typeId){
		for(ContainerType type : this.typeSys.data){
			if(type.typeId == typeId){
				return type.name;
			}
		}
		return "";
	}

	// Set the name for a given typeId.
	void setTypeName(Integer typeId, String name){
		for(ContainerType type : this.typeSys.data){
			if(type.typeId == typeId){
				type.name = name;
			}
		}
	}

	// Get the type IDs of all the types contained for a given typeId.
	HashSet<Integer> getTypesContained(Integer typeId){
		HashSet<Integer> set = new HashSet<Integer>();
		for(ContainerType type : this.typeSys.data){
			if(type.typeId == typeId){
				set = type.typesContained;
			}
		}
		return set;
	}

	// Set the type IDs contained for a given typeId.
	void setTypesContained(Integer typeId, HashSet<Integer> set){
		for(ContainerType type : this.typeSys.data){
			if(type.typeId == typeId){
				type.typesContained = set;
				break;
			}
		}
	}

	// Add a type ID contained for a given typeId.
	void addTypeContained(Integer typeId, Integer containedTypeId){
		for(ContainerType type : this.typeSys.data){
			if(type.typeId == typeId){
				type.typesContained.add(containedTypeId);
				break;
			}
		}
	}

	// Remove a type ID contained for a given typeId.
	void removeTypeContained(Integer typeId, Integer containedTypeId){
		for(ContainerType type : this.typeSys.data){
			if(type.typeId == typeId){
				type.typesContained.remove(containedTypeId);
				break;
			}
		}
	}

	// These methods are for manipulating the list of active containers.

	// Add a container.
	void addContainer(Integer containerId, Integer typeId, String name, String directory){
		this.containers.add(new Container(containerId, typeId, name, directory));
	}

	// Remove a container by giving its containerId, which is supposed to be unique.
	void removeContainer(Integer containerId){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				this.containers.remove(container);
				break;
			}
		}
	}

	// Get a list of container IDs in use.
	ArrayList<Integer> activeContainerIds(){
		ArrayList<Integer> lis = new ArrayList<Integer>();
		for(Container container : this.containers){
			lis.add(container.containerId);
		}
		return lis;
	}

	// Get a list of container type IDs in use.
	// Note that this is not the same as the method for the type system,
	// as some type IDs may exist in the type system but remain unused in the file system.
	ArrayList<Integer> activeContainerTypeIds(){
		ArrayList<Integer> lis = new ArrayList<Integer>();
		for(Container container : this.containers){
			lis.add(container.typeId);
		}
		return lis;
	}

	// These methods are for manipulating a particular container.

	// Get the name for a given containerId.
	String getContainerName(Integer containerId){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				return container.name;
			}
		}
		return "";
	}

	// Set the name for a given containerId.
	void setContainerName(Integer containerId, String name){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				container.name = name;
				break;
			}
		}
	}
	
	// Get the typeId for a given containerId.
	Integer getContainerTypeId(Integer containerId){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				return container.typeId;
			}
		}
		return -1;
	}

	// Set the typeId for a given containerId.
	void setContainerTypeId(Integer containerId, Integer typeId){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				container.typeId = typeId;
				break;
			}
		}
	}

	// Get the path for a given container ID in the underlying file system.
	String getContainerPath(Integer containerId){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				return container.path;
			}
		}
		return "";
	}

	// Set the path for a given container ID in the underlying file system.
	void setContainerPath(Integer containerId, String directory){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				container.path = directory;
				break;
			}
		}
	}

	// Get the items contained for a given container ID.
	HashSet<Integer> getContainerItems(Integer containerId){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				return container.items;
			}
		}
		return new HashSet<Integer>();
	}

	// Set the items contained for a given container ID.
	void setContainerItems(Integer containerId, HashSet<Integer> items){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				container.items = items;
			}
		}
	}
	
	// Add an item to a given container ID.
	void addContainerItem(Integer containerId, Integer itemContainerId){
		for(Container container : this.containers){
			for(Integer typeId : getTypesContained(getContainerTypeId(containerId))){
				if(getContainerTypeId(itemContainerId) == typeId){
					container.items.add(itemContainerId);
				}
			}
		}
	}

	// Remove an item from a given container ID.
	void removeContainerItem(Integer containerId, Integer itemContainerId){
		for(Container container : this.containers){
			if(container.containerId == containerId){
				container.items.remove(itemContainerId);
			}
		}
	}
}