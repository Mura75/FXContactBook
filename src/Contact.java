import java.util.Observable;
import java.util.UUID;

/**
 * Created by Murager on 3/11/17.
 */
public class Contact implements Comparable {

    private int id;

    private String name;

    private String surname;

    private String phone;

    private String email;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object obj) {

        Contact contact2 = (Contact)obj;
        String nameAndSurname = name + " " + surname;
        String nameAndSurname2 = contact2.getName() + " " + contact2.getSurname();
        return nameAndSurname.compareTo(nameAndSurname2);
    }
}
