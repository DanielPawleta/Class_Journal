import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;


public class Main {
    static Connection connection;
    private MyFrame myFrame;
    private Vector<Vector<String>> dataRowClass;//in case of multi results from SQL it's vector of vectors of classes

    public static void main(String[] args) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class_journal", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        new Main();

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

    //Methods for working with DB table 'students'
    protected String addStudent(String first_name, String last_name, String city, int phone_number, String date_of_birth, int parents_phone_number, String class_attending){

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
            if (class_attending==null){
                preparedStatement.setNull(7,Types.VARCHAR);
            }
            else {
                preparedStatement.setString(7,class_attending);
            }
            result = preparedStatement.executeUpdate();

            if (class_attending!= null) addStudentToClassOnFirstFreeSlot(getLatestStudentId(),class_attending);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("affected rows: " + result);
        return query;
    }

    protected int findStudent(String firstName, String lastName) {
        System.out.println("find student in main with first name = " + firstName + " and last name = " + lastName);
        Vector<Vector<String>> dataRowStudent;
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
                String className = resultSet.getString("class");
                if (className!=null){;
                    className = getClassNameByClassId(Integer.parseInt(className));
                    System.out.println("class name: " + className);
                }
                studentRow.add(className);
                dataRowStudent.add(studentRow);

                System.out.println(resultSet.getString("first_name"));
            }

            if (dataRowStudent.size()==0){
                result=0;
            }

            else if (dataRowStudent.size()==1){
                showStudentFrame(dataRowStudent);
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
        Vector<Vector<String>> dataRowStudent;
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
                String className = resultSet.getString("class");
                if (className!=null){;
                    className = getClassNameByClassId(Integer.parseInt(className));
                    System.out.println("class name: " + className);
                }
                studentRow.add(className);
                dataRowStudent.add(studentRow);

                System.out.println(resultSet.getString("first_name"));
            }

            if (dataRowStudent.size()==0){
                result=0;
            }

            else if (dataRowStudent.size()==1){
                showStudentFrame(dataRowStudent);
                result=1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }

