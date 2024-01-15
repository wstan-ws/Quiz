package personal.project.TriviaWeb.repo;

public class Queries {
    
    public static final String SQL_INSERT_EASY10 = """
            INSERT INTO easy10(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_EASY10 = """
            SELECT *
            FROM easy10;
            """;
    public static final String SQL_INSERT_MEDIUM10 = """
            INSERT INTO medium10(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_MEDIUM10 = """
            SELECT *
            FROM medium10;
            """;
    public static final String SQL_INSERT_HARD10 = """
            INSERT INTO hard10(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_HARD10 = """
            SELECT *
            FROM hard10;
            """;
    public static final String SQL_INSERT_EASY20 = """
            INSERT INTO easy20(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_EASY20 = """
            SELECT *
            FROM easy20;
            """;
    public static final String SQL_INSERT_MEDIUM20 = """
            INSERT INTO medium20(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_MEDIUM20= """
            SELECT *
            FROM medium20;
            """;
    public static final String SQL_INSERT_HARD20 = """
            INSERT INTO hard20(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_HARD20 = """
            SELECT *
            FROM hard20;
            """;
    public static final String SQL_INSERT_EASY30 = """
            INSERT INTO easy30(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_EASY30 = """
            SELECT *
            FROM easy30;
            """;
    public static final String SQL_INSERT_MEDIUM30 = """
            INSERT INTO medium30(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_MEDIUM30= """
            SELECT *
            FROM medium30;
            """;
    public static final String SQL_INSERT_HARD30 = """
            INSERT INTO hard30(name, score)
                VALUES(?, ?)
            """;
    public static final String SQL_SELECT_HARD30 = """
            SELECT *
            FROM hard30;
            """;
}
