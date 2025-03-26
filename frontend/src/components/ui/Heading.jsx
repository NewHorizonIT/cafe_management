import clsx from "clsx";
import React from "react";

const Heading = ({ title, className, des }) => {
  return (
    <div className={clsx("bg-linear-to-t w-full py-3 px-1", className)}>
      <h1 className="text-3xl">{title}</h1>
      {des ? <p className="text-sm text-base-content py-3">{des}</p> : <></>}
    </div>
  );
};

export default Heading;
