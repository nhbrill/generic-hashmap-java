import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/*
* AUTHOR: Nate Brill
* FILE: MyHashMap.java
* PURPOSE: Java HashMap collection object.
*/

public class MyHashMap<K, V> {
	private int NUMBUCKETS = 8;
	private int size;
	private List<HashNode<K, V>> map;
	private Integer[] conflicts;
	
	public MyHashMap() {
		/*
         * PURPOSE: To initialize a HashMap by setting a 2d list to an
         * ArrayList containing LinkedLists representing nodes that contain the
         * key value pairs. Conflicts keeps track of collisions and size is the
         * size of the HashMap.
         */
		this.size = 0;
		this.conflicts = new Integer[8];
		this.map = new ArrayList<HashNode<K, V>>();
		for (int i = 0; i < NUMBUCKETS; i++) {
			map.add(null);
			conflicts[i] = 0;
		}
	};
	
	public V put(K key, V value) {
		/*
         * PURPOSE: Adds a value to the HashMap first by creating a node with
         * the key value pair and determining whether its hash value maps to
         * another value in which case it adds it to the node list, otherwise
         * it adds the node to the HashMap.
         * 
         * @param key, the key being added.
         * @param value, the value associated with the key being added.
         * 
         * @return rValue, the value associated with the node being replaced or
         * null if no such node exists.
         */
		V rValue = null;
		HashNode<K, V> node = new HashNode<K, V>(key, value);
		if (map.get(hash(key)) == null) { // Adding new value.
			map.set(hash(key), node);
			size ++;
		} else if (this.containsKey(key)) { // Replacing value.
			HashNode<K, V> tempNode = map.get(hash(key));
			while (tempNode != null) {
				if (tempNode.getKey().equals(key)) {
					rValue = tempNode.getValue();
					tempNode.setValue(value);
				}
				tempNode = tempNode.getNext();
			}
		} else { // Collision occurred.
			node.setNext(map.get(hash(key)));
			map.set(hash(key), node);
			conflicts[hash(key)] += 1;
			size ++;
		}
		return rValue;
	};
	
	public V get(K key) {
		/*
         * PURPOSE: To get a value from the HashMap using the hash functions
         * and the linked list. If the key is found it returns the value given
         * to the key, else it returns null if the key is not in the HashMap.
         * 
         * @param key, the key whose value the function is trying to find.
         * 
         * @return V, the value being returned in the key value pair. null if 
         * their is no key equal to the key passed in, in the HashMap.
         */
		HashNode<K, V> tempNode = map.get(hash(key));
		while (tempNode != null) {
			if (tempNode.getKey().equals(key)) {
				return tempNode.getValue();
			}
			tempNode = tempNode.getNext();
		}
		return null;
	};
	
	public Set<K> keySet() {
		/*
         * PURPOSE: To return a set that contains all the keys in the HashMap
         * by iterating through all valid hash indexes in the ArrayList then
         * iterating through the linked list to get the keys.
         * 
         * @return keySet, a set of all the keys in the HashMap.
         */
		Set<K> keySet = new HashSet<K>();
		for (HashNode<K, V> node : map) {
			HashNode<K, V> tempNode = node;
			while (tempNode != null) {
				keySet.add(tempNode.getKey());
				tempNode = tempNode.getNext();
			}
		}
		return keySet;
	};
	
	public boolean containsKey(K key) {
		/*
         * PURPOSE: Determines whether the HashMap contains a certain key. It
         * accomplishes that by searching for the key by getting its hash 
         * value then by iterating through the conflicting nodes at that
         * hash value seeing if the key in the node is equal.
         * 
         * @param key, the key searched for.
         * 
         * @return boolean, returns true if the HashMap contains the key and
         * false if there is no key in the HashMap.
         */
		HashNode<K, V> tempNode = map.get(hash(key));
		while (tempNode != null) {
			if (tempNode.getKey().equals(key)) {
				return true;
			}
			tempNode = tempNode.getNext();
		}
		return false;
	};
	
