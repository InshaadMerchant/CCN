package com.example.ccn;

public class RegistrationHelperClass {
    String name,username, description, email, security_question,securityquestion_answer;

    public RegistrationHelperClass(String name, String username, String description) {
        this.name = name;
        this.username = username;
        this.description = description;
        this.email = email;
        this.security_question = security_question;
        this.securityquestion_answer = securityquestion_answer;
    }

    public RegistrationHelperClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    public String getSecurityquestion_answer() {
        return securityquestion_answer;
    }

    public void setSecurityquestion_answer(String securityquestion_answer) {
        this.securityquestion_answer = securityquestion_answer;
    }
}
