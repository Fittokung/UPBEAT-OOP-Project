import Link from "next/link";
import styles from "./menu.module.css";
import axios from "axios";

const Menu = () => {
  const handleModeSelect = async () => {
    try {
      const response = await axios.post("http://localhost:8083/startGame", {
        Strat: true,
      });
      console.log(response);
      console.log(response.data);
    } catch (error) {
      if (error.response) {
        console.error("Server responded with error:", error.response.data);
      } else if (error.request) {
        console.error("No response received:", error.request);
      } else {
        console.error("Error setting up the request:", error.message);
      }
    }
  };

  const handleStartGame = () => {
    handleModeSelect();
    // console.log(name);
    // window.location.href = "/pages/menu";
  };
  return (
    <div className={styles.menuContainer}>
      <div className="rpgui-content">
        <p
          style={{
            fontFamily: "mic",
            fontSize: "300px",
            textAlign: "center",
            marginTop: "170px",
          }}
        >
          UPBEAT
        </p>
        <div className={styles.buttonGroup}>
          <Link href="/pages/Game">
            <button
              className="rpgui-button"
              type="button"
              onClick={handleStartGame()}
            >
              <p style={{ fontFamily: "hello", marginTop: "5px" }}>START</p>
            </button>
          </Link>
          <Link href="/pages/howto">
            <button className="rpgui-button" type="button">
              <p style={{ fontFamily: "hello", marginTop: "5px" }}>
                HOW TO PLAY
              </p>
            </button>
          </Link>
          <Link href="/pages/Configuration">
            <button className="rpgui-button" type="button">
              <p style={{ fontFamily: "hello", marginTop: "5px" }}>
                SETTING CONFIG
              </p>
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Menu;
