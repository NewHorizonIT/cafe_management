import React, { useState } from "react";
import { Button, Modal } from "./index";

const Cart = ({ items, onRemove, onUpdateQuantity, onClose }) => {
  const [isOrderModalOpen, setIsOrderModalOpen] = useState(false);
  const [deliveryAddress, setDeliveryAddress] = useState("");

  const handlePlaceOrder = () => {
    setIsOrderModalOpen(true);
  };

  const handleConfirmOrder = () => {
    console.log("Order confirmed:", { items, deliveryAddress });
    alert("Đơn hàng đã được đặt thành công!");
    setIsOrderModalOpen(false);
    setDeliveryAddress("");
  };

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
      {items.length > 0 && (
        <Button
          lable="Place Order"
          className="btn btn-primary mt-4"
          eventHandler={handlePlaceOrder}
        />
      )}

      <Modal
        isOpen={isOrderModalOpen}
        title="Confirm Your Order"
        onClose={() => setIsOrderModalOpen(false)}
      >
        <h3 className="text-lg font-bold">Order Summary</h3>
        <ul className="list-disc list-inside">
          {items.map((item) => (
            <li key={item.id}>
              {item.name} - Quantity: {item.quantity} - Price: {item.price}
            </li>
          ))}
        </ul>
        <div className="mt-4">
          <label
            className="block text-gray-700 font-bold mb-2"
            htmlFor="address"
          >
            Delivery Address
          </label>
          <textarea
            id="address"
            value={deliveryAddress}
            onChange={(e) => setDeliveryAddress(e.target.value)}
            className="textarea textarea-bordered w-full"
            required
          ></textarea>
        </div>
        <Button
          lable="Confirm Order"
          className="btn btn-success mt-4"
          eventHandler={handleConfirmOrder}
        />
      </Modal>
    </div>
  );
};

export default Cart;
