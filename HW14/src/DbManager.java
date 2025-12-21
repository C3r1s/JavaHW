import java.sql.*;
import java.util.*;

public class DbManager {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/hospital_db?user=postgres&password=postgres";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void init() {
        String createDoctors = """
            CREATE TABLE IF NOT EXISTS doctors(
                id SERIAL PRIMARY KEY,
                full_name VARCHAR(100),
                speciality VARCHAR(50)
            );
        """;
        String createPatients = """
            CREATE TABLE IF NOT EXISTS patients(
                id SERIAL PRIMARY KEY,
                full_name VARCHAR(100),
                age INT,
                doctor_id INT REFERENCES doctors(id)
            );
        """;
        try (Connection c = connect(); Statement st = c.createStatement()) {
            st.execute(createDoctors);
            st.execute(createPatients);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void insertDoctor(Doctor d) {
        String sql = "INSERT INTO doctors(full_name, speciality) VALUES (?,?)";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, d.getFullName()); ps.setString(2, d.getSpeciality()); ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<Doctor> getDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors ORDER BY id";
        try (Connection c = connect(); Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next())
                list.add(new Doctor(rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("speciality")));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static void insertPatient(Patient p) {
        String sql = "INSERT INTO patients(full_name, age) VALUES (?,?)";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getFullName()); ps.setInt(2, p.getAge()); ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<Patient> getPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY id";
        try (Connection c = connect(); Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next())
                list.add(new Patient(rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age")));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static void assignPatientToDoctor(int patientId, int doctorId) {
        String sql = "UPDATE patients SET doctor_id = ? WHERE id = ?";
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, doctorId); ps.setInt(2, patientId); ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}