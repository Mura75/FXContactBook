import java.util.UUID;

/**
 * Created by Murager on 3/11/17.
 */
public class Contact {

    private int id;

    private String name;

    public Contact() {
        id = Math.abs(UUID.randomUUID().hashCode());
    }

    public Contact(String name) {
        id = Math.abs(UUID.randomUUID().hashCode());
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
