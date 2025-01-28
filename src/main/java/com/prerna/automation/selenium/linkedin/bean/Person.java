package com.prerna.automation.selenium.linkedin.bean;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
	String personName;
	String jobTitle;
	String companyName;
	String location;

	ArrayList<String> phoneNumbers;
	ArrayList<String> handsetPhoneNumbers;
	ArrayList<String> links;
	ArrayList<String> twitters;
	ArrayList<String> emails;

	public Person() {
		phoneNumbers = new ArrayList();
		links = new ArrayList();
		twitters = new ArrayList();
		emails = new ArrayList();
		handsetPhoneNumbers = new ArrayList();
	}

	public void addPhoneNumber(String phoneNumber) {
		if (!phoneNumbers.contains(phoneNumber))
			phoneNumbers.add(phoneNumber);
	}

	public void addHandsetPhoneNumber(String phoneNumber) {
		if (!handsetPhoneNumbers.contains(phoneNumber))
			handsetPhoneNumbers.add(phoneNumber);
	}

	public void addLink(String link) {
		if (!links.contains(link))
			links.add(link);
	}

	public void addTwitter(String twitter) {
		if (!twitters.contains(twitter))
			twitters.add(twitter);
	}

	public void addEmail(String email) {
		if (!emails.contains(email))
			emails.add(email);
	}
	// result = ;

	public String getCellValue(int colIndex) {
		String result = "";

		switch (colIndex) {
		case 0:
			return personName;
		case 1:
			return jobTitle;
		case 2:
			return companyName;
		case 3:
			return location;
		case 4:
			result = "";
			for (String str : phoneNumbers) {
				result = result + str + ",";
			}
			return result;
		case 5:
			result = "";
			for (String str : handsetPhoneNumbers) {
				result = result + str + ",";
			}
			return result;
		case 6:
			result = "";
			for (String str : links) {
				result = result + str + ",";
			}
			return result;
		case 7:
			result = "";
			for (String str : twitters) {
				result = result + str + ",";
			}
			return result;
		case 8:
			result = "";
			for (String str : emails) {
				result = result + str + ",";
			}
			return result;
		default:
			return "NO DATA";
		}
	}

	public String csvString() {
		String result = "";
		result = result + "\"" + personName + "\"" + ",";
		result = result + "\"" + jobTitle + "\"" + ",";
		result = result + "\"" + companyName + "\"" + ",";
		result = result + "\"" + location + "\"" + ",";

		for (String str : phoneNumbers) {
			result = result + "\"" + str + "\"" + ",";
		}

		for (String str : links) {
			result = result + "\"" + str + "\"" + ",";
		}

		for (String str : twitters) {
			result = result + "\"" + str + "\"" + ",";
		}

		for (String str : emails) {
			result = result + "\"" + str + "\"" + ",";
		}

		return result;
	}
}
