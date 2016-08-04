
package au.edu.unsw.soacourse.topdown;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="eventSetId1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="eventSetId2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "eventSetId1",
    "eventSetId2"
})
@XmlRootElement(name = "showMarketDataRequest")
public class ShowMarketDataRequest {

    @XmlElement(required = true)
    protected String eventSetId1;
    @XmlElement(required = true)
    protected String eventSetId2;

    /**
     * 获取eventSetId1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventSetId1() {
        return eventSetId1;
    }

    /**
     * 设置eventSetId1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventSetId1(String value) {
        this.eventSetId1 = value;
    }

    /**
     * 获取eventSetId2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventSetId2() {
        return eventSetId2;
    }

    /**
     * 设置eventSetId2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventSetId2(String value) {
        this.eventSetId2 = value;
    }

}
