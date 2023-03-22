package dbpractise;

public class Runner {

	public static void main(String[] args) {
		try (PetDOA DAO = new PetDOA("jdbc:mysql://localhost:3306/pets", "root", "pass");) {
			System.out.println(DAO.create("Doggo", 8, "Brown", "Saint Bernard"));
			System.out.println(DAO.read());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
