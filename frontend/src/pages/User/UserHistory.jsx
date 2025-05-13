import api from "@/api/api";
import { Modal } from "@/components/ui";
import convertTime from "@/utils/convertTime";
import React, { useEffect, useState } from "react";

const UserHistory = () => {
  const [orders, setOrders] = useState([]);
  const [orderDetails, setOrderDetails] = useState(null);
  const [selectedOrder, setSelectedOrder] = useState(null);

  useEffect(() => {
    const fetchOrder = async () => {
      try {
        const response = await api.get("/orders");
        setOrders(response.data);
      } catch (error) {
        console.error("Error fetching orders:", error);
      }
    };
    fetchOrder();
  }, []);

  const fetchOrderDetails = async (orderId) => {
    try {
      const response = await api.get(`/orders/${orderId}`);
      setOrderDetails(response.data);
      setSelectedOrder(response.data); // Update selected order with the fetched details
    } catch (error) {
      console.error("Error fetching order details:", error);
    }
  };

  const fetchDrinkDetails = async (drinkId) => {
    try {
      const response = await api.get(`/drinks/${drinkId}`);
      return response.data;
    } catch (error) {
      console.error("Error fetching drink details:", error);
    }
  };

  return (
    <div className="p-4 md:p-8 bg-transparent">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-2xl md:text-4xl font-bold mb-4">
          Lịch sử giao dịch
        </h1>
        <div className="shadow-md rounded-lg p-4">
          <table className="table-auto w-full border border-gray-300">
            <thead>
              <tr className="bg-base-300">
                <th className="px-4 py-2">Ngày</th>
                <th className="px-4 py-2">Số tiền</th>
                <th className="px-4 py-2">Tình trạng</th>
                <th className="px-4 py-2">Chi tiết</th>
              </tr>
            </thead>
            <tbody>
              {orders.map((order) => (
                <tr key={order.id}>
                  <td className="border px-4 py-2">
                    {convertTime(order.createdAt)}
                  </td>
                  <td className="border px-4 py-2">{order.total}</td>
                  <td className="border px-4 py-2">{order.status}</td>
                  <td className="border px-4 py-2">
                    <button
                      className="btn btn-sm btn-primary"
                      onClick={() => fetchOrderDetails(order.id)} // Fetch details for the clicked order
                    >
                      Xem
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        {selectedOrder && (
          <Modal
            isOpen={!!selectedOrder}
            onClose={() => setSelectedOrder(null)}
          >
            <div className="mt-4 bg-transparent rounded-lg p-4">
              <h2 className="text-xl font-bold mb-2">Chi tiết đơn hàng</h2>
              <p className="mb-2">Ngày: {orderDetails.createdAt}</p>
              <p className="mb-2">Tình trạng: {orderDetails.status}</p>
              <h3 className="text-lg font-semibold">Sản phẩm:</h3>
              {/* Uncomment and use when the product details are available */}
              <ul className="list-disc list-inside">
                {selectedOrder?.orderDetails.map((item, index) => (
                  <li key={index}>
                    {fetchDrinkDetails(item.drinkId).name} - Số lượng:{" "}
                    {item.quantity} - Giá: {item.price}
                  </li>
                ))}
              </ul>
              <button
                className="btn btn-sm bg-primary text-primary-content mt-4"
                onClick={() => setSelectedOrder(null)}
              >
                Đóng
              </button>
            </div>
          </Modal>
        )}
      </div>
    </div>
  );
};

export default UserHistory;
