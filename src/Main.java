import java.sql.*;


public class Main {
    static Connection connection;

    public static void main(String[] args) {
        //MyFrame myFrame = new MyFrame();
        //

        Main main = new Main();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class_journal","root","password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students");

            while (resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
            }

            main.addStudent("Mateuszek","Kawulok",
                     "Zory",474852154,"1990-04-11",845697412,"2a");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String addStudent(String first_name, String last_name, String city,
                            int phone_number, String date_of_birth, int parents_phone_number, String class_attendand){
        int result = 0;
        Date date_of_birth_as_Date = Date.valueOf(date_of_birth);

        String query = "INSERT INTO `class_journal`.`students`" +
                "(`first_name`" +
                ",`last_name`" +
                ",`city`," +
                "`phone_number`," +
                "`date_of_birth`," +
                "`parents_phone_number`," +
                "`class`)" +
                "VALUES (?,?,?,?,?,?,?);";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,first_name);
            preparedStatement.setString(2,last_name);
            preparedStatement.setString(3,city);
            preparedStatement.setInt(4,phone_number);
            preparedStatement.setDate(5,date_of_birth_as_Date);
            preparedStatement.setInt(6,parents_phone_number);
            preparedStatement.setString(7,class_attendand);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("affected rows: " + result);
        return query;
    }
}
