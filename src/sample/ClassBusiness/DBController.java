package sample.ClassBusiness;

import org.apache.commons.lang3.RandomStringUtils;

import java.sql.*;

public class DBController{
    private static Connection connection= null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DBController(String s){
        connection = null;
        this.statement = null;
        this.preparedStatement = null;
        this.resultSet = null;
        Connection con;
    }
    static void connect() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "wild hunt");
                System.out.println("S-a conectat la baza de date");
            } catch (Exception e) {
                System.out.println(e + "Nu s-a conectat");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static boolean findUser(String userName) throws SQLException {
        Statement statement = connection.createStatement();
        String string = new String("select * from mydb.user where mydb.user.username='" + userName + "';");
        ResultSet resultSet = statement.executeQuery(string);
        if (resultSet.next()) {
            return resultSet.getString("user") != "";
        }
        return false;
    }
    public static boolean findEmail(String email) throws SQLException {
        Statement statement = connection.createStatement();
        String string= new String("select * from mydb.user where mydb.user.email='"+email+"';");
        ResultSet resultSet=statement.executeQuery(string);
        if(resultSet.next()){
            return resultSet.getString("email") != "";
        }
        return false;
    }
    public static void changePass(String email)throws SQLException{
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String pwd = RandomStringUtils.random( 8, characters );
        Statement statement = connection.createStatement();
        String string= new String("update mydb.user set mydb.user.password= '"+pwd+"' where mydb.user.email='"+email+"';");
        statement.executeUpdate(string);
    }
    public static boolean logUtil(String userName,String pass) throws SQLException{
        Statement statement = connection.createStatement();
        String string = new String("select *from mydb.user where mydb.user.username='" + userName + "';");
        ResultSet resultSet = statement
                .executeQuery(string);
        if(resultSet.next()) {
            return ((resultSet.getString("username").contains(userName)) && (resultSet.getString("password").contains(pass)));
        }
        return false;
    }
    public static boolean addUser(String userName,String pass,String email) throws SQLException {
        if(!findUser(userName)) {
            Statement statement = connection.createStatement();
            String string = new String("Insert into mydb.user(username,email,password) VALUES('" + userName + "','" + email + "','" +
                    pass + "');");
            statement.executeUpdate(string);
            return true;
        }
        else return false;
    }
    public static ResultSet getStars() throws SQLException {
        Statement statement = connection.createStatement();
        return statement
                .executeQuery("select * from movies.stars");
    }
    public static ResultSet getDirectors() throws SQLException {
        Statement statement = connection.createStatement();
        return statement
                .executeQuery("select * from movies.directors");
    }
    public static ResultSet getGenres() throws SQLException {
        Statement statement = connection.createStatement();
        return statement
                .executeQuery("select * from movies.genres");
    }
    public static ResultSet getMovies() throws SQLException {
        Statement statement = connection.createStatement();
        return statement
                .executeQuery("select * from movies.movies");
    }
    static ResultSet getGenresFromId(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String s="select * from movies.genres where movies.genres.id='"+id+"';";
        return statement
                .executeQuery(s);
    }
    public static ResultSet getOneMovie(int id) throws SQLException{
        Statement statement = connection.createStatement();
        String s=new String("select * from movies.movies where movies.movies.id="+id+";");
        System.out.println(s);
        return statement
                .executeQuery(s);
    }
    public static ResultSet getOneGenre(int id) throws SQLException{
        Statement statement = connection.createStatement();
        String s=new String("select * from movies.genres where movies.genres.id="+id+";");
        return statement
                .executeQuery(s);
    }
    public static ResultSet getOneActor(int id) throws SQLException{
        Statement statement = connection.createStatement();
        String s=new String("select * from movies.stars where movies.stars.id="+id+";");
        return statement
                .executeQuery(s);
    }
    public static ResultSet getOneDirector(int id) throws SQLException{
        Statement statement = connection.createStatement();
        String s=new String("select * from movies.directors where movies.directors.id="+id+";");
        return statement
                .executeQuery(s);
    }
    public static ResultSet getStarFromMovie(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String s = new String("select * from movies.stars as s, movies.movies_stars as ms where ms.movies_id=" + id + " and ms.stars_id=s.id;");
        return statement
                .executeQuery(s);
    }
    public static ResultSet getMovieFromGenre(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String s = new String("select * from movies.movies as m, movies.movies_genres as mg where mg.genres_id=" + id + " and mg.movies_id=m.id;");
        return statement
                .executeQuery(s);
    }
    static ResultSet getMovieFromGenreOrderedRating(int id) throws SQLException{
        Statement statement = connection.createStatement();
        String s = new String("select * from movies.movies as m, movies.movies_genres as mg where mg.genres_id=" + id + " and mg.movies_id=m.id order by m.imdb_rating desc;");
        return statement
                .executeQuery(s);
    }
    static ResultSet getMovieFromGenreOrderedNume(int id) throws SQLException{
        Statement statement = connection.createStatement();
        String s = new String("select * from movies.movies as m, movies.movies_genres as mg where mg.genres_id=" + id + " and mg.movies_id=m.id order by m.title;");
        return statement
                .executeQuery(s);
    }
    public static ResultSet getMovieFromStar(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String s = new String("select * from movies.movies as m, movies.movies_stars as mg where mg.stars_id=" + id + " and mg.movies_id=m.id;");
        return statement
                .executeQuery(s);
    }
    public static ResultSet getMovieFromDirector(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String s = new String("select * from movies.movies as m, movies.movies_directors as mg where mg.directors_id=" + id + " and mg.movies_id=m.id;");
        return statement
                .executeQuery(s);
    }
    public static ResultSet getDirectorFromMovie(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String s = new String("select * from movies.directors as d, movies.movies_directors as md where md.movies_id=" + id + " and md.directors_id=d.id;");
        return statement
                .executeQuery(s);
    }
    public static ResultSet getGenreFromMovie(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String s = new String("select * from movies.genres as d, movies.movies_genres as md where md.movies_id=" + id + " and md.genres_id=d.id;");
        return statement
                .executeQuery(s);
    }
    public static void addStarWithPhoto(String name1,String about1,String image_URL1,int year1) throws SQLException{
        Statement statement = connection.createStatement();
        String string = new String("Insert into movies.stars(name,about,image_URL,year) VALUES('"+name1+"','"+about1+"','"+
                image_URL1+"','"+year1+"');");
        statement.executeUpdate(string);
    }
    public static void addDirectorWithPhoto(String name1,String about1,String image_URL1,int year1) throws SQLException{
        Statement statement = connection.createStatement();
        String string= new String("Insert into movies.directors(name,about,image_URL,year) VALUES('"+name1+"','"+about1+"','"+
            image_URL1+"','"+year1+"');");
        statement.executeUpdate(string);
}
    public static void addGenreWithPhoto(String name1,String image_URL1) throws SQLException{
        Statement statement = connection.createStatement();
        String string= new String("Insert into movies.genres(name,image_URL) VALUES('"+name1+"','"+
                image_URL1+"');");
        statement.executeUpdate(string);
    }
    public static void addMovie(String title1,int year1,String image_URL1,String certificate1,
                                int runtime1,double imdb_rating1,String description1,
                                int metascore1,int votes1,int gross1,String moviescore1) throws SQLException{
        Statement statement = connection.createStatement();
        String string= new String("Insert into movies.movies(title,year,image_URL,certificate,runtime," +
                       "imdb_rating,description,metascore,votes,gross,moviescol) VALUES('"+title1+"','"+
                       year1+"','"+image_URL1+"','"+certificate1+"','"+runtime1+"','"+imdb_rating1+
                       "','"+description1+"','"+metascore1+"','"+votes1+"','"+gross1+"','"+moviescore1+"');");
        statement.executeUpdate(string);
    }
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception ignored) {

        }
    }

}