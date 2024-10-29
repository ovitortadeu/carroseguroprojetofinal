package br.com.carroseguro.dao;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection conexao;
    private String url;
    private String user;
    private String pass;
    private String driver;

    public ConnectionFactory(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }
    public static ConnectionFactory getInstance() {
        ConnectionFactory result = instance;
        if (result != null) {
            return result;
        }
        Properties prop = new Properties();
        FileInputStream file = null;
        try {
            file = new FileInputStream("./src/main/resources/application.properties");
            prop.load(file);
            String url = prop.getProperty("datasource.url");
            String user = prop.getProperty("datasource.username");
            String pass = prop.getProperty("datasource.password");
            String driver = prop.getProperty("datasource.driver-class-name");
            file.close();
            if (instance == null) {
                instance = new ConnectionFactory(url, user, pass, driver);
            }
            return instance;
        } catch (FileNotFoundException e) {
            System.out.println("Erro (FileNotFoundException): " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro (IOException): " + e.getMessage());
        }
        return null;
    }
    public Connection getConexao() {
        try {
            if (this.conexao != null && !this.conexao.isClosed()) {
                return this.conexao;
            }
            if (this.getDriver() == null || this.getDriver().isEmpty()) {
                throw new ClassNotFoundException("Nome da classe nulo ou em branco");
            }
            if (this.getUrl() == null || this.getUrl().isEmpty()){
                throw new SQLException("URL de conexão nulo ou em branco");
            }
            if (this.getUser() == null || this.getUser().isEmpty()) {
                throw new SQLException("Usuário nulo ou em branco");
            }
            Class.forName(this.getDriver());
            this.conexao = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPass());
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro nome da class: " + e.getMessage());
            System.exit(1);
        }
        return conexao;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }
}