package sg.edu.nus.iss.ibfb4ssfassessment.model;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Login [email=" + email + ", birthDate=" + birthDate + "]";
    }
    
    
    
}
