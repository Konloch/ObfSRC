package com.konloch;

import com.konloch.disklib.DiskReader;
import com.konloch.obfsrc.ObfSRC;

import java.io.IOException;

/**
 * @author Konloch
 * @since 2/19/2023
 */
public class TestObfSRC
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Static Variable Example:\n" +
				new ObfSRC("public static final String unicodeFilter = \"",
						"\";",
				"static " + DiskReader.readString("./src/test/java/com/konloch/HiddenSourceCodeExample.txt"))
				.obfuscate());
		
		System.out.println("Method Function Call Example:\n" +
				new ObfSRC("System.out.println(\"", "\");", DiskReader.readString("./src/test/java/com/konloch/HiddenSourceCodeExample.txt"))
				.obfuscate());
		
		System.out.println("Comments Example:\n" +
				new ObfSRC("//", "", "\nstatic " + DiskReader.readString("./src/test/java/com/konloch/HiddenSourceCodeExample.txt"))
				.obfuscate());
		
		System.out.println("Doc-Comments Example:\n" +
				new ObfSRC("/*", "*/", "static " + DiskReader.readString("./src/test/java/com/konloch/HiddenSourceCodeExample.txt"))
				.obfuscate());
	}
}
