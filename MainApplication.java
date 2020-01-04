
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainApplication {

	public static void main(String[] args) throws IOException {
		ContactManager contactManager = ContactManager.getInstance();

		Contact momoContact = new Contact.Builder()
				.name(new Name.Builder().contactName("Momo").contactLastName("Lechi").build())
				.phone(new Phone.Builder().phoneNumber("0503455456").homePhone("097655312").build()).build();

		Contact robertContact = new Contact.Builder()
				.name(new Name.Builder().contactName("Robert").contactLastName("Moanch").build())
				.phone(new Phone.Builder().phoneNumber("0503334445").homePhone("097665532").build()).build();

		Contact danaContact = new Contact.Builder()
				.name(new Name.Builder().contactName("Gal").contactLastName("Sohani").build())
				.phone(new Phone.Builder().phoneNumber("0503336678").homePhone("097485239").build()).build();

		Contact yasminContact = new Contact.Builder()
				.name(new Name.Builder().contactName("Yasmin").contactLastName("Negi").build())
				.phone(new Phone.Builder().phoneNumber("0543124455").homePhone("097688352").build()).build();

		contactManager.addContact(momoContact);
		contactManager.addContact(robertContact);
		contactManager.addContact(danaContact);
		contactManager.addContact(yasminContact);
		contactManager.displayContacts();

	
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("temp.dat"));) {
			contactManager.getListContacts().forEach(e -> e.writeObject(output));
		}


	}
}
