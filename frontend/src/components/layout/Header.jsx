import React, { useEffect, useState } from "react";
import Container from "./Container";
import Button from "@/components/ui/Button";
import { Menu } from "@/components/pages/Header";
import useAuthStore from "@/store/useAuthStore";
import { Avatar, Input, ToggleTheme } from "../ui";
import { CartIcon, SearchIcon } from "@/components/icons";
import { NavLink } from "react-router-dom";
import Cart from "../ui/Cart";
import { motion, AnimatePresence } from "framer-motion";
import useCategoryStore from "@/store/useCategoryStore";

const Header = () => {
  const { isLogin } = useAuthStore();
  const [isCartVisible, setIsCartVisible] = useState(false);
  const { categories, fetchCategories } = useCategoryStore();

  useEffect(() => {
    fetchCategories();
  }, []);

  const links = [
    {
      id: 1,
      name: "Trang chủ",
      path: "/",
    },
    {
      id: 2,
      name: "Giới thiệu",
      path: "/about",
    },
    {
      id: 3,
      name: "Danh mục",
      path: "/",
      sub: [...categories],
    },
  ];

  const toggleCartVisibility = () => {
    setIsCartVisible(!isCartVisible);
  };

  return (
    <Container className="bg-base-300 shadow-2xl border-b-2 z-50 fixed">
      <div className="navbar w-full h-[60px] bg-transparent justify-between">
        <div className="navbar-start w-min">
          <div className="dropdown">
            <div tabIndex={0} role="button" className="btn btn-ghost lg:hidden">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                className="h-5 w-5"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                {" "}
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M4 6h16M4 12h8m-8 6h16"
                />{" "}
              </svg>
            </div>
            <ul
              tabIndex={0}
              className="menu menu-sm dropdown-content bg-base-100 rounded-box z-1 mt-3 w-52 p-2 shadow"
            >
              <li>
                <a>Item 1</a>
              </li>
              <li>
                <a>Parent</a>
                <ul className="p-2">
                  <li>
                    <a>Submenu 1</a>
                  </li>
                  <li>
                    <a>Submenu 2</a>
                  </li>
                </ul>
              </li>
              <li>
                <a>Item 3</a>
              </li>
            </ul>
          </div>
          <p className="text-3xl font-playwrite">MyCoffe</p>
        </div>
        <div className="navbar-center hidden lg:flex px-10">
          <Menu links={links} />
        </div>
        <div className="navbar-end gap-2">
          {isLogin ? (
            <>
              <Input size="md" icon={<SearchIcon />} type="search" />
              <ToggleTheme />
              <Button
                lable={<CartIcon />}
                className="bg-transparent shadow-none border-0"
                eventHandler={toggleCartVisibility}
              />
              <AnimatePresence>
                {isCartVisible && (
                  <motion.div
                    initial={{ x: "100%" }}
                    animate={{ x: 0 }}
                    exit={{ x: "100%" }}
                    transition={{ type: "spring", stiffness: 300, damping: 30 }}
                    className="fixed top-0 right-0 w-1/3 h-full bg-base-100 shadow-lg z-50"
                  >
                    <Cart
                      handleClose={toggleCartVisibility}
                      isCartOpen={isCartVisible}
                    />
                  </motion.div>
                )}
              </AnimatePresence>
              <div className="dropdown dropdown-hover">
                <div
                  tabIndex={0}
                  role="button"
                  className="m-1 p-0 rounded-full"
                >
                  <Button
                    lable={<Avatar />}
                    className="shadow-none bg-transparent border-0"
                  />
                </div>
                <ul
                  tabIndex={0}
                  className="dropdown-content menu bg-base-100 rounded-box z-1 w-52 p-2 shadow-sm"
                >
                  <li>
                    <NavLink to="/user/profile">Thông tin cá nhân</NavLink>
                  </li>
                  <li>
                    <NavLink to="/user/history">Lịch sử mua hàng</NavLink>
                  </li>
                </ul>
              </div>
            </>
          ) : (
            <>
              <Input size="md" icon={<SearchIcon />} type="search" />
              <ToggleTheme />
              <Button
                lable={<NavLink to="/auth/login">Đăng kí</NavLink>}
                className="px-3 py-2 bg-primary text-primary-content min-w-24"
              />
              <Button
                lable={<NavLink to="/auth/register">Đăng nhập</NavLink>}
                className="px-3 py-2 bg-primary text-primary-content min-w-24"
              />
            </>
          )}
        </div>
      </div>
    </Container>
  );
};

export default Header;
