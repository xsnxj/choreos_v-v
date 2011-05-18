package br.usp.ime.choreos.vvws.ws;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class StoreTest {

	@Test
	public void testPurchaseShouldAlwaysWork(){
		StoreWS ws = new StoreWS();

		assertTrue(ws.purchase(null, null));
		
		assertTrue(ws.purchase(new CD(null, null, null, null), new Customer()));
		
	}
	
	@Test
	public void testSearchByArtistWithMock(){
		StoreWS ws = new StoreWS();
		
		List<CD> cds = ws.searchByArtist("Non existant artist");
		assertTrue(cds.isEmpty());
		
		cds = ws.searchByArtist("Floyd");
		assertEquals(cds.size(), 1);
		assertEquals(cds.get(0).getArtist(), "Pink Floyd");
	
		// Case sensitive search
		cds = ws.searchByArtist("floyd");
		assertTrue(cds.isEmpty());
	}
	
	@Test
	public void testSearchByGenreWithMock(){
		StoreWS ws = new StoreWS();
		
		List<String> rockArtists = new ArrayList<String>();
		rockArtists.add("Pink Floyd");
		rockArtists.add("Led Zeppelin");
		rockArtists.add("Bon Jovi");
		rockArtists.add("The Beatles");

		List<CD> cds = ws.searchByGenre("Non existant genre");
		assertTrue(cds.isEmpty());
		
		cds = ws.searchByGenre("Rock");
		assertEquals(cds.size(), 4);
		for(CD cd : cds){
			assertTrue(rockArtists.contains(cd.getArtist()));
		}
	}
	
	
}
