package com.example.UpbeatWebSocket.Expr;

import com.example.UpbeatWebSocket.GameState.*;
import com.example.UpbeatWebSocket.GameState.Region;
import com.example.UpbeatWebSocket.GameState.Territory;
import com.example.UpbeatWebSocket.Parser.SyntaxErrorException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Double.*;
import static org.testng.Assert.*;
public class BinaryArithExprTest {
    DoubleLit zero = new DoubleLit(0);
    DoubleLit one = new DoubleLit(1);
    DoubleLit Neone = new DoubleLit(-1);
    DoubleLit LargeInt = new DoubleLit(1000);
    DoubleLit LargeInt2 = new DoubleLit(1001);
    DoubleLit LargeNeInt = new DoubleLit(-1000);
    DoubleLit LargeNeInt2 = new DoubleLit(-1001);
    public void Upmap(Map<String,  Double> table) {
        table.put("x",100.0);
    }



    public Player doplayer() {
        List<Player> player =new ArrayList<>();
        Territory map = new Territory(9 , 9 , 100 ,100 ,100 ,100 ,100 , new String[]{"Ton"});
        ConstructionPlan x = new ConstructionPlan(new ArrayList<>());
        Player ton = new Player("Ton" , map , new Region(1 , 1));
        ton.variable.put("x" , 100.0);
        return  ton ;
    }

