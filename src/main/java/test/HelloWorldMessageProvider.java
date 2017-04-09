package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

interface MessageProvider
{
    String getMessage();
}
@Service("messageProvider")
public class HelloWorldMessageProvider implements MessageProvider
{
    private String message;
    @Autowired
    public HelloWorldMessageProvider(String message)
    {
        this.message = message;
    }
    @Override
    public String getMessage()
    {
        return this.message;
    }
}
@Service("constructorConfusion")
class ConstructorConfusion implements MessageProvider
{
    private String someValue;
    @Autowired
    ConstructorConfusion(@Value("message")String someValue)
    {
        this.someValue = someValue + "called: String";
    }
    ConstructorConfusion(int someValue)
    {
        this.someValue = String.valueOf(someValue) + "called: int";
    }
    @Override
    public String getMessage()
    {
        return this.someValue;
    }
}
interface MessageRenderer
{
    void render();
    void setMessageProvider(MessageProvider messageProvider);
    MessageProvider getMessageProvider();
}
@Service("messageRenderer")
class StandardOutMessageRenderer implements MessageRenderer
{
    private MessageProvider messageProvider;
    @Override
    public void render()
    {
        if(this.messageProvider == null)
        {
            throw new RuntimeException("Bla-bla-bla-bla");
        }
        System.out.println(messageProvider.getMessage());
    }
    @Override
    public void setMessageProvider(MessageProvider messageProvider)
    {
        this.messageProvider = messageProvider;
    }
    @Override
    public MessageProvider getMessageProvider()
    {
        return this.messageProvider;
    }
}

class DemoDeclarationSpringComponents
{
    public static void main(String...args)
    {
       DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("META-INF/spring/app-context-annotation.xml"));
        MessageRenderer messageRenderer = defaultListableBeanFactory.getBean("messageRenderer", StandardOutMessageRenderer.class);
        messageRenderer.render();
    }
}