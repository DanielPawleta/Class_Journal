import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;


public class Main {
    static Connection connection;
    private MyFrame myFrame;
    private Vector<Vector<String>> dataRowStudent;
    private Vector<Vector<String>> dataRowClass;

    public static void main(String[] args) {
        Main main = new Main();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class_journal", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //main.showStudents();
        main.showClasses();
        //main.findStudent("Daniell","Pawleta");
        //main.addStudent("Mateuszek","Kawulok", "Zory",474852154,"1990-04-11",845697412,"2a");
    }

    public Main() {
        myFrame = new MyFrame(this);
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

    private void showClasses(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from class");

            while (resultSet.next()){
                System.out.println(resultSet.getString("class_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected boolean checkClassName(String className){
        boolean isThisClassNameNotTaken = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from class");

            ArrayList<String> classNames = new ArrayList<>();
            while (resultSet.next()){
                classNames.add(resultSet.getString("class_name"));
            }
            isThisClassNameNotTaken = !classNames.contains(className);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isThisClassNameNotTaken;
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
        System.out.println("find student in main with first name = " + firstName + " and last name = " + lastName);
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

            dataRowStudent = new Vector<>();

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
                dataRowStudent.add(studentRow);

                System.out.println(resultSet.getString("first_name"));
            }

            if (dataRowStudent.size()==0){
                result=0;
            }

            else if (dataRowStudent.size()==1){
                //fire show student frame
                //int studentId = Integer.parseInt(dataRow.get(0).get(0));
                showStudentFrame();
                result=1;
            }

            else {
                ChooseStudentFrame chooseStudentFrame = new ChooseStudentFrame(myFrame, dataRowStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }

    protected int findStudent(int selectedStudentId) {
        System.out.println("find student in main with id = " + selectedStudentId);
        ResultSet resultSet;
        int result = 9;
        int count=0;

        String query = "SELECT * FROM students " +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(selectedStudentId));

            resultSet = preparedStatement.executeQuery();

            dataRowStudent = new Vector<>();

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
                dataRowStudent.add(studentRow);

                System.out.println(resultSet.getString("first_name"));
            }

            if (dataRowStudent.size()==0){
                result=0;
            }

            else if (dataRowStudent.size()==1){
                //fire show student frame
                //int studentId = Integer.parseInt(dataRow.get(0).get(0));
                showStudentFrame();
                result=1;
            }

            else {
                ChooseStudentFrame chooseStudentFrame = new ChooseStudentFrame(myFrame, dataRowStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }

    protected Vector<Vector<String>> findStudentsWithoutClass () {
        System.out.println("find student with no class value selectd");
        ResultSet resultSet;
        int count=0;

        String query = "SELECT * FROM students " +
                "WHERE class is null;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

            dataRowStudent = new Vector<>();

            while (resultSet.next()) {
                count++;
                Vector<String> studentRow = new Vector<>();
                studentRow.add(resultSet.getString("id"));
                studentRow.add(resultSet.getString("first_name"));
                studentRow.add(resultSet.getString("last_name"));
                /*
                studentRow.add(resultSet.getString("city"));
                studentRow.add(resultSet.getString("phone_number"));
                studentRow.add(resultSet.getString("date_of_birth"));
                studentRow.add(resultSet.getString("parents_phone_number"));
                studentRow.add(resultSet.getString("class"));
                 */
                dataRowStudent.add(studentRow);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found " + count +" students with no class definied");
        return dataRowStudent;
    }

    //for no-database connection purpose and testing
    /*
    protected int findStudent(String firstName, String lastName) {
        dataRow = new Vector<>();
        Vector<String> studentRow = new Vector<>();
        studentRow.add("74");//id
        studentRow.add("Daniel");
        studentRow.add("Pawleta");
        studentRow.add("Rybnik");
        studentRow.add("578412012");
        studentRow.add("1900-01-01");
        studentRow.add("541478523");
        studentRow.add("1d");
        dataRow.add(studentRow);

        showStudentFrame();
        return 1;
    }

    protected int findStudent(int selectedStudentId) {
        dataRow = new Vector<>();
        Vector<String> studentRow = new Vector<>();
        studentRow.add("74");//id
        studentRow.add("Daniel");
        studentRow.add("Pawleta");
        studentRow.add("Rybnik");
        studentRow.add("578412012");
        studentRow.add("1900-01-01");
        studentRow.add("541478523");
        studentRow.add("1d");
        dataRow.add(studentRow);

        showStudentFrame();
        return 1;
    }
     */

    protected void showStudentFrame(){
        System.out.println("Show student frame from main");
        StudentFrame studentFrame = new StudentFrame(myFrame, dataRowStudent);
    }

    protected void showClassFrame(){
        System.out.println("Show class frame from main");
        ClassFrame classFrame = new ClassFrame(myFrame, dataRowClass);
    }

    public int updateStudent(int i, int selectedStudentId, String newVaule) {
        //i stands for column name of value to be updated
        //0 - first name
        //1 - last name
        //2 - city
        //3 - phone number
        //4 - date of birth
        //5 - parents phone number
        //6 - class

        String columnName="";
        switch (i) {
            case 0:
                columnName = "`first_name`";
                break;
            case 1:
                columnName = "`last_name`";
                break;
            case 2:
                columnName = "`city`";
                break;
            case 3:
                columnName = "`phone_number`";
                break;
            case 4:
                columnName = "`date_of_birth`";
                break;
            case 5:
                columnName = "`parents_phone_number`";
                break;
            case 6:
                columnName = "`class`";
                break;
        }

        int result = 0;
        String query = "UPDATE `class_journal`.`students` SET " + columnName +
                " = ? " +
                "WHERE id = ?;";
        try {
            System.out.println(columnName + query + newVaule + selectedStudentId);

            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newVaule);
            preparedStatement.setString(2, String.valueOf(selectedStudentId));

            result = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("affected rows: " + result);
        return result;

    }

    protected String addClass(String className, String supervisingTeacher, ArrayList<Integer> studentsId){
        int result = 0;

        int student1;
        int student2;


        String query = "INSERT INTO `class_journal`.`class`" +
                "(`class_name`" +
                ",`supervising_teacher`" +
                ",`student_1`" +
                ",`student_2`" +
                ",`student_3`" +
                ",`student_4`)" +
                "VALUES (?,?,?,?,?,?);";



        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,className);
            preparedStatement.setString(2,supervisingTeacher);


            for (int j=0; j<studentsId.size();j++){
                if (studentsId.get(j) != -1){
                    preparedStatement.setInt(j+3,studentsId.get(j));
                }
                else preparedStatement.setNull(j+3,Types.INTEGER);
            }

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("affected rows: " + result);
        return query;
    }

    protected int findClass(String className) {
        System.out.println("find class in main with class name = " + className);
        ResultSet resultSet;
        int result = 9;
        int count=0;

        String query = "SELECT * FROM class " +
                "WHERE class_name = "+
                "?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,className);

            resultSet = preparedStatement.executeQuery();

            dataRowClass = new Vector<>();

            while (resultSet.next()) {
                count++;
                Vector<String> classRow = new Vector<>();
                classRow.add(resultSet.getString("id"));
                classRow.add(resultSet.getString("class_name"));
                classRow.add(resultSet.getString("supervising_teacher"));
                classRow.add(resultSet.getString("student_1"));
                classRow.add(resultSet.getString("student_2"));
                classRow.add(resultSet.getString("student_3"));
                classRow.add(resultSet.getString("student_4"));
                classRow.add(resultSet.getString("student_5"));
                classRow.add(resultSet.getString("student_6"));
                classRow.add(resultSet.getString("student_7"));
                classRow.add(resultSet.getString("student_8"));
                classRow.add(resultSet.getString("student_9"));
                classRow.add(resultSet.getString("student_10"));

                dataRowClass.add(classRow);

                System.out.println(resultSet.getString("class_name"));
            }

            if (dataRowClass.size()==0){
                result=0;
            }

            else if (dataRowClass.size()==1){
                showClassFrame();
                result=1;
            }

            else {
                ChooseStudentFrame chooseStudentFrame = new ChooseStudentFrame(myFrame, dataRowClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }

    protected int findClass(boolean classNameKnown, String supervisingTeacher) {
        System.out.println("find class in main without class name and with supervising teacher = " + supervisingTeacher);
        ResultSet resultSet;
        int result = 9;
        int count=0;

        String query = "SELECT * FROM class " +
                "WHERE supervising_teacher = "+
                "?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,supervisingTeacher);

            resultSet = preparedStatement.executeQuery();

            dataRowClass = new Vector<>();

            while (resultSet.next()) {
                count++;
                Vector<String> classRow = new Vector<>();
                classRow.add(resultSet.getString("id"));
                classRow.add(resultSet.getString("class_name"));
                classRow.add(resultSet.getString("supervising_teacher"));
                classRow.add(resultSet.getString("student_1"));
                classRow.add(resultSet.getString("student_2"));
                classRow.add(resultSet.getString("student_3"));
                classRow.add(resultSet.getString("student_4"));
                classRow.add(resultSet.getString("student_5"));
                classRow.add(resultSet.getString("student_6"));
                classRow.add(resultSet.getString("student_7"));
                classRow.add(resultSet.getString("student_8"));
                classRow.add(resultSet.getString("student_9"));
                classRow.add(resultSet.getString("student_10"));

                dataRowClass.add(classRow);

                System.out.println(resultSet.getString("class_name"));
            }

            if (dataRowClass.size()==0){
                result=0;
            }

            else if (dataRowClass.size()==1){
                showClassFrame();
                result=1;
            }

            else {
                ChooseClassFrame chooseClassFrame = new ChooseClassFrame(myFrame, dataRowClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }

    public String getStudentNameAndLastName(int studentId) {
        System.out.println("get student name and last name for id: " + studentId);
        ResultSet resultSet;
        String nameAndLastName = "no results";

        String query = "SELECT first_name, last_name FROM students " +
                "WHERE id = "+
                "?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(studentId));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nameAndLastName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameAndLastName;
    }
}
