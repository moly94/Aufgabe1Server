package fh.hagenberg.PenederMauler;

import com.sun.deploy.util.Waiter;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;

/**
 * Created by Felix on 07.12.2016.
 */
public class EnvDataServer extends UnicastRemoteObject implements IEnvironmentData{

    public static EnvData mEnvData;

    private static EnvDataTask task;

    public EnvDataServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            EnvDataServer envServer= new EnvDataServer();
            /*Naming.rebind(InetAddress.getLocalHost().getHostAddress()+"/EnvDataServer", envServer);
            System.out.println("Server is waiting!");*/

            String codebase= "http://" + (InetAddress.getLocalHost().getHostAddress()) + ":8080/EnvDataServer/";
            // last"/"
            System.setProperty("java.rmi.server.codebase", codebase);
            // startlocalRMI-Registry if needed
            //LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Registry reg = LocateRegistry.getRegistry("127.0.0.1");
            // registerService
            reg.rebind("EnvDataServer", envServer);
            System.out.println("Der Server wartet auf Anfragen");
            Timer dataTimer = new Timer();
            mEnvData=new EnvData();
            task=new EnvDataTask(mEnvData);
            dataTimer.schedule(task, 0, 2000);
            System.out.println(task.getEnvData().getAirPressure());
        } catch (Exception _e) {
            _e.printStackTrace();
        }
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
            return task.getEnvData();
        else
            return null;
    }

    @Override
    public EnvData[] requestAll() throws RemoteException {
        EnvData[] env = new EnvData[1];
        env[0]=task.getEnvData();
        return env;
    }
}
