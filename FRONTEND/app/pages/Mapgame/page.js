"use client";
import Link from "next/link";
import React, { useState } from "react";
import styles from "./mapgame.module.css";
import HexGrid from "../components/HexGrid";
import { MapInteractionCSS } from "react-map-interaction";
import axios from "axios";

const Mapgame = () => {
  const [row, setRow] = useState(0);
  const [col, setCol] = useState(0);
  const [dep, setDep] = useState(0);
  const [bud, setBud] = useState(0);

  const getRowandCol = () => {
    axios
      .get("http://localhost:8083/config")
      .then((response) => {
        // console.log(response);
        // console.log(response);
        const data = response.data[0]; // Assuming response.data is an array with a single object
        const rowValue = data.row; // Accessing the 'row' property
        const colValue = data.col;
        setRow(rowValue);
        setCol(colValue);
        const Deposit = data.max_dep;
        const Budget = data.init_budget;
        setDep(Deposit);
        setBud(Budget);
        // console.log("Row value:", rowValue);
        // console.log("Col value:", colValue);
      })
      .catch((error) => {
        console.error("Error retrieving player names:", error);
      });
  };

  const handleModeSelect = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8083/sendContruction",
        {
          cstplan: planText,
        }
      );
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

  const handlePlanChange = (event) => {
    handleModeSelect();
  };

  return (
    <div className={styles.bg}>
      <div className="rpgui-content">
        <div
          className="rpgui-container framed"
          style={{
            width: "850px",
            height: "650px",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            marginTop: "40px",
            marginLeft: "50px",
          }}
        >
          {getRowandCol()};
          <MapInteractionCSS
            showControls
            defaultValue={{
              scale: 0.5,
              translation: { x: 20, y: -50 },
            }}
            minScale={0.5}
            maxScale={3}
            translationBounds={{
              xMax: 400,
              yMax: 200,
            }}
          >
            <HexGrid row={row} column={col} />
          </MapInteractionCSS>
        </div>
        <div
          className="rpgui-container framed-golden-2"
          style={{
            width: "550px",
            height: "675px",
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-between", // Align items with space between
            alignItems: "center",
            marginTop: "25px",
            marginLeft: "950px",
            position: "relative",
          }}
        >
          <div
            className="rpgui-container"
            style={{
              marginTop: "20px", // Adjust margin top for spacing
            }}
          >
            <span
              style={{
                fontFamily: "hello",
                fontSize: "32px",
                textAlign: "center",
                marginLeft: "15px",
              }}
            >
              OPPONENT'TURN
            </span>
            <hr className="golden" style={{ marginTop: "10px" }}></hr>
            <div className={styles.box}>
              {" "}
              <p
                style={{
                  fontSize: "20px",
                  fontFamily: "hello",
                  textAlign: "center",
                }}
              >
                <p
                  style={{
                    textAlign: "center",
                    marginBottom: "-20px",
                    fontSize: "24px",
                    fontFamily: "hello",
                    marginTop: "12px",
                  }}
                >
                  PLAYER 1
                </p>
                <br />
                <br />
                DEPOSIT:{dep}
                <br />
                <br />
                BUDGET:{bud}
                <br />
                <br />
                COLUMN:{col} ROW:{row}
              </p>
              <div className={styles.boxs}>
                {" "}
                <p
                  style={{
                    fontSize: "20px",
                    fontFamily: "hello",
                    textAlign: "center",
                  }}
                >
                  <p
                    style={{
                      textAlign: "center",
                      marginBottom: "-20px",
                      fontSize: "24px",
                      fontFamily: "hello",
                      marginTop: "12px",
                    }}
                  >
                    PLAYER 2
                  </p>
                  <br />
                  <br />
                  DEPOSIT:-
                  <br />
                  <br />
                  BUDGET:-
                  <br />
                  <br />
                  COLUMN:20 ROW:20
                </p>
              </div>
            </div>
          </div>

          <Link href="/pages/Game">
            <button
              class="rpgui-button golden"
              type="button"
              style={{
                marginTop: "550px",
                marginInlineStart: "10px",
              }}
            >
              <p style={{ fontFamily: "hello", marginTop: "12px" }}>
                CONSTRUCTION
              </p>
            </button>
            <button
              className="rpgui-button golden"
              type="button"
              style={{
                marginInlineStart: "40px",
              }}
            >
              <p style={{ fontFamily: "hello", marginTop: "15px" }}>CONFIRM</p>
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Mapgame;
