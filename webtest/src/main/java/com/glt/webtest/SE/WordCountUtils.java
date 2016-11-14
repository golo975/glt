package main.java.com.glt.webtest.SE;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * ͳ��word�ĵ���ҳ��
 * 
 * @author gaolong
 *
 */
public class WordCountUtils {

	public static int count = 0;

	public static void main(String[] args) throws Exception {

		// String srcDir = "F:/workdoc";
		String srcDir = "E:/workhy/doc";

		File dir = new File(srcDir);
		// File[] files = dir.listFiles();
		Collection<File> filelist = FileUtils.listFiles(dir, new String[] {
				"doc", "docx" }, true);

		for (File file : filelist) {
			String filePath = file.getPath();
			if ("docx".equalsIgnoreCase(filePath.substring(filePath
					.lastIndexOf('.') + 1))) {
				parse2007(filePath);
			} else if ("doc".equalsIgnoreCase(filePath.substring(filePath
					.lastIndexOf('.') + 1))) {
				try {
					parse2003(filePath);
				} catch (OfficeXmlFileException e) {
					parse2007(filePath);
				}

			}

		}
		System.out.println("�ļ�������" + filelist.size());
		;
		System.out.println("��ҳ����" + count);

	}

	public static void parse2007(String filePath) throws Exception {
		XWPFDocument docx = new XWPFDocument(
				POIXMLDocument.openPackage(filePath));
		int pages = docx.getProperties().getExtendedProperties()
				.getUnderlyingProperties().getPages();// ��ҳ��
		System.out.println(filePath);
		System.out.println(pages);
		count += pages;
		// int wordCount =
		// docx.getProperties().getExtendedProperties().getUnderlyingProperties().getCharacters();//
		// ���Կո�����ַ��� ���⻹��getCharactersWithSpaces()������ȡ���ո����������
		// System.out.println ("pages=" + pages + " wordCount=" + wordCount);
	}

	public static void parse2003(String filePath) throws Exception {
		WordExtractor doc = new WordExtractor(new FileInputStream(filePath));
		int pages = doc.getSummaryInformation().getPageCount();// ��ҳ��
		System.out.println(filePath);
		System.out.println(pages);
		count += pages;
		// int wordCount = doc.getSummaryInformation().getWordCount();//���ַ���
		// System.out.println ("pages=" + pages + " wordCount=" + wordCount);
	}
}
