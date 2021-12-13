import java.util.Scanner;

public class Utils {
    /**
     * 統一管理scanner,
     * 同時多個scanner讀取 會發生java.util.NoSuchElementException: No line found
     */
    final static Scanner sc = new Scanner(System.in);

    /**
     * 詢問yes no 問題，並判斷使用者回答是否正確，如錯誤則重新輸入
     * 
     * @param question 要問得問題
     * @return 如使用者答 y 則回傳 true ， 答 n 則回傳 false
     */
    public static boolean askYesNoQuestion(String question) {
        boolean ansCorrectly = false;
        while (!ansCorrectly) {
            System.out.print(question + " ");

            String ans = Utils.sc.nextLine();
            if (ans.equals("y")) {
                return true;
            } else if (ans.equals("n")) {
                return false;
            } else {
                System.out.println("回答錯誤!請重新回答!");
            }
        }
        return false;
    }
}
