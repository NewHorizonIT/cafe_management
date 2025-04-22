import React, { useState } from "react";
import { Button, Modal } from "./index";
import useCartStore from "@/store/useCartStore";
import { CloseIcon } from "../icons";

const Cart = ({ handleClose }) => {
  // const { cart, removeFromCart, updateQuantity } = useCartStore((state) => ({
  //   cart: state.cart,
  //   removeFromCart: state.removeFromCart,
  //   updateQuantity: state.updateQuantity,
  // }));

  const cart = useCartStore((state) => state.cart);
  const removeFromCart = useCartStore((state) => state.removeFromCart);
  const updateQuantity = useCartStore((state) => state.updateQuantity);
  const cleanCart = useCartStore((state) => state.clearCart);

  const [isOrderModalOpen, setIsOrderModalOpen] = useState(false);
  const [deliveryAddress, setDeliveryAddress] = useState("");

  const handlePlaceOrder = () => {
    setIsOrderModalOpen(true);
  };

  const handleConfirmOrder = () => {
    console.log("Order confirmed:", { cart, deliveryAddress });
    alert("Đơn hàng đã được đặt thành công!");
    setIsOrderModalOpen(false);
    setDeliveryAddress("");
    cleanCart();
  };

  return (
    <div className="cart bg-base-100 p-4 shadow-lg rounded-lg relative">
      <h2 className="text-2xl mb-5">Giỏ hàng</h2>
      <Button
        lable={<CloseIcon />}
        className="bg-transparent shadow-none border-0 absolute top-2 right-2"
        eventHandler={handleClose}
      ></Button>
      {cart.length === 0 ? (
        <p className="text-center">Giỏ hàng trống</p>
      ) : (
        <table className="table-auto w-full">
          <thead>
            <tr>
              <th className="px-4 py-2">Tên</th>
              <th className="px-4 py-2">Giá</th>
              <th className="px-4 py-2">Số lượng</th>
              <th className="px-4 py-2">Hành động</th>
            </tr>
          </thead>
          <tbody>
            {cart.map((item) => (
              <tr key={item.id} className="cart-item">
                <td className="border px-4 py-2">{item.name}</td>
                <td className="border px-4 py-2">{item.price}</td>
                <td className="border px-4 py-2">
                  <input
                    type="number"
                    min="1"
                    value={item.quantity ?? 1}
                    onChange={(e) => {
                      const newQty = Number(e.target.value);
                      if (item.quantity !== newQty) {
                        updateQuantity(item.id, newQty);
                      }
                    }}
                    className="w-16 text-center border rounded"
                  />
                </td>
                <td className="border px-4 py-2">
                  <Button eventHandler={() => removeFromCart(item.id)}>
                    Xóa
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      {cart.length > 0 && (
        <Button
          lable="Đặt hàng"
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
          {cart.map((item) => (
            <li key={item.id}>
              {item.name} - Quantity: {item.quantity || 1} - Price: {item.price}
            </li>
          ))}
        </ul>
        <div className="mt-4 font-bold">
          Total Price:{" "}
          {cart.reduce(
            (total, item) => total + item.price * (item.quantity || 1),
            0
          )}
        </div>
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
