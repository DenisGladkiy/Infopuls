package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;


/**
 * Created by Денис on 12/5/16.
 */
public class TariffDomParser {

    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    private Document domDocument;

    public Tariffs parseXmlDomFile(String fileName){
        Tariffs tariffs = null;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            File file = new File(fileName);
            domDocument = docBuilder.parse(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        Node n = domDocument.getFirstChild();
        if(n.getNodeName().equals("tns:tarifs")){
            tariffs = makeTariffsObject(domDocument);
        }
        return tariffs;
    }

    private Tariffs makeTariffsObject(Document document) {
        Tariffs tariffs = new Tariffs();
        Tariff tariff;
        Node node;
        NodeList nodeList = document.getElementsByTagName("tarif");
        for(int i = 0; i < nodeList.getLength(); i++){
            node = nodeList.item(i);
            tariff = makeTariff(node);
            tariffs.addTariff(tariff);
        }
        return tariffs;
    }

    private Tariff makeTariff(Node node){
        Tariff tariff = null;
        if(node.getNodeName().equals("tarif")) {
            tariff = new Tariff();
            NodeList nl = node.getChildNodes();
            for(int j = 0; j < nl.getLength(); j++){
                Node n = nl.item(j);
                String element = n.getNodeName();
                switch (element){
                    case "name":
                        tariff.setName(n.getTextContent());
                        break;
                    case "operator_name":
                        tariff.setOperator(n.getTextContent());
                        break;
                    case "payroll":
                        tariff.setPayroll(getDouble(n.getTextContent()));
                        break;
                    case "sms_price":
                        tariff.setSms_price(getDouble(n.getTextContent()));
                        break;
                    case "call_prices":
                        CallPrices callPrices = makeCallPrices(n);
                        tariff.setCallPrices(callPrices);
                        break;
                    case "parameters":
                        Parameters parameters = makeParameters(n);
                        tariff.setParameters(parameters);
                        break;
                }
            }
        }
        return tariff;
    }

    private Parameters makeParameters(Node node) {
        Parameters parameters = new Parameters();
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++){
            Node n = nodeList.item(i);
            String element = n.getNodeName();
            switch (element){
                case "favorite_numbers":
                    parameters.setFavoriteNumbers(Integer.parseInt(n.getTextContent()));
                    break;
                case "count_from":
                    parameters.setCountFrom(Integer.parseInt(n.getTextContent()));
                    break;
                case "activation_price":
                    parameters.setActivationPrice(getDouble(n.getTextContent()));
                    break;
            }
        }
        return parameters;
    }

    private CallPrices makeCallPrices(Node node){
        CallPrices prices = new CallPrices();
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++){
            Node n = nodeList.item(i);
            String element = n.getNodeName();
            switch (element){
                case "inside":
                    prices.setInside(getDouble(n.getTextContent()));
                    break;
                case "outside":
                    prices.setOutside(getDouble(n.getTextContent()));
                    break;
                case "landline":
                    prices.setLandline(getDouble(n.getTextContent()));
                    break;
            }
        }
        return prices;
    }

    private Double getDouble(String s){
        return Double.parseDouble(s);
    }
}
