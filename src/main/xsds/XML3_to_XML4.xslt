<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

    <xsl:key name="courseKey" match="course" use="courseName"/>

    <xsl:template match="/students">
        <courses>
            <xsl:for-each select="student/course[generate-id() = generate-id(key('courseKey', courseName)[1])]">
                <xsl:sort select="sum(key('courseKey', courseName)/totalScore) div count(key('courseKey', courseName))" data-type="number" order="descending"/>
                <xsl:variable name="currentCourse" select="courseName"/>
                <course>
                    <courseName><xsl:value-of select="courseName"/></courseName>
                    <xsl:for-each select="key('courseKey', $currentCourse)">
                        <student>
                            <studentId><xsl:value-of select="../studentId"/></studentId>
                            <studentName><xsl:value-of select="../name"/></studentName>
                            <totalScore><xsl:value-of select="totalScore"/></totalScore>
                        </student>
                    </xsl:for-each>
                    <averageScore>
                        <xsl:value-of select="format-number(sum(key('courseKey', $currentCourse)/totalScore) div count(key('courseKey', $currentCourse)), '#.##')"/>
                    </averageScore>
                </course>
            </xsl:for-each>
        </courses>
    </xsl:template>
</xsl:stylesheet>
