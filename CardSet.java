import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardSet {
    private List<Card> set = new ArrayList<>();

    /**
     * 不給牌
     */
    public CardSet() {

    }

    /**
     * 初始化指定數目的牌
     * 
     * @param numberOfSet 幾副牌
     */
    public CardSet(int numberOfSet) {
        for (int i = 0; i < numberOfSet; i++) {
            newSet();
        }
    }

    /**
     * 按照順序將牌加入set
     */
    private void newSet() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                set.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(set);
    }

    public void add(Card card) {
        set.add(card);
    }

    /**
     * 計算整副牌的點數最大會是多少，有考慮ACE為1或11的情況
     * 
     * @return 最大的點數
     */
    public int calculateRank() {
        int ans = 0;
        int numberOfAceCard = 0;
        for (Card card : set) {
            if (card.getRank().equals(Rank.ACE)) {
                numberOfAceCard++;
                continue;
            }
            ans += card.getRankAsInt();
        }

        if (numberOfAceCard > 0) {
            if (ans + 11 + numberOfAceCard - 1 <= 21) {// 如果一張a為11其他為1
                ans = ans + 11 + numberOfAceCard - 1;
            } else {// 全部a皆為1
                ans += numberOfAceCard;
            }
        }
        return ans;
    }

    /**
     * 計算除了第一張牌其餘的牌點數是多少
     * 
     * @return 最大的點數
     */
    public int calculateRankWithout1stCard() {
        int ans = 0;
        for (int i = 1; i < set.size();i++) {
            ans += set.get(i).getRankAsInt();
        }
        return ans;
    }

    /**
     * 發一張牌
     * 
     * @return 回傳一張牌
     */
    public Card dealACard() {
        Card tmp = set.get(0);
        set.remove(0);
        return tmp;
    }

    /**
     * 以unmodifiableList取得所有的牌
     * 
     * @return
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(set);// TODO 是否用到？？
    }

    /**
     * 清除儲存的所有牌
     */
    public void clear() {
        set.clear();
    }

    /**
     * 印出所有牌和點數總和 
     */
    /*public void print() {// TODO override toString??
        for (Card card : set) {
            System.out.print(card.getSuit().getString() + card.getRank().getRankStr() + " ");
        }
        System.out.println("");
        System.out.println("總和為:" + calculateRank());
    }*/

    /**
     * 印出除了第一張牌以外的所有牌以及點數和(用於印出莊家手中的牌)
     */
    /*public void printWithHiding1stCard() {
        int i = 0;
        for (Card card : set) {
            if (i == 0) {
                System.out.print("*** ");
                i++;
                continue;
            }
            System.out.print(card.getSuit().getString() + card.getRank().getRankStr() + " ");
        }
        System.out.println("");
        System.out.println("總和為: n + " + (calculateRank() - set.get(0).getRankAsInt()));
    }*/

}
