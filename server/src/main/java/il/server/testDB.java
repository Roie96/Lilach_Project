package il.server;


import il.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class testDB {
    public static Session session;

    private static SessionFactory getSessionFactory(boolean init) throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.

        configuration.configure();
        configuration.setProperty("hibernate.connection.url", Main.databaseName);
        configuration.setProperty("hibernate.connection.password", Main.databasePass);
        if(init)
            configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        else
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.show-sql", "true");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.timezone", "UTC");
        configuration.setProperty("hibernate.connection.serverTimezone", "UTC");
        configuration.setProperty("spring.jpa.hibernate.ddl-auto", "create");
        configuration.addAnnotatedClass(Product.class).addAnnotatedClass(User.class).addAnnotatedClass(Complain.class).addAnnotatedClass(Order.class)
                .addAnnotatedClass(Employee.class).addAnnotatedClass(Store.class).addAnnotatedClass(SystemAdmin.class)
                .addAnnotatedClass(StoreEmployee.class).addAnnotatedClass(NetworkManger.class).addAnnotatedClass(CustomerService.class)
                .addAnnotatedClass(BranchManager.class).addAnnotatedClass(CartProduct.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void generateItems()throws Exception{
        Product flower;

        flower = new Product("whiteroses", 20,true,25,"flower", "white");
        CatalogControl.saveNewFlower(flower,"src/main/resources/images/Lotus.png");

        flower = new Product("sunflower", 50,true, 5, "flower", "yellow");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("chinaFlower", 70,false, 0, "flower", "red");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("pin", 90,false, 0,"flower", "pink");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("whiteroses", 120,true, 50, "flower", "white");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("sunflower", 25.99,true, 50, "flower", "yellow");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("Lotus", 100, true, 10,"flower","pink");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("Rose1", 200,true,25,"flower", "offwhite");
        CatalogControl.saveNewFlower(flower,"src/main/resources/images/Lotus.png");

        flower = new Product("Rose12", 250,true, 5, "flower", "light blue");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("Rose14", 300,false, 0, "flower", "gentle fuchsia");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("Rose13", 350,false, 0,"flower", "fuchsia");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("Rose15", 400,true, 50, "flower", "fuchsia white");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("Rose16", 450,true, 50, "flower", "yellow greenish");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");

        flower = new Product("Rose17", 550, true, 10,"flower","pink");
        CatalogControl.saveNewFlower(flower, "src/main/resources/images/Lotus.png");


        session.flush();
    }

    private static void generateStores(List<Product> products){
        SystemAdmin a = new SystemAdmin("admin admin", "admin", "adminadmin");
        testDB.session.save(a);
        Store store = new Store("Haifa");
        testDB.session.save(store);
        BranchManager b = new BranchManager("Malki Grossman", "malki123456" , "123456789", store);
        session.save(b);
        session.flush();

        StoreEmployee e = new StoreEmployee("Shir Snea", "shir123456" , "123456789");
        session.save(e);
        store.addEmployee(e);
        session.flush();

        e = new StoreEmployee("Liran Eliav", "liran123456" , "123456789");
        session.save(e);
        store.addEmployee(e);
        session.flush();

        Store store2 = new Store("Tel Aviv");
        testDB.session.save(store2);
        b = new BranchManager("Dean Amar", "dean123456" , "123456789", store2);
        session.save(b);

        e = new StoreEmployee("Ido Shitrit", "ido123456" , "123456789");
        session.save(e);
        store2.addEmployee(e);
        e = new StoreEmployee("Roie Shahar", "roie123456" , "123456789");
        session.save(e);
        store2.addEmployee(e);
        session.flush();

        Store store3 = new Store("Jerusalem");
        testDB.session.save(store3);
        b = new BranchManager("Itai Zeitony", "itai123456" , "123456789", store3);
        session.save(b);

        e = new StoreEmployee("Shahar Tavor", "shahar123456" , "123456789");
        session.save(e);
        store3.addEmployee(e);

        CustomerService j = new CustomerService("Shir Tzadok", "shir123456" , "123456789");
        session.save(j);

        NetworkManger i = new NetworkManger("Alla Wakbar", "alla123", "123456789");
        session.save(i);


        User u1 = new User("ido7746", "123456789", "1234567812345678", 1, "ido", "123456789");
        User u2 = new User("haziza8", "123456789", "1234567812345678", 1, "dolev", "123456789");
        User u3 = new User("cr7", "123456789", "1234567812345678", 1, "cristiano", "123456789");
        User u4 = new User("robocolos", "123456789", "1234567812345678", 1, "ori shahr", "123456789");
        User u5 = new User("goat", "123456789", "1234567812345678", 2, "leo messi", "123456789");
        User u6 = new User("boom", "123456789", "1234567812345678", 3, "maldini", "123456789");

        testDB.session.save(u1);
        testDB.session.save(u2);
        testDB.session.save(u3);
        testDB.session.save(u4);
        testDB.session.save(u5);
        testDB.session.save(u6);

        store.addUser(u1);
        store.addUser(u2);
        store.addUser(u3);

        store2.addUser(u1);
        store2.addUser(u4);
        store2.addUser(u5);
        store2.addUser(u3);
        store2.addUser(u2);

        store3.addUser(u6);
        store3.addUser(u2);
        store3.addUser(u3);
        session.flush();
    }

    public static void openSession(){
        try {
            System.out.println("open session to mySQL");
            SessionFactory sessionFactory = getSessionFactory(false);
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error: cant open session to mySQL.");
            exception.printStackTrace();
        }
    }

    public static void initMySQL(){
        try {
            System.out.println("open session to mySQL");
            SessionFactory sessionFactory = getSessionFactory(true);
            session = sessionFactory.openSession();
            session.beginTransaction();
            generateItems();
            session.getTransaction().commit(); // Save everything.
            closeSession();
            List<Product> products = SimpleServer.getAllItems(Product.class);
            openSession();
            generateStores(products);
            session.getTransaction().commit(); // Save everything.
            closeSession();

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error: cant init mySQL!");
            exception.printStackTrace();
        } finally {
            session.close();
            System.out.println("close session to mySQL");
            System.out.println("init mySQL!");
        }
    }

    public static void closeSession(){
        try {
            if (session != null) {
                session.getTransaction().rollback();
            }
            session.close();
            System.out.println("close session to mySQL");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}