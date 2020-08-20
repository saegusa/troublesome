package uno;

import org.junit.jupiter.api.Test;

/**
 * Java 14 spec test.
 * 
 * @see <a href="https://qiita.com/taumax/items/4208d5cdbb786b76a5ed">article</a>
 */
@SuppressWarnings("preview")
public class Java14Test_Preview {
	
	/**
	 * JEP 359：Records (Preview)
	 * Eclipse では NullPointerException が発生する
	 * Maven では　正常に動作する
	 */
	@Test
	public void JEP359 () {
		record Person(String name, int age) {}

		Person person = new Person("taumax", 13);
		System.out.println("name:" + person.name());
		System.out.println("age:" + person.age());

		System.out.println(person);
	}
}
