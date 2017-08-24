package com.narys.klase;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.narys.klase.UserForma;

import database.Narys;
import database.NarysDao;

public class Main {

    public static void main(String[] args) {
    	
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
     
        NarysDao narysDao = new NarysDao();
        Narys naujas = new Narys(55, "Saulius", "Stankunas", "1998-09-22", "man",
        	"sauliusstankunas22", "sauliusstankunas@gmail.com");

        //narysDao.addNarys(naujas);
        narysDao.updateNarys(naujas);
        //narysDao.deleteNarys(7);
        
        //int i = 0;
        //for (Narys narys : narysDao.getAllNariai()) {
		//System.out.println(++i + " " + narys.getName());
       	//}
        //for (Narys narys : narysDao.getNarysByName("Emilija")) {
        //System.out.println(++i + " " + narys.getName());
        //}

       	//System.out.println(narysDao.getNarysById(160).getName());

        //session.persist(narys);
        //transaction.commit();
        //session.close();

        //System.out.println("Data inserted successfully");
        
        UserForma forma = new UserForma();
    }
}
