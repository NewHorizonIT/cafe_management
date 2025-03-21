import React from "react";
import { NavLink } from "react-router-dom";

const ErrorPage = () => {
  return (
    <div className="w-screen h-screen bg-base-100 flex justify-center items-center">
      <NavLink
        className="bg-primary text-primary-content rounded px-3 py-2"
        to="/"
      >
        Go To Home Page
      </NavLink>
    </div>
  );
};

export default ErrorPage;
