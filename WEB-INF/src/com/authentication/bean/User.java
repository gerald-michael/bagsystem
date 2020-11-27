package com.authentication.bean;
import java.util.Date;
import java.math.BigInteger;
import java.security.MessageDigest;

public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;
    private Date dateCreated;
    private Date dateUpdated;
    private String password;
    private String confirmPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setPassword(String password) {
        this.password = encrypt(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = encrypt(confirmPassword);
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    public static String encrypt(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String cipherText = number.toString(16);
            while (cipherText.length() < 32) {
                cipherText = "0" + cipherText;
            }
            return cipherText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validatePassword(){
        return this.password.equals(this.confirmPassword);
    }

}
