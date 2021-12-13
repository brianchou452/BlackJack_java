import java.util.Scanner;

public class Utils {
    /**
     * 統一管理scanner,
     * 同時多個scanner讀取 會發生java.util.NoSuchElementException: No line found
     */
    final static Scanner sc = new Scanner(System.in);

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
