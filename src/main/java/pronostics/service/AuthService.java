package pronostics.service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import pronostics.model.User;
import pronostics.repository.UserRepository;

@Service
public class AuthService {
	
	private StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
	public StrongPasswordEncryptor pwdEncryptor = new StrongPasswordEncryptor();
	
	@Inject
	public UserRepository userRepo;
	
	@PostConstruct
	public void load() {
		pwdEncryptor = new StrongPasswordEncryptor();
		textEncryptor = new StrongTextEncryptor();
		textEncryptor.setPassword(pwdEncryptor.encryptPassword("privateKey"));
	}
	
	public User checkAuth(String login, String password) {
		User user = userRepo.findByUserName(login);
		
		if(user != null) {
			if(pwdEncryptor.checkPassword(password, user.getPassword())) {
//				return createToken(login);
				return user;
			}
		}
		return null;
	}
	
	public String createToken(String login) {
		DateTime today = new DateTime();
		DateTime expiredTime = today.plusDays(5);
		String encryptedTime = textEncryptor.encrypt(login + ":" + expiredTime.toString());
		return encryptedTime;
	}
	
	public String encryptPwd(String password) {
		return pwdEncryptor.encryptPassword(password);
	}
	
	public boolean isValidToken(String login, String encryptedToken) {
		String decryptedToken = textEncryptor.decrypt(login + ":" + encryptedToken);
		String tokenLogin = decryptedToken.split(":")[0];
		String tokenDate = decryptedToken.split(":")[1];
		DateTime time = new DateTime(tokenDate);
		return login == tokenLogin && time.isAfterNow();
	}
}
