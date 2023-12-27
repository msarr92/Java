package dao;
import java.util.List;


public interface Repository <T> {
    public int getRoleExist();
    public int add(T t);

    public List<T> list();
    public List<T> list(int role);
}
