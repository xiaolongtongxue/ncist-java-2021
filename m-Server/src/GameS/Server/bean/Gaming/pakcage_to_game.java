package GameS.Server.bean.Gaming;

import GameS.Server.bean.Net.Login;

public class pakcage_to_game {
    private final Gaming game;
    private final String str;
    private final Login[] login;
    public pakcage_to_game(Gaming game, String str, Login[] login){
        this.game=game;
        this.str=str;
        this.login=login;
    }
    public Gaming getGame(){
        return this.game;
    }
    public String getStr(){
        return this.str;
    }
    public Login[] getLogin(){
        return this.login;
    }
}
