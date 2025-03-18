import React from "react";
import style from "./hexGridStyle.module.css";

const HexGrid = ({ column, row }) => {
  const columnArray = Array(column).fill(0) || [];
  const gridArray = Array(row).fill(columnArray) || [];

  return (
    <div className="scale-75 ml-32">
      {gridArray.map((data, key) => {
        return (
          <div className="flex" key={key}>
            {data.map((data2, k) => {
              return (
                <div
                  key={k}
                  style={{
                    transform: `translate(-${(35 * k) / 3}px, ${
                      k % 2 === 0 ? "21px" : "0px"
                    })`,
                    marginTop: k % 2 === 0 ? "2px" : "0px",
                  }}
                  className={style["hex-grid-content"]}
                ></div>
              );
            })}
          </div>
        );
      })}
    </div>
  );
};

export default HexGrid;
