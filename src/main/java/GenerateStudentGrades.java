import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Random;

public class GenerateStudentGrades {

    public static void main(String[] args) {
        try {
            // 读取xml1.xml文件
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/main/xmls/xml1.xml");

            // 创建新的Document
            Document newDoc = builder.newDocument();
            Element root = newDoc.createElement("students");
            newDoc.appendChild(root);

            // 生成原始学生信息
            Element originalStudent = newDoc.createElement("student");
            root.appendChild(originalStudent);

            Element studentIdElement = newDoc.createElement("studentId");
            studentIdElement.appendChild(newDoc.createTextNode(doc.getElementsByTagName("studentId").item(0).getTextContent()));
            originalStudent.appendChild(studentIdElement);

            Element nameElement = newDoc.createElement("name");
            nameElement.appendChild(newDoc.createTextNode(doc.getElementsByTagName("name").item(0).getTextContent()));
            originalStudent.appendChild(nameElement);

            Element genderElement = newDoc.createElement("gender");
            genderElement.appendChild(newDoc.createTextNode(doc.getElementsByTagName("gender").item(0).getTextContent()));
            originalStudent.appendChild(genderElement);

            Element birthdateElement = newDoc.createElement("birthdate");
            birthdateElement.appendChild(newDoc.createTextNode(doc.getElementsByTagName("birthdate").item(0).getTextContent()));
            originalStudent.appendChild(birthdateElement);

            Element gradeElement = newDoc.createElement("grade");
            gradeElement.appendChild(newDoc.createTextNode(doc.getElementsByTagName("grade").item(0).getTextContent()));
            originalStudent.appendChild(gradeElement);

            Element classElement = newDoc.createElement("class");
            classElement.appendChild(newDoc.createTextNode(doc.getElementsByTagName("class").item(0).getTextContent()));
            originalStudent.appendChild(classElement);

            // 随机生成五门课程的成绩
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                Element courseElement = newDoc.createElement("course");
                originalStudent.appendChild(courseElement);

                Element courseNameElement = newDoc.createElement("courseName");
                courseNameElement.appendChild(newDoc.createTextNode("Course" + (i + 1)));
                courseElement.appendChild(courseNameElement);

                Element dailyScoreElement = newDoc.createElement("dailyScore");
                dailyScoreElement.appendChild(newDoc.createTextNode(String.valueOf(random.nextInt(101))));
                courseElement.appendChild(dailyScoreElement);

                Element finalScoreElement = newDoc.createElement("finalScore");
                finalScoreElement.appendChild(newDoc.createTextNode(String.valueOf(random.nextInt(101))));
                courseElement.appendChild(finalScoreElement);

                int totalScore = (Integer.parseInt(dailyScoreElement.getTextContent()) + Integer.parseInt(finalScoreElement.getTextContent())) / 2;
                Element totalScoreElement = newDoc.createElement("totalScore");
                totalScoreElement.appendChild(newDoc.createTextNode(String.valueOf(totalScore)));
                courseElement.appendChild(totalScoreElement);
            }

            // 随机生成9名学生信息
            for (int j = 0; j < 9; j++) {
                Element student = newDoc.createElement("student");
                root.appendChild(student);

                Element randomStudentIdElement = newDoc.createElement("studentId");
                randomStudentIdElement.appendChild(newDoc.createTextNode("S" + (j + 2) + "2345"));
                student.appendChild(randomStudentIdElement);

                Element randomNameElement = newDoc.createElement("name");
                randomNameElement.appendChild(newDoc.createTextNode("学生" + (j + 2)));
                student.appendChild(randomNameElement);

                Element randomGenderElement = newDoc.createElement("gender");
                randomGenderElement.appendChild(newDoc.createTextNode(j % 2 == 0 ? "男" : "女"));
                student.appendChild(randomGenderElement);

                Element randomBirthdateElement = newDoc.createElement("birthdate");
                randomBirthdateElement.appendChild(newDoc.createTextNode("20" + (j + 2) + "-01-01"));
                student.appendChild(randomBirthdateElement);

                Element randomGradeElement = newDoc.createElement("grade");
                randomGradeElement.appendChild(newDoc.createTextNode(doc.getElementsByTagName("grade").item(0).getTextContent()));
                student.appendChild(randomGradeElement);

                Element randomClassElement = newDoc.createElement("class");
                randomClassElement.appendChild(newDoc.createTextNode(doc.getElementsByTagName("class").item(0).getTextContent()));
                student.appendChild(randomClassElement);

                // 随机生成五门课程的成绩
                for (int k = 0; k < 5; k++) {
                    Element courseElement = newDoc.createElement("course");
                    student.appendChild(courseElement);

                    Element courseNameElement = newDoc.createElement("courseName");
                    courseNameElement.appendChild(newDoc.createTextNode("Course" + (k + 1)));
                    courseElement.appendChild(courseNameElement);

                    Element dailyScoreElement = newDoc.createElement("dailyScore");
                    dailyScoreElement.appendChild(newDoc.createTextNode(String.valueOf(random.nextInt(101))));
                    courseElement.appendChild(dailyScoreElement);

                    Element finalScoreElement = newDoc.createElement("finalScore");
                    finalScoreElement.appendChild(newDoc.createTextNode(String.valueOf(random.nextInt(101))));
                    courseElement.appendChild(finalScoreElement);

                    int totalScore = (Integer.parseInt(dailyScoreElement.getTextContent()) + Integer.parseInt(finalScoreElement.getTextContent())) / 2;
                    Element totalScoreElement = newDoc.createElement("totalScore");
                    totalScoreElement.appendChild(newDoc.createTextNode(String.valueOf(totalScore)));
                    courseElement.appendChild(totalScoreElement);
                }
            }

            // 输出生成的学生信息到xml3.xml文件
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File("src/main/xmls/xml3.xml"));
            transformer.transform(source, result);

            System.out.println("生成的学生信息已保存到xml3.xml文件中");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
