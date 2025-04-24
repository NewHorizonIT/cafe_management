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
  const [paymentMethod, setPaymentMethod] = useState("cod");

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
    handleClose();
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
        title="Xác nhận đơn hàng"
        onClose={() => setIsOrderModalOpen(false)}
      >
        <h3 className="text-lg font-bold">Tóm tắt đơn hàng</h3>
        <ul className="list-disc list-inside">
          {cart.map((item) => (
            <li key={item.id}>
              {item.name} - SL: {item.quantity || 1} - Giá: {item.price}
            </li>
          ))}
        </ul>
        <div className="mt-4 font-bold">
          Tổng tiền:{" "}
          {cart.reduce(
            (total, item) =>
              total +
              parseInt(item.price.replace(/[^0-9]/g, ""), 10) *
                (item.quantity || 1),
            0
          )}{" "}
          VND
        </div>
        <div className="mt-4">
          <label
            className="block text-gray-700 font-bold mb-2"
            htmlFor="address"
          >
            Địa chỉ giao hàng
          </label>
          <textarea
            id="address"
            value={deliveryAddress}
            onChange={(e) => setDeliveryAddress(e.target.value)}
            className="textarea textarea-bordered w-full"
            required
          ></textarea>
        </div>
        <div className="mt-4">
          <label
            htmlFor="paymentMethod"
            className="block text-gray-700 font-bold mb-2"
          >
            Phương thức thanh toán
          </label>
          <select
            id="paymentMethod"
            value={paymentMethod}
            onChange={(e) => setPaymentMethod(e.target.value)}
            className="select select-bordered w-full"
            required
          >
            <option value="cod">Thanh toán khi nhận hàng (COD)</option>
            <option value="bank">Chuyển khoản ngân hàng</option>
            <option value="momo">Ví MoMo</option>
          </select>
        </div>
        <Button
          lable="Xác nhận"
          className="btn bg-primary text-primary-content mt-4"
          eventHandler={handleConfirmOrder}
        />
      </Modal>
    </div>
  );
};

export default Cart;
