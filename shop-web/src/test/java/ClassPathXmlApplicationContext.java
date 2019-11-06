import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.List;

public class ClassPathXmlApplicationContext {

    private String xml;

    public Object getBean(String id) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //创建一个xml解析器
        SAXReader saxReader = new SAXReader();
        //得到xml文档对象
        Document document = saxReader.read(this.getClass().getClassLoader().getResource(xml));
        //获取根节点
        Element element = document.getRootElement();
        //获取根节点下所有子节点
        List<Element> elementList = element.elements();
        Object obj = null;
        for (Element element1 :
                elementList) {
            //获取xml中的的<bean>标签的id属性
            String sonBeanId = element1.attributeValue("id");
            //如果获取到的id属性值和传进来的参数值一样，获取class属性
            if (!sonBeanId.equals(id)) {
                continue;
            }
            String sonClassName = element1.attributeValue("class");
            //加载类文件
            Class<?> forname = Class.forName(sonClassName);
            //实例化
            obj = forname.newInstance();
            //遍历<bean>标签下的所有节点
            List<Element> elementList1 = element1.elements();
            //遍历节点
            for (Element element2 :
                    elementList1) {
                //获取属性和属性值
                String name = element2.attributeValue("name");
                String value = element2.attributeValue("value");
                Field field = forname.getDeclaredField(name);
                field.setAccessible(true);
                field.set(obj, value);
                field.setAccessible(false);
            }
        }
        return obj;
    }

    public ClassPathXmlApplicationContext(String xml) {
        this.xml = xml;
    }

    public ClassPathXmlApplicationContext() {
    }
}
