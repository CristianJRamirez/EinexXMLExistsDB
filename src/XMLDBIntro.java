/**
 * Created by 45858000w on 20/03/17.
 */
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import java.io.File;


public class XMLDBIntro {
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static String driver = "org.exist.xmldb.DatabaseImpl";

    public static void main(String args[]) throws XMLDBException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{

        afegirFitxer("mondial2.xml");

    }

    private static void afegirFitxer(String fl) throws XMLDBException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{
        File f = new File("mondial2.xml");

        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // crear el manegador
        DatabaseManager.registerDatabase(database);

        // adquirir la col·lecció que volem tractar
        Collection col = DatabaseManager.getCollection(URI+"/db","admin","admin");
        System.out.println(col.getName());

        //Creem la col·lecció on guardarem el recurs
        CollectionManagementService colmgt = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        //l'hi donem un nom a la nova col·lecció
        col = colmgt.createCollection("Recursos");
                
        //afegir el recurs que farem servir
        Resource res = col.createResource("mondial2.xml","XMLResource");
        res.setContent(f);
        col.storeResource(res);
                
                
                
        //Creamos un nuevo recurso vacio dentro del programa y le damos el recurso mondial2.xml que esta dentro de la col·lección
        Resource res2 = col.getResource("mondial2.xml");

        //Mostramos el contenido de un recurso por pantalla
        System.out.println( res2.getContent() );


    }
}
