package com.andriodleaf.xml.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.andriodleaf.xml.entity.Person;

public class XmlTools {

	/**--------------SAX解析XML-------------------*/
	/**
	 * @param mInputStream 需要解析的person.xml的文件流对象
	 * @param nodeName 节点名称
	 * @return mList Person对象集合
	 */
	public static ArrayList<Person> saxAnalysis(InputStream mInputStream,String nodeName){
		//1、获取创建一个SAX解析工厂实例
		SAXParserFactory mSaxParserFactory = SAXParserFactory.newInstance();
		try {
			//2、调用工厂实例中的newSAXParser()方法创建SAXParser解析对象
			SAXParser mSaxParser = mSaxParserFactory.newSAXParser();
			//3、实例化CustomHandler(DefaultHandler的子类)
			CustomHandler mHandler = new CustomHandler(nodeName);
			/**
			 * 4、连接事件源对象XMLReader到事件处理类DefaultHandler中
			 * 查看parse(InputStream is, DefaultHandler dh)方法源码如下：
			 * public void parse(InputSource is, DefaultHandler dh)
			 *      throws SAXException, IOException {
			 *      if (is == null) {
			 *           throw new IllegalArgumentException("InputSource cannot be null");
			 *       }
			 *     // 获取事件源XMLReader，并通过相应事件处理器注册方法setXXXX()来完成的与ContentHander、DTDHander、ErrorHandler，
			 *     // 以及EntityResolver这4个接口的连接。
			 *       XMLReader reader = this.getXMLReader();
			 *       if (dh != null) {
			 *           reader.setContentHandler(dh);
			 *           reader.setEntityResolver(dh);
			 *           reader.setErrorHandler(dh);
			 *           reader.setDTDHandler(dh);
			 *       }
			 *       reader.parse(is);
			 *   }
			 */
			mSaxParser.parse(mInputStream, mHandler);
			//5、通过DefaultHandler返回我们需要的数据集合
			return mHandler.getList();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param is
	 * @param dh
	 * @throws SAXException
	 * @throws IOException
	 */
	 
	
	/**--------------DOM解析XML-------------------*/
	/**
	 * @param mInputStream 需要解析的person.xml的文件流对象
	 * @return mList Person对象集合
	 */
	public static ArrayList<Person> domAnalysis(InputStream mInputStream){
		ArrayList<Person> mList = new ArrayList<Person>();
		
		//1、创建文档对象工厂实例
		DocumentBuilderFactory mDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			//2、调用DocumentBuilderFactory中的newDocumentBuilder()方法创建文档对象构造器
			DocumentBuilder mDocumentBuilder = mDocumentBuilderFactory.newDocumentBuilder();
			//3、将文件流解析成XML文档对象
			Document mDocument = mDocumentBuilder.parse(mInputStream);
			//4、使用mDocument文档对象得到文档根节点
			Element mElement = mDocument.getDocumentElement();
			//5、根据名称获取根节点中的子节点列表
			NodeList mNodeList =  mElement.getElementsByTagName("person");
			//6 、获取子节点列表中需要读取的节点信息
			for(int i = 0 ;i < mNodeList.getLength();i++){
				Person mPerson = new Person();
				Element personElement = (Element) mNodeList.item(i);
				//获取person节点中的属性
				if(personElement.hasAttributes()){
					mPerson.setId(Integer.parseInt(personElement.getAttribute("id")));
				}
				if(personElement.hasChildNodes()){
				//获取person节点的子节点列表
				 NodeList mNodeList2 = personElement.getChildNodes();
				 //遍历子节点列表并赋值
				 for(int j = 0;j < mNodeList2.getLength();j++){
					    Node mNodeChild = mNodeList2.item(j);
						if(mNodeChild.getNodeType() == Node.ELEMENT_NODE){
							if("name".equals(mNodeChild.getNodeName())){
								mPerson.setUserName(mNodeChild.getFirstChild().getNodeValue());
							}else if("height".equals(mNodeChild.getNodeName())){
								mPerson.setHeight(Float.parseFloat(mNodeChild.getFirstChild().getNodeValue()));
							}else if("imageurl".equals(mNodeChild.getNodeName())){
								mPerson.setImageUrl(mNodeChild.getFirstChild().getNodeValue());
							}
						}
					}
				}
				mList.add(mPerson);
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	
	/**--------------PULL解析XML-------------------*/
	/**
	 * @param mInputStream 需要解析的person.xml的文件流对象
	 * @param encode 设置字符编码
	 * @return mList Person对象集合
	 */
	public static ArrayList<Person> PullAnalysis(InputStream mInputStream,String encode){
		ArrayList<Person> mList = null;
		Person mPerson = null;
		try {
			//1、获取PULL解析工厂实例对象
			XmlPullParserFactory mXmlPullParserFactory = XmlPullParserFactory.newInstance();
			//2、使用XmlPullParserFactory的newPullParser()方法实例化PULL解析实例对象
			XmlPullParser mXmlPullParser = mXmlPullParserFactory.newPullParser();
			//3、设置需解析的XML文件流和字符编码
			mXmlPullParser.setInput(mInputStream, encode);
			//4、获取事件解析类型
			int eventType = mXmlPullParser.getEventType();
			//5、循环遍历解析，当文档解析结束时结束循环
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				//开始解析文档
				case XmlPullParser.START_DOCUMENT:
					mList = new ArrayList<Person>();
					break;
				//开始解析节点
				case XmlPullParser.START_TAG:
					if("person".equals(mXmlPullParser.getName())){
						mPerson = new Person();
						//获取该节点中的属性的数量
						int attributeNumber = mXmlPullParser.getAttributeCount();
						if(attributeNumber > 0){
							//获取属性值
							mPerson.setId(Integer.parseInt(mXmlPullParser.getAttributeValue(0)));
						}
					}else if("name".equals(mXmlPullParser.getName())){
						//获取该节点的内容
						mPerson.setUserName(mXmlPullParser.nextText());
					}else if("height".equals(mXmlPullParser.getName())){
						mPerson.setHeight(Float.parseFloat(mXmlPullParser.nextText()));
					}else if("imageurl".equals(mXmlPullParser.getName())){
						mPerson.setImageUrl(mXmlPullParser.nextText());
					}
					break;
				//解析节点结束
				case XmlPullParser.END_TAG:
					if("person".equals(mXmlPullParser.getName())){
						mList.add(mPerson);
						mPerson = null;
					}
					break;
				default:
					break;
				}
				eventType = mXmlPullParser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
}
