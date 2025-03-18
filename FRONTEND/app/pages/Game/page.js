"use client";
import Link from "next/link";
import React, { useState } from "react";
import HexGrid from "../components/HexGrid";
import CountTimer from "../components/CountTimer";
import styles from "./game.module.css";
import AceEditor from "react-ace";
import "ace-builds/src-noconflict/mode-java";
import "ace-builds/src-noconflict/theme-dracula";
import "ace-builds/src-noconflict/ext-language_tools";
import { MapInteractionCSS } from "react-map-interaction";
import axios from "axios";

const App = () => {
  const [planText, setPlanText] = useState("");
  const [errorMgs, setErrorMgs] = useState(null);
  const [row ,setRow]= useState(0);
  const [col ,setCol]= useState(0);


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
        <div className={styles.counttimerwrapper}>
          <CountTimer
            countdownTimestampMs={Date.now() + 1 * 60 * 1000}
            minutes={1}
            seconds={0}
          />
        </div>
        <div
          className="rpgui-container framed"
          style={{
            width: "850px",
            height: "650px",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            marginTop: "10px",
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
            marginTop: "-15px",
            marginLeft: "950px",
            position: "relative",
          }}
        >
          <p
            style={{
              fontFamily: "hello",
              fontSize: "28px",
              textAlign: "center",
              marginTop: "10px",
              marginBottom: "10px",
            }}
          >
            CONSTRUCTION PLAN
          </p>
          <AceEditor
            className="my-editor"
            mode="java"
            theme="dracula"
            name="plan-editor"
            editorProps={{ $blockScrolling: true }}
            fontSize="15px"
            setOptions={{
              fontFamily: "Monaco",
              enableBasicAutocompletion: true,
              enableLiveAutocompletion: true,
              enableSnippets: true,
              enableMultiselect: true,
            }}
            onChange={(e) => {
              setPlanText(e);
              console.log(e);
              setErrorMgs("");
            }}
          />
          <div style={{ display: "flex", alignItems: "center" }}>
            <Link href="/pages/Mapgame">
              <button
                className="rpgui-button"
                type="button"
                style={{ marginRight: "10px" }}
                onClick={handlePlanChange()}
              >
                <p style={{ fontFamily: "hello" }}>CONFIRM</p>
              </button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default App;
