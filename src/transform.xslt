<?xml version="1.0" encoding="UTF-8" ?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" doctype-public="XSLT-compat" omit-xml-declaration="yes" encoding="UTF-8" indent="yes" />
    <xsl:template match="person|document">
        <xsl:element name="{name()}">
            <xsl:for-each select="./*">
                <xsl:sort select="count(./*)" data-type="number" order="ascending"/>
                <xsl:if test="count(./*) = 0 ">
                    <xsl:attribute name="{name()}">
                        <xsl:value-of select="current()" />
                    </xsl:attribute>
                </xsl:if>
                <xsl:if test="count(./*) > 0 ">
                    <xsl:apply-templates select="."/>
                </xsl:if>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:transform>