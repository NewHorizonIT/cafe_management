import clsx from "clsx";
import React from "react";

const Button = ({ lable, eventHandler, className }) => {
  return (
    <button className={clsx("btn", className)} onClick={eventHandler}>
      {lable}
    </button>
  );
};

export default Button;
