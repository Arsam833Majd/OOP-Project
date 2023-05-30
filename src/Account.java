import java.util.regex.Pattern;

public class Account {
    String Name , Password  ;
    int ID ;
    boolean loggedIn ;
    void login(String pass) {
        if (pass.equals(Password)){
            System.out.println("logged in successfully !");
            loggedIn = true ;
        }else{
            System.out.println("incorrect password ! try again");
            loggedIn = false ;
        }
    }
    static boolean checkPass(String pass){
        if(Pattern.matches("\\w{8,20}" , pass))
            return true ;
        return false ;
    }
    void ChangePassword(String newPass){

    }
    void logOut(){

    }
}
