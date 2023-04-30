import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTesting extends Remote {
    public String runTest(String testName) throws RemoteException;
}
