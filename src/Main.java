import dao.IRole;
import dao.IUser;
import dao.RoleImpl;
import dao.UserImpl;
import entity.Role;
import entity.User;

import java.io.Console;
import java.util.Scanner;

import static dao.UserImpl.hashPassword;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //DB db = new DB();
        //db.getConnexion();
        IRole roleDao= new RoleImpl();
        IUser userDao=new UserImpl();
        Scanner scan=new Scanner(System.in);
        Console console = System.console();


        Role role=new Role();
        System.out.println("Donner le role:");
        String nomRole=scan.nextLine();
        role.setNomRole(nomRole);
        int ok=roleDao.add(role);
        if (ok==1)
            System.out.println("Enregistrer");
        else
            System.out.println("Echec");
        System.out.println("Listes des Roles");
        for (Role role1:roleDao.list()){
            System.out.println("ID= "+role1.getIdRole());
            System.out.println("Nom du Role= "+role1.getNomRole());
        }

/*
     String password = maskPassword(scanner);

        System.out.println("Mot de passe saisi : " + password);
 */

        User user=new User();
        System.out.println("Donner l'email:");
        String nomEmail=scan.nextLine();
        user.setEmail(nomEmail);
        System.out.print("Saisissez votre mot de passe : ");
        String mdp= scan.nextLine();
        hashPassword(user.setPassword(mdp));
        int rolExit= userDao.getRoleExist();
        if (rolExit!=-1){
            Role rl=new Role();
            rl.setIdRole(rolExit);
            user.setRole(rl);
        }else {
            System.out.println("Aucun ID de role existe dans la table");
        }
        int ko=userDao.add(user);
        if (ko==1)
            System.out.println("Enregistrer");
        else
            System.out.println("Echec");

        System.out.println("Liste des Utilisateurs");
        for (User user1:userDao.list()){
            System.out.println("ID: "+user1.getId());
            System.out.println("Email: "+user1.getPassword());
        }

    }

}
