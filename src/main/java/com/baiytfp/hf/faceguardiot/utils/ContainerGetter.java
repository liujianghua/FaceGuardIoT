package com.baiytfp.hf.faceguardiot.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerGetter {
	public static <K, V> HashMap<K, V> hashMap() {
		return new HashMap<K, V>();
	}

	public static <K, V> HashMap<K, V> hashMap(int capacity) {
		return new HashMap<K, V>(capacity);
	}

	public static <K extends Enum<K>, V> EnumMap<K, V> enumMap(Class<K> type) {
		return new EnumMap<K, V>(type);
	}

	public static <K, V> ConcurrentHashMap<K, V> concurHashMap() {
		return new ConcurrentHashMap<K, V>();
	}

	public static <K, V> TreeMap<K, V> treeMap() {
		return new TreeMap<K, V>();
	}

	public static <K, V> TreeMap<K, V> treeMap(Comparator<K> comparator) {
		return new TreeMap<K, V>(comparator);
	}

	public static <K, V> ConcurrentHashMap<K, V> concurHashMap(int capacity) {
		return new ConcurrentHashMap<K, V>(capacity);
	}

	public static <T> ArrayList<T> arrayList(Collection<T> list) {
		return new ArrayList<T>(list);
	}

	public static <T> LinkedHashSet<T> linkedHashSet() {
		return new LinkedHashSet<T>();
	}

	public static <K, V> LinkedHashMap<K, V> linkedHashMap() {
		return new LinkedHashMap<K, V>();
	}

	public static <T> LinkedHashSet<T> linkedHashSet(Collection<T> set) {
		return new LinkedHashSet<T>(set);
	}

	public static <T> HashSet<T> hashSet(Collection<T> set) {
		return new HashSet<T>(set);
	}

	public static <T> ArrayList<T> arrayList() {
		return new ArrayList<T>();
	}

	public static <T> ArrayList<T> arrayList(int capacity) {
		return new ArrayList<T>(capacity);
	}

	public static <T> LinkedList<T> linkedList() {
		return new LinkedList<T>();
	}

	public static <T> LinkedList<T> linkedList(Collection<T> collection) {
		return new LinkedList<T>(collection);
	}

	public static <T> HashSet<T> hashSet(int capacity) {
		return new HashSet<T>(capacity);
	}

	public static <T> HashSet<T> hashSet() {
		return new HashSet<T>();
	}

	public static <T> TreeSet<T> treeSet() {
		return new TreeSet<T>();
	}

	public static <T> TreeSet<T> treeSet(Comparator<T> comparator) {
		return new TreeSet<T>(comparator);
	}

	public static <T> ConcurrentLinkedQueue<T> concurLinkedQueue() {
		return new ConcurrentLinkedQueue<T>();
	}

	public static <T> PriorityQueue<T> priorityQueue(Comparator<T> comparator) {
		return new PriorityQueue<T>(11, comparator);
	}

	public static <T> PriorityQueue<T> priorityQueue() {
		return new PriorityQueue<T>();
	}

	public static <T> CopyOnWriteArrayList<T> copyOnWriteArrayList() {
		return new CopyOnWriteArrayList<T>();
	}

	public static <T> CopyOnWriteArrayList<T> copyOnWriteArrayList(Collection<T> coll) {
		return new CopyOnWriteArrayList<T>(coll);
	}

	public static <T> Set<T> treeSet(Collection<T> coll) {
		TreeSet<T> treeSet = new TreeSet<T>(coll);
		return treeSet;
	}

}