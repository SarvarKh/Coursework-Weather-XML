<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet  version="2.0"
                 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 exclude-result-prefixes="xsi"
                 xmlns:xs="http://www.w3.org/2001/XMLSchema"
                 xmlns:my="http://example.com/my-namespace">

    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Weather Table</title>
            </head>
            <body>
                <table border="1">
                    <tr>
                        <th>Country</th>
                        <th>City</th>
                        <th>Day</th>
                        <th>Temperature</th>
                        <th>Humidity</th>
                        <th>Wind Speed</th>
                    </tr>
                    <xsl:apply-templates select="countries/country"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="country">
        <xsl:apply-templates select="cities/city"/>
    </xsl:template>

    <xsl:template match="city">
        <xsl:for-each select="days/day">
            <tr>
                <td><xsl:value-of select="ancestor::country/@name"/></td>
                <td><xsl:value-of select="ancestor::city/@name"/></td>
                <td><xsl:value-of select="@name"/></td>
                <td><xsl:value-of select="temperature/min"/> C - <xsl:value-of select="temperature/max"/> C</td>
                <td><xsl:value-of select="humidity/min"/>% - <xsl:value-of select="humidity/max"/>%</td>
                <td><xsl:value-of select="wind-speed/min"/>km/h - <xsl:value-of select="wind-speed/max"/>km/h</td>
            </tr>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>