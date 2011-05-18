package br.usp.ime.choreos.vv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.usp.ime.choreos.vv.exceptions.ParserException;

public class RequestBuilderTest {

	@Test
	public void shouldReturnSameSimpleXmlWithNoParameters() throws ParserException{
		String sampleXml = "<senv:Envelope>" 
			+ "<senv:Body>"
			+ "<tns:search_by_brandResponse>" 
			+ "</tns:search_by_brandResponse>"
			+ "</senv:Body>" 
			+ "</senv:Envelope>";

		String result = new RequestBuilder().buildRequest(sampleXml, null);

		assertEquals(sampleXml, result);
	}

	@Test
	public void shouldReturnSameSlighltyMoreComplicatedXml() throws ParserException{
		String sampleXml = "<senv:Envelope " 
			+ "xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2003/03/addressing\" " 
			+ "xmlns:tns=\"tns\">" 
			+ "<senv:Body>"
			+ "<tns:search_by_brandResponse xmln=\"schema\">" 
			+ "<tns:search_by_brandResult>" 
			+ "</tns:search_by_brandResult>" 
			+ "</tns:search_by_brandResponse>" 
			+ "</senv:Body>" 
			+ "</senv:Envelope>";

		String result = new RequestBuilder().buildRequest(sampleXml, null);

		assertEquals(sampleXml, result);
	}
	
	@Test
	public void shouldReturnXmlWithOneParameterReplacedWithTheProperContent() throws ParserException{
		String sampleXml = "<senv:Envelope " 
			+ "                             xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2003/03/addressing\" " 
			+ "                             xmlns:tns=\"tns\">" 
			+ "<senv:Body>"
			+ "    <tns:search_by_brand xmln=\"schema\">" 
			+ "            <s1:name>?</s1:name>" 
			+ "    </tns:search_by_brand>" 
			+ "</senv:Body>" 
			+ "</senv:Envelope>";
		
		Item root = new ItemImpl("search_by_brand");
		Item child = new ItemImpl("name");
		child.setContent("test");
		root.addChild(child);
		
		String result = new RequestBuilder().buildRequest(sampleXml, root);
		
		String expectedXml = "<senv:Envelope " 
			+ "xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2003/03/addressing\" " 
			+ "xmlns:tns=\"tns\">" 
			+ "<senv:Body>"
			+ "<tns:search_by_brand xmln=\"schema\">" 
			+ "<s1:name>test</s1:name>" 
			+ "</tns:search_by_brand>" 
			+ "</senv:Body>" 
			+ "</senv:Envelope>";
		
		assertEquals(expectedXml, result);
	}
	
	@Test
	public void shouldReturnXmlWithSeveralParametersReplacedWithTheProperContent() throws ParserException{
		String sampleXml = "<senv:Envelope " + 
		"xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2003/03/addressing\" " + 
		"xmlns:tns=\"tns\"> " + 
		"<senv:Body>" +
		"<tns:search_by_categoryResponse>  " +
		"<tns:search_by_categoryResult>" +
		"<s1:Item>" +
		"<s1:category>?</s1:category>  " +
		"<s1:price>?</s1:price>" +
		"<s1:model>?</s1:model>  " +
		"<s1:brand>?</s1:brand>" +
		"</s1:Item>" +
		"<s1:Item>" +
		"<s1:category>?</s1:category>" +
		"<s1:price>?</s1:price>" +
		"<s1:model>?</s1:model>" +
		"<s1:brand>?</s1:brand>" +
		"</s1:Item>" +
		"<s1:Item>" +
		"<s1:category>?</s1:category>" +
		"<s1:price>?</s1:price>  " +
		"<s1:model>?</s1:model>" +
		"<s1:brand>?</s1:brand>" +
		"</s1:Item>" +                                  
		"</tns:search_by_categoryResult>" +
		"</tns:search_by_categoryResponse>" +
		"</senv:Body>" +
		"</senv:Envelope>";
		
		Item root = new ItemImpl("search_by_category");

		Item item1 = new ItemImpl("Item");
		Item childA = new ItemImpl("category");
		childA.setContent("mouse");
		item1.addChild(childA);                  
		Item childB = new ItemImpl("price");
		childB.setContent("89.2");
		item1.addChild(childB);                  
		Item childC = new ItemImpl("model");
		childC.setContent("RZG145");
		item1.addChild(childC);                  
		Item childD = new ItemImpl("brand");
		childD.setContent("Razor");
		item1.addChild(childD);          
		root.addChild(item1);

		Item item2 = new ItemImpl("Item");
		Item childE = new ItemImpl("category");
		childE.setContent("mouse");
		item2.addChild(childE);                  
		Item childF = new ItemImpl("price");
		childF.setContent("61.0");
		item2.addChild(childF);                  
		Item childG = new ItemImpl("model");
		childG.setContent("CCCC");
		item2.addChild(childG);                  
		Item childH = new ItemImpl("brand");
		childH.setContent("Clone");
		item2.addChild(childH);          
		root.addChild(item2);

		Item item3 = new ItemImpl("Item");
		Item childI = new ItemImpl("category");
		childI.setContent("mouse");
		item3.addChild(childI);                  
		Item childJ = new ItemImpl("price");
		childJ.setContent("61.0");
		item3.addChild(childJ);                  
		Item childK = new ItemImpl("model");
		childK.setContent("MS23F");
		item3.addChild(childK);                  
		Item childL = new ItemImpl("brand");
		childL.setContent("Microsoft");
		item3.addChild(childL);          
		root.addChild(item3);
		
		String result = new RequestBuilder().buildRequest(sampleXml, root);
		
		String expectedXml = "<senv:Envelope " + 
		"xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2003/03/addressing\" " + 
		"xmlns:tns=\"tns\"> " + 
		"<senv:Body>" +
		"<tns:search_by_categoryResponse>  " +
		"<tns:search_by_categoryResult>" +
		"<s1:Item>" +
		"<s1:category>mouse</s1:category>  " +
		"<s1:price>89.2</s1:price>  " +
		"<s1:model>RZG145</s1:model>  " +
		"<s1:brand>Razor</s1:brand>" +
		"</s1:Item>" +
		"<s1:Item>" +
		"<s1:category>mouse</s1:category>" +
		"<s1:price>61.0</s1:price>" +
		"<s1:model>CCCC</s1:model>" +
		"<s1:brand>Clone</s1:brand>" +
		"</s1:Item>" +
		"<s1:Item>" +
		"<s1:category>mouse</s1:category>" +
		"<s1:price>  61.0</s1:price>  " +
		"<s1:model>MS23F</s1:model>" +
		"<s1:brand>Microsoft</s1:brand>" +
		"</s1:Item>" +                                  
		"</tns:search_by_categoryResult>" +
		"</tns:search_by_categoryResponse>" +
		"</senv:Body>" +
		"</senv:Envelope>";
		
		assertEquals(expectedXml, result);
	}

}
