package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import vu.lt.entities.Player;
import vu.lt.persistence.PlayersDAO;

@Model
public class Players implements Serializable {

    @Inject
    private PlayersDAO playersDAO;

    private Player playerToCreate = new Player();

    private List<Player> allPlayers;
    @PostConstruct
    public void init(){
        loadPlayers();
    }

    public void loadPlayers() {
        this.allPlayers = playersDAO.loadAll();
    }

    public List<Player> getAllPlayers(){
        return allPlayers;
    }

    @Transactional
    public String createPlayer(){
        this.playersDAO.persist(playerToCreate);
        return "success";
    }

    public Player getPlayerToCreate() {
        return playerToCreate;
    }

    public void setPlayerToCreate(Player playerToCreate) {
        this.playerToCreate = playerToCreate;
    }
}
