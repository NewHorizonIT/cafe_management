import { AuthLayout, DefaultLayout, UserLayout } from "@/layouts";
import DashboardLayout from "@/layouts/DashboardLayout";
import { CashierDashboard } from "@/pages";
import { BillHistory, CashierStats, OrderApprove } from "@/pages/Cashier";
import ErrorPage from "@/pages/ErrorPage";
import { WareHouseDashboard } from "@/pages/Warehouse";
import ImportForm from "@/pages/Warehouse/ImportForm";
import ImportStats from "@/pages/Warehouse/ImportStats";
import MaterialCRUD from "@/pages/Warehouse/ProductCRUD";
import SupplierCRUD from "@/pages/Warehouse/SupplierCRUD";
import { lazy } from "react";
import { createBrowserRouter } from "react-router-dom";
import {
  UserManagement,
  EnterpriseManager,
  AdminDashboard,
} from "../pages/Admin";
import PrivateRoute from "./privateRouter";
import UserProfile from "@/pages/User/UserProfile";
import UserHistory from "@/pages/User/UserHistory";
import DrinksByCategory from "@/pages/DrinksByCategory";
import ProductDetail from "@/pages/ProductDetail";
import IngredientCRUD from "@/pages/Warehouse/IngredientCRUD";
import ProductCRUD from "@/pages/Warehouse/ProductCRUD";

const Home = lazy(() => import("@/pages/Home"));
const About = lazy(() => import("@/pages/About"));
const LoginPage = lazy(() => import("@/pages/Login"));
const SigninPage = lazy(() => import("@/pages/Signin"));

const router = createBrowserRouter([
  {
    path: "/",
    element: <DefaultLayout />,
    children: [
      {
        index: true,
        element: <Home />,
      },
      {
        path: "about",
        element: <About />,
      },
      {
        path: ":category",
        element: <DrinksByCategory />,
      },
      {
        path: "products/:id",
        element: <ProductDetail />,
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
    path: "/cashier",
    element: <DashboardLayout />,
    children: [
      {
        index: true,
        element: <CashierDashboard />,
      },
      {
        path: "orders",
        element: <OrderApprove />,
      },
      {
        path: "bills",
        element: <BillHistory />,
      },
      {
        path: "stats",
        element: <CashierStats />,
      },
    ],
  },
  {
    path: "/warehouse",
    element: <DashboardLayout />,
    children: [
      {
        index: true,
        element: <WareHouseDashboard />,
      },
      {
        path: "materials",
        element: <IngredientCRUD />,
      },
      {
        path: "stats",
        element: <ImportStats />,
      },
      {
        path: "suppliers",
        element: <SupplierCRUD />,
      },
      {
        path: "purchases",
        element: <ImportForm />,
      },
      {
        path: "products",
        element: <ProductCRUD />,
      },
    ],
  },
  {
    path: "/admin",
    element: <DashboardLayout />,
    children: [
      {
        index: true,
        element: <AdminDashboard />,
      },
      {
        path: "users",
        element: <UserManagement />,
      },
      {
        path: "enterprise",
        element: <EnterpriseManager />,
      },
      {
        path: "bills",
        element: <BillHistory />,
      },
    ],
  },
  {
    path: "user",
    element: <DefaultLayout />,
    children: [
      { path: "profile", element: <UserProfile /> },
      { path: "history", element: <UserHistory /> },
    ],
  },
  { path: "*", element: <ErrorPage /> },
]);

export default router;
