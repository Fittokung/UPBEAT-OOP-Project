package com.example.UpbeatWebSocket.GameState;

import com.example.UpbeatWebSocket.Expr.SyntaxErrorExpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Territory {
    private int territory_row ;
    private  int territory_col;
//    private int init_plan_min = 0 ;
//    private int init_plan_sec = 0 ;
    private double init_budget ;
    private double init_center_dep ;
//    private double plan_rev_min = 0 ;
//    private  double plan_rev_sec = 0 ;
    private  double rev_cost ;

    private final double fee = 1 ;

    protected double max_dep;
    protected double interest_pct;
    protected int turn = 1;
    private  int round = 0;

    private List<Player> Player;
    protected Region[][] territory; // need to cahnge to private

    String[] nameOfPlayer ;
    public Territory(){}

    public Territory(int m, int n, long init_budget, long init_center_dep, long rev_cost, long interest_pct, long max_dep, String[] nameOfPlayer){
        this.territory_row = m;
        this.territory_col = n;
        this.init_budget = init_budget;
        this.init_center_dep = init_center_dep;
        this.nameOfPlayer = nameOfPlayer;
        this.Player = new ArrayList<>();
        this.rev_cost = rev_cost;
        this.max_dep = max_dep;
        this.interest_pct = interest_pct;
        this.territory = new Region[m][n];


        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                territory[i][j] = new Region(i, j);
            }
        }
        setAdjacentRegion();
        setUpPlayer();
    }
    private void setAdjacentRegion(){
        for (int i = 0; i < territory_row; i++){
            for (int j = 0; j < territory_col; j++){
                Region pointer = territory[i][j];
                if (i > 0) pointer.up = territory[i - 1][j];
                if (i < territory_row - 1) pointer.down = territory[i + 1][j];
                if (j%2 == 0) {
                    if(j > 0) {pointer.upleft = territory[i][j-1];}
                    if(j > 0 && i < territory_row-1){pointer.downleft = territory[i+1][j-1];}
                    if(j < territory_col-1 ) {pointer.upright = territory[i][j+1];}
                    if(j < territory_col-1 && i < territory_row-1 ) {pointer.downright = territory[i+1][j+1];}
                }else{
                    if(i > 0) {pointer.upleft = territory[i-1][j-1];}
                    if(j <= territory_col-1){pointer.downleft = territory[i][j-1];}
                    if(i > 0 && j<territory_col-1) {pointer.upright = territory[i-1][j+1];}
                    if(j<territory_col-1) {pointer.downright = territory[i][j+1];}
                }
            }

        }
    }
    private void setUpPlayer(){
        for (String s : nameOfPlayer) {
            Region RandomRegion;
            int[] location = randomSpawnMap();
            int row = location[0];
            int col = location[1];
            RandomRegion = territory[row][col];
            Player p = new Player(s, this, RandomRegion);
            RandomRegion.addDeposit(init_center_dep);
            RandomRegion.setOwner(p);
            p.addBudget(init_budget);
            this.Player.add(p);
        }
    }
    private int[] randomSpawnMap(){
        int [] location = new int[2];
        int row = new Random().nextInt(territory_row);
        int col = new Random().nextInt(territory_col);
        while(territory[row][col].getOwner() != null){
            row = new Random().nextInt(territory_row);
            col = new Random().nextInt(territory_col);
        }
        location[0] = row ;
        location[1] = col ;
        return location ;

    }
    public void calculateInterest(Player user){
        user.CalculateInterestRate();
    }
    public void updateTurn() throws SyntaxErrorExpr {
        Player.get(round).evaluatePlan();
        calculateInterest(Player.get(round));
        round = (round + 1) % Player.size();
        if (round == 0) turn++;
    }

    public int getTerritory_col() {
        return territory_col;
    }

    public int getTerritory_row() {
        return territory_row;
    }

    public double getFee() {
        return fee;
    }
    public double getMax_dep() {
        return max_dep;
    }

    public double getRev_cost() {
        return rev_cost;
    }

    public List<Player> getPlayer() {
        return Player;
    }

    //ForTest
    public Region getRegion(int row, int col){
        return territory[row][col];
    }
}
