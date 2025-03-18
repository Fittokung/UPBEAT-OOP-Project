"use client";
import React, { useState } from "react";
import styles from "./Configuration.module.css";
import Link from "next/link";
import axios from "axios";


function Configuration() {
  const [rowValue, setRowValue] = useState(9);
  const [columnValue, setColumnValue] = useState(9);
  const [writeMin, setWriteMin] = useState("59");
  const [writeSec, setWriteSec] = useState("00");
  const [changeMin, setChangeMin] = useState("59");
  const [changeSec, setChangeSec] = useState("00");
  const [initialBudget, setInitialBudget] = useState(10000);
  const [depositCenter, setDepositCenter] = useState(100);
  const [changingPlanCost, setChangingPlanCost] = useState(100);
  const [maxDeposit, setMaxDeposit] = useState(100000);
  const [interestPercent, setInterestPercent] = useState(5);

  const handleInputChange = (setStateFunc) => (event) => {
    const newValue = event.target.value;
    if (!isNaN(newValue)) {
      setStateFunc(newValue);
    }
  };

  const handleModeSelect = async () => {
    try {
      const response = await axios.post("http://localhost:8083/configfile", {
        row : rowValue,
        col : columnValue,
        init_budget : initialBudget,
        init_center_dep : depositCenter,
        rev_cost : changingPlanCost,
        max_dep : maxDeposit,
        interest_pct : interestPercent,
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
    window.location.href = "/pages/menu";
  };

  const handleRowChange = (event) => {
    const newValue = parseInt(event.target.value);
    if (newValue >= 9 && newValue <= 40) {
      setRowValue(newValue);
    }
  };

  const handleColumnChange = (event) => {
    const newValue = parseInt(event.target.value);
    setColumnValue(newValue);
  };

  const handleWriteTimeChange = (type, value) => {
    if (type === "min") {
      setWriteMin(parseInt(value));
    } else if (type === "sec") {
      const sec = parseInt(value);
      if (sec % 5 === 0 && sec >= 0 && sec <= 59) {
        setWriteSec(sec);
      }
    }
  };

  const handleChangeTimeChange = (type, value) => {
    if (type === "min") {
      setChangeMin(parseInt(value));
    } else if (type === "sec") {
      const sec = parseInt(value);
      if (sec % 5 === 0 && sec >= 0 && sec <= 59) {
        setChangeSec(sec);
      }
    }
  };

  return (
    <div className={styles.configurationPage}>
      <div className="rpgui-content">
        <p
          style={{
            fontFamily: "mic",
            textAlign: "center",
            fontSize: "200px",
            marginTop: "90px",
          }}
        >
          CONFIGURATION
        </p>
        <div
          className="rpgui-container framed"
          style={{
            width: "750px",
            height: "440px",
            marginLeft: "25%",
            marginTop: "80px",
          }}
        >
          <div className={styles.settings}>
            <div className={styles.setting}>
              <label htmlFor="row">Row</label>
              <input
                type="range"
                min={9}
                max={40}
                value={rowValue}
                onChange={handleRowChange}
                id="row"
              />
              <span>{rowValue}</span>
            </div>
            <div className={styles.setting}>
              <label htmlFor="column">Column</label>
              <input
                type="range"
                min={9}
                max={40}
                value={columnValue}
                onChange={handleColumnChange}
                id="column"
              />
              <span>{columnValue}</span>
            </div>
            <div className={styles.setting}>
              <label htmlFor="initialBudget">Initial Budget</label>
              <input
                type="text"
                min={5000}
                max={15000}
                value={initialBudget}
                onChange={handleInputChange(setInitialBudget)}
                id="initialBudget"
              />
            </div>
            <div className={styles.setting}>
              <label>time</label>
              <div className={styles.timePicker}>
                <input
                  type="number"
                  min={0}
                  max={59}
                  value={writeMin}
                  onChange={(event) =>
                    handleWriteTimeChange("min", event.target.value)
                  }
                />
                <h2>:</h2>
                <input
                  type="number"
                  min={0}
                  max={59}
                  step={5}
                  value={writeSec}
                  onChange={(event) =>
                    handleWriteTimeChange("sec", event.target.value)
                  }
                />
              </div>
            </div>
            <div className={styles.setting}>
              <label htmlFor="depositCenter">Deposit Center</label>
              <input
                type="text"
                min={50}
                max={200}
                value={depositCenter}
                onChange={handleInputChange(setDepositCenter)}
                id="depositCenter"
              />
            </div>
            <div className={styles.setting}>
              <label>Revision time</label>
              <div className={styles.timePicker}>
                <input
                  type="number"
                  min={0}
                  max={59}
                  value={changeMin}
                  onChange={(event) =>
                    handleChangeTimeChange("min", event.target.value)
                  }
                />
                <h2>:</h2>
                <input
                  type="number"
                  min={0}
                  max={59}
                  step={5}
                  value={changeSec}
                  onChange={(event) =>
                    handleChangeTimeChange("sec", event.target.value)
                  }
                />
              </div>
            </div>
            <div className={styles.setting}>
              <label htmlFor="changingPlanCost">Changing Cost</label>
              <input
                type="text"
                min={50}
                max={200}
                value={changingPlanCost}
                onChange={handleInputChange(setChangingPlanCost)}
                id="changingPlanCost"
              />
            </div>
            <div className={styles.setting}>
              <label htmlFor="maxDeposit">Max Deposit</label>
              <input
                type="text"
                min={400000}
                max={1600000}
                step={100000}
                value={maxDeposit}
                onChange={handleInputChange(setMaxDeposit)}
                id="maxDeposit"
              />
            </div>
            <div className={styles.setting}>
              <label htmlFor="interestPercent">Interest Percent</label>
              <input
                type="text"
                min={1}
                max={10}
                value={interestPercent}
                onChange={handleInputChange(setInterestPercent)}
                id="interestPercent"
              />
            </div>
          </div>
        </div>
        <button
          class="rpgui-button golden"
          type="button"
          style={{ marginLeft: "680px", marginTop: "535px" }}
          onClick={handleStartGame}
        >
          <p style={{ fontFamily: "hello", marginTop: "15px" }}>CONFIRM</p>
        </button>
        <Link href="/pages/menu">
          <div className={styles.imagContainer}></div>
        </Link>
      </div>
    </div>
  );
}

export default Configuration;
