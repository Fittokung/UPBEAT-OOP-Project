import React from "react";
import styles from "./Howto.module.css";
import Link from "next/link";

const Howto = () => {
  const message = `Welcome to the Game Guide!
In this game, players start with only the city center at their disposal. Before the game begins, each player must devise an initial construction plan. Once ready, the first player takes their turn to start the game.
During each turn, players have the opportunity to accrue interest on their regions and revise their construction plans. They can perform various tasks according to their plan until completed, at which point the next player's turn begins.
Commands Available:
1. Opponent Expression: This command reveals the location of the closest region belonging to an opponent in one of six directions from the current region occupied by the city crew.
2. Nearby Function: Searches for the opponent's region closest to the city crew's current location in a given direction.
3. Relocate Command: Relocates the city center to the current region.
4. Move Command: Moves the city crew one unit in the specified direction.
5. Invest Command: Adds more deposits to the current region occupied by the city crew.
6. Collect Command: Retrieves deposits from the current region occupied by the city crew.
7. Shoot Command: Attempts to attack a region located one unit away from the city crew in the specified direction. 
Be cautious: attacking one's own region results in self-destruction, and attacking an opponent's city center can lead to victory or defeat!
Now that you're equipped with these commands, venture forth and conquer the game!`;

  return (
    <div className={styles.bg}>
      <div className="rpgui-content">
        <div
          className="rpgui-container framed"
          style={{
            width: "900px",
            height: "650px",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            marginLeft: "300px",
            marginTop: "40px",
          }}
        >
          <p
            style={{
              fontFamily: "mic",
              fontSize: "120px",
              marginBottom: "50px",
              marginTop: "40px", // Added marginTop for spacing
            }}
          >
            HOW TO PLAY
            <hr class="golden" style={{ marginTop: "45px" }}></hr>
          </p>
          <div
            className={styles.box}
            style={{
              backgroundColor: "#D3D3D3",
              width: "80%",
              height: "450px",
              overflow: "auto",
              padding: "20px",
              fontFamily: "sun",
              fontSize: "20px",
              textAlign: "center",
              borderRadius: "3px",
              whiteSpace: "pre-line",
              marginTop: "-35px",
            }}
          >
            {message}
          </div>
        </div>
        <Link href="/pages/menu">
          <div className={styles.imagContainer}></div>
        </Link>
      </div>
    </div>
  );
};

export default Howto;
