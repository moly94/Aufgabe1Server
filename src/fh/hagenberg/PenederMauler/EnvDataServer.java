package fh.hagenberg.PenederMauler;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Felix on 07.12.2016.
 */
public class EnvDataServer extends UnicastRemoteObject implements IEnvironmentData{

    public EnvData mPressure;

    public EnvDataServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            EnvDataServer cookieServer= new EnvDataServer();
            Naming.rebind(InetAddress.getLocalHost().getHostAddress()+"/10EnvDataServer", cookieServer);
            System.out.println("Server is waiting!");
        } catch (Exception _e) {
            _e.printStackTrace();
        }
    }

    private void changePressure(){

    }

    @Override
    public String[] requestEnvironmentDataTypes() throws RemoteException {
        String [] s= new String [1];
        s[0]="pressure";
        return s;
    }

    @Override
    public EnvData requestEnvironmentData(String _type) throws RemoteException {
        if (_type.equals("pressure"))
            return new EnvData();
        else
            return null;
    }

    @Override
    public EnvData[] requestAll() throws RemoteException {
        EnvData[] env = new EnvData[1];
        return env;
    }
}
