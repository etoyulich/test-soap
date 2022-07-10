<?xml version="1.0" encoding="UTF-8" ?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="xml" doctype-public="XSLT-compat" omit-xml-declaration="yes" encoding="UTF-8" indent="yes" />

    <xsl:template match="person|document">
        <xsl:element name="{name()}">
            <xsl:apply-templates select="document|name|surname|patronymic|birthDate|gender|series|number|type|issueDate" />
        </xsl:element>
    </xsl:template>

    <xsl:template match="name|surname|patronymic|birthDate|gender|series|number|type|issueDate">
        <xsl:attribute name="{name()}">
            <xsl:value-of select="current()" />
        </xsl:attribute>
    </xsl:template>
</xsl:transform>