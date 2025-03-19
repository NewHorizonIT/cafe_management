import React from "react";
import { NavLink } from "react-router-dom";

const NavItem = ({ lable, path }) => {
  return (
    <NavLink to={path} className="text-lg ">
      {lable}
    </NavLink>
  );
};

export default NavItem;
