/**
 * Created by Gradergage on 30.06.2017.
 */
import java.sql.*;

public class Main {
    public static void main(String [] args)
    {
        // New class, used to perform connections by jdbc thin driver
        jdbc_oracle dbc=new jdbc_oracle();
        dbc.setURL("Server.dpat.in", "ORCL",1521);
        dbc.Connect("practice","practice123");
        Connection conn = dbc.getConnection();



        try {
            Statement st = null;
            ResultSet rs = null;
            st = conn.createStatement();


            //Q1 Count of fired employees
            rs = st.executeQuery (
                    "SELECT Count(*) " +
                    "FROM EMPLOYEES " +
                    "WHERE EMPLOYEE_ACTIVE = 0");
            while (rs.next()) {
                System.out.println("1) Count of fired employees: " + rs.getString(1));
            }

            //Q2 Locations with incorrect coords
            System.out.println("\n2) Locations with incorrect coords:");
            rs = st.executeQuery (
                    "SELECT l.LOCATION_NAME, COUNT(c.COORD_ID) AS CNT " +
                    "FROM COORDS c, LOCATIONS l " +
                    "WHERE c.LOCATION_ID=l.LOCATION_ID "+
                    "GROUP BY l.LOCATION_NAME " +
                    "HAVING COUNT(*) = 0");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            //Q3 Offices with their floor count sorted in descending order
            System.out.println("\n3) Offices with their floor count sorted in descending order:");
            rs = st.executeQuery (
                    "SELECT o.OFFICE_NAME, COUNT(f.FLOOR_ID) AS CNT " +
                    "FROM OFFICES o, FLOORS f " +
                    "WHERE o.OFFICE_ID=f.OFFICE_ID "+
                    "GROUP BY o.OFFICE_NAME "+
                    "ORDER BY CNT DESC");
            while (rs.next()) {
                System.out.println(rs.getString(1)+" "+ rs.getString(2));
            }

            //Q4 Contracts with their amount of locations over 5
            System.out.println("\n4) Contracts with their amount of locations over 5:");
            rs = st.executeQuery (
                    "SELECT c.CONTRACT_NAME, COUNT(l.LOCATION_ID) AS CNT " +
                    "FROM CONTRACTS c, LOCATIONS l " +
                    "WHERE c.CONTRACT_ID=l.CONTRACT_ID "+
                    "GROUP BY c.CONTRACT_NAME " +
                    "HAVING COUNT(*) > 5 " +
                    "ORDER BY CNT DESC");
            while (rs.next()) {
                System.out.println(rs.getString(1)+" "+ rs.getString(2));
            }

            //Q5 most young employee's city
            rs = st.executeQuery (
                    "SELECT c.CITY_NAME " +
                     "FROM CITIES c, OFFICES o, FLOORS f, COORDS co, EMPLOYEES e " +
                     "WHERE e.BIRTH_DATE=(SELECT MIN(BIRTH_DATE) FROM EMPLOYEES) "+
                     "AND e.EMPLOYEE_ID=co.ENTITY_ID "+
                     "AND co.FLOOR_ID=f.FLOOR_ID "+
                     "AND f.OFFICE_ID=o.OFFICE_ID "+
                     "AND o.CITY_ID=c.CITY_ID");
            while (rs.next()) {
                System.out.println("\n5) most young employee's city: " + rs.getString(1));
            }

            //Q6 Offices and floors occupied by managers and administrators
            System.out.println("\n6) Offices and floors occupied by managers and administrators:");
            rs = st.executeQuery (
                    "SELECT o.OFFICE_NAME, f.FLOOR_NAME " +
                    "FROM OFFICES o, FLOORS f, COORDS co, EMPLOYEES e, GROUPS g " +
                    "WHERE (e.GROUP_ID=1 OR e.GROUP_ID=2) "+
                    "AND e.EMPLOYEE_ID=co.ENTITY_ID "+
                    "AND co.FLOOR_ID=f.FLOOR_ID "+
                    "AND f.OFFICE_ID=o.OFFICE_ID " +
                    "GROUP BY o.OFFICE_NAME, f.FLOOR_NAME");
            while (rs.next()) {
                System.out.println(rs.getString(1)+" "+ rs.getString(2));
            }
            dbc.Disconnect(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
