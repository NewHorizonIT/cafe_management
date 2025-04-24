import React, { useState } from "react";

const Order = () => {
  const [orderDetails, setOrderDetails] = useState({
    product: "",
    quantity: 1,
    customerName: "",
    customerPhone: "",
    customerAddress: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setOrderDetails({ ...orderDetails, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Order submitted:", orderDetails);
    alert("Đơn hàng đã được gửi thành công!");
    setOrderDetails({
      product: "",
      quantity: 1,
      customerName: "",
      customerPhone: "",
      customerAddress: "",
    });
  };

  return (
    <div className="p-4 md:p-8 bg-base-100">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-2xl md:text-4xl font-bold mb-4">Đặt hàng</h1>
        <form
          onSubmit={handleSubmit}
          className="bg-white shadow-md rounded-lg p-4"
        >
          <div className="mb-4">
            <label
              className="block text-gray-700 font-bold mb-2"
              htmlFor="product"
            >
              Sản phẩm
            </label>
            <input
              type="text"
              id="product"
              name="product"
              value={orderDetails.product}
              onChange={handleInputChange}
              className="input input-bordered w-full"
              required
            />
          </div>
          <div className="mb-4">
            <label
              className="block text-gray-700 font-bold mb-2"
              htmlFor="quantity"
            >
              Số lượng
            </label>
            <input
              type="number"
              id="quantity"
              name="quantity"
              value={orderDetails.quantity}
              onChange={handleInputChange}
              className="input input-bordered w-full"
              min="1"
              required
            />
          </div>
          <div className="mb-4">
            <label
              className="block text-gray-700 font-bold mb-2"
              htmlFor="customerName"
            >
              Tên khách hàng
            </label>
            <input
              type="text"
              id="customerName"
              name="customerName"
              value={orderDetails.customerName}
              onChange={handleInputChange}
              className="input input-bordered w-full"
              required
            />
          </div>
          <div className="mb-4">
            <label
              className="block text-gray-700 font-bold mb-2"
              htmlFor="customerPhone"
            >
              Số điện thoại
            </label>
            <input
              type="text"
              id="customerPhone"
              name="customerPhone"
              value={orderDetails.customerPhone}
              onChange={handleInputChange}
              className="input input-bordered w-full"
              required
            />
          </div>
          <div className="mb-4">
            <label
              className="block text-gray-700 font-bold mb-2"
              htmlFor="customerAddress"
            >
              Địa chỉ
            </label>
            <textarea
              id="customerAddress"
              name="customerAddress"
              value={orderDetails.customerAddress}
              onChange={handleInputChange}
              className="textarea textarea-bordered w-full"
              required
            ></textarea>
          </div>
          <button type="submit" className="btn btn-primary w-full">
            Gửi đơn hàng
          </button>
        </form>
      </div>
    </div>
  );
};

export default Order;
