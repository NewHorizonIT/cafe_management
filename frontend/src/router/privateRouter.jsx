import { createBrowserRouter, Navigate, Outlet } from "react-router-dom";

const PrivateRoute = ({ user, allowedRoles }) => {
  if (!user) return <Navigate to="/login" replace />;

  if (!allowedRoles.includes(user.role)) return <Navigate to="/" replace />;

  return <Outlet />;
};

const privateRouter = createBrowserRouter([]);

export default privateRouter;
