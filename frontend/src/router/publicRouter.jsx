import { AuthLayout, DefaultLayout } from "@/layouts";
import ErrorPage from "@/pages/ErrorPage";
import { lazy } from "react";
import { createBrowserRouter } from "react-router-dom";

const Home = lazy(() => import("@/pages/Home"));
const About = lazy(() => import("@/pages/About"));
const LoginPage = lazy(() => import("@/pages/Login"));
const SigninPage = lazy(() => import("@/pages/Signin"));

const publicRouter = createBrowserRouter([
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
  { path: "*", element: <ErrorPage /> },
]);

export default publicRouter;
