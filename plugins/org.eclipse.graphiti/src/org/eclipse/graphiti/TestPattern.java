/**
 * 
 */
package org.eclipse.graphiti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xIS02273
 * @since 0.10
 * 
 */
public class TestPattern {

	private static final Pattern PSF_PATTERN = Pattern.compile("<project reference=\"[^,]+,((:[a-z]+:)([a-z|A-Z|0-9]+)"
			+ "(:([0-9]+))?([/|a-z|A-Z|_|0-9]+)),([A-Z|a-z|0-9|_|\\.]+),([A-Z|a-z|0-9|_|\\.]+)(,(.*?)){0,1}\"/>");

	private static final Pattern PSF_PATTERN2 = Pattern.compile("<project reference=\"[^,]+,((:[a-z]+:)([a-z|A-Z|0-9|\\.]+)"
					+ "(:([0-9]+)?)([/|a-z|A-Z|_|0-9]+)),([/|A-Z|a-z|0-9|_|\\.]+),([A-Z|a-z|0-9|_|\\.]+)(,(.*?)){0,1}\"/>");
	
	private static final Pattern PSF_PATTERN3 = Pattern
			.compile("<project reference=\"[^,]+,((:[a-z]+:)([a-z|A-Z|0-9|\\.]+)"
					+ "(:([0-9]+)?)?([/|a-z|A-Z|_|0-9]+)),([/|A-Z|a-z|0-9|_|\\.]+),([A-Z|a-z|0-9|_|\\.]+)(,(.*?)){0,1}\"/>");
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String psfContents = readPSF();
		System.out.println("Hola");
		for (Matcher matcher = PSF_PATTERN3.matcher(psfContents); matcher.find();) {
			System.out.println("Toma " + matcher.toString());
			for (int i = 0; i <= matcher.groupCount(); i++)
				System.out.println("" + i + ": " + matcher.group(i));
			break;
		}
		System.out.println("**************************************");
		for (Matcher matcher = PSF_PATTERN2.matcher(psfContents); matcher.find();) {
			System.out.println("Toma " + matcher.toString());
			for (int i = 0; i <= matcher.groupCount(); i++)
				System.out.println("" + i + ": " + matcher.group(i));
			break;
		}
		// for (Matcher matcher = PSF_PATTERN2.matcher(psfContents);
		// matcher.find();) {
		// System.out.println("Toma " + matcher.toString());
		// }
		System.out.println("Adios");
	}

	private static String readPSF() throws Exception {
		BufferedReader file = new BufferedReader(new FileReader("c:\\users\\xis02273\\Downloads\\VEGA_HEAD.psf"));
		StringBuilder sb = new StringBuilder();
		while (file.ready()) {
			sb.append(file.readLine());
			sb.append("\n");
		}
		file.close();
		return sb.toString();
	}

}