	public boolean containsValue(V value) {
		/*
         * PURPOSE: Determines whether the HashMap contains a certain value. It
         * accomplishes this task by iterating through all the values to see if
         * node with the value.
         * 
         * @param value, the value searched for.
         * 
         * @return boolean, returns true if the HashMap contains the value and
         * false if there is no value in the HashMap.
         */
		for (HashNode<K, V> node : map) {
			HashNode<K, V> tempNode = node;
			while (tempNode != null) {
				if (tempNode.getValue().equals(value)) {
					return true;
				}
				tempNode = tempNode.getNext();
			}
		}
		return false;
	};
	
	public void clear() {
		/*
         * PURPOSE: To clear the HashMap by setting every value to a new value
         * when the HashMap was initialized making it an empty HashMap.
         */
		this.size = 0;
		this.conflicts = new Integer[8];
		this.map = new ArrayList<HashNode<K, V>>();
		for (int i = 0; i < NUMBUCKETS; i++) {
			map.add(null);
			conflicts[i] = 0;
		}
	};
	
	public V remove(K key) {
		/*
         * PURPOSE: To remove a key in the HashMap first by seeing if it is the
         * first node in the bucket and then if it is the next node when it 
         * finds the node it removes it from the bucket and decreases the size
         * of the HashMap if the key is not in the HashMap it returns null.
         * 
         * @param key, the key being removed.
         * 
         * @return rValue, the value associated with the removed key.
         */
		V rValue = null;
		HashNode<K, V> tempNode = map.get(hash(key));
		if (tempNode != null) { // Checks the first Node.
			if (tempNode.getKey().equals(key)) {
				rValue = tempNode.getValue();
				map.set(hash(key), tempNode.getNext());
				if (conflicts[hash(key)] > 0) {
					conflicts[hash(key)] -= 1;
				}
				size --;
			} else { // Checks the remaining nodes in the list.
				while (tempNode != null) {
					if (tempNode.getNext() != null) {
						if (tempNode.getNext().getKey().equals(key)) {
							rValue = tempNode.getNext().getValue();
							tempNode.setNext(tempNode.getNext().getNext());
							conflicts[hash(key)] -= 1;
							size --;
						}
					}
					tempNode = tempNode.getNext();
				}
			}
		}
		return rValue;
	};
	
	public int size() {
		/*
         * PURPOSE: Returns the size of the HashMap.
         * 
         * @return size, an Integer that represents the amount of nodes or key
         * value pairs in the HashMap. 
         */
		return size;
	};
	
	public boolean isEmpty() {
		/*
         * PURPOSE: Returns whether the HashMap contains any key value pairs.
         * 
         * @return boolean, returns true if the HashMap is empty or false if it
         * contains nodes.
         */
		if (size == 0) {
			return true;
		}
		return false;
	};
	
	public void printTable() {
		/*
         * PURPOSE: To print a string representation of the HashMap showing
         * what collisions occurred and what indexes the keys were hashed to.
         */
		Integer totalConflicts = 0;
		for (int i = 0; i < NUMBUCKETS; i++) {
			String node = "[";
			totalConflicts += conflicts[i];
			HashNode<K, V> tempNode = map.get(i);
			while (tempNode != null) {
				node += tempNode.getKey() + ", ";
				tempNode = tempNode.getNext();
			}
			node += "]";
			System.out.println("Index " + i + ": (" + conflicts[i] +
					" conflicts), " + node);
		}
		System.out.println("Total # of Conflicts: " + totalConflicts);
	};
	
	private int hash(K key) {
		/*
         * PURPOSE: To give a hash value to index a node in the HashMap.
         */
		int hashCode = key.hashCode();
		int index = hashCode % NUMBUCKETS;
		return Math.abs(index);
	};
	
}
