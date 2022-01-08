import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;


public class Main {
    static Connection connection;

    public static void main(String[] args) {
        Main main = new Main();
        MyFrame myFrame = new MyFrame(main);
        //
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class_journal", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //main.showStudents();
        main.findStudent("Daniell","Pawleta");
        //main.addStudent("Mateuszek","Kawulok", "Zory",474852154,"1990-04-11",845697412,"2a");

    }



    private void showStudents(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students");

            while (resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String addStudent(String first_name, String last_name, String city, int phone_number, String date_of_birth, int parents_phone_number, String class_attendand){

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

    protected int findStudent(String firstName, String lastName) {
        ResultSet resultSet;
        int result = 9;
        int count=0;

        String query = "SELECT * FROM students " +
                "WHERE first_name = "+
                "?"+
                " AND "+
                "last_name = "+
                "?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);

            resultSet = preparedStatement.executeQuery();

            Vector<Vector<String>> dataRow = new Vector<>();

            while (resultSet.next()) {
                count++;
                Vector<String> studentRow = new Vector<>();
                studentRow.add(resultSet.getString("id"));
                studentRow.add(resultSet.getString("first_name"));
                studentRow.add(resultSet.getString("last_name"));
                studentRow.add(resultSet.getString("city"));
                studentRow.add(resultSet.getString("phone_number"));
                studentRow.add(resultSet.getString("date_of_birth"));
                studentRow.add(resultSet.getString("parents_phone_number"));
                studentRow.add(resultSet.getString("class"));
                dataRow.add(studentRow);

                System.out.println(resultSet.getString("first_name"));
                //list.add(Integer.parseInt(resultSet.getString("id")));
            }
            //list.add(0,count);

            if (dataRow.size()==0){
                result=0;
            }

            else if (dataRow.size()==1){
                //fire show student frame
                result=1;
            }

            else {
                new ChooseStudentFrameTest(dataRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }
}
