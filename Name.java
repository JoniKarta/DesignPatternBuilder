import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Name implements Comparable<Name>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String name;
	private final String lastName;

	public Name(Builder builder) {
		this.name = builder.name;
		this.lastName = builder.lastName;
	}

	public static class Builder {
		private String name;
		private String lastName;

		private final int NAME_SIZE = 3;

		public Builder contactName(final String name) {
			if (name.length() < NAME_SIZE) {
				throw new InputMismatchException("Name should be at length 3 at least");
			}
			this.name = name;
			return this;
		}

		public Builder contactLastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder read(Scanner scanner) {
			this.name = scanner.nextLine();
			this.lastName = scanner.nextLine();
			return this;
		}

		public Builder readObjectStream(ObjectInputStream input) {
			Name nameObj = null;
			try {
				nameObj = (Name) input.readObject();
			} catch (ClassNotFoundException e) {}
			  catch (IOException e) {}
			this.name = nameObj.name;
			this.lastName = nameObj.lastName;
			return this;
		}

		public Name build() {
			return new Name(this);
		}

	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return name;
	}

	public void write(PrintWriter pw) {
		pw.println(getName());
		pw.println(getLastName());
	}
	
	public void writeName(ObjectOutputStream output) {
		try {
			output.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "Name: " + getName() + ", Last Name: " + getLastName();
	}

	@Override
	public int compareTo(Name o) {
		if (this.getName().compareToIgnoreCase(o.getName()) == 0
				&& this.getLastName().compareToIgnoreCase(o.getLastName()) == 0) {
			return 0;
		}
		return this.getName().compareToIgnoreCase(o.getName()) < 0 ? 1 : -1;
	}
}
