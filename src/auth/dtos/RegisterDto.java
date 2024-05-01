package auth.dtos;

public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String currencyCode;

    public RegisterDto() {
    }

    public RegisterDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.currencyCode = "CLP";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
