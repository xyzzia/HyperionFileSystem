package HypergraphFileSystem;

import java.util.Set;
import java.util.HashSet;

class Container {
	// This is the class for containers, which are like folders in a traditional file system in that they contain files.
	// Containers are also like folders in that they can contain other containers, but unlike folders where any folder could contain another,
	// containers are subject to rules about which other containers they can contain.

	// Each container gets a unique identifier, called a containerId, from the file system which creates it.
	Integer containerId;

	// Each container must have a typeId which is currently in use by the file system.
	// This determines which containers can contain and be contained by the container in question.
	Integer typeId;

	// Each container has a name, which need not be unique, which is displayed to the user.
	String name;

	// Store a file's path in the underlying file system as a string.
	// Some containers act as traditional folders, and thus have no associated path.
	String path;

	// Each container has a set of items located within it. Each item is given by its containerId.
	// Some containers act as files, and thus contain no items.
	HashSet<Integer> items;

	Container(Integer containerId, Integer typeId, String name, String path){
		this.containerId = containerId;
		this.typeId = typeId;
		this.name = name;
		this.path = path;
		items = new HashSet<Integer>();
	}

	Integer getContainerId() {
		return containerId;
	}

	void setContainerId(Integer containerId) {
		this.containerId = containerId;
	}

	Integer getTypeId() {
		return typeId;
	}

	void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getPath() {
		return path;
	}

	void setPath(String path) {
		this.path = path;
	}

	Set<Integer> getItems() {
		return items;
	}

	void setItems(HashSet<Integer> items) {
		this.items = items;
	}

	// Add an item to the container.
	void AddItem(Container item){
		this.items.add(item.containerId);
	}

	// Remove and item from the container.
	void removeItem(Container item){
		this.items.remove(item.containerId);
	}
}