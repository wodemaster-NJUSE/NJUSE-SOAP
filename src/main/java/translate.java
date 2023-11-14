import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class translate {
    public static void main(String[] args) {
        try {
            // 创建转换器工厂
            TransformerFactory factory = TransformerFactory.newInstance();

            // 创建XSLT转换器
            Source xslt = new StreamSource(new File("src/main/xsds/XML3_to_XML4.xslt")); // 替换为实际的XSLT文件路径
            Transformer transformer = factory.newTransformer(xslt);

            // 执行转换
            Source xmlInput = new StreamSource(new File("src/main/xmls/xml3.xml")); // 替换为实际的XML3文件路径
            Result xmlOutput = new StreamResult(new File("src/main/xmls/xml4.xml")); // 替换为实际的XML4文件路径
            transformer.transform(xmlInput, xmlOutput);

            System.out.println("XML3已转换为XML4并按照课程均分排序");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
