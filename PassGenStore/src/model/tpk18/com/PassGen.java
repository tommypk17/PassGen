package model.tpk18.com;
import java.util.Random;
public class PassGen {
	public static String generatePassword(){
		String password = "";
		Random rand = new Random();

		for(int i = 0; i < 8; i++){
			int randCharInt = rand.nextInt(90);
			while(randCharInt < 65){
				randCharInt = rand.nextInt(90);
			}
			int randInt = rand.nextInt(10);
			if(i < 3){
				password+=(char)randCharInt;
			}else{
				password+=randInt;
			}
		}
		return password;
	}
}
