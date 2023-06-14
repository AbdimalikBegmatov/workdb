package Repository;

import java.util.List;

public interface BaseOperation<T> {
    public boolean connDb();
    public List<T> getAll();
    public boolean save(T value);
    public T getById(int id);
    public boolean update(int id,T value);
}
