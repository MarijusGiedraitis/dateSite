package com.narys.test;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Narys;
import database.NarysDao;

public class DBTest {

	@Test
	public void testSearchingSingleNarysById() {
		
		//int id, String name, String surname, String birthDate, String sex,  
		 //String password, String email) {
	
		
		Narys narys = new Narys(8, "Donatas", "Cibulskas", "1990-05-10", "man",
				"donatascibulskas10", "donatascibulskas@gmail.com");
		NarysDao narysDao = new NarysDao();
		narysDao.deleteNarys(8);
		narysDao.addNarys(narys);
		
		Narys narys2 = narysDao.getNarysById(8);
		
		assertNarysEqual(narys, narys2);
		
	}
	private void assertNarysEqual(Narys narys1, Narys narys2){
		assertEquals(narys1.getId(), narys2.getId());
		assertEquals(narys1.getName(), narys2.getName());
		assertEquals(narys1.getSurname(), narys2.getSurname());
		assertEquals(narys1.getEmail(), narys2.getEmail());
		assertEquals(narys1.getBirthDate(), narys2.getBirthDate());
		assertEquals(narys1.getSex(), narys2.getSex());
		assertEquals(narys1.getPassword(), narys2.getPassword());
}
}
