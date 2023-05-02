
import org.junit.Test;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import static org.junit.Assert.assertEquals;

public class RemoteTestingTest {
    private static final String SERVER_NAME = "RemoteTesting";
    private static final int REGISTRY_PORT = 1099;

    @Test
    public void testRunTest() throws Exception {

        startServer();

        try {

            RemoteTesting remoteTesting = (RemoteTesting) Naming.lookup("rmi://localhost/" + SERVER_NAME);


            String result = remoteTesting.runTest("Test1");

            assertEquals("Test result for Test1", result);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void startServer() throws RemoteException {
        try {

            RemoteTesting remoteTesting = new RemoteTestingImpl();


            Registry registry = null;
            try {
                registry = LocateRegistry.getRegistry(REGISTRY_PORT);
                registry.list();
            } catch (RemoteException e) {

                registry = LocateRegistry.createRegistry(REGISTRY_PORT);
            }


            Naming.rebind(SERVER_NAME, remoteTesting);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
            throw new RemoteException("Failed to start server", e);
        }
    }
}
