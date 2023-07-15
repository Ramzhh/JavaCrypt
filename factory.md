# Factory
## Lecture
### What is a Java Factory?
A Java Factory is like a special factory in the computer world. It has the power to create things in a computer program.

### How Does a Java Factory Work?
The Java Factory follows specific rules called "design patterns" to determine what object to create based on certain information or conditions. 

### The "create" Method
The Java Factory has a method called "create" which is responsible for creating objects. When someone asks the factory for a new object, it calls the "create" method. This method looks at the given information and decides which specific object to create.

### Understanding Classes
In the computer world, a "class" is like an instruction manual for the factory. It tells the factory how to create a particular object. The class defines the properties and abilities that the object should have.

### Instances of Objects
When the Java Factory creates an object based on a class, it is called an "instance" of that class. It's similar to following instructions to draw a specific picture. By following the instructions, you create an instance of that picture.

### Conclusion
To summarize, a Java Factory is a special factory in the computer world that creates objects based on rules and instructions defined by classes. It uses the "create" method to determine which specific object to create, and the created objects are known as instances.

## Example
```java
public interface MarkdownElement {
    String render();
}

public class Heading implements MarkdownElement {
    private String text;
    private int level;

    public Heading(String text, int level) {
        this.text = text;
        this.level = level;
    }

    @Override
    public String render() {
        return "#".repeat(level) + " " + text;
    }
}

public class Paragraph implements MarkdownElement {
    private String text;

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }
}

public class MarkdownElementFactory {
    public MarkdownElement createMarkdownElement(String type, String content) {
        if (type.equalsIgnoreCase("heading")) {
            int level = Integer.parseInt(content.substring(0, 1));
            String text = content.substring(2);
            return new Heading(text, level);
        } else if (type.equalsIgnoreCase("paragraph")) {
            return new Paragraph(content);
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        MarkdownElementFactory factory = new MarkdownElementFactory();

        MarkdownElement heading = factory.createMarkdownElement("heading", "2 Heading Text");
        System.out.println(heading.render()); // Output: ## Heading Text

        MarkdownElement paragraph = factory.createMarkdownElement("paragraph", "This is a paragraph.");
        System.out.println(paragraph.render()); // Output: This is a paragraph.
    }
}

```