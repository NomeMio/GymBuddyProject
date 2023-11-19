package beans;

import java.time.LocalDate;

public class UserBean {

    private String username;
    /*private final PersonalInfoBean personalInfo;
    private String type;*/
    /*private final CredentialsBean credentials;*/

    /*public UserBean(String username, String type, PersonalInfoBean personalInfo, CredentialsBean credentials) {
        *//*This is a constructor without syntax check and is used by controller*//*
        this.username = username;
        this.personalInfo = personalInfo;
        this.type = type;
        this.credentials = CredentialsBean.ctorWithoutSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }*/

    public UserBean(String username/*, PersonalInfoBean personalInfo, String type, CredentialsBean credentials*/) /*throws InvalidUserInfoException, InvalidFiscalCodeException, InvalidCredentialsException, InvalidBirthException, EmptyFieldsException*/ {
        /*This is a constructor with syntax check and is used by view*/
        setUsername(username);
        /*setType(type);
        this.personalInfo = personalInfo;*/
/*
        this.credentials = CredentialsBean.ctorWithSyntaxCheck(credentials.getEmail(), credentials.getPassword());
*/
    }

    public String getUsernameBean() {
        return username;
    }

    private void setUsername(String username) {
        if(isValidLength(username)){
            this.username = username;
        }else {
            System.out.println("ERRORE CAMPO USERNAME");
        }
    }

    private boolean isValidLength(String str) {
        return str.length() <= 20;
    }

}