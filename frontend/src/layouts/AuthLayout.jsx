import React from "react";
import { Outlet } from "react-router-dom";

const AuthLayout = () => {
  return (
    <div className="w-screen h-screen p-3 bg-base-100 flex justify-center items-center">
      <Outlet />
    </div>
  );
};

export default AuthLayout;
