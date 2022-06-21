package il.server;

import il.entities.*;

import java.io.IOException;
public class OrderControl {



    public static void cancelOrder(int id) throws Exception {
        testDB.openSession();
        Order a = testDB.session.get(Order.class, id);
        if(a.isCanceled())
            throw new Exception("order "+a.getId()+ "allready canceld");
        a.setStatus(1);
        a.setCanceled(true);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

    public static void sendNewOrder(Order o){
        try{
            String title = "New order from Lilach Store";
            String text = "Hey!, \n we receive you order number " + o.getId() + ",\n to "+o.getAddress()+".\n";
            SendEmail.sendTo(o.getReciveEmail(),title,text);
        }
        catch (Exception e){

        }
    }


    public static void deliverdOrder(int orderId)  {
        testDB.openSession();
        Order o = testDB.session.get(Order.class, orderId);
        o.setStatus(2);
        String email = o.getUser().getMail();
        String text = "Hey "+o.getUser().getName()+" we glad to inform you that you order has been shipped"+"\nHave a nice day, Lilach team.";
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
        SendEmail.sendTo(email,"Your Order has been shipped!",text);
        System.out.println("Order has been delivered");

    }


    public static void refund(int id, double refund){
        testDB.openSession();
        User u = testDB.session.get(User.class, id);
        u.setCredit(u.getCredit()+refund);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();

    }



    public static Order newOrder(Order order, int storeID, int userID) throws IOException {
        testDB.openSession();
        Store store = testDB.session.get(Store.class, storeID);
        User user = testDB.session.get(User.class, userID);

        if(!user.getListstore().contains(store)){
            System.out.println(user.getUserName() + " try to made order is store that he never register!");
        }
        else{
            for(CartProduct p : order.getProducts())
                testDB.session.save(p);

            testDB.session.save(order);
            user.addOrder(order);
            store.addOrder(order);
        }

        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
        if(!order.getReciveEmail().equals(""))
            sendNewOrder(order);
        return order;
    }

}
