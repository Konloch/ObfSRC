package com.konloch.obfsrc;

/**
 * @author Konloch
 * @since 2/26/2023
 */
public class ObfSRC
{
	private final String pretendJavaStart;
	private final String pretendJavaEnd;
	private final String realJava;
	
	public ObfSRC(String pretendJavaStart, String pretendJavaEnd, String realJava)
	{
		this.pretendJavaStart = pretendJavaStart;
		this.pretendJavaEnd = pretendJavaEnd;
		this.realJava = realJava;
	}
	
	public String obfuscate()
	{
		return obfuscate(false);
	}
	
	public String obfuscate(boolean bypassObfuscation)
	{
		return pretendJavaStart + (!bypassObfuscation ? unicodeEscape(pretendJavaEnd + realJava + "//") : pretendJavaEnd + realJava + "//") + pretendJavaEnd;
	}
	
	private static String unicodeEscape(String string)
	{
		StringBuilder b = new StringBuilder();
		for (char c : string.toCharArray())
			b.append("\\u").append(String.format("%04X", (int) c));
		return b.toString();
	}
}
