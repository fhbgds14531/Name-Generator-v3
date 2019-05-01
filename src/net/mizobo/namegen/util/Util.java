package net.mizobo.namegen.util;

public class Util {
	public static String capitalize(String s) {
		String returnString = "";
		if (s.contains(" ")) {
			s = s.toLowerCase();
			String[] parts = s.split(" ");
			for (String s1 : parts) {
				char[] chars = s1.toCharArray();
				for(int i = 0; i < chars.length; i++){
					if(i == 0) chars[i] = Character.toTitleCase(chars[i]);
					if(chars[i] == '\"' || chars[i] == '\'' || chars[i] == '-'){
						if(i != chars.length - 1){
							chars[i + 1] = Character.toTitleCase(chars[i+1]);
						}
					}
				}
				returnString += String.valueOf(chars) + " ";
			}
		} else {
			char[] chars = s.toCharArray();
			for(int i = 0; i < chars.length; i++){
				if(i == 0) chars[i] = Character.toTitleCase(chars[i]);
				if(chars[i] == '\"' || chars[i] == '\'' || chars[i] == '-'){
					if(i != chars.length - 1){
						chars[i + 1] = Character.toTitleCase(chars[i+1]);
					}
				}
			}
			returnString += String.valueOf(chars) + " ";
		}
		return returnString.trim();
	}
}
