package imag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String regex="([0-9]+[.][0-9]+)";
		String input= "34.5,";

		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(input);
		//while(matcher.find()) {
		System.out.println("dfdfdf    kndkcd".indexOf(" "));
		//}
	}

}
