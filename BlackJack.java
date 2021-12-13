public class BlackJack {
    private Player[] players;
    private CardSet cardOnTable = new CardSet(4);

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
        bookmakerAddCard();
        askPlayerToAddCard();
        compareRank();
        printGameResult();

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
            if (player.isBookmaker()) {
                continue;
            }
            if (!player.isStopGetCard() && player.getStatus() == PlayerStatus.PLAYING) {
                System.out.println("玩家" + player.getPlayerNo());
                player.getSet().print();
                for (int i = 0; i < 5; i++) {
                    System.out.println("是否要加牌(y/n)");
                    String userAns = Utils.sc.nextLine();
                    if (userAns.equals("y")) {
                        player.getCard(cardOnTable.dealACard());
                        player.getSet().print();
                        if (player.getSet().calculateRank() > 21) {
                            System.out.println("玩家" + player.getPlayerNo() + " 爆排輸了");
                            player.setStatus(PlayerStatus.LOSE);
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
                System.out.println("玩家" + player.getPlayerNo() + " 過五關未爆牌贏了");
                player.setStatus(PlayerStatus.WIN);
            }

        }
    }

    private void bookmakerAddCard() {
        // TODO 莊家拿牌
        System.out.println("莊家");
        players[0].getSet().printWithHiding1stCard();;
        while (players[0].getSet().calculateRank() < 17) {
            players[0].getCard(cardOnTable.dealACard());
            players[0].getSet().printWithHiding1stCard();
            if (players[0].getSet().calculateRank() > 21) {
                System.out.println("莊家輸了");
                players[0].setStatus(PlayerStatus.LOSE);
            }
        }
    }

    private void compareRank() {
        int bookmakerRank = players[0].getSet().calculateRank();
        for (Player player : players) {
            if (player.isBookmaker() || !(player.getStatus() == PlayerStatus.PLAYING)) {
                continue;
            }
            int currentPlayerRank = player.getSet().calculateRank();
            if (currentPlayerRank < bookmakerRank) {
                player.setStatus(PlayerStatus.LOSE);
            } else if (currentPlayerRank == bookmakerRank) {
                player.setStatus(PlayerStatus.TIE);
            } else {
                player.setStatus(PlayerStatus.WIN);
            }
        }
    }

    private void printGameResult() {
        System.out.println("莊家的牌為:");
        players[0].getSet().print();
        for (Player player : players) {
            if (player.getStatus() == PlayerStatus.WIN) {
                System.out.println("玩家" + player.getPlayerNo() + "贏了");
            } else if (player.getStatus() == PlayerStatus.LOSE) {
                System.out.println("玩家" + player.getPlayerNo() + "輸了");
            } else if (player.getStatus() == PlayerStatus.TIE) {
                System.out.println("玩家" + player.getPlayerNo() + "與莊家平手");
            }
        }
    }

}
