
import org.springframework.stereotype.Service;

/**
 * Created by ДЕТИ on 09.04.2017.
 */
@Service("injection")
class InjectionSimple
{
    private String name = "Nikita";
    private Integer age = 16;
    private Boolean isProgrammer = false;
    public void setName(String name)
    {
        this.name = name;
    }
    public void setAge(Integer age)
    {
        this.age = age;
    }
    public void setIsProgrammer(Boolean isProgrammer)
    {
        this.isProgrammer = isProgrammer;
    }
    public String toString()
    {
        return "Name: " + this.name + "\n" + "Age: " + this.age + "\n" + "IsProgrammer: " + this.isProgrammer + "\n";
    }
}