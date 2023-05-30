import java.util.Scanner;
public class EntryPoint {
    Scanner scanner ;
    UsersData usersData ;
    EntryPoint(Scanner sc){
        usersData = new UsersData() ;
        scanner = sc ;
    }
    void loginOrRegister() {
        boolean entered = false;
        while (!entered) {
            System.out.println("Login or Register :");
            String answer = scanner.nextLine();
            System.out.println("type your role (Admin , Costumer , Delivery)");
            boolean correctRole = false;
            String role = "";
            while (!correctRole) {
                System.out.println("role :");
                role = scanner.nextLine();
                if (role.equals("Admin") || role.equals("Costumer") || role.equals("Delivery"))
                    correctRole = true;
                else
                    System.out.println("choose the correct role !");
            }
            if (answer.equals("login")) {
                boolean loggedIn = false;
                while (!loggedIn) {
                    System.out.println("name :");
                    String name = scanner.nextLine();
                    int userID = usersData.searchUser(role, name);
                    System.out.println("password :");
                    String pass = scanner.nextLine();
                    switch (role) {
                        case "Admin": {
                            if (usersData.admins.size() != 0) {
                                usersData.admins.get(userID).login(pass);
                                if (usersData.admins.get(userID).loggedIn) {
                                    usersData.admins.get(userID).startAdmin();
                                    loggedIn = true;
                                    entered = true ;
                                }
                            } else {
                                System.out.println("No one has registered , please register first");
                                loggedIn = true;
                            }
                            break;
                        }
                        case "Costumer": {
                            if (usersData.costumers.size() != 0) {
                                usersData.costumers.get(userID).login(pass);
                                if (usersData.costumers.get(userID).loggedIn) {
                                    usersData.costumers.get(userID).startCostumer();
                                    loggedIn = true;
                                    entered = true ;
                                }
                            } else {
                                System.out.println("No one has registered , please register first");
                                loggedIn = true;
                            }
                            break;
                        }
                        case "Delivery": {
                            if (usersData.deliveries.size() != 0) {
                                usersData.deliveries.get(userID).login(pass);
                                if (usersData.deliveries.get(userID).loggedIn) {
                                    usersData.deliveries.get(userID).startDelivery();
                                    loggedIn = true;
                                    entered = true ;
                                }
                            } else {
                                System.out.println("No one has registered , please register first");
                                loggedIn = true;
                            }
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    if (!loggedIn)
                        System.out.println("information was incorrect ! maybe you haven't registered yet");
                }
            } else if (answer.equals("register")) {
                boolean registered = false;
                while (!registered) {
                    System.out.println("choose a name and password , your password must contain alphanumeric characters");
                    System.out.println("name :");
                    String name = scanner.nextLine();
                    System.out.println("password :");
                    String pass = scanner.nextLine();
                    if (Account.checkPass(pass)) {
                        if (usersData.searchUser(role, name) != -1)
                            System.out.println("your name is taken , please choose a different name");
                        else {
                            usersData.register(role, name, pass);
                            entered = true ;
                            registered = true ;
                        }
                    } else {
                        System.out.println("your password must contain alphanumeric characters , try again");
                    }
                }
            }
        }
    }

}
