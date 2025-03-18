package com.example.UpbeatWebSocket.GameState;

public class Region extends Territory {
    private int row;
    private int col;
    private double deposit;
    public Region up = null, upright = null, upleft = null, down = null, downright = null, downleft = null;
    private Player owner = null;
    public Region(int x , int y){
        this.row = x ;
        this.col = y ;
    }
//    public boolean adjacentCheck(Region region){
//        if (region == up || region == upright || region == upleft || region == down || region == downright || region == downleft){
//            return true ;
//        }
//        return false;
//    }


    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public double getDeposit(){
        return deposit;
    }
    public void addDeposit(double n){
        deposit = Math.max(super.max_dep,deposit+n);
    }
    public void subDeposit(double n){
        deposit = Math.max(0,deposit-n);
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
    public double getInterestRate(){
        return super.interest_pct * Math.log10(deposit)*Math.log(super.turn);
    }
    public Region moveDirection(String direction){
        if (direction.equals("up"))return this.up;
        if (direction.equals("down")) return this.down;
        if (direction.equals("upleft"))return this.upleft;
        if (direction.equals("upright"))return this.upright;
        if (direction.equals("downleft"))return this.downleft;
        return this.downright;


    }
    public void ClearRegion(Player player){
        if(owner.getCityCenter().equals(this)){
            owner.LoseGame();
        } else {
            if(!player.regionContain(this)) return;
            owner.removeRegion(this);
            this.owner = null;
        }
    }
    public void InterestRate(){
        double b = super.interest_pct;
        double d = this.deposit ;
        double t = super.turn ;
        double r = b * Math.log10(d) * Math.log(t);
        if (owner != null) {
            this.addDeposit(d*r/100);
        }

    }

}
