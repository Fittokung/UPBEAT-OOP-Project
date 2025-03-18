package com.example.UpbeatWebSocket.Parser;

import com.example.UpbeatWebSocket.Expr.SyntaxErrorExpr;
import com.example.UpbeatWebSocket.GameState.Player;
import com.example.UpbeatWebSocket.GameState.Region;
import com.example.UpbeatWebSocket.GameState.Territory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class ConstructionPlanParserTest {
    private List<String> Command(String command){
        List<String> file =  new ArrayList<>();
        file.add(command);
        return file;
    }
    public Player setUpGame()  {
        String[] p = {"Ton"};
        Territory map = new Territory(10, 10, 10000, 500, 100, 100, 100, p);
        List<Player> p1 = map.getPlayer();
        return p1.getFirst();
    }

    public List<Player> setUpGame2()  {
        String[] p = {"Ton","Tiger"};
        Territory map = new Territory(10, 6, 1000, 500, 100, 100, 100, p);
        List<Player> p1 = map.getPlayer();
        return p1;
    }


    Player Player = setUpGame();
    Territory territory = Player.territory();
    int row = territory.getTerritory_row();
    int lastRow = row-1;
    int col = territory.getTerritory_col();
    int lastCol = col-1;
    int longest = Math.max(row , col);
    double LongestPath = Math.pow(longest,2);;
    int MidRow = (row-1)/2;
    int MidCol = (col-1)/2;
    @Test
    public void TopLeftConnerTest() throws SyntaxErrorException, SyntaxErrorExpr {

        //Move Up
        Player.blink(territory , 0 , 0);
        List<String> p = Command("move up");
        Player.setPlan(p);
        for (int i = 0; i <= row; i++) {
            assertEquals(territory.getRegion(0,0),Player.getCityCrew());
            Player.evaluatePlan();

        }


        //Move Upright
        Player.blink(territory , 0 , 0);
        p = Command("move upright");
        Player.setPlan(p);
        assertEquals(territory.getRegion(0,0),Player.getCityCrew());
        Player.evaluatePlan();
        for (int i = 0; i < LongestPath ; i++) {
            assertEquals(territory.getRegion(0,1),Player.getCityCrew());
            Player.evaluatePlan();
        }

        //Move downright
        Player.blink(territory , 0 , 0);
        p = Command("move downright");
        Player.setPlan(p);
        int i = 0 ;
        for (int j = 0; j < LongestPath ; j++) {
            if (i < row && j < col){
                if (j%2 == 0 ){
                    assertEquals(territory.getRegion(i,j),Player.getCityCrew());
                    i++;
                }
                else {
                    assertEquals(territory.getRegion(i,j),Player.getCityCrew());
                }
            }else {
                assertNotEquals(null,Player.getCityCrew());
            }
            Player.evaluatePlan();
        }

        // Move Down
        Player.blink(territory , 0 , 0);
        i = 0 ;
        p = Command("move down");
        Player.setPlan(p);
        for (int j = 0; j < row ; j++) {
            assertEquals(territory.getRegion(i,0),Player.getCityCrew());
            Player.evaluatePlan();
            i++;
        }
        assertEquals(territory.getRegion(territory.getTerritory_row()-1,0),Player.getCityCrew());
        Player.evaluatePlan();

        // Move downleft
        Player.blink(territory , 0 , 0);
        p = Command("move downleft");
        Player.setPlan(p);
        for (int j = 0; j < LongestPath ; j++) {
            assertNotEquals(null,Player.getCityCrew());
            assertEquals(territory.getRegion(0,0),Player.getCityCrew());
            Player.evaluatePlan();
        }

        //Move upleft
        Player.blink(territory , 0 , 0);
        p = Command("move upleft");
        Player.setPlan(p);
        for (int j = 0; j < LongestPath ; j++) {
            assertNotEquals(null,Player.getCityCrew());
            assertEquals(territory.getRegion(0,0),Player.getCityCrew());
            Player.evaluatePlan();
        }
    }

    @Test
    public void TopRightConnerTest() throws SyntaxErrorException,  SyntaxErrorExpr {



        //Move Up
        Player.blink(territory , 0 , col-1);
        List<String> p = Command("move up");
        Player.setPlan(p);
        for (int i = 0; i < row; i++) {
            assertEquals(territory.getRegion(0,col-1),Player.getCityCrew());
            Player.evaluatePlan();

        }

        //Move Upright
        Player.blink(territory , 0 , col-1);
        p = Command("move upright");
        Player.setPlan(p);
        assertEquals(territory.getRegion(0,col-1),Player.getCityCrew());
        Player.evaluatePlan();
        for (int i = 0; i < LongestPath ; i++) {
            assertEquals(territory.getRegion(0,col-1),Player.getCityCrew());
            Player.evaluatePlan();
        }

        //Move downright
        Player.blink(territory , 0 , col-1);
        p = Command("move downright");
        Player.setPlan(p);
        for (int i = 0; i < LongestPath ; i++) {
            assertEquals(territory.getRegion(0,col-1),Player.getCityCrew());
            Player.evaluatePlan();
        }


        // Move Down
        Player.blink(territory , 0, col-1);
        int i = 0 ;
        p = Command("move down");
        Player.setPlan(p);
        for (int j = 0; j < row ; j++) {
            assertEquals(territory.getRegion(j,col-1),Player.getCityCrew());
            Player.evaluatePlan();
            i++;
        }
        Player.evaluatePlan();
        assertEquals(territory.getRegion(row-1,col-1),Player.getCityCrew());

        // Move downleft
        Player.blink(territory , 0 , col-1);
        p = Command("move downleft");
        Player.setPlan(p);
        i = 0 ;
        for (int j = col-1; j >= 0 ; j--) {
            if (i < row ){
                if (j%2 == 0 ){
                    assertEquals(territory.getRegion(i,j),Player.getCityCrew());
                    i++;
                }
                else {
                    assertEquals(territory.getRegion(i,j),Player.getCityCrew());
                }
            }else {
                assertNotEquals(null,Player.getCityCrew());
            }
            Player.evaluatePlan();
        }
        Player.evaluatePlan();
        if (col-1 % 2 == 0){
            assertEquals(territory.getRegion(lastRow/2,0),Player.getCityCrew());
        }else {
            assertEquals(territory.getRegion((lastCol-1)/2,0),Player.getCityCrew());
        }

        //Move upleft
        Player.blink(territory , 0 , col-1);
        p = Command("move upleft");
        Player.setPlan(p);
        assertEquals(territory.getRegion(0,col-1),Player.getCityCrew());
        for (int j =col-1 ; j > 0 ; j--) {
            Player.evaluatePlan();
            assertNotEquals(null,Player.getCityCrew());
            if ((col-1) %2 == 0){
                assertEquals(territory.getRegion(0,col-2),Player.getCityCrew());
            }else {
                assertEquals(territory.getRegion(0,col-1),Player.getCityCrew());
            }
        }
    }
    @Test
    public void BottomRightConnerTest() throws SyntaxErrorException, SyntaxErrorExpr {


        //Move Up
        Player.blink(territory , row-1 , col-1);
        List<String> p = Command("move up");
        Player.setPlan(p);
        for (int i = 0; i < row; i++) {
            assertEquals(territory.getRegion(row-1-i,col-1),Player.getCityCrew());
            Player.evaluatePlan();

        }
        //Move Upright
        Player.blink(territory , row-1 , col-1);
        p = Command("move upright");
        Player.setPlan(p);
        assertEquals(territory.getRegion(row-1,col-1),Player.getCityCrew());
        Player.evaluatePlan();
        for (int i = 0; i < LongestPath ; i++) {
            assertEquals(territory.getRegion(row-1,col-1),Player.getCityCrew());
            Player.evaluatePlan();
        }

        //Move downright
        Player.blink(territory , row-1 , col-1);
        p = Command("move downright");
        Player.setPlan(p);
        for (int i = 0; i < LongestPath ; i++) {
            assertEquals(territory.getRegion(row-1,col-1),Player.getCityCrew());
            Player.evaluatePlan();
        }

        // Move Down
        Player.blink(territory , row-1, col-1);
        int i = row-1 ;
        p = Command("move down");
        Player.setPlan(p);
        for (int j = 0; j < row ; j++) {
            assertEquals(territory.getRegion(row-1,col-1),Player.getCityCrew());
            Player.evaluatePlan();
            i++;
        }

        // Move downleft
        Player.blink(territory , row-1 , col-1);
        p = Command("move downleft");
        Player.setPlan(p);
        i = row-1 ;
        for (int j = col-1; j >= 0 ; j--) {
            if (i < row ){
                if (j%2 == 0 ){
                    assertEquals(territory.getRegion(i,j),Player.getCityCrew());
                    i++;
                }
                else {
                    assertEquals(territory.getRegion(i,j),Player.getCityCrew());
                }
            }else {
                assertNotEquals(null,Player.getCityCrew());
            }
            Player.evaluatePlan();
        }

        //Move upleft
        Player.blink(territory , row-1 , col-1);
        p = Command("move upleft");
        Player.setPlan(p);
        i = row-1 ;
        int temp = 0;
        assertEquals(territory.getRegion(row-1,col-1),Player.getCityCrew());
        for (int j =col-1 ; j > 0  ; j--) {
            assertNotEquals(null,Player.getCityCrew());
            assertEquals(territory.getRegion(i,col-1-temp),Player.getCityCrew());
            if (j % 2 != 0) {
                i--;
            }
            Player.evaluatePlan();
            temp++;

        }
    }
    @Test
    public void BottomLeftConnerTest() throws SyntaxErrorException,SyntaxErrorExpr {

        //Move Up
        Player.blink(territory , row-1 , 0);
        List<String> p = Command("move up");
        Player.setPlan(p);
        for (int i = 0; i < row; i++) {
            assertEquals(territory.getRegion(row-1-i,0),Player.getCityCrew());
            Player.evaluatePlan();

        }
        //Move Upright
        Player.blink(territory , row-1 , 0);
        p = Command("move upright");
        Player.setPlan(p);
        int temp = 0;
        assertEquals(territory.getRegion(row-1,0),Player.getCityCrew());
        int i=row-1;
        for (int j =0 ; j < col-1  ; j++) {
            assertNotEquals(null,Player.getCityCrew());
            assertEquals(territory.getRegion(i, temp),Player.getCityCrew());
            if (j % 2 != 0) {
                i--;
            }
            Player.evaluatePlan();
            temp++;

        }

        //Move downright
        Player.blink(territory , row-1 , 0);
        p = Command("move downright");
        Player.setPlan(p);
        assertEquals(territory.getRegion(row-1,0),Player.getCityCrew());
        for (int j =col-1 ; j > 0  ; j--) {
            assertNotEquals(null,Player.getCityCrew());
            assertEquals(territory.getRegion(row-1,0),Player.getCityCrew());
            Player.evaluatePlan();
        }

        // Move Down
        Player.blink(territory , row-1, 0);
        p = Command("move down");
        Player.setPlan(p);
        for (int j = 0; j < row ; j++){
            assertNotEquals(null,Player.getCityCrew());
            assertEquals(territory.getRegion(row-1,0),Player.getCityCrew());
            Player.evaluatePlan();
        }

        // Move downleft
        Player.blink(territory , row-1 , 0);
        p = Command("move downleft");
        Player.setPlan(p);
        for (int j = col-1; j >= 0 ; j--){
            assertNotEquals(null,Player.getCityCrew());
            assertEquals(territory.getRegion(row-1,0),Player.getCityCrew());
            Player.evaluatePlan();
        }

        //Move upleft
        Player.blink(territory , row-1 , 0);
        p = Command("move upleft");
        Player.setPlan(p);
        for (int j = 0; j < LongestPath ; j++) {
            assertNotEquals(null,Player.getCityCrew());
            assertEquals(territory.getRegion(row-1 , 0),Player.getCityCrew());
            Player.evaluatePlan();
        }
    }
    @Test
    public void MiddleMapTest() throws SyntaxErrorException,SyntaxErrorExpr {

        int i;
        int midRow = (row-1)/2;
        int midCol = (col-1)/2;


        //Move Up
        Player.blink(territory , midRow , midCol);
        List<String> p = Command("move up");
        Player.setPlan(p);
        for (int j = midRow; j >= 0; j--) {
            assertEquals(territory.getRegion(j, midCol),Player.getCityCrew());
            Player.evaluatePlan();

        }

        //Move Upright
        Player.blink(territory , midRow , midCol);
        p = Command("move upright");
        Player.setPlan(p);
        i  = midRow ;
        for (int j = midCol; j < LongestPath ; j++) {
            if (j < col){
                if (j%2 == 0 ) {
                    assertEquals(territory.getRegion(i, j), Player.getCityCrew());
                }else {
                    assertEquals(territory.getRegion(i, j), Player.getCityCrew());
                    i--;
                }
            }

            Player.evaluatePlan();

        }


        //Move downright
        Player.blink(territory , midRow , midCol);
        p = Command("move downright");
        Player.setPlan(p);
        i = midRow ;
        for (int j = midCol; j < LongestPath ; j++) {
            if (j < col){
                if (j%2 == 0 ) {
                    assertEquals(territory.getRegion(i, j), Player.getCityCrew());
                    i++;
                }else {
                    assertEquals(territory.getRegion(i, j), Player.getCityCrew());
                }
            }

            Player.evaluatePlan();

        }


        // Move Down
        Player.blink(territory , midRow , midCol);
        p = Command("move down");
        Player.setPlan(p);
        for (int j = midRow; j <= row; j++) {
            if (j < row){
                assertEquals(territory.getRegion(j, midCol),Player.getCityCrew());
            }else {
                assertEquals(territory.getRegion(row-1, midCol),Player.getCityCrew());
            }

            Player.evaluatePlan();
        }

        // Move downleft
        Player.blink(territory , midRow , midCol);
        p = Command("move downleft");
        Player.setPlan(p);
        i = midRow ;
        for (int j = midCol; j >= 0; j--) {
            if (j < col){
                if (j%2 == 0 ) {
                    assertEquals(territory.getRegion(i, j), Player.getCityCrew());
                    i++;

                }else {
                    assertEquals(territory.getRegion(i, j), Player.getCityCrew());
                }
            }
            Player.evaluatePlan();
        }

        //Move upleft
        Player.blink(territory , midRow , midCol);
        p = Command("move upleft");
        Player.setPlan(p);
        i = midRow ;
        for (int j = midCol; j >= 0; j--) {
            if (j < col){
                if (j%2 == 0 ) {
                    assertEquals(territory.getRegion(i, j), Player.getCityCrew());

                }else {
                    assertEquals(territory.getRegion(i, j), Player.getCityCrew());
                    i--;
                }
            }
            Player.evaluatePlan();
        }


    }
    @Test
    public void RegionTest() throws SyntaxErrorException, SyntaxErrorExpr {
        Player Player = setUpGame();
        Territory territory = Player.territory();
        Player.blink(territory,0,0);
        // invest Test
        List<String> p = Command("move down invest 100 ");
        Player.setPlan(p);
        Player.evaluatePlan();
        assertEquals(territory.getRegion(1,0).getOwner(),Player);
        Player.evaluatePlan();
        assertEquals(territory.getRegion(2,0).getOwner(),Player);

        //Attack test
        p = Command("shoot up 1000");
        Player.setPlan(p);
        Player.evaluatePlan();
        if (Player.getBudget() > 1000){
            assertNull(territory.getRegion(1, 0).getOwner());
        }else {
            assertNotNull(territory.getRegion(1, 0).getOwner());
        }

        //Relocate test
        p = Command("move down invest 100 relocate");
        Player.setPlan(p);
        Player.evaluatePlan();
        assertEquals(territory.getRegion(3,0).getOwner(),Player);
        assertEquals(territory.getRegion(3,0),Player.getCityCenter());

        // collect
        p = Command("collect (deposit / 4)");
        Player.setPlan(p);
        double money = Player.getBudget();
        Player.evaluatePlan();
        assertEquals(money+ 24 ,Player.getBudget());
    }
    @Test
    public void WhileTest() throws SyntaxErrorException,SyntaxErrorExpr {
        Player Player = setUpGame();
        int MapRow = Player.territory().getTerritory_row()-1;
        int MapCol = 0;
        Territory territory = Player.territory();
        Player.blink(territory,0,0);
        List<String> p = Command("while (budget) { move down invest 100 }");
        Player.setPlan(p);
        Player.evaluatePlan();
        assertEquals(Player.getBudget() , 0.0);
        assertEquals(territory.getRegion(MapRow , MapCol) , Player.getCityCrew());

    }
    @Test
    public void IfTest() throws SyntaxErrorException, SyntaxErrorExpr {
        Player Player = setUpGame();
        Territory territory = Player.territory();
        Player.blink(territory,0,0);
        double playerMoney = Player.getBudget();
        List<String> p = Command("if (budget) then {move down move down}  else  {move up move up }");
        Player.setPlan(p);
        Player.evaluatePlan();
        if(Player.getBudget() > 0){
            assertEquals(territory.getRegion(2,0),Player.getCityCrew());
        } else {
            assertEquals(territory.getRegion(0,0),Player.getCityCrew());
        }
    }

    @Test
    public void opponentTest() throws SyntaxErrorException, SyntaxErrorExpr{
        // Setup map to Test
        List<Player> Player = setUpGame2();
        Player p1 = Player.get(0);
        Player p2 = Player.get(1);
        Region oldSpotP1 = p1.getCityCrew();
        Region oldSpotP2 = p2.getCityCrew();
        Territory territory1 = p1.territory();
        p1.blink(territory1 ,MidRow,MidCol);
        p1.setCityCenter(territory1.getRegion(MidRow,MidCol));
        p1.getCityCenter().setOwner(p1);
        oldSpotP1.setOwner(null);
        Region upleftRegion = p1.getCityCrew().moveDirection("upleft");
        Region upRegion = p1.getCityCrew().moveDirection("up");
        Region uprightRegion = p1.getCityCrew().moveDirection("upright");
        Region downrightRegion = p1.getCityCrew().moveDirection("downright");
        Region downRegion = p1.getCityCrew().moveDirection("down");
        Region downleftRegion = p1.getCityCrew().moveDirection("downleft");

        List<String> p = Command("invest 100");
        List<String> command = Command("X = opponent");
        p2.setPlan(p);
        p2.blink(territory1, upleftRegion.getRow(), upleftRegion.getCol());
        p2.setCityCenter(territory1.getRegion(upleftRegion.getRow(), upleftRegion.getCol()));
        p2.getCityCenter().setOwner(p2);
        oldSpotP2.setOwner(null);

        p1.setPlan(command);
        p1.evaluatePlan();
        assertEquals(16.0,p1.variable.get("X"));

    }

    @Test
    public void infoTest() throws SyntaxErrorException, SyntaxErrorExpr{

        // Setup map to Test
        List<Player> Player = setUpGame2();
        Player p1 = Player.get(0);
        Player p2 = Player.get(1);
        Region oldSpotP1 = p1.getCityCrew();
        Region oldSpotP2 = p2.getCityCrew();
        Territory territory1 = p1.territory();
        p1.blink(territory1 ,0,0);
        p1.setCityCenter(territory1.getRegion(0,0));
        p1.getCityCenter().setOwner(p1);
        oldSpotP1.setOwner(null);

        p2.blink(territory1,row-1,0);
        p2.setCityCenter(territory1.getRegion(row-1,0));
        p2.getCityCenter().setOwner(p2);
        oldSpotP2.setOwner(null);

        List<String> p = Command("invest 1000");
        p2.setPlan(p);
        p2.evaluatePlan();
        p = Command("distance = nearby down");
        p1.setPlan(p);
        p1.evaluatePlan();
        double x = p2.getCityCrew().getRow();
        double y = p2.getCityCrew().getDeposit();
        if (p2.getBudget() >= 1000){
            assertEquals(p2,p2.territory().getRegion(row-1,0).getOwner());
            assertEquals(100*x+y,p1.variable.get("distance"));
        }
        p = Command("distance = nearby downright");
        p1.setPlan(p);
        p1.evaluatePlan();
        assertEquals(0.0,p1.variable.get("distance"));
    }

    @Test
    public void WhileWithExpTest() throws SyntaxErrorException, SyntaxErrorExpr{
        Player Player = setUpGame();
        int MapRow = Player.territory().getTerritory_row() - 1;
        int MapCol = 0;
        Territory territory = Player.territory();
        Player.blink(territory, 0, 0);
        List<String> p = Command("while (budget) { j = j + 1 invest 100 }");
        Player.setPlan(p);
        Player.evaluatePlan();
        assertEquals(0.0,Player.getBudget());
    }
    @Test
    public void Test() throws SyntaxErrorException, SyntaxErrorExpr{
        Player Player = setUpGame();
        int MapRow = Player.territory().getTerritory_row() - 1;
        int MapCol = 0;
        Territory territory = Player.territory();
        Player.blink(territory, 0, 0);
        List<String> p = Command("t = t + 1 m = 0 while (deposit) { if (deposit - 100) then collect (deposit / 4) else if (budget - 25) then invest 25 else {} if (budget - 100) then {} else done opponentLoc = opponent if (opponentLoc / 10 - 1) then if (opponentLoc % 10 - 5) then move downleft else if (opponentLoc % 10 - 4) then move down else if (opponentLoc % 10 - 3) then move downright else if (opponentLoc % 10 - 2) then move downright else if (opponentLoc % 10 - 1) then move upright else move up else if (opponentLoc) then if (opponentLoc % 10 - 5) then { cost = 10 ^ (nearby upleft % 100 + 1) if (budget - cost) then shoot upleft cost else {}} else if (opponentLoc % 10 - 4) then {cost = 10 ^ (nearby downleft % 100 + 1)if (budget - cost) then shoot downleft cost else {}} else if (opponentLoc % 10 - 3) then {cost = 10 ^ (nearby down % 100 + 1) if (budget - cost) then shoot down cost else {}}else if (opponentLoc % 10 - 2) then {cost = 10 ^ (nearby downright % 100 + 1)if (budget - cost) then shoot downright cost else {}} else if (opponentLoc % 10 - 1) then {cost = 10 ^ (nearby upright % 100 + 1)if (budget - cost) then shoot upright cost else {}}else {cost = 10 ^ (nearby up % 100 + 1)if (budget - cost) then shoot up cost else {}} else {dir = random % 6 if (dir - 4) then move upleft else if (dir - 3) then move downleft else if (dir - 2) then move down else if (dir - 1) then move downright else if (dir) then move upright else move up m = m + 1}} if (budget - 1) then invest 1 else {}\n");
        Player.setPlan(p);
        Player.evaluatePlan();
    }
    @Test
    public void TestReadConfig() throws SyntaxErrorException, SyntaxErrorExpr{
        Player Player = setUpGame();
        int MapRow = Player.territory().getTerritory_row() - 1;
        int MapCol = 0;
        Territory territory = Player.territory();
        Player.blink(territory, 0, 0);
        List<String> p = Command("m=20 n=15 init_plan_min=5 init_plan_sec=0 init_budget=10000 init_center_dep=100 plan_rev_min=30 plan_rev_sec=0 rev_cost=100 max_dep=1000000 interest_pct=5 ");
        TokenizerConfig x = new TokenizerConfig(p);
    }
}

