import java.sql.*;
import java.util.Date;

public class Main {
    static Connection connection;

    public static void main(String[] args) {
        //MyFrame myFrame = new MyFrame();

        Main main = new Main();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class_journal","root","password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students");

            while (resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
                //System.out.println(resultSet.getDate("date_of_birth"));
            }

            main.addStudent("Mateusz","Kawulok",
                    "Zory",474852154,new Date("1990-04-11"),845697412,"2a");


        } catch (SQLException e) {
            e.printStackTrace();
        }





    }

    private String addStudent(String first_name, String last_name, String city,
                            int phone_number, Date date_of_birth, int parents_phone_number, String class_attendand){
        int result = 0;
        String query = "INSERT INTO `class_journal`.`students`" +
                "(`id`" +
                "`first_name`" +
                "`last_name`" +
                "`city`" +
                "`phone_number`" +
                "`date_of_birth`" +
                "`parents_phone_number`" +
                "`class`" +
                "VALUES (" +
                first_name +
                last_name +
                city +
                phone_number +
                date_of_birth +
                parents_phone_number +
                class_attendand +");";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("affected rows: " + result);
        return query;


    }
}
