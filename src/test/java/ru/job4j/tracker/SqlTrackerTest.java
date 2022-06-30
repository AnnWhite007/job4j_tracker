package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * 3. Liquibase. Интеграционные тесты.
 * Библиотека позволяет автоматически поддерживать схему базы данных, в частности предварительно ее создавать.
 * В данном примере мы будем создавать схему данных для наших тестов в приложении.
 * При тестировании чаще всего используют базу данных, которая хранит данные в памяти (в оперативной или в файловой системе),
 * причем работа с ней из вне идет также как с обычной базой данных, в чем и состоит прелесть JDBC.
 * в тестах мы будем использовать другую базу данных:
 * - URL для подключения разбивается на две части. Первая часть jdbc:h2:~/testdb;. h2 указывает, что мы используем драйвер h2.
 * Далее идет путь к базе данных, которая хранит данные. База данных в этом случае хранится в файловой системе. ~ указывает,
 * что базу данных нужно создать в директории пользователя (например, на Windows это C:\Users\User, где User это ваша учетная запись в системе).
 * Далее во второй части идут настройки для работы с базой данных. MODE указывает на то, какую БД надо имитировать.
 * PostgreSQL поддерживает работу с идентификаторами, игнорируя регистр, например ITEMS, Items, items будет одинаково работать,
 * но H2 учитывает регистр, поэтому нужно указать последний аргумент для игнорирования регистра;
 * - пароль и логин пусты. При работе с H2 нам необязательно их указывать. Однако, если в программе присутствует какая-то логика для работы с паролем и логином,то их можно указать;
 * - наконец, идет драйвер, который обеспечивает взаимодействие с БД. Здесь мы указываем драйвер, который добавили с зависимостью Maven в проект - драйвер БД H2.
 * Два профиля - test и production. Один будет использоваться для тестов, другой при работе с пользователем.
 * Оба профиля имеют идентификатор и настройки для работы с БД: URL, имя пользователя, пароль и драйвер.
 * файл master.xml - в этом файле мы указываем скрипт, который нужно выполнить на этапе сборки приложения.
 * <p>
 * - на все тесты создаем одно подключение к БД, чтобы ускорить их, поэтому Connection делаем статическим;
 * - в методе initConnection() выполняется инициализация подключения.
 * Данный метод обозначен аннотацией @BeforeClass, т.е. метод выполняется один раз до начала тестов;
 * - в методе closeConnection() выполняется закрытие подключения. Данный метод обозначен аннотацией @AfterClass, т.е. метод выполняется один раз после тестов;
 * - в методе wipeTable() мы чистим таблицу items после внесенных изменений. Делается это специально, чтобы облегчить тестирование,
 * иначе изменения, внесенные один тестом, будут видны другому. Данный метод обозначен аннотацией @After, т.е. метод выполняется после каждого теста;
 * - метод whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() представляет собой уже тест.
 * Обратите внимание, что после вставки мы находим item по сгенерированному БД ключу.
 */

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenSaveItemAndFindByNameThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item2"));
        Item item3 = tracker.add(new Item("item2"));
        assertThat(tracker.findByName("item2"), is(List.of(item2, item3)));
    }

    @Test
    public void whenSaveItemAndFindAllThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item2"));
        Item item3 = tracker.add(new Item("item2"));
        assertThat(tracker.findAll(), is(List.of(item1, item2, item3)));
    }

    @Test
    public void whenSaveItemReplaceAndFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item2"));
        Item item3 = tracker.add(new Item("item3"));
        tracker.delete(item2.getId());
        assertThat(tracker.findAll(), is(List.of(item1, item3)));
    }

    @Test
    public void whenNoFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        tracker.delete(item1.getId());
        assertThat(tracker.findByName("item1"), is(List.of()));
    }

    @Test
    public void whenReplaceFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = new Item("item2");
        tracker.replace(item1.getId(), item2);
        assertThat(tracker.findById(item1.getId()).getName(), is("item2"));
    }

    @Test
    public void whenDeleteFindById() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("item1"));
        tracker.delete(item1.getId());
        assertThat(tracker.findById(item1.getId()), is(nullValue()));
    }
}