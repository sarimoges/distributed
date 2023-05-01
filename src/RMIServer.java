import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {

            RemoteTesting remoteTesting = new RemoteTestingImpl();


            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RemoteTesting", remoteTesting);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}