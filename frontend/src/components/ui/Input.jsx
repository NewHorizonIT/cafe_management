import clsx from "clsx";
import React from "react";

const Input = ({ size, icon, placeholder, type }) => {
  return (
    <label className="input">
      {icon ? icon : <></>}
      <input
        type={type}
        required
        placeholder={placeholder}
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
