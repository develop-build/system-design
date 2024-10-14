package org.com.deepak.snakeandladder.service;
import java.util.HashMap;
import org.com.deepak.snakeandladder.model.Dice;
import org.com.deepak.snakeandladder.model.Entities;
import org.com.deepak.snakeandladder.model.PairPosition;

public class PlaySnakeAndLadder {
    HashMap<String, PairPosition> playerHistory;
    HashMap<String, Integer> playerLatestPosition;
    Entities entities;
    Dice dice;

    public PlaySnakeAndLadder(int N) {
        playerHistory = new HashMap<>();
        playerLatestPosition = new HashMap<>();
        entities = Entities.getInstance();
        dice = new Dice(N);
    }

    public String playGame() {
        initializePlayersStartValue();
        int i = -1;
        do {
            i++;
            if (i >= entities.getPlayers().size()) {
                i = 0;
            }
            StringBuilder str = new StringBuilder();
            String playerName = entities.getPlayers().get(i);
            str.append(playerName);
            int diceNumber = dice.getNumberOnDice();
            int endPosition = playerLatestPosition.get(playerName) + diceNumber;
            String sl="";
            if(checkForDiceNumberGreaterThanHundred(endPosition)){
                str.append(" rolled a ").append(diceNumber);
                str.append(" and moved from ").append(playerLatestPosition.get(playerName));
                if(entities.getSnakes().get(endPosition)!=null){
                    sl = " after Snake dinner";
                    playerLatestPosition.put(playerName, entities.getLadders().get(endPosition));
                }
                else {
                    if(entities.getLadders().get(endPosition)!=null){
                        sl = " after Ladder climb";
                        playerLatestPosition.put(playerName, entities.getLadders().get(endPosition));
                    }
                    playerLatestPosition.put(playerName, endPosition);
                }
                str.append(" to ").append(playerLatestPosition.get(playerName));
                str.append(sl);
            }
            System.out.println(str);
        } while (!isPlayerWon(entities.getPlayers().get(i)));
        
        return entities.getPlayers().get(i);
    }

    private boolean isPlayerWon(String player) {
        return playerLatestPosition.get(player) == 100;
    }

    private boolean checkForDiceNumberGreaterThanHundred(int endPosition) {
        return endPosition <= 100;
    }

    private void initializePlayersStartValue() {
        for (int i = 0; i < entities.getPlayers().size(); i++) {
            playerLatestPosition.put(entities.getPlayers().get(i), 0);
        }
    }
}
