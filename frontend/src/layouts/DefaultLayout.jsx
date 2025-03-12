import { Header } from "@/components/layout";
import React from "react";
import { Outlet } from "react-router-dom";

const DefaultLayout = () => {
  return (
    <div className="bg-base-100 min-h-screen h-max">
      <Header />
      <Outlet />
    </div>
  );
};

export default DefaultLayout;
