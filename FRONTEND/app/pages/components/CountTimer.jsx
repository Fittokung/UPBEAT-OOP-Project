// CountTimer.jsx
import React, { useState, useEffect } from "react";
import "./CountdownTimer.module.css"; // This line seems incorrect, it should be "./CountTimer.module.css" if the file name is correct
import { getRemainingTimeUnitMsTimestamp } from "./CountdownTimerUtils";

const CountTimer = ({ countdownTimestampMs, minutes, seconds }) => {
  const [remainingTime, setRemainingTime] = useState({
    minutes: minutes < 10 ? `0${minutes}` : minutes.toString(),
    seconds: seconds < 10 ? `0${seconds}` : seconds.toString(),
  });

  useEffect(() => {
    const intervalId = setInterval(() => {
      updateRemainingTime(countdownTimestampMs);
    }, 1000);

    return () => clearInterval(intervalId);
  }, [countdownTimestampMs]);

  function updateRemainingTime(countdown) {
    setRemainingTime(getRemainingTimeUnitMsTimestamp(countdown));
  }

  const totalSeconds =
    Number(remainingTime.seconds) + Number(remainingTime.minutes) * 60;
  const totalDurationSeconds = Number(seconds) + Number(minutes) * 60;
  const percentageRemaining = (totalSeconds / totalDurationSeconds) * 100;

  let textColor = "#fff";
  if (percentageRemaining > 60) {
    textColor = "#32CD32";
  } else if (percentageRemaining > 20) {
    textColor = "yellow";
  } else {
    textColor = "red";
  }

  return (
    <div className="countdown-timer">
      <span
        style={{
          paddingRight: "10px",
          color: "white",
          fontSize: "25px",
          fontFamily: "hello",
        }}
      >
        TIME REMAINING :
      </span>
      <span style={{ color: textColor, fontSize: "25px", fontFamily: "hello" }}>
        {remainingTime.minutes}
      </span>
      <span style={{ color: textColor, fontSize: "25px", fontFamily: "hello" }}>
        :
      </span>
      <span style={{ color: textColor, fontSize: "25px", fontFamily: "hello" }}>
        {remainingTime.seconds}
      </span>
    </div>
  );
};

export default CountTimer;
