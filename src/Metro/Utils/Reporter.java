package Metro.Utils;

import Metro.Driver;
import Metro.Line;
import Metro.Station;
import Metro.Train;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Денис on 12/7/16.
 */
public class Reporter {
    final String FILE_NAME = "E://java/metro.xml";
    final static String NAMESPACE = "http://www.univer.com/metro";
    private XmlSerializer serializer;
    private XmlPullParserFactory factory;
    List<Line> lines = null;

    public Reporter(List<Line> lines) {
        try {
            factory = XmlPullParserFactory.newInstance();
            serializer = factory.newSerializer();
            factory.setNamespaceAware(true);
            this.lines = lines;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public void report(){
        try {
            serializer.setOutput(new PrintWriter(FILE_NAME, "UTF-8"));
            serializer.setPrefix("", NAMESPACE);
            serializer.startDocument("UTF-8", true);
            serializer.text("\n");
            startTag("metro");
            for(Line l : lines){
                reportMetroLine(l);
            }
            endTag("metro");
            serializer.endDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reportMetroLine(Line line) throws IOException {
        HashMap<String, String> attrs = new HashMap<String, String>();
        attrs.clear();
        attrs.put("name", line.getName());
        startTag("line", attrs);
        startTag("stations");
        for(Station s : line.getStations()){
            reportMetroStation(s);
        }
        endTag("stations");
        startTag("trains");
//        for(Train t : line.getTrains()){
//            reportMetroTrain(t);
//        }
        endTag("trains");
        endTag("line");
    }

    private void reportMetroStation(Station station) throws IOException {
        HashMap<String, String> attrs = new HashMap<String, String>();
        int passengersNumber = station.getPassengers().size();
        String passengers = String.valueOf(passengersNumber);
        attrs.clear();
        attrs.put("name", station.getName());
        startTag("station", attrs);
        writeLine("passengers", passengers);
        endTag("station");
    }

    private void reportMetroTrain(Train train) throws IOException {
        HashMap<String, String> attrs = new HashMap<String, String>();
        Driver driver = train.getDriver();
        int id = train.getTrainId();
        String trainId = String.valueOf(id);
        attrs.clear();
        attrs.put("ID", trainId);
        startTag("train", attrs);
        writeLine("total_passengers", String.valueOf(train.getTotalPassengers()));
        startTag("driver");
        writeLine("driver_name", driver.getName());
        writeLine("driver_exp", String.valueOf(driver.getExperience()));
        endTag("driver");
        startTag("carriages");
        writeLine("carriage", train.getCarr1().getType().toString());
        writeLine("carriage", train.getCarr2().getType().toString());
        writeLine("carriage", train.getCarr3().getType().toString());
        writeLine("carriage", train.getCarr4().getType().toString());
        writeLine("carriage", train.getCarr5().getType().toString());
        endTag("carriages");
        endTag("train");
    }

    private void startTag(String tag) throws IllegalArgumentException, IllegalStateException, IOException {
        serializer.startTag(NAMESPACE, tag);
        serializer.text("\n");
    }

    private void startTag(String tag, HashMap<String, String> attributes)
            throws IllegalArgumentException, IllegalStateException, IOException {
        serializer.startTag(NAMESPACE, tag);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            serializer.attribute("", entry.getKey(), entry.getValue());
        }
        serializer.text("\n");
    }

    private void endTag(String tag) throws IllegalArgumentException, IllegalStateException, IOException {
        serializer.endTag(NAMESPACE, tag);
        serializer.text("\n");
    }

    private void writeLine(String tag, String line) throws IOException {
        serializer.startTag(NAMESPACE, tag);
        serializer.text(line);
        serializer.endTag(NAMESPACE, tag);
        serializer.text("\n");
    }
}
