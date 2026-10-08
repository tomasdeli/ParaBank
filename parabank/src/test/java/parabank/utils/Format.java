package parabank.utils;

import java.text.Normalizer;

public class Format {	
	public static String normalize(String value) {
		value = Normalizer.normalize(value, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase().replaceAll("\\s+", "");
		
		return value;
	}
}