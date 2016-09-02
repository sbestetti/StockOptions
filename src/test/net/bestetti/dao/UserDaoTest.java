package test.net.bestetti.dao;

import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.User;

public class UserDaoTest {

	public static void main(String[] args) {
		
		User user = new User();
		
		user.setFirstName("Teste");
		user.setLastName("Java");
		user.setEmail("jrosca@ali.com"); //Existing email
		user.setPassword("xpto");
		
		new UserDao().add(user);

	}

}
