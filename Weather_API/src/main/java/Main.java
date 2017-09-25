import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main
{

    public static void main(String[] args) throws Exception
    {
        Server server = new Server();

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);
       // server.setDumpAfterStart(true);
        server.addConnector(connector);

        ProtectionDomain domain = Main.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        
        System.out.println("----------");
        System.out.println( Main.class.getResource("/WEB-INF/jcgrealm.txt"));
        System.out.println("----------");
        
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());
		HashLoginService loginService = new HashLoginService("JCGRealm");
		loginService.setConfig( Main.class.getResource("/WEB-INF/jcgrealm.txt").toString());
		server.addBean(loginService);
        server.setHandler(webapp);
        
        server.start();
        server.join();
 
    }
}