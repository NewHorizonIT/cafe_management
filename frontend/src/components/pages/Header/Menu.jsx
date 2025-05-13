// import clsx from "clsx";
import React from "react";
import { NavLink } from "react-router-dom";
import { NavItem } from ".";

const Menu = ({ links }) => {
  console.log("MENU", links);
  return (
    <ul className="menu menu-horizontal px-1">
      {links.map((link) => (
        <li key={link.id}>
          {link.sub ? (
            <details>
              <summary className="text-lg">{link.name}</summary>
              <ul className="p-2 min-w-24 bg-base-100">
                <Menu links={link.sub} />
              </ul>
            </details>
          ) : (
            <NavItem lable={link.name} path={link.path || `/${link.name}`} />
          )}
        </li>
      ))}
    </ul>
  );
};

export default Menu;
