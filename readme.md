## 本次作业要求



- 按照部门（包含但不限于学校内部）设计学生相关的Schema数据类型。要求：按数据持有，模块化分离数据定义按照Schema集中化原则，使用Namespace管理数据结构合理且满足实际应用需求（含合理的其他数据结构）使用任意工具（WTP XML Schema Editor）

- 以校档案馆档案管理为业务背景：设计适应于该服务的学生信息，给出对应的Schema数据类型给出携带个人信息的XML实例（XML1）使用Schema验证其合法性以上课签到为业务背景：设计适应于该服务的学生信息，给出对应的Schema数据类型给出携带个人信息的XML实例（XML2）使用Schema验证其合法性

- 以教务系统为业务背景：设计以学生个人为聚合的成绩报告单，给出对应的Schema数据类型使用DOM读取XML1 随机产生另9名学生信息每位同学随机生成五门课程的成绩（包含平时成绩、期末成绩和总评成绩）（XML3）要求至少有1名同学，任意成绩低于60分
- 以教务系统为业务背景：设计以课程为聚合的成绩报告单，给出对应的Schema数据类型使用XSLT将XML3转化为符合该数据类型的XML文档，并依据总评成绩对每门课程进行排序。XML4采用SAX处理XML4，仅保留出现任意不及格成绩的课程成绩（含分项）记录。XML5
- 使用JavaEE开发提交相关脚本、程序提交pdf说明相关脚本程序，并展示关键脚本、程序和结果（最简）注意对应原则的使用需要处理Namespace需要在合理的位置使用Schema进行合法性验证



## 实现过程

1. 完成学生信息schema：studentMessege.xsd
2. 给出对应xml1实例
3. 完成上课签到schema：present.xsd
4. 给出对应xml2实例
5. 通过运行xmlTest.java验证两个xml的合法性，具体验证流程可以看注解
6. 完成学生个人为聚合的成绩报告单schema：studentGrades.xsd
7. 实现GenerateStudentGrades.java：使用DOM读取XML1 随机产生另9名学生信息每位同学随机生成五门课程的成绩（包含平时成绩、期末成绩和总评成绩）（XML3）要求至少有1名同学，任意成绩低于60分
8. 设计以课程为聚合的成绩报告单：courseReport.xsd，并利用XML3_to_XML4.xslt，在translate.java中把xml3转为所需xml4
9. 通过XMLFilter.java把xml4转为xml5，XML4采用SAX处理XML4，仅保留出现任意不及格成绩的课程成绩（含分项）记录XML5。



执行步骤：

1. 运行xmlTest：检验xml1，xml2是否符合对应schema
2. 运行GenerateStudentGrades.java
3. 运行translate.java
4. 运行XMLFilter.java

也可以直接执行run.sh文件



部分运行截图：

![image-20231114174503123](C:\Users\wodemaster\AppData\Roaming\Typora\typora-user-images\image-20231114174503123.png)

