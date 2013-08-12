/**
 * ４桁の数字を当てるゲームです。
 */
package moscowmule2240.java002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ４桁の数字を当てるゲームです。
 * 
 * @author moscowmule2240
 */
public class HitAndBlow {

	/**
	 * 正解の桁数
	 */
	private static final int NUMBER = 4;

	/**
	 * ゲームを開始します。
	 * 
	 * @param answer
	 *            正解の桁数
	 * @throws IOException
	 *             標準入力からの読み込みに失敗した場合
	 */
	private static void startGame(int answer) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// 正解を取得
		List<Integer> numbers = HitAndBlow.getNumbers(HitAndBlow.NUMBER);

		// 挑戦回数
		int count = 1;

		while (true) {
			try {
				System.out.println("4桁の数値を入力してください。(" + count + "回目)");

				// 入力チェック
				List<Integer> input = HitAndBlow.checkInput(reader.readLine());

				// 回答
				List<Integer> result = HitAndBlow.checkNumbers(numbers, input);

				System.out.println("ヒット：" + result.get(0) + ", ブロウ：" + result.get(1));
				if (result.get(0) == HitAndBlow.NUMBER) {
					break;
				}
				count++;

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println(count + "回目でクリア！");
		System.out.println("おめでとうございます！");
	}

	/**
	 * 正解を作成します。
	 * 
	 * @param length
	 *            正解の桁数
	 * @return 正解
	 */
	private static List<Integer> getNumbers(int length) {
		List<Integer> list = new ArrayList<>();
		while (list.size() < length) {
			int number = (int) (Math.random() * 10);
			if (!list.contains(number)) {
				list.add(number);
			}
		}
		return list;
	}

	/**
	 * 入力内容をチェックします。
	 * 
	 * @param line
	 *            入力内容
	 * @return 入力した数値のリスト
	 */
	private static List<Integer> checkInput(String line) {
		if (line.length() != 4) {
			throw new IllegalArgumentException("入力が4桁ではありません。");
		}

		int inputNumber;
		try {
			inputNumber = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("入力は数値のみです。", e);
		}

		if (inputNumber < 0) {
			throw new IllegalArgumentException("入力は正の整数のみです。");
		}

		List<Integer> checkNumbers = new ArrayList<>();
		for (int i = 0; i < line.length(); i++) {
			Integer integer = Integer.parseInt(String.valueOf(line.charAt(i)));
			if (checkNumbers.contains(integer)) {
				throw new IllegalArgumentException("入力に重複があります。");
			}
			checkNumbers.add(integer);
		}

		return checkNumbers;
	}

	/**
	 * 答えあわせを行います。
	 * 
	 * @param numbers
	 *            正解の数値
	 * @param input
	 *            入力した数値
	 * @return １番目：ヒットした数<br>
	 *         ２番目：ブロウした数
	 */
	private static List<Integer> checkNumbers(List<Integer> numbers, List<Integer> input) {
		List<Integer> result = new ArrayList<>();
		result.add(0);
		result.add(0);

		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				if (input.get(i) == numbers.get(j)) {
					int index;
					if (i == j) {
						index = 0;
					} else {
						index = 1;
					}
					result.set(index, result.get(index) + 1);
					break;
				}
			}
		}

		return result;
	}

	/**
	 * エントリーポイント。
	 * 
	 * @param args
	 *            なし
	 */
	public static void main(String[] args) {
		try {
			HitAndBlow.startGame(HitAndBlow.NUMBER);
		} catch (Exception e) {
			System.out.println("続行不可能な例外が発生しました。ゲームを終了します。");
			e.printStackTrace();
		}
	}
}
