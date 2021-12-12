public class BlackJack {
    private Player[] players;
    private CardSet cardOnTable = new CardSet(1);

    public BlackJack() {
        players = new Player[2];
        players[0] = new Player(0);
        players[1] = new Player(1);
        players[0].setBookmaker(true);
    }

    public BlackJack(int numberOfPlayers) {
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(i);
        }
        players[0].setBookmaker(true);
    }

    public void start() {
        cardOnTable.shuffle();
        dealCard();
        dealCard();
        
        askPlayerToAddCard();
        
        
    }

    /**
     * 發一張牌給所有player
     */
    private void dealCard() {
        for (Player player : players) {
            player.getCard(cardOnTable.dealACard());
        }
    }

    private void askPlayerToAddCard() {
        for (Player player : players) {
            if (!player.isStopGetCard() && !player.isLose()) {
                System.out.println("玩家" + player.getPlayerNo());
                player.getSet().print();
                for (int i = 0; i < 5; i++) {
                    System.out.println("是否要加牌(y/n)");
                    String userAns = Utils.sc.nextLine();
                    if (userAns.equals("y")) {
                        player.getCard(cardOnTable.dealACard());
                        player.getSet().print();
                        if (player.getSet().calculateRank() > 21) {
                            System.out.println("玩家" + player.getPlayerNo() + " 輸了");
                            player.setLose(true);
                            break;
                        }
                    } else if (userAns.equals("n")) {
                        player.setStopGetCard(true);
                        break;
                    } else {
                        System.out.println("輸入錯誤!");
                        i--;
                    }
                }
                
            }
            
        }
    }
}
