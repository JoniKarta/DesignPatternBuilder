import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactManager {

	private ArrayList<Contact> contactList;
	private int listSize;

	private static ContactManager contactManager;

	private ContactManager() {
		contactList = new ArrayList<Contact>();
	}

	public static ContactManager getInstance() {
		if (contactManager == null) {
			contactManager = new ContactManager();
		}
		return contactManager;
	}

	public void addContact(Contact newContact) {
		contactList.add(newContact);
		Collections.sort(contactList);
		listSize++;

	}

	public Contact removeContact(int idx) {
		listSize--;
		Contact contact = contactList.remove(idx);
		return contact;
	}

	public Contact removeContact(Name name) {
		Contact contact = null;
		for (Contact c : contactList) {
			if (contact == null && c.getName().compareTo(name) == 0) {
				contactList.remove(c);
				contact = c;
				listSize--;
			}
		}
		return contact;
	}

	public int getListSize() {
		return listSize;
	}

	public void displayContacts() {
		contactList.forEach(e -> System.out.println(e));
	}

	public Contact binarySearch(List<Contact> contactList, Contact key) {
		int high = contactList.size();
		int low = 0;
		while (low < high) {
			int mid = (low + high) / 2;
			if(contactList.get(mid).compareTo(key) == 0) {
				return contactList.get(mid);
			}
			else if(contactList.get(mid).compareTo(key)  < 0 ) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		return null;
	}
	public List<Contact> getListContacts() {
		return contactList;
	}

}
