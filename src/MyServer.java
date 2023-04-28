import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.CosNaming.*;
 
public class MyServer {
    public static void main(String[] args) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
 
            // get reference to root POA and activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
 
            // create the servant object and register it with the ORB
            MyObjectImpl myObj = new MyObjectImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(myObj);
            MyObject href = MyObjectHelper.narrow(ref);
 
            // get reference to the Naming Service and bind the object reference to a name
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "MyObject";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);
 
            System.out.println("MyServer ready and waiting...");
 
            // wait for incoming requests
            orb.run();
 
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
 
class MyObjectImpl extends MyObjectPOA {
    public String sayHello() {
        return "Hello from MyServer!";
    }
}