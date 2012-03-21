/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cargo;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bouncycastle.util.encoders.Base64;

import com.blucargo.BlucargoRequestHandler;
import com.blucargo.model.CargoOffer;

//import org.jivesoftware.sparkimpl.plugin.cargo.model.CargoOffer;


/**
 *
 * @author kadet
 */
public class SerializationTest {

    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(null);
//        System.out.println("Entity Manager FActory created");
//        EntityManager em = emf.createEntityManager();
//        System.out.println("Entity Manager created");


//        Injector injector = Guice.createInjector(new HibernateModule());
//        System.out.println("Created injector");
//        org.hibernate.cfg.Configuration hibernateConfig = injector.getInstance(org.hibernate.cfg.Configuration.class);
//
//        SessionFactory sessionFactory = hibernateConfig.buildSessionFactory();
//        System.out.println("SessionFactory created");

//
//                Configuration cfg = new Configuration().
//                //addClass(org.hibernate.auction.Item.class).
////                addClass(org.hibernate.auction.Bid.class).
//                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect").
////                setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/test").
//                setProperty("hibernate.order_updates", "true").
//                setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver").
//                setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/cargo").
//                setProperty("hibernate.connection.username", "root").
//                setProperty("hibernate.connection.password", "").
//                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect").
////                setProperty("hibernate.current_session_context_class", "").
////                setProperty("hibernate.cache.provider_class", "").
//                setProperty("hibernate.show_sql", "true").
//                setProperty("hibernate.hbm2ddl.auto", "update");
//
//                SessionFactory session = cfg.buildSessionFactory();
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cargo";
            Connection con = DriverManager.getConnection(url, "root", "");

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet srs = stmt.executeQuery("SELECT * FROM CargoOffer");

            System.out.println("Select query executed");
            while (srs.next()) {
                int id = srs.getInt("id");
                String cityFrom = srs.getString("cityfrom");
                System.out.println(id + "     " + cityFrom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlucargoRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlucargoRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        CargoOffer co = new CargoOffer();
        co.setCityFrom("Oslo");
        ArrayList<CargoOffer> offerList = new ArrayList<CargoOffer>();

        offerList.add(co);
        offerList.add(co);
        offerList.add(co);


        ByteArrayOutputStream bos = null;
        ObjectOutputStream out = null;
        try {
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(offerList);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String toEncode=new String(bos.toByteArray());

        byte[] encodedText;
        Base64 base64 = new Base64();

        encodedText = base64.encode(bos.toByteArray());
        String sEncodedText = new String(encodedText);

        ArrayList<CargoOffer> offerList2 = new ArrayList<CargoOffer>();
        byte[] decodedText = base64.decode(sEncodedText.getBytes());

        ByteArrayInputStream bis = null;
        ObjectInputStream in = null;
        try {
            bis = new ByteArrayInputStream(decodedText);
            in = new ObjectInputStream(bis);
            offerList2 = (ArrayList<CargoOffer>) in.readObject();
            System.out.println("offerList size:" + offerList.size());
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }



    }
}
