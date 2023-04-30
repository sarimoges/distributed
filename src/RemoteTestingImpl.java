import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteTestingImpl extends UnicastRemoteObject implements RemoteTesting {
    protected RemoteTestingImpl() throws RemoteException {
        super();
    }

    @Override
    public String runTest(String testName) throws RemoteException {
        // execute test and return result
        return "Test result for " + testName;
    }
}