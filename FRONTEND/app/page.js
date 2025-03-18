"use client";
import React, { useEffect, useState } from "react";
import styles from "./home.module.css";
import createWebsocket from "./api/createWebsocket";
import axios from "axios";

const Home = () => {
  const [name, setName] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    createWebsocket.initializeWebSocket();
    const client = createWebsocket.getClient();
    console.log("WebSocket Client:", client);
  }, []);

  const handleModeSelect = async () => {
    try {
      const response = await axios.post("http://localhost:8083/player", {
        name : name,
        plan : "",
        money: 0,
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

  const handleInputChange = (event) => {
    setName(event.target.value);
  };

  const handleJoinGame = () => {
    if (name.trim() === "") {
      setErrorMessage("Please input your name.");
    } else {
      handleModeSelect();
      console.log(name);
      window.location.href = "/pages/menu";
    }
  };

  return (
    <div className="outer-container">
      <div className="inner-container"></div>
      <div className="content-wrapper">
        <h1 className={styles.desc}>UPBEAT</h1>
        <input
          type="text"
          placeholder="Please input your name..."
          className={styles.nameinput}
          value={name}
          onChange={handleInputChange}
        />
        {errorMessage && (
          <p
            style={{
              color: "red",
              fontFamily: "Loveded",
              textAlign: "center",
              fontSize: "25px",
              marginLeft: "72px",
              marginTop: "20px",
              marginBottom: "-15px",
            }}
          >
            {errorMessage}
          </p>
        )}
        <button className="button" onClick={handleJoinGame}>
          <span className="button-text">Join Game</span>
          <div className="fill-container"></div>
        </button>
      </div>
    </div>
  );
};

export default Home;
