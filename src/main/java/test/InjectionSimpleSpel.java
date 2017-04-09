package test;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("injectionSpel")
class InjectionSimpleSpEL
{
    private String name;
    private Integer age;
    private Boolean isProgrammer;
    public static void main(String...args)
    {
        GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext();
        genericXmlApplicationContext.load("classpath:C:META-INF/spring/app-context-injection-annotation.xml");
        genericXmlApplicationContext.refresh();
        InjectionSimpleSpEL injectionSimpleSpEL = (InjectionSimpleSpEL) genericXmlApplicationContext.getBean("injectionSpel");
        System.out.println(injectionSimpleSpEL);
    }
    @Value("#{injection.name}")
    public void setName(String name)
    {
        this.name = name;
    }
    @Value("#{injection.age}")
    public void setAge(Integer age)
    {
        this.age = age;
    }
    @Value("#{injection.isProgrammer}")
    public void setIsProgrammer(Boolean isProgrammer)
    {
        this.isProgrammer = isProgrammer;
    }
    public String toString()
    {
        return "Name: " + this.name + "\n" + "Age: " + this.age + "\n" + "IsProgrammer: " + this.isProgrammer + "\n";
    }
}