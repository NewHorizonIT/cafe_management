import React, { useState } from "react";
import Container from "./Container";
import Button from "@/components/ui/Button";
import { Menu } from "@/components/pages/Header";
import links from "@/constants/paths";
import useAuthStore from "@/store/useAuthStore";
import { Avatar, Input, ToggleTheme } from "../ui";
import { CartIcon, SearchIcon } from "@/components/icons";
import { NavLink } from "react-router-dom";
import Cart from "../ui/Cart";

const Header = () => {
  const { isLogin } = useAuthStore();

  const [cartItems, setCartItems] = useState([
    {
      id: "CART001",
      name: "Cà phê sữa đá",
      price: 30000,
      quantity: 2,
    },
    {
      id: "CART002",
      name: "Trà đào cam sả",
      price: 45000,
      quantity: 1,
    },
    {
      id: "CART003",
      name: "Bánh mì phô mai",
      price: 25000,
      quantity: 3,
    },
  ]);
  const [isCartVisible, setIsCartVisible] = useState(false);

  const handleRemove = (id) => {
    setCartItems(cartItems.filter((item) => item.id !== id));
  };

  const handleUpdateQuantity = (id, quantity) => {
    setCartItems(
      cartItems.map((item) =>
        item.id === id ? { ...item, quantity: parseInt(quantity, 10) } : item
      )
    );
  };

  const toggleCartVisibility = () => {
    setIsCartVisible(!isCartVisible);
  };

  return (
    <Container className="bg-base-300 shadow-2xl border-b-2">
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
              {isCartVisible && (
                <div className="fixed top-0 right-0 w-1/3 h-full bg-base-100 shadow-lg z-50">
                  <Cart
                    items={cartItems}
                    onRemove={handleRemove}
                    onUpdateQuantity={handleUpdateQuantity}
                    onClose={toggleCartVisibility}
                  />
                </div>
              )}
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
                    <NavLink to="/user/profile">Profile</NavLink>
                  </li>
                  <li>
                    <NavLink to="/user/history">Lich su mua hang</NavLink>
                  </li>
                </ul>
              </div>
            </>
          ) : (
            <>
              <ToggleTheme />
              <Button
                lable={<NavLink to="/auth/login">Dang nhap</NavLink>}
                className="px-3 py-2 bg-primary text-primary-content min-w-24"
              />
              <Button
                lable={<NavLink to="/auth/register">Dang ki</NavLink>}
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
