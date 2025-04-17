import { AuthLayout, DefaultLayout } from "@/layouts";
import DashboardLayout from "@/layouts/DashboardLayout";
import { CashierDashboard } from "@/pages";
import ErrorPage from "@/pages/ErrorPage";
import { lazy } from "react";

const Home = lazy(() => import("@/pages/Home"));
const About = lazy(() => import("@/pages/About"));
const LoginPage = lazy(() => import("@/pages/Login"));
const SigninPage = lazy(() => import("@/pages/Signin"));

const publicRouter = [
  {
    path: "/",
    element: <DefaultLayout />,
    children: [
      {
        path: "/",
        element: <Home />,
      },
      {
        path: "about",
        element: <About />,
      },
    ],
  },
  {
    path: "/auth",
    element: <AuthLayout />,
    children: [
      {
        path: "register",
        element: <SigninPage />,
      },
      {
        path: "login",
        element: <LoginPage />,
      },
    ],
  },
  {
    path: "/cashier/",
    element: <DashboardLayout />,
    children: [
      {
        path: "/",
        children: <CashierDashboard />,
      },
    ],
  },
  { path: "*", element: <ErrorPage /> },
];

export default publicRouter;
