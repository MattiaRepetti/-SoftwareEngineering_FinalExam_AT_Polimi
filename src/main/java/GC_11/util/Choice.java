package GC_11.util;

import GC_11.model.Player;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Choice implements Serializable{
    public Choice(Player player, String input) throws IllegalArgumentException {
        this.player = player;

        List<String> tmp = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(input);
        while (st.hasMoreTokens()) {
            tmp.add(st.nextToken());
        }

        this.choice = Choice.Type.valueOf(tmp.get(0));

        for(String p : tmp){
            if(tmp.indexOf(p) != 0){
                params.add(p);
            }
        }

        //Controls
        switch(this.choice){
            case FIND_MATCH:
            case SEE_PERSONALGOAL:
                if(params.size() != 0) throw new IllegalArgumentException();
                break;
            case SEE_COMMONGOAL:
                if(params.size() != 1) throw new IllegalArgumentException();
                Integer common_checker;
                try{
                   common_checker = Integer.parseInt(params.get(0));
                } catch(NumberFormatException e){
                    throw new InvalidParameterException();
                }
                if(common_checker != 0 && common_checker != 1) throw new InvalidParameterException();
                break;
            case PICK_COLUMN:
                if(params.size() != 1) throw new IllegalArgumentException();
                Integer column_checker;
                try{
                    column_checker = Integer.parseInt(params.get(0));
                } catch(NumberFormatException e){
                    throw new InvalidParameterException();
                }
                if(column_checker < 0 || column_checker >= 5) throw new InvalidParameterException();
                break;
            case INSERT_NAME:
                if(params.size() != 1) throw new IllegalArgumentException();
                //Chosen a Max for name length
                if(params.get(0).length() >= 64) throw new InvalidParameterException();
                break;
            case SELECT_TILE:
                if(params.size() != 2) throw new IllegalArgumentException();
                Integer row_checker, col_checker;
                try{
                    row_checker = Integer.parseInt(params.get(0));
                    col_checker = Integer.parseInt(params.get(1));
                } catch(NumberFormatException e){
                    throw new InvalidParameterException();
                }
                if(row_checker < 0 || row_checker >= 9 || col_checker < 0 || col_checker >= 9 ) throw new InvalidParameterException();
                break;
            case LOGIN:
                if(params.size() != 2) throw new IllegalArgumentException();
                if(params.get(0).length() >= 64) throw new InvalidParameterException();
                //Chosen password minimum length
                if(params.get(1).length() < 8 || params.get(1).length() >= 64) throw new InvalidParameterException();
                break;
            case CHOOSE_ORDER:
                if(params.size() > 3) throw new IllegalArgumentException();
                Integer ord1_checker, ord2_checker, ord3_checker;
                try{
                    ord1_checker = Integer.parseInt(params.get(0));
                    ord2_checker = Integer.parseInt(params.get(1));
                    ord3_checker = Integer.parseInt(params.get(2));
                } catch(NumberFormatException e){
                    throw new InvalidParameterException();
                }
                if(ord1_checker < 1 || ord1_checker > 3) throw new InvalidParameterException();
                if(ord2_checker < 1 || ord2_checker > 3) throw new InvalidParameterException();
                if(ord3_checker < 1 || ord3_checker > 3) throw new InvalidParameterException();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public enum Type{
        INSERT_NAME, LOGIN, FIND_MATCH, SEE_COMMONGOAL, SEE_PERSONALGOAL, SELECT_TILE, PICK_COLUMN, CHOOSE_ORDER
    }

    private List<String> params;
    private Type choice;

    private Player player;

    public Type getChoice() {
        return choice;
    }

    public List<String> getParams() {
        return params;
    }

    public Player getPlayer() {
        return player;
    }
}
