package system;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class FuzzyMatchClass {

	/*
	 * t0 = [SORTED_INTERSECTION] 
	 * t1 = [SORTED_INTERSECTION] + [SORTED_REST_OF_STRING1] 
	 * t2 = [SORTED_INTERSECTION] + [SORTED_REST_OF_STRING2]
	 * 
	 * result = max(t0,t1,t2)
	 * 
	 */

	public int getRatio(String s1, String s2, boolean debug) {
		
		if (s1.length() >= s2.length()) {
			// We need to swap s1 and s2		
			String temp = s2;
			s2 = s1;
			s1 = temp;			
		}

		s1 = escapeString(s1);
		s2 = escapeString(s2);

		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();

		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		
		//сплитим по ' ' -> все слова храним в сетах
		StringTokenizer st1 = new StringTokenizer(s1);		
		while (st1.hasMoreTokens()) {
			set1.add(st1.nextToken());
		}

		StringTokenizer st2 = new StringTokenizer(s2);		
		while (st2.hasMoreTokens()) {
			set2.add(st2.nextToken());
		}

		//находим пересечение множеств (поиск одинаковых слов)
		SetView<String> intersection = Sets.intersection(set1, set2);

		//сортируем
		TreeSet<String> sortedIntersection = Sets.newTreeSet(intersection);

		if (debug) {
		    System.out.print("—ортированное пересечение --> ");
		for (String s:sortedIntersection) 
			System.out.print(s + " ");
		}

		//раличи€ 1
		SetView<String> restOfSet1 = Sets.symmetricDifference(set1, intersection);
		TreeSet<String> sortedRestOfSet1 = Sets.newTreeSet(restOfSet1);

		//раличи€ 2
		SetView<String> restOfSet2 = Sets.symmetricDifference(set2, intersection);
		TreeSet<String> sortedRestOfSet2 = Sets.newTreeSet(restOfSet2);

		if (debug) {
		System.out.print("\nЌевошедшие из 1 сета --> ");
		for (String s:sortedRestOfSet1) 
			System.out.print(s + " ");
		
		System.out.print("\nЌевошедшие из 2 сета -->");
		for (String s:sortedRestOfSet2) 
			System.out.print(s + " ");
		}
		
		String t0 = "";
		String t1 = "";
		String t2 = "";
		
		for (String s:sortedIntersection) {
			t0 = t0 + " " + s;			
		}
		t0 = t0.trim();
		
		Set<String> setT1 = Sets.union(sortedIntersection, sortedRestOfSet1);
		for (String s:setT1) {
			t1 = t1 + " " + s;			
		}
		t1 = t1.trim();
		
		Set<String> setT2 = Sets.union(sortedIntersection, sortedRestOfSet2);
		for (String s:setT2) {
			t2 = t2 + " " + s;			
		}

		t2 = t2.trim();

		int amt1 = calculateLevensteinDistance(t0, t1);
		int amt2 = calculateLevensteinDistance(t0, t2);
		int amt3 = calculateLevensteinDistance(t1, t2);
		
		if (debug) {
			System.out.println();
			System.out.println("t0 = " + t0 + " --> " + amt1);
			System.out.println("t1 = " + t1 + " --> " + amt2);
			System.out.println("t2 = " + t2 + " --> " + amt3);
			System.out.println();
		}
		
		int finalScore = Math.max(Math.max(amt1, amt2), amt3);
		return finalScore;
	}
	
	public static int calculateLevensteinDistance(String s1, String s2) {
		@SuppressWarnings("deprecation")
		int distance = StringUtils.getLevenshteinDistance(s1, s2);
		double ratio = ((double) distance) / (Math.max(s1.length(), s2.length()));
		return 100 - new Double(ratio*100).intValue();	
	}

	public static String escapeString(String token) {

		StringBuffer s = new StringBuffer(token.length());

		CharacterIterator it = new StringCharacterIterator(token);
		for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
			switch (ch) {
			//'-,)(!`\":/][?;~><
			case '\'':
			case '/':
			case '\\':
			case '-':
			case '∞':
			case 'є':
			case 'Ч':
			case '{':
			case '_':
			case ',':
			case ')':
			case '(':
			case '!':
			case '`':
			case '\"':
			case ':':
			case ']':
			case '[':
			case '?':
			case ';':
			case '~':
			case '<':
			case 'Л':
			case '>':
			case '$':
			case '*':
			case '%':
			case '.':
			case '#':
			case '@':
			case 'Ђ':
			case 'ї':
			case 'Е':
			case 'Д':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '0':
				s.append(" ");
				break;
			default:
				s.append(ch);
				break;
			}
		}

		token = s.toString();
		return token;
	}
}