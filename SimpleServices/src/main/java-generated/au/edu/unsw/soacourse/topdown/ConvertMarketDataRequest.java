
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
 *         &lt;element name="eventSetId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="targetCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "eventSetId",
    "targetCurrency"
})
@XmlRootElement(name = "convertMarketDataRequest")
public class ConvertMarketDataRequest {

    @XmlElement(required = true)
    protected String eventSetId;
    @XmlElement(required = true)
    protected String targetCurrency;

    /**
     * 获取eventSetId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventSetId() {
        return eventSetId;
    }

    /**
     * 设置eventSetId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventSetId(String value) {
        this.eventSetId = value;
    }

    /**
     * 获取targetCurrency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetCurrency() {
        return targetCurrency;
    }

    /**
     * 设置targetCurrency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetCurrency(String value) {
        this.targetCurrency = value;
    }

}
