package xjcTest;

/**
 * Created by Денис on 12/10/16.
 */
public class Main {

    public static void main(String[] args) {
        ObjectFactory factory = new ObjectFactory();
        TariffMarshaller marshaller = new TariffMarshaller("E://java//InfoPulseUniver//src//xjcTest//tariffs.xml");
        marshaller.marshall(factory.createTarifs());
    }
}
