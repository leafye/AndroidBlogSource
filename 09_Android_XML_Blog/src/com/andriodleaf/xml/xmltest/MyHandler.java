package com.andriodleaf.xml.xmltest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX解析类
 * @author AndroidLeaf
 */
public class MyHandler extends DefaultHandler {

	//当开始读取文档标签时调用该方法
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	//当开始读取节点元素标签时调用该方法
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		//do something
	}

	//当读取节点元素的子类信息时调用该方法
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		//do something
	}
	
	//当结束读取节点元素标签时调用该方法
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		//do something
	}
	
	//当结束读取文档标签时调用该方法
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		//do something
	}
}
