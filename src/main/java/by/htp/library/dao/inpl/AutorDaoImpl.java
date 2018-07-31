package by.htp.library.dao.inpl;

import static by.htp.library.dao.util.MySqlPropertyManager.getLogin;
import static by.htp.library.dao.util.MySqlPropertyManager.getPass;
import static by.htp.library.dao.util.MySqlPropertyManager.getUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.library.book.Autor;
import by.htp.library.dao.AutorDao;

public class AutorDaoImpl implements AutorDao {

	private static final String SELECT_AUTOR_BY_ID = "SELECT * FROM authors WHERE id_author = ?";
	private static final String INSERT_AUTOR = "INSERT INTO authors(name, surname, birthdate) VALUES (?, ?, ?)";
	private static final String UPDATE_AUTOR = "UPDATE authors SET name = ?, surname= ?, birthdate = ? WHERE name = ? AND surname= ? AND birthdate = ?";
	private static final String DELETE_AUTOR = "DELETE FROM authors WHERE name = ? AND surname = ? AND birthdate = ?";
	private static final String SELECT_AUTORS = "SELECT * FROM authors";

	@Override
	public Autor readById(int id) {

		Autor autor = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_AUTOR_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				autor = buildAutor(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autor;
	}

	private Autor buildAutor(ResultSet rs) throws SQLException {
		Autor autor = new Autor();
		autor.setSurname(rs.getString("surname"));
		autor.setName(rs.getString("name"));
		autor.setBirthdate(rs.getDate("birthdate"));
		return autor;
	}

	@Override
	public List<Autor> getAutors() {
		
		List<Autor> autors = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(SELECT_AUTORS);
			
			while(result.next()) {
				Autor tempAutor = buildAutor(result);
				autors.add(tempAutor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autors;
	}

	@Override
	public void addAutor(Autor autor) {
		String name = autor.getName();
		String surname = autor.getSurname();

		SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
		String birthdate = formatForDate.format(autor.getBirthdate());
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(INSERT_AUTOR);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, birthdate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deliteAutor(Autor autor) {
		String name = autor.getName();
		String surname = autor.getSurname();

		SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
		String birthdate = formatForDate.format(autor.getBirthdate());
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(DELETE_AUTOR);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, birthdate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAutor(Autor autorCurrent, Autor autorNew) {
		String nameCurrent = autorCurrent.getName();
		String nameNew = autorNew.getName();

		String surnameCurrent = autorCurrent.getSurname();
		String surnameNew = autorNew.getSurname();

		SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
		Date dateBirthdateCurrent = autorCurrent.getBirthdate();
		String birthdateCurrent = formatForDate.format(dateBirthdateCurrent);
		Date dateBirthdateNew = autorCurrent.getBirthdate();
		String birthdateNew = formatForDate.format(dateBirthdateNew);

		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_AUTOR);

			ps.setString(1, nameNew);
			ps.setString(2, surnameNew);
			ps.setString(3, birthdateNew);
			ps.setString(4, nameCurrent);
			ps.setString(5, surnameCurrent);
			ps.setString(6, birthdateCurrent);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
