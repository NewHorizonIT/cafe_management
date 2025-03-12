import clsx from "clsx";
import React from "react";

const Input = ({ size, icon }) => {
  return (
    <label className="input">
      {icon ? icon : <></>}
      <input
        type="search"
        required
        placeholder="Search"
        className={clsx("input focus:outline-none", {
          "input-xs": size === "xs",
          "input-md": size === "md",
          "input-lg": size === "lg",
          "input-xl": size === "xl",
        })}
      />
    </label>
  );
};

export default Input;
