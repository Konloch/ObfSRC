package com.konloch.obfsrc.gui;

import com.konloch.disklib.DiskReader;
import com.konloch.obfsrc.ObfSRC;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * @author Konloch
 * @since 7/16/2023
 */
public class ObfGUI
{
	private JFrame frame;
	private JTextArea sourceCode;
	private JScrollPane sourceCodeSP;
	private JTextArea commentsObfCode;
	private JScrollPane commentsObfCodeSP;
	private JTextArea docCommentsObfCode;
	private JScrollPane docCommentsObfCodeSP;
	private JTextArea methodCallObfCode;
	private JScrollPane methodCallObfCodeSP;
	
	public void build()
	{
		sourceCode = new JTextArea(null, "//any valid Java code can be inserted here\n" + "//keep in mind you - need to use fully qualified names\n" + "{\n" + "    System.out.println(\"Hidden Source Code Execution Example\");\n" + "}", 10, 60);
		commentsObfCode = new JTextArea(null, null, 10, 60);
		docCommentsObfCode = new JTextArea(null, null, 10, 60);
		methodCallObfCode = new JTextArea(null, null, 10, 60);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Comments", commentsObfCodeSP = new JScrollPane(commentsObfCode));
		tabbedPane.add("Doc-Comments", docCommentsObfCodeSP = new JScrollPane(docCommentsObfCode));
		tabbedPane.add("Console Out", methodCallObfCodeSP = new JScrollPane(methodCallObfCode));
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				UIManager.getBoolean("SplitPane.continuousLayout"),
				new JButton(UIManager.getString("SplitPane.leftButtonText")),
				new JButton(UIManager.getString("SplitPane.rightButtonText")));
		split.add(sourceCodeSP = new JScrollPane(sourceCode), JSplitPane.TOP);
		split.add(tabbedPane, JSplitPane.BOTTOM);
		
		frame = new JFrame("ObfSRC");
		frame.setLayout(new BorderLayout());
		frame.add(split, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		sourceCode.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e)
			{
				obfSRC();
			}
			
			@Override
			public void removeUpdate(DocumentEvent e)
			{
				obfSRC();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e)
			{
				obfSRC();
			}
		});
		
		obfSRC();
	}
	
	private void obfSRC()
	{
		final String source = sourceCode.getText();
		final String consoleOut = "Method Function Call Example:\n" + new ObfSRC("System.out.println(\"", "\");", source).obfuscate();
		final String comment = "Comments Example:\n" + new ObfSRC("//", "", "\nstatic " + source).obfuscate();
		final String docComment = "Doc-Comments Example:\n" + new ObfSRC("/*", "*/", "static " + source).obfuscate();
		
		SwingUtilities.invokeLater(()->{
			commentsObfCode.setText(comment);
			commentsObfCode.setCaretPosition(0);
			docCommentsObfCode.setText(docComment);
			docCommentsObfCode.setCaretPosition(0);
			methodCallObfCode.setText(consoleOut);
			methodCallObfCode.setCaretPosition(0);
		});
	}
	
	public static void main(String[] args)
	{
		ObfGUI gui = new ObfGUI();
		gui.build();
	}
}