    @Test
    public void Plush() throws SyntaxErrorExpr {
        Player user = doplayer();
        assertEquals(1, new BinaryArithExpr(zero, "+", one).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "+", zero).evaluate(user));
        assertEquals(-1, new BinaryArithExpr(zero, "+", Neone).evaluate(user));
        assertEquals(1000, new BinaryArithExpr(zero, "+", LargeInt).evaluate(user));
        assertEquals(-1000, new BinaryArithExpr(zero, "+", LargeNeInt).evaluate(user));
    }

    @Test
    public void SubT() throws SyntaxErrorExpr {
        Player user = doplayer();
        assertEquals(-1 * one.evaluate(user), new BinaryArithExpr(zero, "-", one).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "-", zero).evaluate(user));
        assertEquals(1, new BinaryArithExpr(zero, "-", Neone).evaluate(user));
        assertEquals(-1 * LargeInt.evaluate(user), new BinaryArithExpr(zero, "-", LargeInt).evaluate(user));
        assertEquals(-1 * LargeNeInt.evaluate(user), new BinaryArithExpr(zero, "-", LargeNeInt).evaluate(user));
    }

    @Test
    public void MutiP() throws SyntaxErrorExpr {
        Player user = doplayer();
        assertEquals(0, new BinaryArithExpr(zero, "*", one).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "*", zero).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "*", Neone).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "*", LargeInt).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "*", LargeNeInt).evaluate(user));
    }

    @Test
    public void Divi() throws SyntaxErrorExpr {
        Player user = doplayer();
        assertEquals(0, new BinaryArithExpr(zero, "/", one).evaluate(user));
        assertThrows(ArithmeticException.class, () -> {
            new BinaryArithExpr(zero, "/", zero).evaluate(user);
        });
        assertEquals(0, new BinaryArithExpr(zero, "/", Neone).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "/", LargeInt).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "/", LargeNeInt).evaluate(user));
    }


    @Test
    public void ModPer() throws SyntaxErrorExpr {
        Player user = doplayer();
        assertEquals(0, new BinaryArithExpr(zero, "%", one).evaluate(user));
        assertEquals(NaN, new BinaryArithExpr(zero, "%", zero).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "%", Neone).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "%", LargeInt).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "%", LargeNeInt).evaluate(user));
    }

    @Test
    public void Power() throws SyntaxErrorExpr {
        Player user = doplayer();
        assertEquals(0, new BinaryArithExpr(zero, "^", one).evaluate(user));
//        assertEquals(NaN,new BinaryArithExpr(zero,"^" , zero).evaluate(table));
        assertEquals(POSITIVE_INFINITY, new BinaryArithExpr(zero, "^", Neone).evaluate(user));
        assertEquals(0, new BinaryArithExpr(zero, "^", LargeInt).evaluate(user));
        assertEquals(POSITIVE_INFINITY, new BinaryArithExpr(zero, "^", LargeNeInt).evaluate(user));

        assertEquals(1, new BinaryArithExpr(one, "^", one).evaluate(user));
        assertEquals(1, new BinaryArithExpr(one, "^", zero).evaluate(user));
        assertEquals(1, new BinaryArithExpr(one, "^", Neone).evaluate(user));
        assertEquals(1, new BinaryArithExpr(one, "^", LargeInt).evaluate(user));
        assertEquals(1, new BinaryArithExpr(one, "^", LargeNeInt).evaluate(user));

        assertEquals(-1, new BinaryArithExpr(Neone, "^", one).evaluate(user));
        assertEquals(1, new BinaryArithExpr(Neone, "^", zero).evaluate(user));
        assertEquals(-1, new BinaryArithExpr(Neone, "^", Neone).evaluate(user));
        assertEquals(1, new BinaryArithExpr(Neone, "^", LargeInt).evaluate(user));
        assertEquals(1, new BinaryArithExpr(Neone, "^", LargeNeInt).evaluate(user));
        assertEquals(-1, new BinaryArithExpr(Neone, "^", LargeInt2).evaluate(user));
        assertEquals(-1, new BinaryArithExpr(Neone, "^", LargeNeInt2).evaluate(user));


        assertEquals(-1, new BinaryArithExpr(Neone, "^", one).evaluate(user));
        assertEquals(1, new BinaryArithExpr(Neone, "^", zero).evaluate(user));
        assertEquals(-1, new BinaryArithExpr(Neone, "^", Neone).evaluate(user));
        assertEquals(1, new BinaryArithExpr(Neone, "^", LargeInt).evaluate(user));
        assertEquals(1, new BinaryArithExpr(Neone, "^", LargeNeInt).evaluate(user));
        assertEquals(-1, new BinaryArithExpr(Neone, "^", LargeInt2).evaluate(user));
        assertEquals(-1, new BinaryArithExpr(Neone, "^", LargeNeInt2).evaluate(user));
    }

    @Test
    public void DivisionByZero() {
        Player user = doplayer();
        assertThrows(ArithmeticException.class, () -> {
            new BinaryArithExpr(one, "/", zero).evaluate(user);
            new BinaryArithExpr(Neone, "/", zero).evaluate(user);
        });
    }

    @Test
    public void CombinationOfOperations() throws SyntaxErrorExpr {
        Player user = doplayer();
        // Test combination of operations
        assertEquals(5.0, new BinaryArithExpr(new BinaryArithExpr(new DoubleLit(2), "+", new DoubleLit(3)), "*", new DoubleLit(1)).evaluate(user));
        // Expected result: 5.0
    }

    @Test
    public void testConstructionPlan() throws SyntaxErrorExpr, SyntaxErrorException {
        Player player = setUpGame();

        List<String> p = Command("t=1+5");
        player.setPlan(p);
        player.evaluatePlan();
        assertEquals(6,player.variable.get("t"));

        p = Command("t = t + 5");
        player.setPlan(p);
        player.evaluatePlan();
        assertEquals(11,player.variable.get("t"));

        p = Command("j = j + 1");
        player.setPlan(p);
        player.evaluatePlan();
        for(int i = 1 ; i < 10 ; i++){
            assertEquals(i,player.variable.get("j"));
            player.evaluatePlan();
        }

        double deposit = player.getCityCrew().getDeposit();
        p = Command("d = deposit / 4");
        player.setPlan(p);
        player.evaluatePlan();
        assertEquals(deposit / 4, player.variable.get("d"));

        p = Command("d2 = deposit - 100");
        player.setPlan(p);
        player.evaluatePlan();
        assertEquals(deposit - 100, player.variable.get("d2"));


        p = Command("b = budget - 100");
        player.setPlan(p);
        player.evaluatePlan();
        assertEquals(player.getBudget() - 100, player.variable.get("b") );

        p = Command("w = 10 q = w + 2 c = q + w");
        player.setPlan(p);
        player.evaluatePlan();
        assertEquals(22, player.variable.get("c"));

        p = Command("dir = random");
        player.setPlan(p);
        player.evaluatePlan();
        double dirValue = player.variable.get("dir");
        assertTrue(dirValue >= 0 && dirValue <= 999);
    }




    private List<String> Command(String command){
        List<String> file =  new ArrayList<>();
        file.add(command);
        return file;
    }

    public Player setUpGame()  {
        String[] p = {"Ton"};
        Territory map = new Territory(5, 5, 1000, 500, 100, 100, 100, p);
        List<Player> p1 = map.getPlayer();
        return p1.get(0);
    }
}
