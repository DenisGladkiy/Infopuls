package XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

/**
 * Created by Денис on 12/4/16.
 */
public class TariffSaxParser extends DefaultHandler {
    private Tariffs tariffs;
    private Tariff tariff;
    private CallPrices callPrices;
    private Parameters parameters;

    private boolean isTariff;
    private boolean isName;
    private boolean isOperator;
    private boolean isPayroll;
    private boolean isCallPrices;
    private boolean isInside;
    private boolean isOutside;
    private boolean isLandline;
    private boolean isSmsPrice;
    private boolean isParameters;
    private boolean isFavoriteNumbers;
    private boolean isCountFrom;
    private boolean isActivationPrice;

    public TariffSaxParser() {
        isName = false;
        isOperator = false;
        isPayroll = false;
        isCallPrices = false;
        isInside = false;
        isOutside = false;
        isLandline = false;
        isSmsPrice = false;
        isParameters = false;
        isFavoriteNumbers = false;
        isCountFrom = false;
        isActivationPrice = false;
    }

    public Tariffs getSaxTariffs(String fileName){
        try{
            File xmlFile = new File(fileName);
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("src/XML/tarifs.xsd"));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            factory.setSchema(schema);
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFile, this);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName){
            case "tns:tarifs":
                tariffs = new Tariffs();
                break;
            case "tarif":
                tariff = new Tariff();
                break;
            case "name":
                isName = true;
                break;
            case "operator_name":
                isOperator = true;
                break;
            case "payroll":
                isPayroll = true;
                break;
            case "call_prices":
                callPrices = new CallPrices();
                break;
            case "inside":
                isInside = true;
                break;
            case "outside":
                isOutside = true;
                break;
            case "landline":
                isLandline = true;
                break;
            case "sms_price":
                isSmsPrice = true;
                break;
            case "parameters":
                parameters = new Parameters();
                break;
            case "favorite_numbers":
                isFavoriteNumbers = true;
                break;
            case "count_from":
                isCountFrom = true;
                break;
            case "activation_price":
                isActivationPrice = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "tarif":
                tariffs.addTariff(tariff);
                break;
            case "call_prices":
                tariff.setCallPrices(callPrices);
                break;
            case "parameters":
                tariff.setParameters(parameters);
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if(isName){
            tariff.setName(new String(ch, start, length));
            isName = false;
        }else if(isOperator){
            tariff.setOperator(new String(ch, start, length));
            isOperator = false;
        }else if(isPayroll){
            tariff.setPayroll(getDouble(ch, start, length));
            isPayroll = false;
        }else if(isInside){
            callPrices.setInside(getDouble(ch, start, length));
            isInside = false;
        }else if(isOutside){
            callPrices.setOutside(getDouble(ch, start, length));
            isOutside = false;
        }else if(isLandline){
            callPrices.setLandline(getDouble(ch, start, length));
            isLandline = false;
        }else if(isSmsPrice){
            tariff.setSms_price(getDouble(ch, start, length));
            isSmsPrice = false;
        }else if(isFavoriteNumbers){
            parameters.setFavoriteNumbers(Integer.parseInt(new String(ch, start, length)));
            isFavoriteNumbers = false;
        }else if(isCountFrom){
            parameters.setCountFrom(Integer.parseInt(new String(ch, start, length)));
            isCountFrom = false;
        }else if(isActivationPrice){
            parameters.setActivationPrice(getDouble(ch, start, length));
            isActivationPrice = false;
        }
    }

    private Double getDouble(char ch[], int start, int length){
        return Double.parseDouble(new String(ch, start, length));
    }
}
