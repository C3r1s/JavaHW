import java.sql.*;
import java.util.*;

public class DbManager {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/cars_db?user=postgres&password=postgres";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS cars(
                id     SERIAL PRIMARY KEY,
                brand  VARCHAR(50),
                model  VARCHAR(50),
                engine REAL,
                year   INTEGER,
                color  VARCHAR(30),
                type   VARCHAR(20)
            );
        """;
        try (Connection c = connect(); Statement st = c.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void insert(Car car) {
        String sql = """
            INSERT INTO cars(brand,model,engine,year,color,type)
            VALUES (?,?,?,?,?,?)
        """;
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, car.getBrand()); ps.setString(2, car.getModel());
            ps.setDouble(3, car.getEngine()); ps.setInt(4, car.getYear());
            ps.setString(5, car.getColor());  ps.setString(6, car.getType());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<Car> select(String where) {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM cars " + (where == null ? "" : "WHERE " + where);
        try (Connection c = connect();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Car(
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getDouble("engine"),
                        rs.getInt("year"),
                        rs.getString("color"),
                        rs.getString("type")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}