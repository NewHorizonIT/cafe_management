import Header from "@/components/layout/dashboard/Header";
import Sidebar from "@/components/layout/dashboard/Siderbar";
import { Outlet } from "react-router-dom";

const DashboardLayout = () => {
  return (
    <div className="flex h-screen">
      <Sidebar />
      <div className="flex flex-col flex-1">
        <Header />
        <main className="p-4 overflow-auto bg-base-200">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default DashboardLayout;
