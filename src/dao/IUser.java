package dao;
import entity.User;

import java.util.List;

public interface IUser extends Repository<User>{
    public List<User> list(int role);
}
