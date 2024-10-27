package com.microservices.AccountService.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String userId;
    private String username;
    private ContactInfo contactInfo;
    private Address address;
    private EmploymentDetails employmentDetails;
    private Preferences preferences;
    private AccountSettings accountSettings;
    private Subscription subscription;

    public User(String username, ContactInfo contactInfo, Address address, EmploymentDetails employmentDetails, Preferences preferences, AccountSettings accountSettings, Subscription subscription) {
        this.username = username;
        this.contactInfo = contactInfo;
        this.address = address;
        this.employmentDetails = employmentDetails;
        this.preferences = preferences;
        this.accountSettings = accountSettings;
        this.subscription = subscription;
    }
    // Getters and Setters

    @Document
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ContactInfo {

        @Id
        private String contactId; // Unique identifier for ContactInfo
        private String phone;
        private String email;
        private String alternateEmail;
        private String fax;
        private String emergencyContactName;
        private String emergencyContactPhone;
        private String linkedin;
        private String twitter;
        private String website;
        private String additionalInfo;

        public ContactInfo(String phone, String email, String alternateEmail, String fax, String emergencyContactName, String emergencyContactPhone, String linkedin, String twitter, String website, String additionalInfo) {
            this.phone = phone;
            this.email = email;
            this.alternateEmail = alternateEmail;
            this.fax = fax;
            this.emergencyContactName = emergencyContactName;
            this.emergencyContactPhone = emergencyContactPhone;
            this.linkedin = linkedin;
            this.twitter = twitter;
            this.website = website;
            this.additionalInfo = additionalInfo;
        }
        // Getters and Setters
    }

    @Document
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Address {
        @Id
        private String addressId; // Unique identifier for Address
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;
        private String apartmentNumber;
        private String landmarks;
        private String notes;
        private String type;

        public Address(String street, String city, String state, String zipCode, String country, String apartmentNumber, String landmarks, String notes, String type) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
            this.apartmentNumber = apartmentNumber;
            this.landmarks = landmarks;
            this.notes = notes;
            this.type = type;
        }

        // Getters and Setters
    }

    @Document
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class EmploymentDetails {

        @Id
        private String employmentId; // Unique identifier for EmploymentDetails
        private String jobTitle;
        private String department;
        private String companyName;
        private String employerId;
        private String supervisorName;
        private String officeLocation;
        private String officePhone;
        private String workEmail;
        private String employmentType;
        private String startDate;

        public EmploymentDetails(String jobTitle, String department, String companyName, String employerId, String supervisorName, String officeLocation, String officePhone, String workEmail, String employmentType, String startDate) {
            this.jobTitle = jobTitle;
            this.department = department;
            this.companyName = companyName;
            this.employerId = employerId;
            this.supervisorName = supervisorName;
            this.officeLocation = officeLocation;
            this.officePhone = officePhone;
            this.workEmail = workEmail;
            this.employmentType = employmentType;
            this.startDate = startDate;
        }
// Getters and Setters
    }

    @Document
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Preferences {

        @Id
        private String preferencesId; // Unique identifier for Preferences
        private String language;
        private String timezone;
        private boolean receiveNewsletters;
        private boolean receiveAlerts;
        private String theme;
        private String dateFormat;
        private String currency;
        private String region;
        private boolean enableTwoFactorAuth;
        private boolean showProfile;

        public Preferences(String language, String timezone, boolean receiveNewsletters, boolean receiveAlerts, String theme, String dateFormat, String currency, String region, boolean enableTwoFactorAuth, boolean showProfile) {
            this.language = language;
            this.timezone = timezone;
            this.receiveNewsletters = receiveNewsletters;
            this.receiveAlerts = receiveAlerts;
            this.theme = theme;
            this.dateFormat = dateFormat;
            this.currency = currency;
            this.region = region;
            this.enableTwoFactorAuth = enableTwoFactorAuth;
            this.showProfile = showProfile;
        }
        // Getters and Setters
    }

    @Document
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class AccountSettings {

        @Id
        private String accountSettingsId; // Unique identifier for AccountSettings
        private String username;
        private String password;
        private boolean emailVerified;
        private String recoveryEmail;
        private String phoneNumber;
        private String accountStatus;
        private String accountType;
        private String subscriptionLevel;
        private String createdDate;
        private String securityQuestion;

        public AccountSettings(String username, String password, boolean emailVerified, String recoveryEmail, String phoneNumber, String accountStatus, String accountType, String subscriptionLevel, String createdDate, String securityQuestion) {
            this.username = username;
            this.password = password;
            this.emailVerified = emailVerified;
            this.recoveryEmail = recoveryEmail;
            this.phoneNumber = phoneNumber;
            this.accountStatus = accountStatus;
            this.accountType = accountType;
            this.subscriptionLevel = subscriptionLevel;
            this.createdDate = createdDate;
            this.securityQuestion = securityQuestion;
        }
        // Getters and Setters
    }

    @Document
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Subscription {

        @Id
        private String subscriptionId; // Unique identifier for Subscription
        private String billingCycle;
        private String status;
        private String planType;
        private String paymentMethod;
        private String startDate;
        private String endDate;
        private boolean autoRenew;
        private String nextPaymentDate;
        private String subscriptionLevel;
        private String lastPaymentDate;

        public Subscription(String billingCycle, String status, String planType, String paymentMethod, String startDate, String endDate, boolean autoRenew, String nextPaymentDate, String subscriptionLevel, String lastPaymentDate) {
            this.billingCycle = billingCycle;
            this.status = status;
            this.planType = planType;
            this.paymentMethod = paymentMethod;
            this.startDate = startDate;
            this.endDate = endDate;
            this.autoRenew = autoRenew;
            this.nextPaymentDate = nextPaymentDate;
            this.subscriptionLevel = subscriptionLevel;
            this.lastPaymentDate = lastPaymentDate;
        }
// Getters and Setters
    }
}
