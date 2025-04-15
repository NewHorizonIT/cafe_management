import useAuthStore from "@/store/useAuthStore";
import { Navigate, Outlet } from "react-router-dom";

const PrivateRoute = ({ allowedRoles }) => {
  const role = useAuthStore((state) => state.data.role);

  if (!role) return <Navigate to="/login" replace />;

  if (!allowedRoles.includes(role)) return <Navigate to="/" replace />;

  return <Outlet />;
};

export default PrivateRoute;
