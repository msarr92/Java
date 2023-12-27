package dao;

import entity.User;
import entity.Role;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements IUser {


    private DB db = new DB();
    private ResultSet rs;
    private int ok;


    @Override
    public int getRoleExist() {
        int rolExist = 1;
        String sql = "SELECT idRole FROM role where id=2";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            if (rs.next()) {
                rolExist = rs.getInt("idRole");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rolExist;
    }

    @Override
    public int add(User user) {

        String sql = "INSERT INTO utilisateur VALUES(null,?,?,?)";
        try {
            //initialisation de la requette
            db.initPrepar(sql);
            //Passage de valeur a la requette
            db.getPstm().setString(1, user.getEmail());
            db.getPstm().setString(2, user.getPassword());
            db.getPstm().setInt(3, user.getRole().getIdRole());
            // db.getPstm().setInt(3,user.getRole().getIdRole());
            //Execution de la requette
            ok = db.executeMaj();
            //Fermeture
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<User> list() {
        List<User> user = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur u  JOIN role r ON u.role = r.idRole";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()) {
                User users = new User();
                users.setIdUser(rs.getInt("u.id"));
                users.setEmail(rs.getString("u.email"));
                Role role = new Role();
                role.setIdRole(rs.getInt("r.idRole"));
                role.setNomRole(rs.getString("r.nomRole"));
                users.setRole(role);
                user.add(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> list(int role) {
        return null;
    }

    // Méthode pour hasher un mot de passe
    public static String hashPassword(String plainTextPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    plainTextPassword.getBytes());

            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return plainTextPassword;
    }
}

    /*
    @Override
    public List<User> list(int role){
            List<User> users = new ArrayList<>();
            String sql = "SELECT * FROM utilisateur u,role rl WHERE u.role=rl.idRole AND rl.email=?";
            try {
                db.initPrepar(sql);
                db.getPstm().setInt(1, role);
                rs = db.executeSelect();
                while (rs.next()) {
                    User u = new User();
                    u.setIdUser(rs.getInt("u.id"));
                    u.setEmail(rs.getString("u.email"));
                    u.setPassword(rs.getString("u.password"));
                    users.add(u);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return users;
    }
     */