import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLFilter extends DefaultHandler {
    private FileWriter writer;
    private StringBuilder currentCourse;
    private StringBuilder currentStudent;
    private String currentElement;
    private boolean isLowScore;
    private boolean hasLowScore;


    public XMLFilter() {
        try {
            writer = new FileWriter("src/main/xmls/xml5.xml");
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<courses>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if (qName.equals("course")) {
            currentCourse = new StringBuilder();
            hasLowScore = false;//开始认定为没有
        } else if (qName.equals("student")) {
            currentStudent = new StringBuilder();


        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("course")) { //到达退出course，如果hasLowScore，才生成这个course，否则不生成
            if(hasLowScore){
                try {
                    writer.write("<course>\n");
                    writer.write(currentCourse.toString());
                    writer.write("</course>\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            isLowScore = false;
        } else if (qName.equals("student")) {
            //退出student，如果是低于60分则写入，否则不写入
            if (isLowScore) {
                currentCourse.append(currentStudent.toString());
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).trim();
        if (currentElement.equals("courseName")) {
            currentCourse.append(data).append("\n");
        } else if (currentElement.equals("studentId")) {
            currentStudent.append(data).append("    ");
        } else if (currentElement.equals("studentName")) {
            currentStudent.append(data).append("    ");
        } else if (currentElement.equals("totalScore")) {
            if (data.matches("\\d+")) {
                int score = Integer.parseInt(data);
                if (score < 60) { System.out.println(score);
                    isLowScore = true;
                    hasLowScore = true;//有一个就是true，表示一定要加进去
                    currentStudent.append(data).append("\n");
                }
                else {
                    currentStudent = new StringBuilder();
                }
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            writer.write("</courses>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLFilter handler = new XMLFilter();
            saxParser.parse(new File("src/main/xmls/xml4.xml"), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
