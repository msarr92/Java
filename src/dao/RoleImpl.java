package dao;

import entity.Role;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleImpl implements IRole{

private DB db=new DB();
private ResultSet rs;
private int ok;

    @Override
    public int getRoleExist() {
        return 0;
    }

    @Override
    public int add(Role role) {
        String sql="INSERT INTO role(idRole,nomRole)VALUES(null,?)";
        try {
            //initialisation de la requette
            db.initPrepar(sql);
            //Passage de valeur a la requette
            db.getPstm().setString(1, role.getNomRole());
            //Execution de la requette
            ok=db.executeMaj();
            //Fermeture
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Role> list() {
        List<Role> roles=new ArrayList<>();
        String sql="SELECT * FROM role ORDER BY nomRole ASC";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                Role role=new Role();
                role.setIdRole(rs.getInt("idRole"));
                role.setNomRole(rs.getString("nomRole"));
               roles.add(role);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public List<Role> list(int role) {
        return null;
    }

}
