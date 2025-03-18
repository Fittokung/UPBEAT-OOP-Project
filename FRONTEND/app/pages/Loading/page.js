import React from "react";
import styles from "./loading.module.css";

const Loading = () => {
  return (
    <div className={styles.menuContainer}>
      <div className="rpgui-content">
        <p
          className={styles.movingText}
          style={{
            textAlign: "center",
            fontSize: "32px",
            marginTop: "350px",
            fontFamily: "hello",
          }}
        >
          WAITING FOR ANOTHER PLAYER...
        </p>
      </div>
    </div>
  );
};

export default Loading;
