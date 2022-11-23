package jdbc;

import java.sql.*;

public class DBConnection_maria{

    private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://49.247.39.108:3309/EMS";

    private static final String DB_USERNAME = "root";

    private static final String DB_PASSWORD = "0312184800";

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;

    private static void connectDB() {

        try {
            Class.forName(DB_DRIVER_CLASS);

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            System.out.println("연결성공");

            stmt = connection.createStatement();
            System.out.println("Statement객체 생성 성공");

            rs = stmt.executeQuery("select write_time from TB_MOMENT WHERE write_time BETWEEN 2022-10-11 AND 2022-10-12");
            //write_time 출력

            while(rs.next()) { //rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다.
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2)); //getInt(1)은 컬럼의 1번째 값을 Int형으로 가져온다. / getString(2)는 컬럼의 2번째 값을 String형으로 가져온다.
            }
            rs.close();
            stmt.close();
            connection.close();

        }

        catch (ClassNotFoundException e) {

            // TODO Auto-generated catch block

            System.out.println("드라이브 로딩 실패");
            System.out.println("사유"+e.getMessage());

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            System.out.println("DB 연결 실패");
            System.out.println("사유"+e.getMessage());

        }
    }

    public static void main(String[] args) {


        DBConnection_maria test = new DBConnection_maria();
        test.connectDB();
    }

}