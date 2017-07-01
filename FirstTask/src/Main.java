import java.sql.*;

public class Main {
    public static void main(String[] args) {

        // New class, used to perform connections by jdbc thin driver
        OracleThinJDBC dbc = new OracleThinJDBC();
        dbc.setURL("Server.dpat.in", "ORCL", 1521);
        dbc.connect("practice", "practice123");

        Connection conn = dbc.getConnection();

        try {
            //Q1 Count of fired employees
            Statement st = conn.createStatement();
            System.out.println("\n2) Count of fired employees:");
            displayExecutedQuery(st,
                    "SELECT Count(*) " +
                            "FROM EMPLOYEES " +
                            "WHERE EMPLOYEE_ACTIVE = 0");

            //Q2 Locations with incorrect coords
            System.out.println("\n2) Locations with incorrect coords:");
            displayExecutedQuery(st,
                    "SELECT l.LOCATION_NAME, COUNT(c.COORD_ID) AS CNT " +
                            "FROM COORDS c, LOCATIONS l " +
                            "WHERE c.LOCATION_ID=l.LOCATION_ID " +
                            "GROUP BY l.LOCATION_NAME " +
                            "HAVING COUNT(*) = 0");

            //Q3 Offices with their floor count sorted in descending order
            System.out.println("\n3) Offices with their floor count sorted in descending order:");
            displayExecutedQuery(st,
                    "SELECT o.OFFICE_NAME, COUNT(f.FLOOR_ID) AS CNT " +
                            "FROM OFFICES o, FLOORS f " +
                            "WHERE o.OFFICE_ID=f.OFFICE_ID " +
                            "GROUP BY o.OFFICE_NAME " +
                            "ORDER BY CNT DESC");

            //Q4 Contracts with their amount of locations over 5
            System.out.println("\n4) Contracts with their amount of locations over 5:");
            displayExecutedQuery(st,
                    "SELECT c.CONTRACT_NAME, COUNT(l.LOCATION_ID) AS CNT " +
                            "FROM CONTRACTS c, LOCATIONS l " +
                            "WHERE c.CONTRACT_ID=l.CONTRACT_ID " +
                            "GROUP BY c.CONTRACT_NAME " +
                            "HAVING COUNT(*) > 5 " +
                            "ORDER BY CNT DESC");

            //Q5 most young employee's city
            System.out.println("\n6) Most young employee's city:");
            displayExecutedQuery(st,
                    "SELECT c.CITY_NAME " +
                            "FROM CITIES c, OFFICES o, FLOORS f, COORDS co, EMPLOYEES e " +
                            "WHERE e.BIRTH_DATE=(SELECT MIN(BIRTH_DATE) FROM EMPLOYEES) " +
                            "AND e.EMPLOYEE_ID=co.ENTITY_ID " +
                            "AND co.FLOOR_ID=f.FLOOR_ID " +
                            "AND f.OFFICE_ID=o.OFFICE_ID " +
                            "AND o.CITY_ID=c.CITY_ID");

            //Q6 Offices and floors occupied by managers and administrators
            System.out.println("\n6) Offices and floors occupied by managers and administrators:");
            displayExecutedQuery(st,
                    "SELECT o.OFFICE_NAME, f.FLOOR_NAME " +
                            "FROM OFFICES o, FLOORS f, COORDS co, EMPLOYEES e, GROUPS g " +
                            "WHERE (e.GROUP_ID=1 OR e.GROUP_ID=2) " +
                            "AND e.EMPLOYEE_ID=co.ENTITY_ID " +
                            "AND co.FLOOR_ID=f.FLOOR_ID " +
                            "AND f.OFFICE_ID=o.OFFICE_ID " +
                            "GROUP BY o.OFFICE_NAME, f.FLOOR_NAME");


        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            dbc.disconnect(conn);
        }

    }

    public static void displayExecutedQuery(Statement st, String query) throws SQLException {

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            for (int i = 1; ; i++) {
                try {

                    rs.getString(i);
                    System.out.print(rs.getString(i) + " ");

                } catch (SQLException e) {
                    break;
                }
            }
            System.out.println();
        }
    }
}
