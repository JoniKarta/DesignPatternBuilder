import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Phone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String phoneNumber;
	private String homePhone;

	public Phone(Builder builder) {
		this.phoneNumber = builder.phoneNumber;
		this.homePhone = builder.homePhone;
	}

	public static class Builder {

		private String phoneNumber;
		private String homePhone;
		private final int VALID_PHONE = 10;
		private final int VALID_HOME_PHONE = 9;

		public Builder phoneNumber(final String phoneNumber) {
			checkValidPhone(phoneNumber, VALID_PHONE);
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder homePhone(final String homePhone) {
			checkValidPhone(homePhone, VALID_HOME_PHONE);
			this.homePhone = homePhone;
			return this;
		}

		public Builder read(Scanner scanner) {
			this.phoneNumber = scanner.nextLine();
			this.homePhone = scanner.nextLine();
			return this;
		}

		public Builder readObjectStream(ObjectInputStream input) {
			Phone phoneObj = null;
			try {
				phoneObj = (Phone) input.readObject();
			} catch (ClassNotFoundException e) {
			} catch (IOException e) {
			}
			this.phoneNumber = phoneObj.phoneNumber;
			this.homePhone = phoneObj.homePhone;
			return this;
		}

		public Phone build() {
			return new Phone(this);
		}

		public boolean checkValidPhone(String phone, int size) {
			if (phone.length() != size) {
				throw new InputMismatchException("Illegal phone format size shoiuld be " + size);
			}
			for (char c : phone.toCharArray()) {
				if (c < '0' || c > '9') {
					throw new InputMismatchException("Illegal phone format size shoiuld be " + size);
				}
			}

			return true;
		}
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void write(PrintWriter pw) {
		pw.println(getPhoneNumber());
		pw.println(getHomePhone());
	}

	public void writePhone(ObjectOutputStream output) {
		try {
			output.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Phone Number: " + getPhoneNumber() + ", Home Numebr " + getHomePhone();
	}
}
