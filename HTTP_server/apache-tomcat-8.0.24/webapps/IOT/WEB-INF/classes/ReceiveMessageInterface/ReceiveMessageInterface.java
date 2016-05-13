package ReceiveMessageInterface;
import java.rmi.*;

public interface ReceiveMessageInterface extends Remote
{
	public void receiveMessage(String query) throws RemoteException;
}

