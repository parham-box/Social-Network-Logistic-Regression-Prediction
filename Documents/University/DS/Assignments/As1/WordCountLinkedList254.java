
import java.io.File;
import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap;
import java.util.LinkedList;

public class WordCountLinkedList254 {
	public static Entry<String, Integer> count_ARRAY(String[] tokens) {
		int CAPACITY = tokens.length;
		String[] words = new String[CAPACITY];
		int[] counts = new int[CAPACITY];
		for (int j = 0; j < tokens.length; j++) {
			String token = tokens[j];
			for (int i = 0; i < CAPACITY; i++) {
				if (words[i] == null) {
					words[i] = token;
					counts[i] = 1;
					break;
				} else if (words[i].equals(token))
					counts[i] = counts[i] + 1;
			}
		}

		int maxCount = 0;
		String maxWord = "";
		for (int i = 0; i < CAPACITY & words[i] != null; i++) {
			if (counts[i] > maxCount) {
				maxWord = words[i];
				maxCount = counts[i];
			}
		}
		return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
	}



	public static Entry<String, Integer> countFAST(String[] tokens) {
	int CAPACITY = tokens.length;
	tokens = mergeSort(tokens);
	String[] words = new String[CAPACITY];
	int[] counts = new int[CAPACITY];
	int x = 0;
	words[0] = tokens[0];

	for (int j = 0; j < tokens.length; j++) {
		if (words[x] != null && words[x].equals(tokens[j])) {
			counts[x]++;
		}else{
			x++;
			words[x] = tokens[j];
			counts[x] = 1;
		}
	}

	int maxCount = 0;
	String maxWord = "";
	for (int i = 0; i < CAPACITY & words[i] != null; i++) {
		if (counts[i] > maxCount) {
			maxWord = words[i];
			maxCount = counts[i];
		}
	}

	return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
}


public static String[] mergeSort(String[] array) {
		String [] sorted = new String[array.length];
		//break rule
		if (array.length == 1) {
				sorted = array;
		} else {
				int mid = array.length/2;
				String[] left = null;
				String[] right = null;
				if ((array.length % 2) == 0) {
						left = new String[array.length/2];
						right = new String[array.length/2];
				} else {
						left = new String[array.length/2];
						right = new String[(array.length/2)+1];
				}

				int j=0;
				for (int i=0; i < mid; i++) {
						left[i] = array[i];
				}
				for (int i = mid; i < array.length; i++) {
						right[j++] = array[i];
				}
				left = mergeSort(left);
				right = mergeSort(right);
				sorted = mergeArray(left,right);
		}

		return sorted;
}

private static String[] mergeArray(String[] left, String[] right) {

	//the size of parent array
		String[] mergedArray = new String[left.length+right.length];
		int leftIndex = 0;
		int rightIndex = 0;
		int mergedIndex = 0;
		int comp = 0;
		while (leftIndex < left.length || rightIndex < right.length) {
				if (leftIndex == left.length) {
						mergedArray[mergedIndex++] = right[rightIndex++];
				} else if (rightIndex == right.length) {
						mergedArray[mergedIndex++] = left[leftIndex++];
				} else {
						comp = left[leftIndex].compareTo(right[rightIndex]);
						//the rightIndex element is smaller
						if (comp > 0) {
								mergedArray[mergedIndex++] = right[rightIndex++];
						} else if (comp < 0) {
								mergedArray[mergedIndex++] = left[leftIndex++];
						} else {
							//equals
								mergedArray[mergedIndex++] = left[leftIndex++];
						}
				}
		}
		return mergedArray;
}



	public static Entry<String, Integer> count_LINKED_LIST(String[] tokens) {
		LinkedList<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>();
		for (int j = 0; j < tokens.length; j++) {
			String word = tokens[j];
			boolean found = false;
			for (int i = 0; i < list.size(); i++) {
				Entry<String, Integer> e = list.get(i);

				if (word.equals(e.getKey())) {
					e.setValue(e.getValue() + 1);
					list.set(i, e);
					found = true;
					break;
				}
			}
			if (!found)
				list.add(new AbstractMap.SimpleEntry<String, Integer>(word, 1));
		}

		int maxCount = 0;
		String maxWord = "";
		for (int i = 0; i < list.size(); i++) {
			int count = list.get(i).getValue();
			if (count > maxCount) {
				maxWord = list.get(i).getKey();
				maxCount = count;
			}
		}
		return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
	}


public static Entry<String, Integer> count_LINKED_LIST_GOOD(String[] tokens) {
	LinkedList<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>();
	for (int j = 0; j < tokens.length; j++) {
		String word = tokens[j];
		boolean found = false;
		Integer i = 0;
		for (Entry<String, Integer> e : list) {
			if (word.equals(e.getKey())) {
				e.setValue(e.getValue() + 1);
				list.set(i, e);
				i++;
				found = true;
				break;
			}
		}
		if (!found)
			list.add(new AbstractMap.SimpleEntry<String, Integer>(word, 1));
	}

	int maxCount = 0;
	String maxWord = "";
	for (int i = 0; i < list.size(); i++) {
		int count = list.get(i).getValue();
		if (count > maxCount) {
			maxWord = list.get(i).getKey();
			maxCount = count;
		}
	}
	return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
}



	static String[] readText(String PATH) throws Exception {
		Scanner doc = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
		int length = 0;
		while (doc.hasNext()) {
			doc.next();
			length++;
		}

		String[] tokens = new String[length];
		Scanner s = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
		length = 0;
		while (s.hasNext()) {
			tokens[length] = s.next().toLowerCase();
			length++;
		}
		doc.close();

		return tokens;
	}

	public static void main(String[] args) throws Exception {

		String PATH = "/Users/mrbox/Documents/University/DS/As1/data/dblp3200.txt";
		String[] tokens = readText(PATH);
		long startTime = System.currentTimeMillis();
		// Entry<String, Integer> entry = count_LINKED_LIST(tokens);
		long endTime = System.currentTimeMillis();
		String time = String.format("%12d", endTime - startTime);
		// System.out.println("Bad: time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());

		tokens = readText(PATH);
		startTime = System.currentTimeMillis();
		Entry<String, Integer> entry = count_LINKED_LIST_GOOD(tokens);
		endTime = System.currentTimeMillis();
		time = String.format("%12d", endTime - startTime);
		System.out.println("Good: time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());
		//
		//
		tokens = readText(PATH);
		startTime = System.currentTimeMillis();
		entry = count_ARRAY(tokens);
		endTime = System.currentTimeMillis();
		time = String.format("%12d", endTime - startTime);
		System.out.println("Array: time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());


		tokens = readText(PATH);
		startTime = System.currentTimeMillis();
		entry = countFAST(tokens);
		endTime = System.currentTimeMillis();
		time = String.format("%12d", endTime - startTime);
		System.out.println("Fast: time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());


	}

}
