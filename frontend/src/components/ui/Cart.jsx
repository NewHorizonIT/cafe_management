import React from "react";
import { Button } from "./index";

const Cart = ({ items, onRemove, onUpdateQuantity, onClose }) => {
  return (
    <div className="cart bg-base-100 p-4 shadow-lg rounded-lg">
      <h2>Shopping Cart</h2>
      <Button
        lable={"Close"}
        className="bg-transparent shadow-none border-0"
        eventHandler={() => {
          onClose();
        }}
      ></Button>
      {items.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <ul>
          {items.map((item) => (
            <li key={item.id} className="cart-item">
              <span>{item.name}</span>
              <span>{item.price}</span>
              <input
                type="number"
                value={item.quantity}
                onChange={(e) => onUpdateQuantity(item.id, e.target.value)}
              />
              <Button onClick={() => onRemove(item.id)}>Remove</Button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Cart;
