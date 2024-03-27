package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Login {

    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email length cannot exceed 50 characters")
    @NotBlank(message = "Email cannot be empty or blank")
    private String email;

    //is there a YYYY-MM-DD format??
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birthday cannot be greater than or equal to present date.")
    private Date birthDate;

    public Login() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // public String getBirthDate() {
    //     if(birthDate == null) return null;
    //     DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    //     return df.format(birthDate);
    // }

    public Date getBirthDate() {
        return birthDate;
    }

    //birthDateString format: dd/mm/yyyy as accordance to task 6
    // public void setBirthDate(String birthDateString) throws ParseException {
    //     Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(birthDateString);
    //     System.out.println(date);
    //     this.birthDate = date;
    // }

    //hard set the time for date of birth to be 23:59:59
    //this allows jakarta validation to be accurate as the time input by user is default to 00:00 which means present date will be ok to insert
    public void setBirthDate(Date birthDate) {
        long dateLong = birthDate.getTime() + 86340000 + 59000;
        System.out.println("birthdate + 23hr59min59sec: >>> " + new Date(dateLong));
        this.birthDate = new Date(dateLong);
    }

    @Override
    public String toString() {
        return "Login [email=" + email + ", birthDate=" + birthDate + "]";
    }
    
    //referred to StackOverflow: https://stackoverflow.com/questions/1116123/how-do-i-calculate-someones-age-in-java
    public Integer getAge() {
        LocalDate loginBirthLocalDate = this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate todayDate = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer userAge =  Period.between(loginBirthLocalDate, todayDate).getYears();

        return userAge;
    }
    
}
