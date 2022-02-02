package dataStructures.dictionary;
import dataStructures.list.List;

import java.util.Iterator;

import dataStructures.list.ArrayList;
import dataStructures.set.AVLSet;
import dataStructures.set.Set;
import dataStructures.tuple.Tuple2;

/**
 * Estructuras de Datos. Grados en Informatica. UMA.
 * Examen de septiembre de 2018.
 *
 * Apellidos, Nombre:
 * Titulacion, Grupo:
 */
public class HashBiDictionary<K,V> implements BiDictionary<K,V>{
	private Dictionary<K,V> bKeys;
	private Dictionary<V,K> bValues;
	
	public HashBiDictionary() {
		bKeys = new HashDictionary<>();
		bValues = new HashDictionary<>();
	}
	
	public boolean isEmpty() {
		return bKeys.isEmpty();
	}
	
	public int size() {
		return bKeys.size();
	}
	
	public void insert(K k, V v) {
		if(bKeys.isDefinedAt(k)){
			bKeys.delete(k);
			bValues.delete(v);
		}
		bKeys.insert(k, v);
		bValues.insert(v, k);
	}
	
	public V valueOf(K k) {
		return bKeys.valueOf(k);
	}
	
	public K keyOf(V v) {
		return bValues.valueOf(v);
	}
	
	public boolean isDefinedKeyAt(K k) {
		return bKeys.isDefinedAt(k);
	}
	
	public boolean isDefinedValueAt(V v) {
		return bValues.isDefinedAt(v);
	}
	
	public void deleteByKey(K k) {
		if(bKeys.isDefinedAt(k)){
			V v = bKeys.valueOf(k);
			bKeys.delete(k);
			bValues.delete(v);
		}
	}
	
	public void deleteByValue(V v) {
		if(bValues.isDefinedAt(v)){
			K k = bValues.valueOf(v);
			bKeys.delete(k);
			bValues.delete(v);
		}
	}
	
	public Iterable<K> keys() {
		return bKeys.keys();
	}
	
	public Iterable<V> values() {
		return bValues.keys();
	}
	
	public Iterable<Tuple2<K, V>> keysValues() {
		return bKeys.keysValues();
	}
	
		
	public static <K,V extends Comparable<? super V>> BiDictionary<K, V> toBiDictionary(Dictionary<K,V> dict) {
		Iterator<V> i = dict.values().iterator();
		V v;
		Set<V> aux = new AVLSet<>();
		while(i.hasNext()){
			v = i.next();
			if(aux.isElem(v)){
				throw new IllegalArgumentException("No inyectivo");
			}
		}
		BiDictionary<K,V> aux2 = new HashBiDictionary<>();
		for(Tuple2<K,V> a : dict.keysValues()){
			aux2.insert(a._1(), a._2());
		}
		return aux2;
	}
	
	public <W> BiDictionary<K, W> compose(BiDictionary<V,W> bdic) {
		BiDictionary<K,W> aux = new HashBiDictionary<>();
		for(Tuple2<K,V> a : this.keysValues()){
			if(bdic.isDefinedKeyAt(a._2())){
				aux.insert(a._1(), bdic.valueOf(a._2()));
			}
		}
		return aux;
	}
		
	public static <K extends Comparable<? super K>> boolean isPermutation(BiDictionary<K,K> bd) {
		return bd.keys()==bd.values();
	}
	
	// Solo alumnos con evaluaci�n por examen final.
    // =====================================
	
	public static <K extends Comparable<? super K>> List<K> orbitOf(K k, BiDictionary<K,K> bd) {
		// TODO
		return null;
	}
	
	public static <K extends Comparable<? super K>> List<List<K>> cyclesOf(BiDictionary<K,K> bd) {
		// TODO
		return null;
	}

    // =====================================
	
	
	@Override
	public String toString() {
		return "HashBiDictionary [bKeys=" + bKeys + ", bValues=" + bValues + "]";
	}
	
	
}
