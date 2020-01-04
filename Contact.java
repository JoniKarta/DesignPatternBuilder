import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Contact implements Comparable<Contact> {

	private Name name;
	private Phone phone;

	public Contact(Builder builder) {
		this.name = builder.name;
		this.phone = builder.phone;
	}

	public Contact(Scanner scanner) {
		this.name = new Name.Builder().read(scanner).build();
		this.phone = new Phone.Builder().read(scanner).build();
	}

	public Contact(ObjectInputStream input) {
		this.name = new Name.Builder().readObjectStream(input).build();
		this.phone = new Phone.Builder().readObjectStream(input).build();

	}

	public static class Builder {
		private Name name;
		private Phone phone;

		public Builder name(Scanner scanner) {
			return null;

		}

		public Builder name(final Name name) {
			this.name = name;
			return this;
		}

		public Builder phone(final Phone phone) {
			if (phone.getPhoneNumber() == null && phone.getPhoneNumber() == null) {
				throw new InputMismatchException("You have to insert at least one way to contact");
			}
			this.phone = phone;
			return this;
		}


		public Contact build() {
			return new Contact(this);
		}
	}

	public Name getName() {
		return name;
	}

	public Phone getPhone() {
		return phone;
	}

	public void write(PrintWriter pw) {
		this.name.write(pw);
		this.phone.write(pw);
	}
	public void writeObject(ObjectOutputStream out) {
		this.name.writeName(out);
		this.phone.writePhone(out);
	}

	@Override
	public String toString() {
		return getName() + " " + getPhone();
	}

	@Override
	public int compareTo(Contact o) {
		return this.name.compareTo(o.getName());
	}

}
