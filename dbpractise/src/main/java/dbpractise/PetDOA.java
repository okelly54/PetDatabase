package dbpractise;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetDOA implements Closeable {

	private final Connection dbconnection;

	public PetDOA(String url, String user, String pass) throws SQLException {
		super();
		this.dbconnection = DriverManager.getConnection(url, user, pass);
	}

	public int create(String PetName, int PetAge, String PetColour, String PetBreed) throws SQLException {
		try (PreparedStatement statement = dbconnection
				.prepareStatement("INSERT INTO pets (PetName, PetAge, PetColour, PetBreed) VALUES (?, ?, ?, ?)");) {
			statement.setString(1, PetName);
			statement.setInt(2, PetAge);
			statement.setString(3, PetColour);
			statement.setString(4, PetBreed);

			return statement.executeUpdate();
		}
	}

	public List<Pet> read() throws SQLException {
		List<Pet> pets = new ArrayList<>();
		try (Connection dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pets", "root", "pass");
				Statement statement = dbconnection.createStatement();
				ResultSet results = statement.executeQuery("SELECT * FROM pets");) {
			while (results.next()) {
				int PetID = results.getInt(1);
				String PetName = results.getString("PetName");
				int PetAge = results.getInt("PetAge");
				String PetColour = results.getString("PetColour");
				String PetBreed = results.getString("PetBreed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pets;
	}

	@Override
	public void close() throws IOException {
		try {
			this.dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
