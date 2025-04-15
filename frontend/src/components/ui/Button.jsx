import clsx from "clsx";
import React from "react";

const Button = ({ lable, eventHandler, className, children }) => {
  return (
    <button className={clsx("btn", className)} onClick={eventHandler}>
      {lable ? lable : children}
    </button>
  );
};

export default Button;
