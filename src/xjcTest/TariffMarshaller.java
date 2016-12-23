package xjcTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Денис on 12/10/16.
 */
public class TariffMarshaller {
    private String outputFile;
    private JAXBContext jaxbContext;
    private final String PACKAGE_NAME="xjcTest";

    public TariffMarshaller(String outputFile){
        this.outputFile = outputFile;
        try{
            jaxbContext = JAXBContext.newInstance(PACKAGE_NAME);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void marshall(Tarifs tarifs){
        Marshaller m = null;
        try {
            m = jaxbContext.createMarshaller();
            m.marshal(tarifs,new FileOutputStream(outputFile));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
