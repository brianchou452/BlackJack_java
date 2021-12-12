import java.util.Scanner;

public class Utils {
    /**
     * 統一管理scanner,
     * 同時多個scanner讀取 會發生java.util.NoSuchElementException: No line found
     */
    final static Scanner sc = new Scanner(System.in);
    
}
