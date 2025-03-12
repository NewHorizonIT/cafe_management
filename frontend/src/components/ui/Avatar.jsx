import clsx from "clsx";
import React from "react";

const Avatar = ({ avatar, size = 6 }) => {
  const link = avatar
    ? avatar
    : "https://th.bing.com/th/id/OIP.4Q7-yMnrlnqwR4ORH7c06AHaHa?w=163&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7";
  return (
    <div className="avatar">
      <div
        className={clsx(
          "ring-primary ring-offset-base-100 rounded-full ring ring-offset-2",
          `w-${size}`
        )}
      >
        <img src={link} />
      </div>
    </div>
  );
};

export default Avatar;
