package com.narys.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import database.Narys;
import database.NarysDao;

public class DBTest2 {

	@Test
	public void testSearchingSingleNarysByName() {
		Narys narys = new Narys(6, "Nerijus", "Klimas", "1996-06-11", "man", "nerijusklimas7", "nerijusklimas@gmail.com");
		NarysDao narysDao = new NarysDao();
		narysDao.deleteNarys(6);
		narysDao.addNarys(narys);
		
		List<Narys> narys2 = narysDao.getNarysByName("Nerijus");
		assertEquals(1, narys2.size());
}
	private void assertStudentEqual(Narys narys, Narys narys2) {	
	}
}