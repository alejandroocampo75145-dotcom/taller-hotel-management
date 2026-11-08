package model;

public class Guest {
    private int id;
    private String fullName;
    private String identityDocument;
    private String phoneNumber;
    private String email;

    public Guest(int id, String fullName, String identityDocument, String phoneNumber, String email) {
        setId(id);
        setFullName(fullName);
        setIdentityDocument(identityDocument);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("The ID must be a positive number");
        }
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("The full name cannot be empty");
        }
        this.fullName = fullName;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        if (identityDocument == null || identityDocument.isBlank()) {
            throw new IllegalArgumentException("The identity document cannot be empty");
        }
        this.identityDocument = identityDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("The phone number cannot be empty");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("The email cannot be empty");
        }
        this.email = email;
    }
}

