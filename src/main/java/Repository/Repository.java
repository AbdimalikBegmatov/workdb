package Repository;

import Database.DbContext;
import Person.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository implements BaseOperation<Person>{
    DbContext db = new DbContext();

    @Override
    public boolean connDb() {
        if(db.connection()!=null) {
            System.out.println("Succesfull connection");
            return true;
        }
        else {
            System.out.println("Error connection");
            return false;
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> person = new ArrayList<>();
        try (Connection conn = db.connection();
             PreparedStatement state=conn.prepareStatement("SELECT * FROM person")) {
            ResultSet set=state.executeQuery();
            while(set.next()){
                person.add(
                        new Person(set.getInt("id"),set.getString("name"),set.getString("surname"),set.getInt("age"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    @Override
    public Person getById(int id) {
        Person person = new Person();
        try (Connection conn = db.connection();
        PreparedStatement state = conn.prepareStatement("SELECT * FROM person WHERE id = ?")) {
            state.setInt(1,id);
            ResultSet rs = state.executeQuery();
            while(rs.next()){
                person = new Person(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getInt("age"));
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(int id,Person value) {
        try (Connection conn = db.connection();
        PreparedStatement state=conn.prepareStatement("UPDATE person SET name=?,surname=?,age=? WHERE id=?")) {
            state.setString(1,value.getName());
            state.setString(2, value.getSurname());
            state.setInt(3,value.getAge());
            state.setInt(4,id);
            state.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Person value) {
        try(Connection conn=db.connection();
        PreparedStatement state = conn.prepareStatement("INSERT INTO person (name, surname, age) VALUES (?,?,?);")){
            state.setString(1,value.getName());
            state.setString(2,value.getSurname());
            state.setInt(3,value.getAge());
            state.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
