package test;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

interface Oracle
{
    String deffineMeanOfLife();
}
@Service("wiseworm")
public class BookwormOracle implements Oracle
{
    private Encyclopedia encyclopedia;
    @Override
    public String deffineMeanOfLife()
    {
       return "Encyclopedias are a waste of money - use the Internet";
    }
    public void setEncyclopedia(Encyclopedia encyclopedia)
    {
        this.encyclopedia = encyclopedia;
    }
    public Encyclopedia getEncyclopedia()
    {
        return this.encyclopedia;
    }
}

class XmlConficWithBeanFactory
{
    public static void main(String...args)
    {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("META-INF/spring/xml-bean-factroy-config.xml"));
        Oracle oracle = (Oracle) defaultListableBeanFactory.getBean("oracle");
        System.out.println(oracle.deffineMeanOfLife());
    }
}