    protected Vector<Vector<String>> findStudentsWithoutClass () {
        System.out.println("find student with no class value selectd");
        Vector<Vector<String>> dataRowStudentsWithoutClass = new Vector<>();
        ResultSet resultSet;
        int count=0;

        String query = "SELECT * FROM students " +
                "WHERE class is null;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

            dataRowStudentsWithoutClass = new Vector<>();

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
                String className = resultSet.getString("class");
                if (className!=null){;
                    className = getClassNameByClassId(Integer.parseInt(className));
                }
                studentRow.add(className);
                dataRowStudentsWithoutClass.add(studentRow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            dataRowStudentsWithoutClass = new Vector<>();
        }

        System.out.println("Found " + count +" students with no class definied");
        return dataRowStudentsWithoutClass;
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

    protected void showStudentFrame(Vector<Vector<String>> dataRowStudent){
        System.out.println("Show student frame from main");
        StudentFrame studentFrame = new StudentFrame(myFrame, dataRowStudent);
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

    private String getLatestStudentId(){
        String latestStudentId = null;

        String query = "SELECT MAX(Id) FROM students;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                latestStudentId = resultSet.getString("MAX(Id)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return latestStudentId;
    }

    protected Vector<Vector<String>> findAllStudent () {
        System.out.println("find all students");
        Vector<Vector<String>> dataRowAllStudents = new Vector<>();
        ResultSet resultSet;
        int count=0;

        String query = "SELECT * FROM students;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

            dataRowAllStudents = new Vector<>();

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
                String className = resultSet.getString("class");
                if (className!=null){;
                    className = getClassNameByClassId(Integer.parseInt(className));
                    System.out.println("class name: " + className);
                }
                studentRow.add(className);
                dataRowAllStudents.add(studentRow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            dataRowAllStudents = new Vector<>();
        }

        System.out.println("Found " + count +" students");
        return dataRowAllStudents;
    }


    //Methods for working with DB table 'teachers'
    protected String addTeacher(String first_name, String last_name, String city, int phone_number, String date_of_birth, String supervising_class){

        int result = 0;
        Date date_of_birth_as_Date = Date.valueOf(date_of_birth);

        String query = "INSERT INTO `class_journal`.`teachers`" +
                "(`first_name`" +
                ",`last_name`" +
                ",`city`," +
                "`phone_number`," +
                "`date_of_birth`," +
                "`class_supervising`)" +
                "VALUES (?,?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,first_name);
            preparedStatement.setString(2,last_name);
            preparedStatement.setString(3,city);
            preparedStatement.setInt(4,phone_number);
            preparedStatement.setDate(5,date_of_birth_as_Date);
            if (supervising_class==null){
                preparedStatement.setNull(6,Types.VARCHAR);
            }
            else {
                preparedStatement.setString(6,supervising_class);
            }
            result = preparedStatement.executeUpdate();

            if (supervising_class!=null) addSupervisingTeacherToClass(getLatestTeacherId(),supervising_class);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("affected rows: " + result);
        return query;
    }

    protected int findTeacher(String firstName, String lastName) {
        System.out.println("find teacher in main with first name = " + firstName + " and last name = " + lastName);
        Vector<Vector<String>> dataRowTeacher = new Vector<>();
        ResultSet resultSet;
        int result = 9;
        int count=0;

        String query = "SELECT * FROM teachers " +
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

            while (resultSet.next()) {
                count++;
                Vector<String> teacherRow = new Vector<>();
                teacherRow.add(resultSet.getString("id"));
                teacherRow.add(resultSet.getString("first_name"));
                teacherRow.add(resultSet.getString("last_name"));
                teacherRow.add(resultSet.getString("city"));
                teacherRow.add(resultSet.getString("phone_number"));
                teacherRow.add(resultSet.getString("date_of_birth"));
                String classSupervisingName = resultSet.getString("class_supervising");
                if (classSupervisingName!=null){
                    classSupervisingName = getClassNameByClassId(Integer.parseInt(classSupervisingName));
                }
                teacherRow.add(classSupervisingName);
                dataRowTeacher.add(teacherRow);

                System.out.println(resultSet.getString("first_name"));
            }

            if (dataRowTeacher.size()==0){
                result=0;
            }

            else if (dataRowTeacher.size()==1){
                showTeacherFrame(dataRowTeacher);
                result=1;
            }

            else {
                ChooseTeacherFrame chooseTeacherFrame = new ChooseTeacherFrame(myFrame, dataRowTeacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }

    protected Vector<Vector<String>> findTeachersWithoutSupervisingClass () {
        System.out.println("find teachers with no supervising class selected");
        Vector<Vector<String>> dataRowTeacher = new Vector<>();
        ResultSet resultSet;
        int count=0;

        String query = "SELECT * FROM teachers " +
                "WHERE class_supervising is null;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count++;
                Vector<String> teacherRow = new Vector<>();
                teacherRow.add(resultSet.getString("id"));
                teacherRow.add(resultSet.getString("first_name"));
                teacherRow.add(resultSet.getString("last_name"));
                dataRowTeacher.add(teacherRow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException ignored){
        }
        System.out.println("Found " + count +" teachers with no class supervising definied");
        return dataRowTeacher;
    }

    protected int findTeacher(int selectedTeacherId) {
        System.out.println("find teacher in main with id = " + selectedTeacherId);
        Vector<Vector<String>> dataRowTeacher;
        ResultSet resultSet;
        int result = 9;
        int count=0;

        String query = "SELECT * FROM teachers " +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(selectedTeacherId));

            resultSet = preparedStatement.executeQuery();

            dataRowTeacher = new Vector<>();

            while (resultSet.next()) {
                count++;
                Vector<String> teacherRow = new Vector<>();
                teacherRow.add(resultSet.getString("id"));
                teacherRow.add(resultSet.getString("first_name"));
                teacherRow.add(resultSet.getString("last_name"));
                teacherRow.add(resultSet.getString("city"));
                teacherRow.add(resultSet.getString("phone_number"));
                teacherRow.add(resultSet.getString("date_of_birth"));
                String classSupervisingName = resultSet.getString("class_supervising");
                if (classSupervisingName!=null){
                    classSupervisingName = getClassNameByClassId(Integer.parseInt(classSupervisingName));
                }
                teacherRow.add(classSupervisingName);
                dataRowTeacher.add(teacherRow);

                System.out.println(resultSet.getString("first_name"));
            }

            if (dataRowTeacher.size()==0){
                result=0;
            }

            else if (dataRowTeacher.size()==1){
                showTeacherFrame(dataRowTeacher);
                result=1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }

    public int updateTeacher(int i, int selectedTeacherId, String newVaule) {
        //i stands for column name of value to be updated
        //0 - first name
        //1 - last name
        //2 - city
        //3 - phone number
        //4 - date of birth
        //5 - class_supervising

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
                columnName = "`class_supervising`";
                break;
        }

        int result = 0;
        String query = "UPDATE `class_journal`.`teachers` SET " + columnName +
                " = ? " +
                "WHERE id = ?;";
        try {
            System.out.println(columnName + query + newVaule + selectedTeacherId);

            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newVaule);
            preparedStatement.setString(2, String.valueOf(selectedTeacherId));

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("affected rows: " + result);
        return result;
    }

    private void setNullForSupervisingTeacherValue(int selectedTeacherId) {
        //
    }

    protected void showTeacherFrame(Vector<Vector<String>> dataRowTeacher){
        System.out.println("Show teacher frame from main");
        TeacherFrame teacherFrame = new TeacherFrame(myFrame, dataRowTeacher);
    }

    protected boolean checkClassName(String className){
        //true - this class name is not in DB
        //flase - this class name is already in DB
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

    public String getTeacherNameAndLastName(int teacherId) {
        System.out.println("get teacher name and last name for id: " + teacherId);
        ResultSet resultSet;
        String nameAndLastName = "no results";

        String query = "SELECT first_name, last_name FROM teachers " +
                "WHERE id = "+
                "?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(teacherId));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nameAndLastName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameAndLastName;
    }

    private String getLatestTeacherId(){
        String latestTeacherId = null;

        String query = "SELECT MAX(Id) FROM teachers;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                latestTeacherId = resultSet.getString("MAX(id)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return latestTeacherId;
    }

    protected Vector<Vector<String>> findAllTeacher () {
        System.out.println("find all teachers");
        Vector<Vector<String>> dataRowAllTeachers = new Vector<>();
        ResultSet resultSet;
        int count=0;

        String query = "SELECT * FROM teachers;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

            dataRowAllTeachers = new Vector<>();

            while (resultSet.next()) {
                count++;
                Vector<String> teacherRow = new Vector<>();
                teacherRow.add(resultSet.getString("id"));
                teacherRow.add(resultSet.getString("first_name"));
                teacherRow.add(resultSet.getString("last_name"));
                teacherRow.add(resultSet.getString("city"));
                teacherRow.add(resultSet.getString("phone_number"));
                teacherRow.add(resultSet.getString("date_of_birth"));
                String classSupervisingName = resultSet.getString("class_supervising");
                if (classSupervisingName!=null){
                    classSupervisingName = getClassNameByClassId(Integer.parseInt(classSupervisingName));
                }
                teacherRow.add(classSupervisingName);
                dataRowAllTeachers.add(teacherRow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            dataRowAllTeachers = new Vector<>();
        }

        System.out.println("Found " + count +" teachers");
        return dataRowAllTeachers;
    }



    //Methods for working with DB table 'class'
    protected String addClass(String className, int supervisingTeacherId, ArrayList<Integer> studentsId){
        int result = 0;

        String query = "INSERT INTO `class_journal`.`class`" +
                "(`class_name`" +
                ",`supervising_teacher`" +
                ",`student_1`" +
                ",`student_2`" +
                ",`student_3`" +
                ",`student_4`" +
                ",`student_5`" +
                ",`student_6`" +
                ",`student_7`" +
                ",`student_8`" +
                ",`student_9`" +
                ",`student_10`)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,className);


            if (supervisingTeacherId!=-1){
                preparedStatement.setInt(2, supervisingTeacherId);
            }
            else preparedStatement.setNull(2,Types.INTEGER);

            for (int j=0; j<studentsId.size();j++){
                if (studentsId.get(j) != -1){
                    //Setting studentId from array to prepared statement
                    preparedStatement.setInt(j+3,studentsId.get(j));
                }
                else preparedStatement.setNull(j+3,Types.INTEGER);
            }

            result = preparedStatement.executeUpdate();

            String classID = String.valueOf(getLatestClassId());

            if (supervisingTeacherId!=-1){
                updateTeacher(5, supervisingTeacherId, classID);
            }

            for (Integer integer : studentsId) {
                if (integer != -1) {
                    //Setting class for students
                    updateStudent(6, integer, classID);
                }
            }

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

            else {
                showClassFrame();
                result=1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Found rows: " + count);
        return result;
    }

    protected void findClass(int classId) {
        System.out.println("find class in main with class Id = " + classId);
        ResultSet resultSet;

        String query = "SELECT * FROM class " +
                "WHERE id = "+
                "?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(classId));

            resultSet = preparedStatement.executeQuery();

            dataRowClass = new Vector<>();

            while (resultSet.next()) {
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
            showClassFrame();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

                System.out.println(resultSet.getString("supervising_teacher"));
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

    public int updateClass(int i, int selectedClassId, String newValue) {
        //i stands for column name to be updated
        //0 - class name
        //1 - supervising teacher
        //2 - student 1
        //3 - student 2
        //4 - student 3
        //5 - student 4
        //6 - student 5

        String columnName="";
        switch (i) {
            case 0:
                columnName = "`class_name`";
                break;
            case 1:
                columnName = "`supervising_teacher`";
                break;
            case 2:
                columnName = "`student_1`";
                break;
            case 3:
                columnName = "`student_2`";
                break;
            case 4:
                columnName = "`student_3`";
                break;
            case 5:
                columnName = "`student_4`";
                break;
            case 6:
                columnName = "`student_5`";
                break;
            case 7:
                columnName = "`student_6`";
                break;
            case 8:
                columnName = "`student_7`";
                break;
            case 9:
                columnName = "`student_8`";
                break;
            case 10:
                columnName = "`student_9`";
                break;
            case 11:
                columnName = "`student_10`";
                break;
        }

        int result = 0;
        String query = "UPDATE `class_journal`.`class` SET " + columnName +
                " = ? " +
                "WHERE id = ?;";
        try {
            System.out.println(columnName + query + newValue + selectedClassId);

            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, String.valueOf(selectedClassId));

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("affected rows: " + result);
        return result;
    }

    protected void addStudentToClassOnFirstFreeSlot(String studentId, String class_attending){
        String firstFreeSlotNumber = this.findFirstFreeSlotInClass(class_attending);

        String query = "UPDATE `class_journal`.`class` SET " + firstFreeSlotNumber  +
                " = ? " +
                "WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, class_attending);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addSupervisingTeacherToClass(String teacherId, String supervising_class){
        String query = "UPDATE `class_journal`.`class` SET supervising_teacher"  +
                " = ? " +
                "WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, teacherId);
            preparedStatement.setString(2, supervising_class);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void showClassFrame(){
        System.out.println("Show class frame from main");
        ClassFrame classFrame = new ClassFrame(myFrame, dataRowClass,0);
    }

    private String findFirstFreeSlotInClass (String classId) {
        String firstFreeSlotFullName = null;
        System.out.println("find first free slot in given class id: " + classId);
        ResultSet resultSet;

        String query = "SELECT * FROM class " +
                "WHERE id = ?;";
        Vector<String> classRow = new Vector<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,classId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i=0;i<classRow.size();i++){
            if (classRow.get(i)==null){
                int firstFreeStudentNumber = i+1;
                firstFreeSlotFullName = "student_" + firstFreeStudentNumber;
                break;
            }
        }
        System.out.println("Found first free slot at: " + firstFreeSlotFullName);
        return firstFreeSlotFullName;
    }

    protected String getClassIdByClassName(String classAttendingName) {
        System.out.println("get class id by class name in main with class name = " + classAttendingName);
        ResultSet resultSet;
        String classId="";

        String query = "SELECT id FROM class " +
                "WHERE class_name = "+
                "?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,classAttendingName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                classId = resultSet.getString("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classId;
    }

    public String getClassNameByClassId(int classId) {
        System.out.println("get class name for id: " + classId);
        ResultSet resultSet;
        String className = "no results";

        String query = "SELECT class_name FROM class " +
                "WHERE id = "+
                "?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(classId));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                className = resultSet.getString("class_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return className;
    }

    protected Vector<Vector<String>> findClassWithoutSupervisingTeacher () {
        Vector<Vector<String>> dataRowClassWithoutSupervisingTeacher = new Vector<>();
        System.out.println("find class with no supervising teacher value selected");
        ResultSet resultSet;
        int count=0;

        String query = "SELECT * FROM class " +
                "WHERE supervising_teacher is null;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count++;
                Vector<String> classRow = new Vector<>();
                classRow.add(resultSet.getString("class_name"));
                dataRowClassWithoutSupervisingTeacher.add(classRow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException ignored){
        }
        System.out.println("Found " + count +" classes with no supervising teacher defined");
        return dataRowClassWithoutSupervisingTeacher;
    }

    protected Vector<Vector<String>> findClassWithEmptyStudentPlaces () {
        System.out.println("find class with free student slots");
        Vector<Vector<String>> dataRowClass = new Vector<>();

        ResultSet resultSet;
        int count=0;

        String query = "SELECT * FROM class " +
                "WHERE student_1 is null " +
                "OR student_2 is null " +
                "OR student_3 is null " +
                "OR student_4 is null " +
                "OR student_5 is null " +
                "OR student_6 is null " +
                "OR student_7 is null " +
                "OR student_8 is null " +
                "OR student_9 is null " +
                "OR student_10 is null;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

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

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException ignored) {
        }

        System.out.println("Found " + count +" classes with free slots");
        return dataRowClass;
    }

    private String getLatestClassId(){
        String latestClassId = null;

        String query = "SELECT MAX(Id) FROM class;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                latestClassId = resultSet.getString("MAX(id)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return latestClassId;
    }

    protected Vector<Vector<String>> findAllClass () {
        System.out.println("find all class");
        Vector<Vector<String>> dataRowAllClasses = new Vector<>();
        ResultSet resultSet;
        int count=0;

        String query = "SELECT * FROM class;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            resultSet = preparedStatement.executeQuery();

            dataRowAllClasses = new Vector<>();

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
                dataRowAllClasses.add(classRow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            dataRowAllClasses = new Vector<>();
        }

        System.out.println("Found " + count +" classes");
        return dataRowAllClasses;
    }

    protected int countFreeStudentSlotsInAllClasses(){
        int result=0;
        Vector<Vector<String>> dataRowAllClasses = this.findAllClass();
        for (Vector<String> classRow : dataRowAllClasses){
            for (int i=3;i<13;i++){
                if (classRow.get(i)==null) result++;
            }
        }
        return result;
    }

    protected void setNullForSupervisingTeacherValue(String classId){
        System.out.println("set null for teacher in class " + this.getClassNameByClassId(Integer.parseInt(classId)));
        String query = "UPDATE `class_journal`.`class` SET `supervising_teacher`" +
                " = ? " +
                "WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, classId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentFromClass(String classId, int selectedStudentId) {
        //first need to check slot number in class for this student
        String studentSlotNumber;
        boolean studentFoundInClass = false;
        String query = "SELECT * FROM class " +
                "WHERE id = "+
                "?;";

        ResultSet resultSet;
        Vector<String> classRow = new Vector<>();
        String studentId = String.valueOf(selectedStudentId);
        String columnName="";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, classId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                for (int i = 1; i <= 10; i++) {
                    columnName = "student_" + i;
                    if (resultSet.getString(columnName).equals(studentId)) {
                        studentSlotNumber = String.valueOf(i);
                        studentFoundInClass =true;
                        break;
                    }
                }
            }
        }
        catch (SQLException e) {
             e.printStackTrace();
        }

        //second set null on found student slot number in selected class
        if (!studentFoundInClass) {
            System.out.println("Error in main.deleteStudentFromClass - student with id: " + selectedStudentId +
                    " not found in class with id: " + classId);
            return;
        }

        query = "UPDATE `class_journal`.`class` SET " + columnName +
                " = ? " +
                "WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, classId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Method for all tables in DB
    protected void deleteRow(int i,String rowId){
        //i stands for table name from which row will be deleted
        // 0 - student table
        // 1 - class table
        // 2 - teacher table
        int result = 0;

        String tableName="";
        switch (i) {
            case 0:
                tableName = "`students`";
                break;
            case 1:
                tableName = "`class`";
                break;
            case 2:
                tableName = "`teachers`";
                break;
        }

        String query = "DELETE FROM `class_journal`." + tableName +
                " WHERE id =?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,rowId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("deleted rows from " + tableName + " :" + result);
    }


}
