package patterncreater;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parameters.Symbols;

public class MapUtil {

	final private static DecimalFormat df = new DecimalFormat("#.##");

	public static void orderAndWrite(Map<String, Double> map, FileWriter fw, int the_top_k, int the_size)
			throws IOException {
//		System.out.println("patterns: ");
//		System.out.println("database size: " + the_size);
		// System.out.println(map);
		List list = new ArrayList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Double>) (o2)).getValue()
						.compareTo(((Map.Entry<String, Double>) (o1)).getValue());
			}
		});
		int count = 0;
		// System.out.println("write: ");
		for (Iterator it = list.iterator(); it.hasNext();) {
			// System.out.println("it: ");
			if (count++ < the_top_k) {
				// System.out.println("top k: "+count);
				Map.Entry<String, Double> entry = (Map.Entry<String, Double>) it.next();
				fw.write(entry.getKey() + Symbols.PATTERN_SEPARATOR_IT + df.format(entry.getValue() / the_size) + "\n");
//				System.out.println(
//						"key: " + entry.getKey() + Symbols.PATTERN_SEPARATOR_IT + entry.getValue().toString() + "\n");
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("A", 0.5);
		map.put("B", 0.3);
		map.put("CD", 0.9);
		// orderAndWrite(map, null, 2);

	}
}