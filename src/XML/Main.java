package XML;

/**
 * Created by Денис on 12/4/16.
 */
public class Main {
    public static void main(String[] args) {
        TariffSaxParser saxParser = new TariffSaxParser();
        Tariffs tariffs = saxParser.getSaxTariffs("E:/java/InfoPulseUniver/src/XML/mobile.xml");
        System.out.println(tariffs);
        TariffDomParser domParser = new TariffDomParser();
        tariffs = domParser.parseXmlDomFile("E:/java/InfoPulseUniver/src/XML/mobile.xml");
        System.out.println(tariffs);
    }
}
