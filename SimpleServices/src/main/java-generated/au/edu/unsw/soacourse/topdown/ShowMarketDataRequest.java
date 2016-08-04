
package au.edu.unsw.soacourse.topdown;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡeventSetId1���Ե�ֵ��
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
     * ����eventSetId1���Ե�ֵ��
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
     * ��ȡeventSetId2���Ե�ֵ��
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
     * ����eventSetId2���Ե�ֵ��
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
