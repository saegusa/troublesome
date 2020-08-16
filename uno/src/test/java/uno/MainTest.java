package uno;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testMain() {
		Main.main(null);
	}
	
	
	/**
	 * Java 14 spec test.
	 * @see https://qiita.com/taumax/items/4208d5cdbb786b76a5ed
	 */
	@Test
	public void JEP35() {
		var a = """
				abc
				test
				アイウエオ
				かきくけこ
				""";
		System.out.println(a);
	}

}