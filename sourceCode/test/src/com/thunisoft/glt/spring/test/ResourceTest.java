package com.thunisoft.glt.spring.test;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ResourceTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		Resource r = new ClassPathResource("com/thunisoft/glt/spring/test/_applicationContext.xml");
		System.out.println(r.exists());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(r.getFile());
		doc.normalize();
	}
	
}
